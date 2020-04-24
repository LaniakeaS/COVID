package com.overwatch.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class CovidApplication
{

	public static void main(String[] args)
	{
		Server.startServer();
		SpringApplication.run(CovidApplication.class, args);
	}

	@GetMapping("/covid")
	public String html()
	{
		return "covid";
	}

}
