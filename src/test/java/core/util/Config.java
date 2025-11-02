package core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) PROPS.load(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String baseUrl() { return System.getProperty("baseUrl", PROPS.getProperty("baseUrl")); }
    public static String browser() { return System.getProperty("browser", PROPS.getProperty("browser", "chrome")); }
    public static boolean headless() { return Boolean.parseBoolean(System.getProperty("headless", PROPS.getProperty("headless", "true"))); }
    public static int implicitWaitSec(){ return Integer.parseInt(PROPS.getProperty("implicitWaitSec","5")); }
    public static int explicitWaitSec() { return Integer.parseInt(PROPS.getProperty("explicitWaitSec", "5")); }
    public static boolean screenshotOnFailure(){ return Boolean.parseBoolean(PROPS.getProperty("screenshotOnFailure","true")); }
}
