package com.olmez.mya.utility;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SourceUtils {
    private static final String SPRING_CONFIG_LOCATION_KEY = "spring.config.location";

    /**
     * src/test/resources/application-test.yml
     */
    public static final String TEST_SOURCE = "classpath:/application-test.yml";

    /**
     * The test profile is "test" as defined in "build.gradle".
     */
    public static final String TEST_PROFILE = "test";

    /**
     * src/main/resources/application-order.yml
     */
    public static final String MAIN_SOURCE = "classpath:/application.properties";

    /**
     * This allows setting application.properties or application.yml file under
     * "src/main/resources" for Spring config settings. When you set this, you
     * should not forget that if you have defined the properties (or yml) file in
     * the system environment, a certain location, the file in that location will
     * not be taken into account. You have to select one of these.
     */
    public static void setSpringConfigLocation() {
        System.setProperty(SPRING_CONFIG_LOCATION_KEY, MAIN_SOURCE);
    }

}