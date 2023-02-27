package com.util;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import lombok.SneakyThrows;

import javax.sql.DataSource;

public class Dbutil {
     public static final DataSource dataSource;
     static{
         MysqlDataSource mysqlDataSource = new MysqlDataSource();
         mysqlDataSource.setUrl("jdbc:mysql://127.00.1:3306/login_user?characterEncoding=utf8&useSSL=false&serverTime=Asia/Shanghai");
         mysqlDataSource.setUser("root");
         mysqlDataSource.setPassword("password");
         dataSource = mysqlDataSource;
     }
     @SneakyThrows
     public static Connection connection(){
         return (Connection) dataSource.getConnection();
     }

}
