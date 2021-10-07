package com.cov19.epidemic;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.cov19.epidemic.mapper")
public class  MyBatisConfig {
    //配置数据源
    @Bean(name = "dataSource", destroyMethod = "close")
    public BasicDataSource basicDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");//驱动
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/epidemic");//数据库地址，本机IP地址一般绩是127.0.0.1,至于名字
        dataSource.setUsername("root");//数据库用户名
        dataSource.setPassword("123456");//其他组员的是123456，数据库密码我的是root！！！这里错了会报连接jdbc异常
        dataSource.setInitialSize(3);//连接池大小
        dataSource.setMaxActive(50);//最大活跃数
        dataSource.setMaxIdle(1);//最大空闲数
        dataSource.setMaxWait(4000);//等待连接时间4秒
        dataSource.setDefaultAutoCommit(false);//禁止自动提交事务
        return dataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean factoryBean =new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);
        SqlSessionFactory factory = null;
        //设置xml文件中类所在的包
        factoryBean.setTypeAliasesPackage("com.cov19.epidemic.bean");
        //让MyBatis自动让将下划线分割的列名转换为小驼峰表示的属性名
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//user_id-->userID
        factoryBean.setConfiguration(configuration);

        //设置映射xml文件路径
        try {
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:com/cov19/epidemic/mapper/*Mapper.xml");
            factoryBean.setMapperLocations(resources);
            factory = factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }
}
