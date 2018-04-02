package ru.javawebinar.topjava.service.hsqldb;

import org.hibernate.cfg.JPAIndexHolder;
import org.springframework.test.context.ActiveProfiles;

import static ru.javawebinar.topjava.Profiles.JPA;

@ActiveProfiles(JPA)
public class JpaHsqldbMealServiceTest extends HsqldbMealServiceTest {
}
