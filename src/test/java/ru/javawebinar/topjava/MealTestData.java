package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final Meal MEAL1 = new Meal(START_SEQ + 2,
            LocalDateTime.of(2018, 3, 19, 10, 00),
            "Завтрак",
            500);

    public static final Meal MEAL2 = new Meal(START_SEQ + 3,
            LocalDateTime.of(2018, 3, 19, 15, 00),
            "Oбед",
            1500);

    public static final Meal MEAL3 = new Meal(START_SEQ + 4,
            LocalDateTime.of(2018, 3, 19, 20, 00),
            "Ужин",
            300);

    public static final Meal MEAL4 = new Meal(START_SEQ + 5,
            LocalDateTime.of(2018, 3, 20, 11, 00),
            "Завтрак",
            200);

    public static final Meal MEAL5 = new Meal(START_SEQ + 6,
            LocalDateTime.of(2018, 3, 20, 16, 00),
            "Oбед",
            1000);

    public static final Meal MEAL6 = new Meal(START_SEQ + 7,
            LocalDateTime.of(2018, 3, 20, 21, 00),
            "Ужин",
            300);

    public static void assertMatchMeal(Meal actual, Meal expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatchMeal(Iterable<Meal> actual, Meal... expected) {
        assertMatchMeal(actual, Arrays.asList(expected));
    }

    public static void assertMatchMeal(Iterable<Meal> actual, Iterable<Meal> expected) {
      //  assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
        assertThat(actual).isEqualTo(expected);
    }

}
