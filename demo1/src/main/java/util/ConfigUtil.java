package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static final Properties properties = new Properties();
    static {
        InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream("application.yml");
        try{
            properties.load(inputStream);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}