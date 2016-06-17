package me.pecolyte.pai.web.springmvc.initializer;

import me.pecolyte.pai.web.springmvc.config.RootConfig;
import me.pecolyte.pai.web.springmvc.config.ServletConfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootConfig.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { ServletConfig.class };
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
 
}