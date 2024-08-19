package in.original.incidentapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class IncidentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        incidentRepository.deleteAll();
        IncidentEntity incident = new IncidentEntity();
        incident.setLevel("3");
        incident.setDate(LocalDate.of(2024, 8, 1));
        incident.setTime(LocalTime.of(12, 0));
        incident.setPlace("500病室");
        incident.setPatientId((long) 1);
        incident.setPatientName("テスト");
        incident.setPatientAge(65);
        incident.setDepartment("5階病棟");
        incident.setJob("看護師");
        incident.setContinuousService("5");
        incident.setLargeCategory("転倒・転落");
        incident.setSmallCategory("転倒");
        incident.setSituation("患者が滑って転んだ");
        incident.setCause("靴下で移動しようとしていた");
        incident.setSuggestion("靴をしっかり履く");
        incident.setCountermeasure("靴をしっかり履くように担当看護師から伝える");
        incidentRepository.update(incident);
    }

    @Test
    public void testShowIncidents() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("incidents"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categoryCounts"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("levelCounts"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departmentCounts"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("jobCounts"));
    }

    @Test
    public void testCreateIncident() throws Exception {
        IncidentForm form = new IncidentForm();
        form.setLevel("2");
        form.setDate(LocalDate.of(2024, 8, 1));
        form.setTime(LocalTime.of(12, 0));
        form.setPlace("整形診察室");
        form.setPatientId((long) 2);
        form.setPatientName("サンプル");
        form.setPatientAge(70);
        form.setDepartment("整形外科");
        form.setJob("医師");
        form.setContinuousService("20");
        form.setLargeCategory("診察");
        form.setSmallCategory("患者間違い");
        form.setSituation("診察室に入ってきた人を診察したが、別の患者だった");
        form.setCause("氏名の確認を行っていない");
        form.setSuggestion("患者間違いのルールを院内で決まる。例えば患者本人にフルネームで名乗ってもらい確認する。");
        form.setCountermeasure("患者本人にフルネームで名乗ってもらい確認する。");

        mockMvc.perform(post("/incidents")
                        .flashAttr("incidentForm", form))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    public void testGenerateAISuggestion() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("cause", "靴下で移動しようとしていた");

        mockMvc.perform(post("/generateAISuggestion")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.suggestion").isNotEmpty());
    }

    @Test
    public void testDeleteIncident() throws Exception {
        long id = incidentRepository.findAll().get(0).getCount();

        mockMvc.perform(post("/incidents/" + id + "/delete")
                        .param("adminPassword", "correct_password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    public void testSearchIncidents() throws Exception {
        mockMvc.perform(get("/searchIncidents")
                        .param("year", "2024")
                        .param("month", "8")
                        .param("department", "5階病棟")
                        .param("job", "看護師"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("incidents"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("categoryCounts"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("levelCounts"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("departmentCounts"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("jobCounts"));
    }
}

