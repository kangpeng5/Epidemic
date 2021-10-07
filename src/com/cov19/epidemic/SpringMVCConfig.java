package com.cov19.epidemic;

import com.cov19.epidemic.common.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class))
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private DateConverter dateConverter;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("epidemic");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //定义了一个内部资源视图解析器
        registry.jsp("/",".jsp");

    }
    //配置日期转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConverter);
    }
    //如果没有匹配到action就使用servlet默认的访问
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //给静态资源放行
        configurer.enable();
    }
}
