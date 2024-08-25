package lk.ijse.pesala_x.notetasker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.pesala_x.notetasker")
@EnableWebMvc
public class WebAppConfig {}
