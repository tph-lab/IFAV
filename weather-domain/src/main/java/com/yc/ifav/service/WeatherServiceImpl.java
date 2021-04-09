package com.yc.ifav.service;

import com.yc.ifav.domain.WeatherDomain;
import com.yc.ifav.util.RedisClient;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl implements WeatherService{


    @Override
    public WeatherDomain getWeather(String province, String city) {
        WeatherDomain w=new WeatherDomain(province,city);
        return w;


    }
}
