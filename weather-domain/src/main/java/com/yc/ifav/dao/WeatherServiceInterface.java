package com.yc.ifav.dao;

import java.util.List;

public interface WeatherServiceInterface {
	//根据城市名和省名直接获取该城市的天气
	public List<String> getWeather(String provinceName,String cityname);
	public List<String> getWeatherList();
	public int getCityNode();
	public int getProvinceCode();
}
