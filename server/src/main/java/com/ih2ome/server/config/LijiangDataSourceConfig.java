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
 * create 2018/08/01
 * email sky.li@ixiaoshuidi.com
 **/
@Configuration
@MapperScan(basePackages = {"com.ih2ome.dao.lijiang"}, sqlSessionTemplateRef = "lijiangSqlSessionTemplate")
public class LijiangDataSourceConfig {

    @Autowired
    @Qualifier("lijiangDataSource")
    private DataSource lijiangDataSource;

    @Bean(name = "lijiangSqlSessionFactory")
    public SqlSessionFactory lijiangSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(lijiangDataSource);
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mapping/lijiang/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name = "lijiangTransactionManager")
    public DataSourceTransactionManager lijiangTransactionManager() {
        return new DataSourceTransactionManager(lijiangDataSource);
    }

    @Bean(name = "lijiangSqlSessionTemplate")
    public SqlSessionTemplate lijiangSqlSessionTemplate(@Qualifier("lijiangSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
        return template;
    }
}
