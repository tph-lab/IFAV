package com.yc.ifav.service;

import com.yc.ifav.domain.WeatherDomain;

public interface WeatherService {
    public WeatherDomain getWeather(String province,String city);
}
