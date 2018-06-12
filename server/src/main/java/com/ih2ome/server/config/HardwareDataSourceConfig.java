package com.ih2ome.server.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Sky
 * create 2018/05/08
 * email sky.li@ixiaoshuidi.com
 **/
@Configuration
@MapperScan(basePackages = {"com.ih2ome.dao.hardware"}, sqlSessionTemplateRef = "hardwareSqlSessionTemplate")
public class HardwareDataSourceConfig {
    @Autowired
    @Qualifier("hardwareDataSource")
    private DataSource hardwareDataSource;

    @Bean(name = "hardwareSqlSessionFactory")
    @Primary
    public SqlSessionFactory hardwareSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(hardwareDataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mapping/hardware/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "hardwareTransactionManager")
    @Primary
    public DataSourceTransactionManager hardwareTransactionManager() {
        return new DataSourceTransactionManager(hardwareDataSource);
    }

    @Bean(name = "hardwareSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate hardwareSqlSessionTemplate(@Qualifier("hardwareSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }
}
