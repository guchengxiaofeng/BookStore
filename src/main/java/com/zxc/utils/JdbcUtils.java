package com.zxc.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author zhu
 * @create 2021-08-19 15:30
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private  static ThreadLocal<Connection> conns=new ThreadLocal<Connection>();
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
//            FileInputStream inputStream = new FileInputStream("BookStore/src/main/java/jdbc.properties");
            properties.load(inputStream);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
         Connection conn= conns.get();
         if(conn==null){
             try {
                 conn=dataSource.getConnection();
                 conn.setAutoCommit(false);
                 conns.set(conn);
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         }
         return conn;

    }
    public static void commitAndClose(){
        Connection conn=conns.get();
        if(conn!=null){
            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }
    public static void rollbackAndClose(){
          Connection conn=conns.get();
          if(conn!=null){
              try {
                  conn.rollback();
              } catch (SQLException throwables) {
                  throwables.printStackTrace();
              }finally {
                  try {
                      conn.close();
                  } catch (SQLException throwables) {
                      throwables.printStackTrace();
                  }
              }
          }
    }
//    public static void close(Connection conn){
//        if(conn!=null){
//            try {
//                conn.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//    }

}
