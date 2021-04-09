package com.yc.ifav.dao;



import com.yc.ifav.entity.MyFavs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MyFavsMapper {

    @Select("select m_id,m_uid,m_date,m_fname,m_furl,m_fdesc from myfavs " +
            "where m_uid=#{muid}")
    public List<MyFavs> findByMyId(int muid);

    @Insert("insert myfavs values(null,#{muid},now(),#{mfname},#{mfurl},#{mfdesc})")
    public int add(MyFavs myFavs);

    @Delete("delete  from myfavs where m_id=#{mid} ")
    public int delete(int mid);


}
