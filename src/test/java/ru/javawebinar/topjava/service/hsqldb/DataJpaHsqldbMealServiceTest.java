package ru.javawebinar.topjava.service.hsqldb;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.DATAJPA;

@ActiveProfiles(DATAJPA)
public class DataJpaHsqldbMealServiceTest extends HsqldbMealServiceTest {
}
