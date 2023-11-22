package com.example.proyecto_integrador.Domains;

public class InformacionDomain {
    private String picPath;

    private String name;

    private String other;

    public InformacionDomain(String picPath, String name, String other) {
        this.picPath = picPath;
        this.name = name;
        this.other = other;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
