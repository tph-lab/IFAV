package com.yc.ifav.contollers;

import com.google.gson.Gson;
import com.yc.ifav.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/weather")
public class WeatherRestController {
    @Autowired
    private WeatherService weatherService;

    @RequestMapping(value = "/getWeather", method = RequestMethod.POST)
    public CompletableFuture<String> get(@RequestParam("province") String province,@RequestParam("city") String city) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<String, Object> map = new HashMap<>();
                map.put("code", 1);
                map.put("data",weatherService.getWeather(province, city));
                return new Gson().toJson(map);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

}
