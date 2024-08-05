package in.original.incidentapp;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import in.original.incidentapp.IncidentData;

@Mapper
public interface IncidentRepository {
    List<IncidentData> findAll();

    @Select("SELECT * FROM incidents WHERE id = #{id}")
    Optional<IncidentEntity> findById(long id);

    @Insert("INSERT INTO incidents (level, date, time, place, patientId, patientName, patientAge, department, job, continuousService, category1, category2, situation, cause, suggestion, countermeasure) VALUES (#{level}, #{date}, #{time}, #{place}, #{patientId}, #{patientName}, #{patientAge}, #{department}, #{job}, #{continuousService}, #{category1}, #{category2}, #{situation}, #{cause}, #{suggestion}, #{countermeasure})")
    void insert(
            @Param("level") String level,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time,
            @Param("place") String place,
            @Param("patientId") Long patientId,
            @Param("patientName") String patientName,
            @Param("patientAge") Integer patientAge,
            @Param("department") String department,
            @Param("job") String job,
            @Param("continuousService") String continuousService,
            @Param("category1") String category1,
            @Param("category2") String category2,
            @Param("situation") String situation,
            @Param("cause") String cause,
            @Param("suggestion") String suggestion,
            @Param("countermeasure") String countermeasure
    );

    @Update("UPDATE incidents SET level = #{level}, date = #{date}, time = #{time}, place = #{place}, patientId = #{patientId}, patientName = #{patientName}, patientAge = #{patientAge}, department = #{department}, job = #{job}, continuousService = #{continuousService}, category1 = #{category1}, category2 = #{category2}, situation = #{situation}, cause = #{cause}, suggestion = #{suggestion}, countermeasure = #{countermeasure} WHERE id = #{id}")
    void update(IncidentEntity incident);

    @Delete("DELETE FROM incidents WHERE id = #{id}")
    void delete(@Param("id") long id);

    @Select("SELECT * FROM incidents WHERE date BETWEEN #{startDate} AND #{endDate} AND (department = #{department} OR #{department} = '') AND (job = #{job} OR #{job} = '')")
    List<IncidentEntity> findByCriteria(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("department") String department,
            @Param("job") String job
    );

    @Select("SELECT DISTINCT department FROM incidents")
    List<String> findDistinctDepartments();

    @Select("SELECT DISTINCT job FROM incidents")
    List<String> findDistinctJobs();

    @Select("SELECT COUNT(*) FROM incidents WHERE date BETWEEN #{startDate} AND #{endDate}")
    long countByDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}