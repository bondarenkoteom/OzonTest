package ozon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropSettings {

    private final Properties properties = new Properties();
    private static PropSettings INSTANCE = null;

    private PropSettings() {
        try {
            properties.load(new FileInputStream(new File("src/test/resources/"
                    + "chrome.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropSettings getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropSettings();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}
