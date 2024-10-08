package in.original.incidentapp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface IncidentMapper {
    @Select("SELECT * FROM incidents WHERE id = #{id}")
    IncidentEntity findById(Long id);

    @Insert("INSERT INTO incidents (level, date, time, place, number, name, age, department, job, continuation, category, segment, situation, cause, suggestion, countermeasure) VALUES (#{level}, #{date}, #{time}, #{place}, #{number}, #{name}, #{age}, #{department}, #{job}, #{continuation}, #{category}, #{segment}, #{situation}, #{cause}, #{suggestion}, #{countermeasure})")
    void insert(
            @Param("level") String level,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time,
            @Param("place") String place,
            @Param("number") long number,
            @Param("name") String name,
            @Param("age") int age,
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

    @Update("UPDATE incidents SET level = #{level}, date = #{date}, time = #{time}, place = #{place}, number = #{number}, name = #{name}, age = #{age}, department = #{department}, job = #{job}, continuation = #{continuation}, category = #{category}, segment= #{segment}, situation = #{situation}, cause = #{cause}, suggestion = #{suggestion}, countermeasure = #{countermeasure} WHERE id = #{id}")
    void update(
            @Param("id") long id,
            @Param("level") String level,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time,
            @Param("place") String place,
            @Param("number") long number,
            @Param("name") String name,
            @Param("age") int age,
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

    @Delete("DELETE FROM incidents WHERE id = #{id}")
    void delete(@Param("id") long id);

    @Select("SELECT * FROM incidents WHERE date >= #{startDate} AND date < #{endDate} AND (department = #{department} OR #{department} = '') AND (job = #{job} OR #{job} = '')")
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
}