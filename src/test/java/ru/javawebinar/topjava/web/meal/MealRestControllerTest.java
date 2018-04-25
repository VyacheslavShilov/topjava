package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

import ru.javawebinar.topjava.MealTestData.*;

import java.time.LocalDateTime;


public class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_MEAL_URL = MealRestController.REST_MEAL_URL + '/';

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_MEAL_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
                assertMatch(mealService.getAll(USER_ID),MEAL6, MEAL5, MEAL4, MEAL3, MEAL2);
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(get(REST_MEAL_URL))
                .andDo(print())
                .andExpect(status().isOk());
        assertMatch(mealService.getAll(USER_ID),MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void testGet() throws Exception{
        mockMvc.perform(get(REST_MEAL_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(JsonUtil.writeValue(MEAL1)));

    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();
        String path = REST_MEAL_URL + MEAL1_ID;
        mockMvc.perform(put(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(mealService.get(MEAL1_ID, USER_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Meal expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_MEAL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Meal returned = TestUtil.readFromJson(action, Meal.class);
        expected.setId(returned.getId());
        assertMatch(returned, expected);
        assertMatch(mealService.getAll(USER_ID),expected, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1 );

    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_MEAL_URL + "filter?" +
                "startDate=2015-05-31T10:15:30" +
                "&startTime=2015-05-31T10:15:30" +
                "&endDate=2015-05-31T10:15:30" +
                "&endTime=2015-05-31T20:15:30"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}