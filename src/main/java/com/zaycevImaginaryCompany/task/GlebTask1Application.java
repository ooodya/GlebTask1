package com.zaycevImaginaryCompany.task;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
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
