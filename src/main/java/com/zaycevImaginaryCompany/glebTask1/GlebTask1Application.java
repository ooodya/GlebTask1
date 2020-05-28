package com.zaycevImaginaryCompany.glebTask1;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.zaycevImaginaryCompany.glebTask1.repository")
@EntityScan("com.zaycevImaginaryCompany.glebTask1.domain")
@PropertySource("classpath:messages.properties")
public class GlebTask1Application implements ApplicationRunner
{
	@Value( "${validation.user.password.empty}" )
	private String message;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(GlebTask1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		System.out.println(message);
		System.out.println("classpath");
	}
}
