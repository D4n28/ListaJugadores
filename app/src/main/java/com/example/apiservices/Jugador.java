package com.example.apiservices;

import android.os.Parcel;
import android.os.Parcelable;

public class Jugador implements Parcelable {
    private String id;
    private String nombre;
    private String posicion;

    public Jugador(String id, String nombre, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.posicion = posicion;
    }

    protected Jugador(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        posicion = in.readString();
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPosicion() { return posicion; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(posicion);
    }
}