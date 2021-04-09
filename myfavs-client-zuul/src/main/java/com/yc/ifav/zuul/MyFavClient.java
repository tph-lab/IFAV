package com.yc.ifav.zuul;

import com.yc.ifav.entity.MyFavs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="BASE-IFAV-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)
public interface MyFavClient {
    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myfav-proxy/myfavs/findById",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("muid") int muid);


    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/myfav-proxy/myfavs/add",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String create(@RequestBody MyFavs fav);

    @RequestMapping(method = RequestMethod.DELETE, value = "/yc-api/myfav-proxy/myfavs/delete")
    String delete(@RequestParam("mid") int mid);
}