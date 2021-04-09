package com.yc.ifav.entity;

import java.io.Serializable;

public class Myrecom implements Serializable {

    private static final long serialVersionUID = 5168141800845369640L;
    private  int g_id;
    private String g_date;
    private String g_fname;
    private String g_furl;
    private String g_fdesc;
    private int g_tid;

    public int getG_tid() {
        return g_tid;
    }

    public void setG_tid(int g_tid) {
        this.g_tid = g_tid;
    }

    private String gname;

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Myrecom() {
    }


    public Myrecom(int g_id, String g_date, String g_fname, String g_furl, String g_fdesc, int g_tid) {
        this.g_id = g_id;
        this.g_date = g_date;
        this.g_fname = g_fname;
        this.g_furl = g_furl;
        this.g_fdesc = g_fdesc;
        this.g_tid = g_tid;
    }

    public  Myrecom(int g_id, String g_date, String g_fname, String g_furl, String g_fdesc){
        this.g_id = g_id;
        this.g_date = g_date;
        this.g_fname=g_fname;
        this.g_furl = g_furl;
        this.g_fdesc = g_fdesc;

    }

    public int getG_id() {
        return g_id;
    }

    public void setG_id(int g_id) {
        this.g_id = g_id;
    }

    public String getG_date() {
        return g_date;
    }

    public void setG_date(String g_data) {
        this.g_date = g_date;
    }

    public String getG_fname() {
        return g_fname;
    }

    public void setG_fname(String g_fname) {
        this.g_fname = g_fname;
    }

    public String getG_furl() {
        return g_furl;
    }

    public void setG_furl(String g_furl) {
        this.g_furl = g_furl;
    }

    public String getG_fdesc() {
        return g_fdesc;
    }

    public void setG_fdesc(String g_fdesc) {
        this.g_fdesc = g_fdesc;
    }



}
