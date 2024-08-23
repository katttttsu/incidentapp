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

    @Insert("INSERT INTO incidents (level, date, time, place, number, name, age, department, job, continuation, category, `segment`, situation, cause, suggestion, countermeasure) VALUES (#{level}, #{date}, #{time}, #{place}, #{number}, #{name}, #{age}, #{department}, #{job}, #{continuation}, #{category}, #{segment}, #{situation}, #{cause}, #{suggestion}, #{countermeasure})")
    void insert(
            @Param("level") String level,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time,
            @Param("place") String place,
            @Param("number") Long number,
            @Param("name") String name,
            @Param("age") Integer age,
            @Param("department") String department,
            @Param("job") String job,
            @Param("continuation") String continuation,
            @Param("category") String category,
            @Param("segment") String segment,
            @Param("situation") String situation,
            @Param("cause") String cause,
            @Param("suggestion") String suggestion,
            @Param("countermeasure") String countermeasure
    );

    @Update("UPDATE incidents SET level = #{level}, date = #{date}, time = #{time}, place = #{place}, number = #{number}, name = #{name}, age = #{age}, department = #{department}, job = #{job}, continuation = #{continuation}, category = #{category}, segment = #{segment}, situation = #{situation}, cause = #{cause}, suggestion = #{suggestion}, countermeasure = #{countermeasure} WHERE id = #{id}")
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

    @Delete("DELETE FROM incidents")
    void deleteAll();

    @Select("SELECT * FROM incidents WHERE YEAR(date) = #{year}")
    List<IncidentEntity> findByYear(int year);
}