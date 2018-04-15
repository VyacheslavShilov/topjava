package ru.javawebinar.topjava;

import org.springframework.core.env.Environment;

public class TestUtil {

    public static boolean itsBadProfiles(String databaseProfile, String conProfile, Environment environment) {
        boolean isDatabaseProfile = false;
        boolean isConProfile= false;

        for (String profile : environment.getActiveProfiles()) {
            if (profile.equals(databaseProfile)) isDatabaseProfile = true;
            if (profile.equals(conProfile)) isConProfile = true;
        }

        return (isConProfile && isDatabaseProfile);
    }

}
