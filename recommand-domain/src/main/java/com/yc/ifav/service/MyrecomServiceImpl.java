package com.yc.ifav.service;


import com.yc.ifav.dao.RecomMapper;
import com.yc.ifav.domain.RecomDomain;
import com.yc.ifav.entity.Myrecom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MyrecomServiceImpl implements  MyrecomService{

    @Autowired(required = false)
    private RecomMapper myrecomMapper;


    @Transactional(readOnly = false)
    @Override
    public List<RecomDomain> list(int muid){
        //@TOOD 缓存
        //缓存没有 则在数据查
        List<Myrecom> list = myrecomMapper.findByMyId(muid);

        //将实体转化为domain
        //public
        List<RecomDomain> recomDomains = new ArrayList<>();
        for(Myrecom recom:list){
            RecomDomain recomDomain = new RecomDomain(recom.getG_id(),recom.getG_date(),recom.getG_fname(),recom.getG_furl(),recom.getG_fdesc());
            recomDomains.add(recomDomain);
        }
        return  recomDomains;


    }

    @Override
    public int add(Myrecom myrecom){
        return myrecomMapper.add(myrecom);
    }

    @Override
    public int delete(int mid){
        return myrecomMapper.delete(mid);

    }




    //显示所有
    @Override
    public List<Myrecom> findAll() {
        return myrecomMapper.findAll();
    }

    //根据标签名查询网址
    @Override
    public List<Myrecom> findByMyId(int g_tid) {
        return myrecomMapper.findByMyId(g_tid);
    }




}
