package com.overwatch.covid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
		//SpringApplication.run(CovidApplication.class, args);
	}

	@GetMapping("/covid")
	public String html() {
		RequestInterface get = Database.getDatabase();
		File china = new File("src/main/resources/static/China.json");
		File world = new File("src/main/resources/static/world.json");
		File news = new File("src/main/resources/static/news.json");

		try
		{
			if(!china.exists())
				china.createNewFile();
			
			if(!world.exists())
				world.createNewFile();

			if(!news.exists())
				news.createNewFile();

			FileOutputStream out = new FileOutputStream(china);
			out.write(get.getChinaData().getBytes());
			out.close();

			out = new FileOutputStream(world);
			out.write(get.getWorldData().getBytes());
			out.close();

			out = new FileOutputStream(news);
			out.write(get.getNews().getBytes());
			out.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return "covid";
	}

}
