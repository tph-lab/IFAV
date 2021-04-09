package com.yc.ifav.service;

import com.yc.ifav.dao.impl.UserMapper;
import com.yc.ifav.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        user.setUAvator("http://120.26.177.155/group1/M00/00/00/eBqxm1_r2E2AB1siAARlMEELfMc680.png");
        userMapper.insert(user);
    }

    @Override
    public User login(User user) {
        if(user.getUName()!=null&&user.getUPwd()!=null&&user.getUName()!=""&&user.getUPwd()!="") {
            Example example = new Example(User.class);   //条件
            Example.Criteria c = example.createCriteria();
            c.andEqualTo("uName",user.getUName());
            c.andEqualTo("uPwd",user.getUPwd());
            List<User> list=userMapper.selectByExample(example);
            if(list.size()>0){
                User user01=list.get(0);
                return user01;
            }
            return null;

        }else if(user.getUEmail()!=null&&user.getUEmail()!=""){
            return emreg(user);
        }
        return null;

    }


    public User emreg(User user){
        User user02=new User();
        user02.setUEmail(user.getUEmail());
        //结果只能返回一条数据否则会抛出异常
        return userMapper.selectOne(user02);

    }

    /**
     * 修改头像
     * @param user
     * @return
     */
    @Override
    public int updateAvator(User user) {
        return userMapper.updateByPrimaryKey(user);
    }


    @Override
    public User selectById(User user) {
        return userMapper.selectByPrimaryKey(user);
    }

}
