/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingsystem.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author mirman
 */
public class DBConnectionPool implements Runnable {

    private Properties props;
    private static DBConnectionPool instance;
    private static int clients;
    private ArrayList<Connection> freeConnections;

    private DBConnectionPool() {
        //loadData();
        clients = 0;
        this.freeConnections = new ArrayList<>();
        // Registering the db driver
        try {
            Driver d;
            try {
                d = (Driver) Class.forName(Constants.DB_DRIVER).newInstance();
                DriverManager.registerDriver(d);
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(DBConnectionPool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(DBConnectionPool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(DBConnectionPool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //java.sql.Driver d=new com.mysql.jdbc.Driver();
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        Thread t = new Thread(this);
        t.setDaemon(true);
        t.start();

    }

//    private void loadData() {
//        try (FileInputStream in = new FileInputStream(
//                Constants.PROPERTY_FILE_PATH)){
//            props = new Properties();
//            props.load(in);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
//        }
//    }
    
    private Connection newConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(Constants.DB_URL,
                    Constants.DB_USERNAME,Constants.DB_PASSWORD);
            freeConnections.add(conn);
        } catch (SQLException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
        }
        return conn;
    }

    public static synchronized DBConnectionPool getInstance() {
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        clients++;
        return instance;
    }

    public synchronized Connection getConnection() {
        // Получаем ссылку на первое свободное соединение
        Connection conn = null;
        // Если уже есть свободные соединения
        if (!this.freeConnections.isEmpty()) {
            //Получаем последнее соединение и удаляем его из коллекции 
            conn = (Connection) freeConnections.get(freeConnections.size() - 1);
            freeConnections.remove(conn);
            //Если соединение ошибочное - пытаемся получить его еще раз (небольшая рекурсия)
            try {
                if (conn.isClosed()) {
                    conn = getConnection();
                }
            } catch (SQLException ex) {
                conn = getConnection();
                Logger.getLogger(Exception.class.getName()).log(Level.ERROR, ex);
            }
        } else {
            //Если свободных соединений нет - создаем новое
            conn = newConnection();
        }
        return conn;
    }

    public synchronized void returnConnection(Connection conn) {
        // Возвращаем свободное соединение в коллекцию
        if (conn != null) {
            this.freeConnections.add(conn);
        }
    }

    public int getConnectionsCount() {
        //Получаем количество свободных соединений
        return freeConnections.size();
    }

    @Override
    public void run() {
        while (true) {
            if (!this.freeConnections.isEmpty()) {
                long ftime = System.currentTimeMillis();
                if ((System.currentTimeMillis() - ftime)
                        > Constants.DB_CONNLIFETIME) {
                    removeLastConnection();
                }
            }
        }
    }

    private synchronized void removeLastConnection() {
        freeConnections.remove(freeConnections.size() - 1);
    }
}
