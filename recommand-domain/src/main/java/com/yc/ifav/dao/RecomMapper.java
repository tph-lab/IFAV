package com.yc.ifav.dao;



import com.yc.ifav.entity.Myrecom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecomMapper {

    //根据标签名来查询网址
    @Select("select g_id,g_date,g_fname,g_furl,g_fdesc from gfavs " +
            "where g_tid=#{g_tid}")
    public List<Myrecom> findByMyId(int g_tid);

    //查询所有网址
    @Select("select g_id,g_date,g_fname,g_furl,g_fdesc from gfavs ")
    public List<Myrecom> findAll();

    //添加新网址
    @Insert("insert into gfavs values(#{g_id},#{g_date},#{g_fname},#{g_furl},#{g_fdesc},#{g_tid})")
    public int add(Myrecom myrecom);





    @Delete("delete  from gfavs where g_id=#{g_id} ")
    public int delete(int mid);


}
