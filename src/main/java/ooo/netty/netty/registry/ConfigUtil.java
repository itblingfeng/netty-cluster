package ooo.netty.netty.registry;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
    private static Properties prop = new Properties();

    static {
        try {
            prop.load(ConfigUtil.class.getResourceAsStream("/registry.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final static String REGISTRY_ADDRESS = prop.getProperty("address", "127.0.0.1");

    public final static int REGISTRY_PORT = Integer.valueOf(prop.getProperty("port", "8090"));
}
