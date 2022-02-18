package goit_it.connector;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertisProvade implements DatabaseManager{
    private String url;
    private final Properties properties;
    private String puser;
    private String ppassword;
    public PropertisProvade(String hostname, String schema, int port, String user, String password) {
        url= String.format("jdbc:postgresql://%s:%d/%s",hostname,port,schema);
        properties=new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password",password);

    }



    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,properties);
    }
}
