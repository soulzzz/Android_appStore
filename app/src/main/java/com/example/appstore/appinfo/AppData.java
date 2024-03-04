package com.example.appstore.appinfo;


import java.io.Serializable;

public class AppData implements Serializable {

    int id ; //apk的id
    int type; //类型；IOS:0, Android:1
    String icon; //icon路径
    String icon_uri; //icon uri路径
    String name; //apk名称
    String detail; //描述
    String short_chain; //短链

    String version_name; //版本名
    String version_code; //版本号
    String update_msg; //更新说明
    String size; //安装包大小
    String package_id; //安装包id
    String package_uri; //安装包uri路径
    double create_at; //创建时间
    String app_id; //app的id
    String package_name; //包名

    String background;
    String kind;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "AppData{" +
                "id=" + id +
                ", type=" + type +
                ", icon='" + icon + '\'' +
                ", icon_uri='" + icon_uri + '\'' +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", short_chain='" + short_chain + '\'' +
                ", version_name='" + version_name + '\'' +
                ", version_code='" + version_code + '\'' +
                ", update_msg='" + update_msg + '\'' +
                ", size='" + size + '\'' +
                ", package_id='" + package_id + '\'' +
                ", package_uri='" + package_uri + '\'' +
                ", create_at=" + create_at +
                ", app_id='" + app_id + '\'' +
                ", package_name='" + package_name + '\'' +
                ", background='" + background + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String geticon() {
        return icon;
    }

    public void seticon(String icon) {
        this.icon = icon;
    }

    public String geticon_uri() {
        return icon_uri;
    }

    public void seticon_uri(String icon_uri) {
        this.icon_uri = icon_uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getshort_chain() {
        return short_chain;
    }

    public void setshort_chain(String short_chain) {
        this.short_chain = short_chain;
    }

    public String getVersion_name() {
        return version_name;
    }

    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }

    public String getVersion_code() {
        return version_code;
    }

    public void setVersion_code(String version_code) {
        this.version_code = version_code;
    }

    public String getUpdate_msg() {
        return update_msg;
    }

    public void setUpdate_msg(String update_msg) {
        this.update_msg = update_msg;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getpackage_id() {
        return package_id;
    }

    public void setpackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getpackage_uri() {
        return package_uri;
    }

    public void setpackage_uri(String package_uri) {
        this.package_uri = package_uri;
    }

    public double getCreate_at() {
        return create_at;
    }

    public void setCreate_at(double create_at) {
        this.create_at = create_at;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getpackage_name() {
        return package_name;
    }

    public void setpackage_name(String package_name) {
        this.package_name = package_name;
    }
}
