package it.polimi.awt.mpcs.web.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

public class WebMvcConfig extends WebMvcConfigurationSupport {
	
	public void addViewControllers(ViewControllerRegistry registry){
		registry.addViewController("/login/form")
		.setViewName("login");
	}
}
