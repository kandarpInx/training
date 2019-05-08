package com.main.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.main")
public class WebAppConfiguration implements WebMvcConfigurer {
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/App/jsps/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	 @Bean
	   public MessageSource messageSource() {
	      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	      source.setBasename("messages");
	      return source;
	   }
	 
	   public org.springframework.validation.Validator getValidator() {
	      LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
	      validator.setValidationMessageSource(messageSource());
	      return validator;
	   }
	
	/*
	 * @Bean(name = "freemarkerConfig") public FreeMarkerConfigurer
	 * getFreemarkerConfig() { FreeMarkerConfigurer config = new
	 * FreeMarkerConfigurer();
	 * 
	 * // Folder containing FreeMarker templates.
	 * config.setTemplateLoaderPath("/WEB-INF/App/ftls/"); return config; }
	 * 
	 * @Bean(name = "viewResolver") public ViewResolver getViewResolver() {
	 * FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
	 * viewResolver.setCache(true); // viewResolver.setPrefix("/WEB-INF/App/ftls/");
	 * viewResolver.setSuffix(".ftl"); return viewResolver; }
	 */
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}