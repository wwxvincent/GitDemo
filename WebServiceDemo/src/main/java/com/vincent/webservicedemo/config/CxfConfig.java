package com.vincent.webservicedemo.config;

import com.vincent.webservicedemo.server.IUserServer;
import jakarta.servlet.Servlet;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.endpoint.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Vincent(Wenxuan) Wang
 * @Date: 8/27/24
 * @Description:
 */
@Configuration
@RequiredArgsConstructor
public class CxfConfig {
    private final IUserServer userServer;

    /**
     * 注入Servlet,注意beanName不能为dispatcherServlet
     */
    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean((Servlet) new CXFServlet(), "/webservice/*");
    }

    @Bean (name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(new SpringBus(), userServer);
        endpoint.publish("/api");
        return endpoint;
    }
}
