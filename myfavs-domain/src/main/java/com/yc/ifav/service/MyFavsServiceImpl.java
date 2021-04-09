package com.yc.ifav.service;

import com.yc.ifav.dao.MyFavsMapper;
import com.yc.ifav.domain.MyFavsDomain;
import com.yc.ifav.entity.MyFavs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MyFavsServiceImpl implements MyFavsService{
    @Autowired(required = false)
    private MyFavsMapper myFavsMapper;

    @Transactional(readOnly = false)
    @Override
    public List<MyFavsDomain> list(int muid) {
        //@TODO: 缓存
        //2.缓存没有，则在数据查
        List<MyFavs> list=myFavsMapper.findByMyId(muid);

        //将实体类转为 domain
        //public MyFavsDomain(int mid, int muid, String mdate, String mfname, String mfurl, String mfdesc, String pic)
        List<MyFavsDomain> favDomains=new ArrayList<>();
        for (MyFavs fav: list){
            MyFavsDomain favDomain=new MyFavsDomain(fav.getMid(),fav.getMuid(),fav.getMdate(),fav.getMfname(),fav.getMfurl(),fav.getMfdesc()
            ,getIcon(fav.getMfurl()));
            favDomains.add(favDomain);
        }
        return favDomains;
    }

    @Override
    public int add(MyFavs favs) {
        return myFavsMapper.add(favs);
    }

    @Override
    public int delete(int mid) {
        return myFavsMapper.delete(mid);
    }

    public String getIcon(String url){
        //https://leetcode-cn.com/favicon.ico
        String []us=url.split("/");
        if(us.length<3){
            return "img/timg.jpg";
        }
        String Icon=us[0]+us[1]+us[2]+"/favicon.ico";
        return Icon;

//        URL url2=null;
//        HttpURLConnection con;
//        int state=-1;
//        try {
//            url2 = new URL(Icon);
//            con = (HttpURLConnection) url2.openConnection();
//            state = con.getResponseCode();
//            if (state == 200) {
//                return Icon;
//            }else {
//                Icon="img/timg.jpg";
//            }
//        }catch (Exception ex) {
//            url2 = null;
//        }
//        url2 = null;

    }
}
