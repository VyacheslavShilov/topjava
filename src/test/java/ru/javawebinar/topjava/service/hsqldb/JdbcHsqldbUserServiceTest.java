package ru.javawebinar.topjava.service.hsqldb;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcHsqldbUserServiceTest extends HsqldbUserServiceTest {
}
