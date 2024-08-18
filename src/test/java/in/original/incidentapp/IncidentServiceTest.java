package in.original.incidentapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class IncidentServiceTest {

    @Mock
    private IncidentRepository incidentRepository;

    @InjectMocks
    private IncidentService incidentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        List<IncidentData> mockData = Arrays.asList(
                new IncidentData("LargeCategory", "SmallCategory", 10),
                new IncidentData("LargeCategory", "SmallCategory", 20)
        );

        when(incidentRepository.findAll()).thenReturn(mockData);

        List<IncidentData> result = incidentService.findAll();
        assertEquals(2, result.size());
        assertEquals("LargeCategory", result.get(0).getLargeCategory());
        assertEquals("SmallCategory", result.get(0).getSmallCategory());
        assertEquals(10, result.get(0).getCount());
        assertEquals("LargeCategory", result.get(1).getLargeCategory());
        assertEquals("SmallCategory", result.get(1).getSmallCategory());
        assertEquals(20, result.get(1).getCount());
    }

    @Test
    void testGetLevelCounts() {
        List<IncidentEntity> mockIncidents = Arrays.asList(
                createIncidentEntityWithLevel("1"),
                createIncidentEntityWithLevel("2"),
                createIncidentEntityWithLevel("1")
        );

        Map<String, Integer> result = incidentService.getLevelCounts(mockIncidents);
        assertEquals(2, result.get("1"));
        assertEquals(1, result.get("2"));
    }

    @Test
    void testGetDepartmentCounts() {
        List<IncidentEntity> mockIncidents = Arrays.asList(
                createIncidentEntityWithDepartment("5階病棟"),
                createIncidentEntityWithDepartment("脳神経外科"),
                createIncidentEntityWithDepartment("5階病棟")
        );

        Map<String, Integer> result = incidentService.getDepartmentCounts(mockIncidents);
        assertEquals(2, result.get("脳神経外科"));
        assertEquals(1, result.get("5階病棟"));
    }

    @Test
    void testGetJobCounts() {
        List<IncidentEntity> mockIncidents = Arrays.asList(
                createIncidentEntityWithJob("看護師"),
                createIncidentEntityWithJob("医師"),
                createIncidentEntityWithJob("看護師")
        );

        Map<String, Integer> result = incidentService.getJobCounts(mockIncidents);
        assertEquals(2, result.get("看護師"));
        assertEquals(1, result.get("医師"));
    }

    private IncidentEntity createIncidentEntityWithLevel(String level) {
        IncidentEntity incident = new IncidentEntity();
        incident.setLevel(level);
        return incident;
    }

    private IncidentEntity createIncidentEntityWithDepartment(String department) {
        IncidentEntity incident = new IncidentEntity();
        incident.setDepartment(department);
        return incident;
    }

    private IncidentEntity createIncidentEntityWithJob(String job) {
        IncidentEntity incident = new IncidentEntity();
        incident.setJob(job);
        return incident;
    }
}


