package com.ht.action;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;

public class Test {
//	方法二：使用代码初始化

	public static void main1(String[] args) {
		//1.创建Activiti配置对象的实例
		ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		//2.设置数据库连接信息
		// 设置数据库地址
		configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activitidemo?createDatabaseIfNotExist&amp;useUnicode=true&amp;characterEncoding=utf8");
		// 数据库驱动
		configuration.setJdbcDriver("com.mysql.jdbc.Driver");
		// 用户名
		configuration.setJdbcUsername("root");
		// 密码
		configuration.setJdbcPassword("1234");
		// 设置数据库建表策略
		/**
		 * DB_SCHEMA_UPDATE_TRUE：如果不存在表就创建表，存在就直接使用
		 * DB_SCHEMA_UPDATE_FALSE：如果不存在表就抛出异常
		 * DB_SCHEMA_UPDATE_CREATE_DROP：每次都先删除表，再创建新的表
		 */
		configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//3.使用配置对象创建流程引擎实例（检查数据库连接等环境信息是否正确）
		ProcessEngine processEngine = configuration.buildProcessEngine();
		System.out.println(processEngine);

	}
//	方法三：使用配置文件：在src下创建activiti.cfg.xml

	public static void main(String[] args) {
		// 加载activiti.cfg.xml 创建流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	}
}
