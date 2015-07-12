package it.polimi.awt.mpcs.service;

import it.polimi.awt.mpcs.service.impl.GalleryServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class StrartUp  implements ServletContextListener
{
	
	private static boolean start = true;
  
    public void contextInitialized(ServletContextEvent event) {
    	if(start){
        final WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        GalleryServiceImpl gal = springContext.getBean(GalleryServiceImpl.class);
        gal.startSearchThread();
        start= false;
    	}
    	
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

		
	}
}
