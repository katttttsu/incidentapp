package in.original.incidentapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.Optional;
import java.util.Collections;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class IncidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IncidentRepository incidentRepository;

    @Mock
    private IncidentMapper incidentMapper;

    @Mock
    private IncidentService incidentService;

    @InjectMocks
    private IncidentController incidentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowIncidentForm() throws Exception {
        mockMvc.perform(get("/incidentForm"))
                .andExpect(status().isOk())
                .andExpect(view().name("incidentForm"))
                .andExpect(model().attributeExists("incidentForm"))
                .andExpect(model().attributeExists("mainCategories"))
                .andExpect(model().attributeExists("subCategoryMap"));
    }

    @Test
    public void testCreateIncident() throws Exception {
        mockMvc.perform(post("/incidents")
                        .param("level", "3")
                        .param("date", "2023-08-1")
                        .param("time", "12:00")
                        .param("place", "500病室")
                        .param("number", "1")
                        .param("name", "テスト")
                        .param("age", "65")
                        .param("department", "5階病棟")
                        .param("job", "看護師")
                        .param("continuation", "5")
                        .param("category", "転倒・転落")
                        .param("segment", "転倒")
                        .param("situation", "患者が滑って転んだ")
                        .param("cause", "靴下で移動しようとしていた")
                        .param("suggestion", "靴をしっかり履く")
                        .param("countermeasure", "靴をしっかり履くように担当看護師から伝える"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testGenerateAISuggestion() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("cause", "Wet floor");

        Map<String, String> response = new HashMap<>();
        response.put("suggestion", "Use wet floor signs.");

        when(incidentController.generateAISuggestion(request)).thenReturn(response);

        mockMvc.perform(post("/generateAISuggestion")
                        .contentType("application/json")
                        .content("{\"cause\": \"Wet floor\"}"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("suggestion"))
                .andExpect(jsonPath("$.suggestion").value("Use wet floor signs."));
    }

    @Test
    public void testShowIncidents() throws Exception {
        when(incidentController.getIncidents(2023, 8, "", "")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("incidents"));
    }

    @Test
    public void testSearchIncidents() throws Exception {
        when(incidentController.getIncidents(2024, 8, "5階病棟", "看護師")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/searchIncidents")
                        .param("year", "2024")
                        .param("month", "8")
                        .param("department", "500病室")
                        .param("job", "看護師"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("incidents"));
    }

    @Test
    public void testIncidentDetail() throws Exception {
        IncidentEntity incident = new IncidentEntity();
        incident.setId((long) 1);
        incident.setPlace("Test Place");

        when(incidentRepository.findById(1)).thenReturn(Optional.of(incident));

        mockMvc.perform(get("/incidents/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("detail"))
                .andExpect(model().attributeExists("incident"))
                .andExpect(model().attribute("incident", incident));
    }

    @Test
    public void testDeleteIncident() throws Exception {
        when(incidentRepository.findById(1)).thenReturn(Optional.of(new IncidentEntity()));

        mockMvc.perform(post("/incidents/1/delete")
                        .param("adminPassword", "admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}

