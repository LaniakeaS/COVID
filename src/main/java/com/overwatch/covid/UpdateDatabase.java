package com.overwatch.covid;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
import org.springframework.stereotype.Service;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateDatabase extends Thread{
	private UpdateInterface database;
	
	// thread initialization
	public UpdateDatabase(UpdateInterface db)
	{
		database = db;
		// TODO
	}
	
	// start thread
	@Override
	public void run() {
		
	}
		public static String getStatisticsService(){
		String url="https://ncov.dxy.cn/ncovh5/view/pneumonia";
        //modify request
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpHost("ncov.dxy.cn");
        httpPojo.setHttpAccept("*/*");
        httpPojo.setHttpConnection("keep-alive");
        httpPojo.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
        httpPojo.setHttpReferer("https://ncov.dxy.cn");
        httpPojo.setHttpOrigin("https://ncov.dxy.cn");
        Map paramObj = new HashMap();
        String htmlResult = httpSendGet(url, paramObj, httpPojo); //whole HTML page
        //System.out.println(htmlResult);
 
        
        // get JSON statics
        String reg= "window.getStatisticsService = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(htmlResult);
 
        String result="";
        if (totalMatcher.find()){
            result = totalMatcher.group(1);
            System.out.println(result);
        }
        return result;
    }
 
 
    /**
     * get China province statics
     * @return
     */
    public static String getAreaStat(){
        String url="https://ncov.dxy.cn/ncovh5/view/pneumonia";
        
        //modify request
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpHost("ncov.dxy.cn");
        httpPojo.setHttpAccept("*/*");
        httpPojo.setHttpConnection("keep-alive");
        httpPojo.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
        httpPojo.setHttpReferer("https://ncov.dxy.cn");
        httpPojo.setHttpOrigin("https://ncov.dxy.cn");
        Map paramObj = new HashMap();
        String htmlResult = httpSendGet(url, paramObj, httpPojo); //whole HTML page
        //System.out.println(htmlResult);
 
       
        //get JSON statics
        String reg= "window.getAreaStat = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(htmlResult);
        String result="";
        if (totalMatcher.find()){
            result = totalMatcher.group(1);
            System.out.println(result);
            
            //China province list; demo
            /*JSONArray array = JSONArray.parseArray(result);
            JSONObject jsonObject = JSONObject.parseObject(array.getString(0));
            String provinceName = jsonObject.getString("provinceName");
            System.out.println("provinceName："+provinceName);*/
        }
 
        return result;
    }
    
    
    public static String getListByCountryTypeService2(){
        String url="https://ncov.dxy.cn/ncovh5/view/pneumonia";
        
        //modify request
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpHost("ncov.dxy.cn");
        httpPojo.setHttpAccept("*/*");
        httpPojo.setHttpConnection("keep-alive");
        httpPojo.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
        httpPojo.setHttpReferer("https://ncov.dxy.cn");
        httpPojo.setHttpOrigin("https://ncov.dxy.cn");
        Map paramObj = new HashMap();
        String htmlResult = httpSendGet(url, paramObj, httpPojo); //whole HTML page
        //System.out.println(htmlResult);
 
        //get JSON statics
        String reg= "window.getListByCountryTypeService2 = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(htmlResult);
 
        String result="";
        if (totalMatcher.find()){
            result = totalMatcher.group(1);
            System.out.println(result);
            
            //global country list; demo
            /*JSONArray array = JSONArray.parseArray(result);
            JSONObject jsonObject = JSONObject.parseObject(array.getString(0));
            String provinceName = jsonObject.getString("continents");
            System.out.println("continents："+provinceName);*/
        }
        return result;
    }
 
 
 
 
/**
     * news
     * @return
     */
    public static String getTimelineService(){
        String url="https://ncov.dxy.cn/ncovh5/view/pneumonia";
        
        //modify request
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpHost("ncov.dxy.cn");
        httpPojo.setHttpAccept("*/*");
        httpPojo.setHttpConnection("keep-alive");
        httpPojo.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
        httpPojo.setHttpReferer("https://ncov.dxy.cn");
        httpPojo.setHttpOrigin("https://ncov.dxy.cn");
        Map paramObj = new HashMap();
        String htmlResult = httpSendGet(url, paramObj, httpPojo); //whole HTML page
        //System.out.println(htmlResult);
 
        //get JSON statics
        String reg= "window.getTimelineService = (.*?)\\}(?=catch)";
        Pattern totalPattern = Pattern.compile(reg);
        Matcher totalMatcher = totalPattern.matcher(htmlResult);
        
        String result="";
        if (totalMatcher.find()){
            result = totalMatcher.group(1);
            System.out.println(result);
            
            //Array list; demo
            /*JSONArray array = JSONArray.parseArray(result);
            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(array.getString(i));
                String title = jsonObject.getString("title");
                System.out.println("title："+title);
            }*/
 
        }
 
        return result;
    }
    
    
    /**
     * get news report history
     * @return
     */
    
    public static String getAllTimelineService(){
        String url="https://file1.dxycdn.com/2020/0130/492/3393874921745912795-115.json?"+Math.round(Math.random()*100000000);
        
        //modify requests
        HttpPojo httpPojo = new HttpPojo();
        httpPojo.setHttpHost("ncov.dxy.cn");
        httpPojo.setHttpAccept("*/*");
        httpPojo.setHttpConnection("keep-alive");
        httpPojo.setHttpUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36");
        httpPojo.setHttpReferer("https://ncov.dxy.cn/ncovh5/view/pneumonia_timeline?from=dxy&link=&share=&source=");
        httpPojo.setHttpOrigin("https://ncov.dxy.cn");
        Map paramObj = new HashMap();
        String htmlResult = httpSendGet(url, paramObj, httpPojo); //whole HTTP page
        System.out.println(htmlResult);
 
        //Array list; demo
        /*JSONObject resultJo = JSONObject.parseObject(htmlResult);
        String dataStr = resultJo.getString("data");
        JSONArray array = JSONArray.parseArray(dataStr);
        for (int i = 0; i < 5; i++) {
            JSONObject jsonObject = JSONObject.parseObject(array.getString(i));
            String title = jsonObject.getString("title");
            System.out.println("title："+title);
        }*/
 
 
        return htmlResult;
    }
 
 
 
    /**
     * HTTP request
     * @param url
     * @param paramObj
     * @param httpPojo
     * @return
     */
    private static String httpSendGet(String url, Map paramObj, HttpPojo httpPojo){
        String result = "";
        String urlName = url + "?" + parseParam(paramObj);
 
        BufferedReader in=null;
        try {
 
            URL realURL = new URL(urlName);
            URLConnection conn = realURL.openConnection();
            
            //fake IP access
            String ip = randIP();
            System.out.println("current fake ip："+ip);
            conn.setRequestProperty("X-Forwarded-For", ip);
            conn.setRequestProperty("HTTP_X_FORWARDED_FOR", ip);
            conn.setRequestProperty("HTTP_CLIENT_IP", ip);
            conn.setRequestProperty("REMOTE_ADDR", ip);
            conn.setRequestProperty("Host", httpPojo.getHttpHost());
            conn.setRequestProperty("accept", httpPojo.getHttpAccept());
            conn.setRequestProperty("connection", httpPojo.getHttpConnection());
            conn.setRequestProperty("user-agent", httpPojo.getHttpUserAgent());
            conn.setRequestProperty("Referer",httpPojo.getHttpReferer()); //fake IP access
            conn.setRequestProperty("Origin", httpPojo.getHttpOrigin()); //fake IP domain name
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            for (String s : map.keySet()) {
                //System.out.println(s + "-->" + map.get(s));
            }
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += "\n" + line;
            }
 
 
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in!=null){
                try {
                    in.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
 
            }
        }
        return result;
    }
 
 
    /**
     * analyze map
     * @param paramObj
     * @return
     */
    public static String parseParam(Map paramObj){
        String param="";
        if (paramObj!=null&&!paramObj.isEmpty()){
            for (Object key:paramObj.keySet()){
                String value = paramObj.get(key).toString();
                param+=(key+"="+value+"&");
 
            }
        }
        return param;
    }
 
    /**
     * fake IP
     * @return
     */
    public static String randIP() {
        Random random = new Random(System.currentTimeMillis());
        return (random.nextInt(255) + 1) + "." + (random.nextInt(255) + 1)
                + "." + (random.nextInt(255) + 1) + "."
                + (random.nextInt(255) + 1);
 
			 // TODO
		 }
	}

