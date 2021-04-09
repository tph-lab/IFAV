package com.yc.ifav.service;


import com.yc.ifav.dao.TagMapper;
import com.yc.ifav.domain.TagDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TagServiceImpl implements TagService{

    @Autowired(required = false)
    private TagMapper tagMapper;


    @Transactional(readOnly = true)
    @Override
    public List<TagDomain> list() {
        //@TODO: 缓存
        //2.缓存没有，则在数据查
        List<TagDomain> list=tagMapper.searchAll();
        return list;
    }

    @Override
    public int update(String tname, int tid) {
        return tagMapper.update(tname,tid);
    }


    @Override
    public int add(TagDomain tagDomain) {
        return tagMapper.add(tagDomain);
    }

    @Override
    public int delete(int tid) {
        return tagMapper.delete(tid);
    }


    @Transactional(readOnly = true)
    //查询是否有该标签，如果没有就插入
    @Override
    public TagDomain findByTag(String tname) {
        return tagMapper.findByTag(tname);
    }
}
