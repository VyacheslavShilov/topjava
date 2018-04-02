package ru.javawebinar.topjava.service.hsqldb;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.Profiles.HSQL_DB;

@ActiveProfiles(HSQL_DB)
public abstract class HsqldbMealServiceTest extends MealServiceTest {
}
