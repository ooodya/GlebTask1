package com.zaycevImaginaryCompany.glebTask1;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaycevImaginaryCompany.glebTask1.config.ValidationConfig;

@SpringBootApplication
@EnableJpaRepositories("com.zaycevImaginaryCompany.glebTask1.repository")
@EntityScan("com.zaycevImaginaryCompany.glebTask1.domain")
@Import(ValidationConfig.class)
@PropertySource("classpath:messages.properties")
public class GlebTask1Application implements ApplicationRunner
{
	public static void main(String[] args)
	{
		SpringApplication.run(GlebTask1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
	}
}
