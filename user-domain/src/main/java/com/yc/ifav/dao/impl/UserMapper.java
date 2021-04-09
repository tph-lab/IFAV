package com.yc.ifav.dao.impl;

import com.yc.ifav.dao.MisBaseMapper;
import com.yc.ifav.entity.User;
import org.apache.ibatis.annotations.Mapper;


//记得加，
@Mapper  //具体操作某张表的mapper           //注意不能跟MisBaseMapper在同一个包下面
//具体的接口，注意要传泛型过去
public interface UserMapper extends MisBaseMapper<User> {

}
