package com.yc.ifav.service;

import com.yc.ifav.domain.TagDomain;

import java.util.List;

public interface TagService {

    //查询所有的Tag
    public List<TagDomain> list();

    //修改书签
    public int update(String tname,int tid);

    //添加书签
    public int add(TagDomain tagDomain);

    //删除书签
    public int delete(int tid);

    public TagDomain findByTag(String tname);



}
