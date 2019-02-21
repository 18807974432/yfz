package com.ht.common;

import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    //创建数据库连接池
    public static DataSource getDataSource() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mybatis2018";
        String username = "root";
        String password = "root";
        return new PooledDataSource(driver, url, username, password);
    }
}
