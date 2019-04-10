package com.epam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * The Class WebConfiguration.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages="com.epam")
public class WebConfiguration {

	/**
	 * View resolver.
	 *
	 * @return the view resolver
	 */
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
