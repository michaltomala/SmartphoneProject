package pl.coderslab.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.converter.BrandConverter;
import pl.coderslab.converter.PhoneConverter;
import pl.coderslab.converter.UserConverter;
//import pl.coderslab.converter.*;
//import pl.coderslab.converter.UserConverter;


@Configuration
@ComponentScan("pl.coderslab")
@EnableWebMvc
@EnableTransactionManagement
public class FormatterConfig implements WebMvcConfigurer{


    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addConverter(getUserConverter());
        registry.addConverter(getPhoneConverter());
        registry.addConverter(getBrandConverter());

    }

    @Bean
    public UserConverter getUserConverter(){ return new UserConverter(); }

    @Bean
    public PhoneConverter getPhoneConverter(){ return new PhoneConverter(); }

    @Bean
    public BrandConverter getBrandConverter (){ return new BrandConverter(); }


}
