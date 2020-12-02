package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
 private static  final String host="jdbc:mysql://localhost:3306/student?autoReconnect=true&useSSL=false";
 private static final String uname="yourUsername";
 private static final String pass="yourPassword";
 public static Connection getConnection() throws SQLException{
return DriverManager.getConnection(host,uname,pass);
 }
}
