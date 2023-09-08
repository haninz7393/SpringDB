package com.sol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.sol.dao.StudentDAO;
import com.sol.dao.StudentDAOImpl;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.sol.controllers"})
@PropertySources({@PropertySource("classpath:ds/datasource-cfg.properties")})
public class AppConfig implements WebMvcConfigurer{
	
		@Autowired
		ApplicationContext applicationContext;
		
		@Autowired
		private Environment env;
		
		@Bean
		public ViewResolver viewResolver() {
			InternalResourceViewResolver ivr = new InternalResourceViewResolver();
			ivr.setPrefix("/WEB-INF/views/");
			ivr.setSuffix(".jsp");
			ivr.setOrder(0);
			return ivr;
		}
		
		@Bean
		DriverManagerDataSource getDataSource() {
			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName(env.getProperty("ds.database-driver"));
			ds.setUrl(env.getProperty("ds.url"));
			ds.setUsername(env.getProperty("ds.username"));
			ds.setPassword(env.getProperty("ds.password"));
			return ds;
		}
		
		@Bean
		public StudentDAO getUserDAO() {
			return new StudentDAOImpl(getDataSource());
			
		}
}
