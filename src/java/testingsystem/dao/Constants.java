/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao;

import org.apache.log4j.Logger;

/**
 *
 * @author mirman
 */
public interface Constants {
    public static final String MYSQL_DAO_FACTORY = "MySQLDAOFactory";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/testingsystemdb?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    public static final int DB_CONNLIFETIME = 30000;
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final Logger logger 
            = Logger.getLogger("./src/java/log4j.properties");
}
