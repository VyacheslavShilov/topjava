package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }


    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL1.getId(), USER_ID);
        assertMatchMeal(meal, MEAL1);
    }

    @Test
    public void delete() {
        service.delete(MEAL6.getId(), USER_ID);
        assertMatchMeal(service.getAll(USER_ID), MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteOtherUser() {
        service.delete(MEAL6.getId(), 3);
        assertMatchMeal(service.getAll(USER_ID), MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getBetweenDateTimes() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL1);
        updated.setDateTime(LocalDateTime.now());
        updated.setCalories(430);
        updated.setDescription("Тестовая еда");
        service.update(updated, USER_ID);
        assertMatchMeal(service.get(MEAL1.getId(), USER_ID), updated);
    }

    @Test
    public void create() throws Exception{
        Meal newMeal = new Meal(null, LocalDateTime.now(), "2 ужин", 500);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());
        /*List<Meal> listServ = service.getAll(USER_ID);
        List<Meal> listEt = Arrays.asList( MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6, newMeal);*/
        assertMatchMeal(service.getAll(USER_ID), newMeal, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }
}