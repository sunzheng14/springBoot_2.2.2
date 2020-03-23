package com.sun.zq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = "com.sun.zq")
@MapperScan("com.sun.zq.dao")
@EnableAspectJAutoProxy
public class Application {
	public static void main(String[] args) {
		//ApplicationContext context = SpringApplication.run(Application.class, new String[]{"a","b"});

		SpringApplication application = new SpringApplication(Application.class);
		//application.setAdditionalProfiles("dev");
		application.setBannerMode(Banner.Mode.OFF);
		application.run(new String[]{"a", "b"});
		System.out.println("spring boot started!");
	}

}
