package com.ht.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class DBConn {
    static SqlSession sqlSession;
    public static SqlSession getSession() {
        try{
            // 读取mybatis.xml配置文件
            InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
            // 由SqlSessionFactoryBuilder和mybatis.xml配置文件获取SqlSessionFactory工厂
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 由sqlSessionFactory打开连接获取SqlSession
            sqlSession = sqlSessionFactory.openSession();
        }catch(Exception e){
            e.printStackTrace();
        }
        return sqlSession;
    }
}
