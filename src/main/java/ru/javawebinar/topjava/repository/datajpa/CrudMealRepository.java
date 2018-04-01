package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);


    @Transactional
    Meal save(Meal meal);

   /* @Transactional
    @Query("UPDATE Meal m SET m.dateTime=?1, m.calories =?2, m.description =?3 WHERE m.id =?4")
    Meal update(LocalDateTime dateTime, int calories, String description, int id);*/

    @Query("SELECT m FROM Meal m WHERE m.id=:id AND m.user.id=:user_id")
    Optional<Meal> findById(@Param("id") int id, @Param("user_id") int userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:user_id ORDER BY m.dateTime DESC")
    List<Meal> findAll(@Param("user_id") Integer userId);

    @Query("SELECT m FROM Meal m WHERE m.user.id=:user_id AND m.dateTime >=:start_date AND  m.dateTime <=:end_date ORDER BY m.dateTime DESC")
    List<Meal> getBetween(@Param("start_date")LocalDateTime startDate, @Param("end_date")LocalDateTime endDate, @Param("user_id") int userId);

}
