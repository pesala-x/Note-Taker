package lk.ijse.pesala_x.notetasker.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.pesala_x.notetasker")
@EnableWebMvc
@EnableJpaRepositories(basePackages = "lk.ijse.pesala_x.notetasker")
@EnableTransactionManagement
public class WebAppConfig {}
