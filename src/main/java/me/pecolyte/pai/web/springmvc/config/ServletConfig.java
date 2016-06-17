package me.pecolyte.pai.web.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "me.pecolyte.pai.web")
public class ServletConfig {
 
}
