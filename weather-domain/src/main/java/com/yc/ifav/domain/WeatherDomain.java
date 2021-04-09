package com.yc.ifav.domain;

import com.yc.ifav.dao.WeatherServiceIml;
import com.yc.ifav.dao.WeatherServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class WeatherDomain {
    private String province;
    private String city;

    private String cityCode;
    private String nowDate;

    private WeatherCondition weatherCondition;//今日天气状况

    private String  ultravioletLight;//紫外线强度

    private String HeatstrokeIndex;//中国人名保险中暑指数

    private String BlooGlucoseIndex;//血糖指数

    private String dressingIndex;//穿衣指数

    private String carWashIndex;//洗车指数

    private String ultravioletIndex;//紫外线指数

    private List<WeatherFuture> weatherFutureList;//未来几天天气

    private WeatherServiceInterface weatherService;

    private List<String> list;

    private int count=1;


    public WeatherDomain() {
        super();
    }




    public WeatherDomain(String province,String city) {

        this.province = province;
        this.city = city;
        this.weatherService=new WeatherServiceIml(province,city);
        list=weatherService.getWeatherList();

        if(list.size()<=1 || list.isEmpty()) {
            throw new NullPointerException("查无此城市或该城市无法被查询到");
        }

        this.cityCode=list.get(++count);
        this.nowDate=list.get(++count);

        String w[]=list.get(++count).split("；");
        String T=w[0].split("：")[2];
        String W=w[1].split("：")[1];
        String H=w[2].split("：")[1];

        this.weatherCondition=new WeatherCondition(T, W, H);
        this.ultravioletLight=qudou(list.get(++count));

        String zhishu=list.get(++count);
        String []gexiangzhishu=zhishu.split("\n");

        int cube=0;
        if(gexiangzhishu.length>0) {
            this.HeatstrokeIndex=qudou(gexiangzhishu[cube<gexiangzhishu.length ? cube:0]);
            this.BlooGlucoseIndex=qudou(gexiangzhishu[++cube<gexiangzhishu.length ? cube:0]);
            this.dressingIndex=qudou(gexiangzhishu[++cube<gexiangzhishu.length ? cube:0]);
            this.carWashIndex=qudou(gexiangzhishu[++cube<gexiangzhishu.length ? cube:0]);
            this.ultravioletIndex=qudou(gexiangzhishu[++cube<gexiangzhishu.length ? cube:0]);
        }

        this.weatherFutureList=getWeatherFutureList(list);
    }


    //去掉逗号
    private String qudou(String s) {
        String []k=s.split("：");
        return k.length==2? k[1].replace("。", ""):k[0].replace("。", "");
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getUltravioletLight() {
        return ultravioletLight;
    }

    public void setUltravioletLight(String ultravioletLight) {
        this.ultravioletLight = ultravioletLight;
    }

    public String getHeatstrokeIndex() {
        return HeatstrokeIndex;
    }

    public void setHeatstrokeIndex(String heatstrokeIndex) {
        HeatstrokeIndex = heatstrokeIndex;
    }

    public String getBlooGlucoseIndex() {
        return BlooGlucoseIndex;
    }

    public void setBlooGlucoseIndex(String blooGlucoseIndex) {
        BlooGlucoseIndex = blooGlucoseIndex;
    }

    public String getDressingIndex() {
        return dressingIndex;
    }

    public void setDressingIndex(String dressingIndex) {
        this.dressingIndex = dressingIndex;
    }

    public String getCarWashIndex() {
        return carWashIndex;
    }

    public void setCarWashIndex(String carWashIndex) {
        this.carWashIndex = carWashIndex;
    }

    public String getUltravioletIndex() {
        return ultravioletIndex;
    }

    public void setUltravioletIndex(String ultravioletIndex) {
        this.ultravioletIndex = ultravioletIndex;
    }


    //获取未来几天的天气
    public List<WeatherFuture> getWeatherFutureList(List<String> list) {
        WeatherFuture w;
        List<WeatherFuture> listWF=new ArrayList<WeatherFuture>();
        for(int k=7;k<list.size();) {
            w=new WeatherFuture(list.get(k++), list.get(k++), list.get(k++), list.get(k++), list.get(k++));
            listWF.add(w);
            w=null;
        }
        return listWF;
    }

    @Override
    public String toString() {
        return "WeatherBean [province=" + province + ", city=" + city + ", cityCode=" + cityCode + ", nowDate="
                + nowDate + ", weatherCondition=" + weatherCondition + ", ultravioletLight=" + ultravioletLight
                + ", HeatstrokeIndex=" + HeatstrokeIndex + ", BlooGlucoseIndex=" + BlooGlucoseIndex + ", dressingIndex="
                + dressingIndex + ", carWashIndex=" + carWashIndex + ", ultravioletIndex=" + ultravioletIndex
                + ", weatherFutureList=" + weatherFutureList + "]";
    }

}
