package com.yc.ifav.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class WeatherServiceIml implements WeatherServiceInterface {


	private final static String SERVICES_HOST = "www.webxml.com.cn";
	private final static String WEATHER_SERVICES_URL = "http://www.webxml.com.cn/WebServices/WeatherWS.asmx/";
	private final static String PROVINCE_CODE_URL = WEATHER_SERVICES_URL + "getRegionProvince";
	private final static String CITY_CODE_URL = WEATHER_SERVICES_URL + "getSupportCityString?theRegionCode=";
	private final static String WEATHER_QUERY_URL = WEATHER_SERVICES_URL + "getWeather?theUserID=&theCityCode=";

	private List<String> weatherList;
	private int provinceCode;
	private int cityNode;

	//直接获取城市信息,
	public  WeatherServiceIml(String provinceName,String cityName) {

		int pcode=this.getProvinceCode(provinceName);
		int ccode=this.getCityCode(pcode, cityName);
		this.getWeather(ccode);
	}


	@Override
	public List<String> getWeather(String provinceName, String cityName) {

		int pcode=this.getProvinceCode(provinceName);
		int ccode=this.getCityCode(pcode, cityName);
		return this.getWeather(ccode);
	}


	private int getProvinceCode(String provinceName){
		int provinceCode=0;
		Document document;
		InputStream inputStream=getSoapInputStream(PROVINCE_CODE_URL);
		try {
			document=setParse(inputStream);
			NodeList nodeList=document.getElementsByTagName("string");
			provinceCode= getCode(nodeList, provinceName);
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.provinceCode=provinceCode;
		return provinceCode;
	}

	private int getCityCode(int provinceCode, String cityName) {
		int cityCode=0;
		Document document;
		InputStream inputStream=getSoapInputStream(CITY_CODE_URL+provinceCode);
		try {
			document=setParse(inputStream);
			NodeList nodeList=document.getElementsByTagName("string");
			cityCode=getCode(nodeList, cityName);
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.cityNode=cityCode;
		return cityCode;
	}

	private List<String> getWeather(int cityCode) {
		List<String> weatherList=new ArrayList<String>();
		Document document;
		InputStream inputStream=getSoapInputStream(WEATHER_QUERY_URL+cityCode);
		try {
			document=setParse(inputStream);
			NodeList nodeList=document.getElementsByTagName("string");
			int length=nodeList.getLength();
			for(int i=0;i<length;i++) {
				Node n=nodeList.item(i);
				String weather=n.getFirstChild().getNodeValue();
				weatherList.add(weather);

			}
			inputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setWeatherList(weatherList);
		return weatherList;
	}



	//得到soap
	private InputStream getSoapInputStream(String url) {
		InputStream inputStream=null;
		try {
			URL urlObj=new URL(url);
			URLConnection urlConnection=urlObj.openConnection();
			urlConnection.setRequestProperty("Host",SERVICES_HOST);
			urlConnection.connect();
			inputStream=urlConnection.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}


	private static Document setParse(InputStream inputStream) throws Exception {
		Document document;
		DocumentBuilderFactory documentBuilderFactory=DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
		document=documentBuilder.parse(inputStream);
		return document;
	}

	private static int getCode(NodeList nodelist,String name) {
		int code=0;
		int length=nodelist.getLength();
		for(int i=0;i<length;i++) {
			Node n=nodelist.item(i);
			String result=n.getFirstChild().getNodeValue();
			String []address=result.split(",");
			String pName=address[0];
			String pCode=address[1];
			if(pName.equalsIgnoreCase(name)) {
				code=Integer.parseInt(pCode);
			}
		}
		return code;
	}

	@Override
	public List<String> getWeatherList() {
		return weatherList;
	}

	private void setWeatherList(List<String> weatherList) {
		this.weatherList = weatherList;
	}

	@Override
	public int getProvinceCode() {
		return provinceCode;
	}


	@Override
	public int getCityNode() {
		return cityNode;
	}


}
