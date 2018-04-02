package ru.javawebinar.topjava.service.hsqldb;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.HSQL_DB;

@ActiveProfiles(HSQL_DB)
public abstract class HsqldbUserServiceTest extends UserServiceTest {
}
