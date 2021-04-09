package com.yc.ifav.future;


import com.yc.ifav.entity.MyFavs;
import com.yc.ifav.service.MyFavsRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class MyFavsFuture {

    @Autowired(required = false)
    private MyFavsRestService favRestService;

    @Async
    public CompletableFuture<String> findAll(int muid) {
        return CompletableFuture.supplyAsync(() -> {
            //return tagService.list();
            return favRestService.findAll(muid);
        });
    }

    @Async
    public CompletableFuture<String> create(MyFavs fav) {
        return CompletableFuture.supplyAsync(() -> {
            return favRestService.create(fav);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return favRestService.delete(id);
        });
    }
}
