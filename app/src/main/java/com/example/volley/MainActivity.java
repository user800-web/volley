package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.volley.Adaptadores.AdaptorProductos;
import com.example.volley.Models.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView lstProductos;

    RequestQueue requestCola;
    JsonObjectRequest jsonObjectRequest;
    public String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstProductos = (ListView)findViewById(R.id.lstProductos);
        requestCola = Volley.newRequestQueue(this);
        requestCola.start();
        obtenerToken();
    }

    public void obtenerToken(){
        String url = "https://api.uealecpeterson.net/public/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        try {
                            String recortado= response.substring(5, response.length());
                            //Toast.makeText(MainActivity.this, recortado, Toast.LENGTH_LONG).show();
                            JSONObject jsonObject =  new JSONObject(recortado);
                            token= jsonObject.getString("access_token");
                            devolverProduct(token);
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "No se pudo conventir el json", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("correo", "czambrano@uteq.edu.ec");
                params.put("clave", "12345678");
                params.put("fuente", "1");
                return params;
            }

        };
        requestCola.add(stringRequest);
    }
    public void devolverProduct(String token){
        String url ="https://api.uealecpeterson.net/public/productos/search";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject JSONlista = null;
                        try {
                            JSONlista = new JSONObject(response);
                            JSONArray JSONlistaProductos=  JSONlista.getJSONArray("productos");

                            ArrayList<Producto> LstProduct = Producto.JsonObjectsBuild(JSONlistaProductos);

                            com.example.volley.Adaptadores.AdaptorProductos adapatorProducto = new com.example.volley.Adaptadores.AdaptorProductos(MainActivity.this, LstProduct);

                            lstProductos.setAdapter(adapatorProducto);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError{
                Map<String,String> params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + token);
                return params;
            }
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("fuente", "1");
                return params;
            }

        };
        requestCola.add(stringRequest);
    }

}