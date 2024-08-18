package lk.ijse.pesala_x.notetasker;

import jakarta.servlet.annotation.WebServlet;
import lk.ijse.pesala_x.notetasker.config.WebAppConfig;
import lk.ijse.pesala_x.notetasker.config.WebAppRootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@WebServlet(name = "helloServlet", value = "/hello.servlet" )
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebAppRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebAppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
