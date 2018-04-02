package ru.javawebinar.topjava.service.postgre;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.Profiles.POSTGRES_DB;

@ActiveProfiles(POSTGRES_DB)
public abstract class PostgresMealServiceTest extends MealServiceTest {

}
