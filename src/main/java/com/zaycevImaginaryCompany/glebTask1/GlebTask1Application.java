package com.zaycevImaginaryCompany.glebTask1;


import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.zaycevImaginaryCompany.glebTask1.domain.Account;
import com.zaycevImaginaryCompany.glebTask1.domain.User;
import com.zaycevImaginaryCompany.glebTask1.service.UserService;

@SpringBootApplication
@EnableJpaRepositories("com.zaycevImaginaryCompany.glebTask1.repository")
@EntityScan("com.zaycevImaginaryCompany.glebTask1.domain")
public class GlebTask1Application implements ApplicationRunner
{
	@Autowired
    private ApplicationContext ctx;
		
	public static void main(String[] args) 
	{
		SpringApplication.run(GlebTask1Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		
	}

}
