package com.overwatch.covid;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class CovidApplication extends Thread
{

	public static void main(String[] args)
	{
		Server.startServer();
		SpringApplication.run(CovidApplication.class, args);
		CovidApplication start = new CovidApplication();
		start.start();
	}

	private static void cmdVue()
	{
		try
		{
			System.out.println("Starting vue..... Please wait.");
			StringBuffer command = new StringBuffer();
			command.append("cmd /c dir");
			command.append(" && cd src/main/resources/static/dist");
			command.append(" && http-server --port 7777");
			Process p = Runtime.getRuntime().exec(command.toString());
			Scanner scanner = new Scanner(p.getInputStream());

			while(scanner.hasNextLine())
				System.out.println(scanner.nextLine());
			
			scanner.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Vue: Start Fail... Please reboot the process");
			System.exit(-1);
		}
	}

	@GetMapping("/covid")
	public String html() {
		RequestInterface get = Database.getDatabase();
		File china = new File("src/main/resources/static/China.json");
		File world = new File("src/main/resources/static/world.json");
		File news = new File("src/main/resources/static/news.json");
		File worldNews = new File("src/main/resources/static/worldnews.json");

		try
		{
			if(!china.exists())
				china.createNewFile();
			
			if(!world.exists())
				world.createNewFile();

			if(!news.exists())
				news.createNewFile();

			if(!worldNews.exists())
				worldNews.createNewFile();

			FileOutputStream out = new FileOutputStream(china);
			out.write(get.getChinaData().getBytes());
			out.close();

			out = new FileOutputStream(world);
			out.write(get.getWorldData().getBytes());
			out.close();

			out = new FileOutputStream(news);
			out.write(get.getNews().getBytes());
			out.close();

			out = new FileOutputStream(worldNews);
			out.write(get.getWorldNews().getBytes());
			out.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return "covid";
	}

	@Override
	public void run()
	{
		cmdVue();
	}

}
