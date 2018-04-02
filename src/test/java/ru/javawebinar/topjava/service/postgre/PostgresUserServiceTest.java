package ru.javawebinar.topjava.service.postgre;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.POSTGRES_DB;

@ActiveProfiles(POSTGRES_DB)
public abstract class PostgresUserServiceTest extends UserServiceTest {
}
