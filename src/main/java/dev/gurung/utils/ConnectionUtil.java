package dev.gurung.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static ConnectionUtil cu = null;
    private static Properties props;

    private ConnectionUtil(){
        props = new Properties();
        InputStream dbProps = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");

        try{
            props.load(dbProps);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //Getting ConnectionUtil
    public static synchronized ConnectionUtil getConnectionUtil(){
        //Check- does connection exist?
        if(cu == null){
            // create one
            cu = new ConnectionUtil();
        }
        //Already exist - return
        return cu;
    }

    //Get Connection SQL(DB)
    public Connection getConnection(){
        Connection conn = null;
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        try{
            conn = DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return conn;
    }
//
//    public static void main(String[] args) {
//        Connection connection = getConnectionUtil().getConnection();
//        if(connection != null){
//            System.out.println("Connection Successful...");
//        }
//        else{
//            System.out.println("Connection Fail, Something went wrong...");
//        }
//    }

}
