package ru.javawebinar.topjava.service.postgre;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaPostgresUserServiceTest extends PostgresUserServiceTest {
}
