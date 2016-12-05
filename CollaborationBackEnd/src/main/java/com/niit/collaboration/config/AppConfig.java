package com.niit.collaboration.config;


import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
@Configuration
@EnableWebMvc
@ComponentScan("com.niit.collaboration")
public class AppConfig extends WebMvcConfigurerAdapter {
Logger log = Logger.getLogger(AppConfig.class);
	
	@Bean
	public ViewResolver viewResolver() {
		log.debug("Starting of the viewResolver method.........");
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".html");
		log.debug("Ending of the viewResolver method.........");
		
		return viewResolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		log.debug("Entered in configureDefaultServletHandling method...............");
		configurer.enable();
	}
}
