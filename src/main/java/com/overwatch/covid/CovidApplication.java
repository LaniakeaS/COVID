package com.overwatch.covid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

@SpringBootApplication
@RestController
public class CovidApplication
{

	public static void main(String[] args)
	{	
		//SpringApplication.run(CovidApplication.class, args);
		Server.startServer();
	}
	
}
