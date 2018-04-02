package ru.javawebinar.topjava.service.postgre;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.JDBC;

@ActiveProfiles(JDBC)
public class JdbcPostgresUserServiceTest extends PostgresUserServiceTest {
}
