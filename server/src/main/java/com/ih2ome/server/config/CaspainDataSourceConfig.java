package com.ih2ome.server.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author Sky
 * create 2018/05/08
 * email sky.li@ixiaoshuidi.com
 **/
@Configuration
@MapperScan(basePackages = {"com.ih2ome.dao.caspain"}, sqlSessionTemplateRef = "caspainSqlSessionTemplate")
public class CaspainDataSourceConfig {
    @Autowired
    @Qualifier("caspainDataSource")
    private DataSource caspainDataSource;

    @Bean(name = "caspainSqlSessionFactory")
    public SqlSessionFactory caspainSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(caspainDataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mapping/caspain/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "caspainTransactionManager")
    public DataSourceTransactionManager caspainTransactionManager() {
        return new DataSourceTransactionManager(caspainDataSource);
    }

    @Bean(name = "caspainSqlSessionTemplate")
    public SqlSessionTemplate caspainSqlSessionTemplate(@Qualifier("caspainSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }
}
