package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {


        //map для нужных дней и колличества калорий потребленных в эти дни
        //сначала выясняются дни которые вызваны в запросе. Калории пока 0
        Map<LocalDate, Integer> dateMap = new HashMap<>();

        mealList
                .stream()
                .filter(u -> u.getDateTime().toLocalTime().isAfter(startTime) && u.getDateTime().toLocalTime().isBefore(endTime))
                .forEach(u -> dateMap.put(u.getDateTime().toLocalDate(), 0));



        //выясняем переел ли клиент в этот день
        for (Map.Entry<LocalDate, Integer> pair: dateMap.entrySet()){
            mealList
                    .stream()
                    .filter(u -> u.getDateTime().toLocalDate().equals(pair.getKey()))
                    .forEach(u -> dateMap.put(pair.getKey(), pair.getValue() + u.getCalories()));
        }



        //результат
        List<UserMealWithExceed> mealWithExceedList = new ArrayList<>();

        mealList
                .stream()
                .filter(u -> u.getDateTime().toLocalTime().isAfter(startTime) && u.getDateTime().toLocalTime().isBefore(endTime))
                .forEach(u -> {
                    Boolean exceedResult = dateMap.get(u.getDateTime().toLocalDate()) > caloriesPerDay;
                    mealWithExceedList.add(new UserMealWithExceed(u.getDateTime(), u.getDescription(), u.getCalories(), exceedResult));
                });

        return mealWithExceedList;
    }


}
