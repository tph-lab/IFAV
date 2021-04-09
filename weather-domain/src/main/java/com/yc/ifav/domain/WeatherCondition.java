package com.yc.ifav.domain;

public class WeatherCondition {
    private String AirTemperature; //气温
    private String WindDirectionAndForce; //风向/风力
    private String Humidity; //湿度

    public WeatherCondition(String airTemperature, String windDirectionAndForce, String humidity) {

        AirTemperature = airTemperature;
        WindDirectionAndForce = windDirectionAndForce;
        Humidity = humidity;

    }

    public String getAirTemperature() {
        return AirTemperature;
    }
    public void setAirTemperature(String airTemperature) {
        AirTemperature = airTemperature;
    }
    public String getWindDirectionAndForce() {
        return WindDirectionAndForce;
    }
    public void setWindDirectionAndForce(String windDirectionAndForce) {
        WindDirectionAndForce = windDirectionAndForce;
    }
    public String getHumidity() {
        return Humidity;
    }
    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    @Override
    public String toString() {
        return "WeatherCondition [AirTemperature=" + AirTemperature + ", WindDirectionAndForce=" + WindDirectionAndForce
                + ", Humidity=" + Humidity + "]";
    }
}
