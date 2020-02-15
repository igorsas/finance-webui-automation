package com.igor.utils.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Property {
//    private static final Logger LOGGER = LogManager.getLogger(Property.class);

    public static String getProperty(final String keyToFile) {
        Properties properties = new Properties();
        try (InputStream input = Property.class.getClassLoader().getResourceAsStream("driver.properties")) {
            properties.load(Objects.requireNonNull(input));
            return properties.getProperty(keyToFile);
        } catch (IOException e) {
//            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
