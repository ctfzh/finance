package com.ih2ome.server;

import com.ih2ome.server.config.MyServletContextListener;
import com.ih2ome.server.controller.ConfigPaymentsController;
import com.ih2ome.server.pingan.sdk.InitConfiguration;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ih2ome"})
@ServletComponentScan
public class ServerApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigPaymentsController.class);


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerApplication.class);
    }

    public static void main(String[] args) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        LOGGER.info("===============aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SpringApplication.run(ServerApplication.class, args);
    }


}

