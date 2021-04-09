package com.yc.ifav.future;

import com.yc.ifav.domain.TagDomain;
import com.yc.ifav.service.TagRestService;
import com.yc.ifav.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class TagFuture {
    @Autowired
    private TagRestService tagRestService;
//    private PiclibRestService piclibRestService;   //业务层

//    @Async   //异步调用
//    public CompletableFuture<String> findById(Integer id) {
//        return CompletableFuture.supplyAsync(() -> {
//            System.out.println("=========================================");
//            return tagService.
//        });
//    }

    @Async
    public CompletableFuture<String> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            //return tagService.list();
            return tagRestService.findAll();
        });
    }

    @Async
    public CompletableFuture<String> create(TagDomain tagDomain) {
        return CompletableFuture.supplyAsync(() -> {
            return tagRestService.create(tagDomain);
        });
    }


    @Async
    public CompletableFuture<String> delete(Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return tagRestService.delete(id);
        });
    }
}
