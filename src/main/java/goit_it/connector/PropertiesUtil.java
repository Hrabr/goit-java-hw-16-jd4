package goit_it.connector;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private Properties properties = null;

    public PropertiesUtil() {
        load();
    }
    public String getHostName(){
        return properties.getProperty("database.hostname");
    }
    public int getPort(){
        return Integer.parseInt(properties.getProperty("database.port"));
    }
    public String getSchema(){
        return properties.getProperty("database.schema");
    }
    public String getUser(){
        return properties.getProperty("database.user");
    }
    public String getPassword(){
        return properties.getProperty("database.password");
    }


    private void load() {
        properties = new Properties();
        try (InputStream is = ClassLoader.getSystemResourceAsStream("prop.properties")) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
