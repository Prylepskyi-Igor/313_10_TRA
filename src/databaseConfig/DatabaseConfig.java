package databaseConfig;

import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class DatabaseConfig {
	// JDBC driver
    //private final String JDBC_DRIVER="com.mysql.jdbc.Driver";

    private String db_url;
    private String server;
    private String port;
    private String db;
    private String db_user;
    private String db_password;
    private Connection conn;

    public DatabaseConfig(String db) {
        this.db = db;
    }
    
    public Connection Config(){
        try {
            //local settings (default)
            server = "localhost";
            port = "3306";
            db_user = "admin8XQ6unM";
            db_password = "SkBRb95CQBD3";

            //remote settings
            if(System.getenv("OPENSHIFT_MYSQL_DB_HOST") != null){
                server = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
                port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                db_user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                db_password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            }

            //database URL
            db_url="jdbc:mysql://" + server + ":" + port + "/" + this.db;

            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(db_url,db_user,db_password);
            
        } catch (ClassNotFoundException | SQLException ex) {
            //Logger.getLogger(Discussion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}