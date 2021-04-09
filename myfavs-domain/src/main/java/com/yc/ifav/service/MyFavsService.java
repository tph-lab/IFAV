package com.yc.ifav.service;

import com.yc.ifav.domain.MyFavsDomain;
import com.yc.ifav.entity.MyFavs;

import java.util.List;

public interface MyFavsService {


    //显示所有
    public List<MyFavsDomain> list(int muid);

    //添加
    public int add(MyFavs favs);

    //删除
    public int delete(int mid);
}
