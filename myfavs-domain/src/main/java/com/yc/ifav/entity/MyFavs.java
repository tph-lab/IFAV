package com.yc.ifav.entity;

public class MyFavs {
    private int mid;
    private int muid;
    private String mdate;
    private String mfname;
    private String mfurl;
    private String mfdesc;

    public MyFavs(int muid, String mfname, String mfurl, String mfdesc) {
        this.muid = muid;
        this.mfname = mfname;
        this.mfurl = mfurl;
        this.mfdesc = mfdesc;
    }

    public MyFavs(int mid, int muid, String mdate, String mfname, String mfurl, String mfdesc) {
        this.mid = mid;
        this.muid = muid;
        this.mdate = mdate;
        this.mfname = mfname;
        this.mfurl = mfurl;
        this.mfdesc = mfdesc;
    }

    public MyFavs() {
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getMuid() {
        return muid;
    }

    public void setMuid(int muid) {
        this.muid = muid;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMfname() {
        return mfname;
    }

    public void setMfname(String mfname) {
        this.mfname = mfname;
    }

    public String getMfurl() {
        return mfurl;
    }

    public void setMfurl(String mfurl) {
        this.mfurl = mfurl;
    }

    public String getMfdesc() {
        return mfdesc;
    }

    public void setMfdesc(String mfdesc) {
        this.mfdesc = mfdesc;
    }
}
