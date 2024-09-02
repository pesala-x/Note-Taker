package lk.ijse.pesala_x.notetasker.config;

import jakarta.servlet.annotation.MultipartConfig;
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
@MultipartConfig (
        fileSizeThreshold = 1024 * 1024 * 2, //2mb
        maxFileSize = 1024 * 1024 * 10, //10mb
        maxRequestSize = 1024 * 1024 * 10 // 10mb
)
public class WebAppConfig {}
