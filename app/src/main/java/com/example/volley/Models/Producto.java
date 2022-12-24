package com.example.volley.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Producto {
    String id;
    String nombre;
    String clase;
    String precioUnitario;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getclase() {
        return clase;
    }

    public void setclase(String clase) {
        this.clase = clase;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
    
    public Producto(JSONObject a) throws JSONException {
        id =  a.getString("id").toString();
        nombre =  a.getString("descripcion").toString() ;
        clase =  a.getString("clase").toString() ;
        precioUnitario = a.getString("precio_unidad").toString() ;
    }

    public static ArrayList<Producto> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Producto> productos = new ArrayList<>();

        for (int i = 0; i < datos.length() && i<20; i++) {
            productos.add(new Producto(datos.getJSONObject(i)));
        }
        return productos;
    }
}
