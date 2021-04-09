package com.yc.ifav.future;

import com.yc.ifav.service.WeatherRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class WeatherFuture {

    @Autowired(required = false)
    private WeatherRestService weatherRestService;

    @Async
    public CompletableFuture<String> get(String province,String city) {
        return CompletableFuture.supplyAsync(() -> {
            return weatherRestService.get(province,city);
        });
    }
}
