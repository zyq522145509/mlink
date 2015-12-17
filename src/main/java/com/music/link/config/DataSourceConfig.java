package com.music.link.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.music.link.mybatis.plugins.PaginationResultSetInterceptor;
import com.music.link.mybatis.plugins.PaginationStatementInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 数据库连接类
 * basePackages=mybatis的mapper所在文件夹
 */
@Configuration
@MapperScan(basePackages = "com.music.link.dao.mapper", sqlSessionFactoryRef = "sqlSessionFactory", sqlSessionTemplateRef = "sqlSessionTemplate")
@EnableTransactionManagement
public class DataSourceConfig {
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.driverClassName"));
        dataSource.setUrl(env.getProperty("mysql.url"));
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("mysql.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("mysql.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("mysql.maxActive")));
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 获取properties中的对应配置信息mapper文件所在的位置
        String mapperPackage = env.getProperty("spring.mybatis.mapperPackage");
        String dialect = env.getProperty("spring.mybatis.dialect");
        Properties properties = new Properties();
        properties.setProperty("dialect", dialect);


        sessionFactory.setDataSource(dataSource);
        sessionFactory.setConfigurationProperties(properties);
        // 设置MapperLocations路径
        if (!StringUtils.isEmpty(mapperPackage)) {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            sessionFactory.setMapperLocations(resourcePatternResolver.getResources(mapperPackage));
        }
        // 设置插件
        sessionFactory.setPlugins(new Interceptor[]{
                new PaginationStatementInterceptor(),
                new PaginationResultSetInterceptor()
        });
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = builder.dataSource(dataSource).build();
        Properties properties = new Properties();
        //实体文件所在的位置
        entityManagerFactory.setPackagesToScan("com.music.link.entity");
        entityManagerFactory.setJpaProperties(properties);
        return entityManagerFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}
