package ru.javawebinar.topjava.service.postgre;

import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.DATAJPA;


@ActiveProfiles(DATAJPA)
public class DataJpaPostgresMealServiceTest extends PostgresMealServiceTest {
}
