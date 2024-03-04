package com.example.appstore.appinfo;


import java.util.List;

public class DataList {
    int code;
    List<AppData> datas;
    String msg;
    boolean ok;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<AppData> getDatas() {
        return datas;
    }

    public void setDatas(List<AppData> datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    @Override
    public String toString() {
        return "DataList{" +
                "code=" + code +
                ", datas=" + datas +
                ", msg='" + msg + '\'' +
                ", ok=" + ok +
                '}';
    }
}
