package in.original.incidentapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(true)
public class IncidentRepositoryTest {

    @Autowired
    private IncidentRepository incidentRepository;

    @Test
    public void testInsertAndFindById() {
        IncidentEntity incident = new IncidentEntity();
        incident.setLevel("3");
        incident.setDate(LocalDate.of(2024, 8, 1));
        incident.setTime(LocalTime.of(12, 0));
        incident.setPlace("500病室");
        incident.setNumber((long) 1);
        incident.setName("テスト");
        incident.setAge(65);
        incident.setDepartment("5階病棟");
        incident.setJob("看護師");
        incident.setContinuation("5");
        incident.setCategory("転倒・転落");
        incident.setSegment("転倒");
        incident.setSituation("患者が滑って転んだ");
        incident.setCause("靴下で移動しようとしていた");
        incident.setSuggestion("靴をしっかり履く");
        incident.setCountermeasure("靴をしっかり履くように担当看護師から伝える");

        incidentRepository.insert(
                incident.getLevel(), incident.getDate(), incident.getTime(), incident.getPlace(),
                incident.getNumber(), incident.getName(), incident.getAge(),
                incident.getDepartment(), incident.getJob(), incident.getContinuation(),
                incident.getCategory(), incident.getSegment(), incident.getSituation(),
                incident.getCause(), incident.getSuggestion(), incident.getCountermeasure()
        );

        Optional<IncidentEntity> foundIncident = incidentRepository.findById((long) 1);
        assertTrue(foundIncident.isPresent());
        assertEquals("Test", foundIncident.get().getName());
    }

    @Test
    public void testFindAll() {
        List<IncidentData> incidents = incidentRepository.findAll();
        assertFalse(incidents.isEmpty());
    }

    @Test
    public void testUpdate() {
        Optional<IncidentEntity> incidentOptional = incidentRepository.findById((long) 1);
        assertTrue(incidentOptional.isPresent());

        IncidentEntity incident = incidentOptional.get();
        incident.setPlace("Updated Place");

        incidentRepository.update(incident);

        Optional<IncidentEntity> updatedIncident = incidentRepository.findById((long) 1);
        assertTrue(updatedIncident.isPresent());
        assertEquals("Updated Place", updatedIncident.get().getPlace());
    }

    @Test
    public void testDelete() {
        incidentRepository.delete((long) 1);

        Optional<IncidentEntity> deletedIncident = incidentRepository.findById((long) 1);
        assertTrue(deletedIncident.isEmpty());
    }

    @Test
    public void testFindByCriteria() {
        LocalDate startDate = LocalDate.of(2024, 8, 1);
        LocalDate endDate = LocalDate.of(2024, 8, 31);
        List<IncidentEntity> incidents = incidentRepository.findByCriteria(startDate, endDate, "Cardiology", "Nurse");
        assertFalse(incidents.isEmpty());
    }
}

