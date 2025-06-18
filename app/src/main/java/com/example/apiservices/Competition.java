package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Competition implements Parcelable {
    private String id;
    private String name;
    private String web;
    private String code;
    private String fundado;

    public Competition(String id, String name, String web, String fundado, String code) {
        this.id = id;
        this.name = name;
        this.web = web;
        this.fundado = fundado;
        this.code = code;
    }

    protected Competition(Parcel in) {
        id = in.readString();
        name = in.readString();
        web = in.readString();
        fundado = in.readString();
        code = in.readString(); // <- ¡importante que este esté aquí también!
    }

    public static final Creator<Competition> CREATOR = new Creator<Competition>() {
        @Override
        public Competition createFromParcel(Parcel in) {
            return new Competition(in);
        }

        @Override
        public Competition[] newArray(int size) {
            return new Competition[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb() {
        return web;
    }
    public void setWeb(String web) {
        this.web = web;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getFundado() {
        return fundado;
    }
    public void setFundado(String fundado) {
        this.fundado = fundado;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(web);
        dest.writeString(fundado);
        dest.writeString(code);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", web='" + web + '\'' +
                ", fundado='" + fundado + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}