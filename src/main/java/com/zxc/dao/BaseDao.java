package com.zxc.dao;

import com.zxc.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhu
 * @create 2021-08-20 15:43
 */
public abstract class BaseDao  {
        private  QueryRunner queryRunner=new QueryRunner();
      public int update(String sql,Object...args){
          Connection conn= JdbcUtils.getConnection();
          try {
              return queryRunner.update(conn,sql,args);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
              throw new RuntimeException(throwables);
          }
      }
      public <T> T queryForOne(Class<T> type,String sql,Object...args){
          Connection conn=JdbcUtils.getConnection();
          try {
              return queryRunner.query(conn,sql,new BeanHandler<>(type),args);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
              throw new RuntimeException(throwables);
          }
      }

      public <T> List<T> queryForList(Class<T> type,String sql,Object...args){
          Connection conn=JdbcUtils.getConnection();
          try {
              return queryRunner.query(conn,sql,new BeanListHandler<>(type),args);
          } catch (SQLException throwables) {
              throwables.printStackTrace();
              throw new RuntimeException(throwables);
          }
      }
      public Object  queryForValue(String sql,Object...args){
        Connection conn=JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }


}
