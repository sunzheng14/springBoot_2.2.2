package com.sun.zq.configuration;

import com.sun.zq.model.SimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "enabled.autoConfituration", matchIfMissing = true)
public class JavaConfiguration {
    static {
        System.out.println("myAutoConfiguration init...");
    }
    @Bean
    public SimpleBean simpleBean(){
        return new SimpleBean();
    }

}
