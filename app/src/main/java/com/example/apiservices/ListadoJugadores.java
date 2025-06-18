package com.example.apiservices;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListadoJugadores extends AppCompatActivity {

    ListView lstJugadores;
    ArrayList<Jugador> jugadores = new ArrayList<>();
    String urlBase = "https://api.football-data.org/v4/teams/"; // + id + "/squad"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_jugadores);

        lstJugadores = findViewById(R.id.lstjugadores);
        String idEquipo = getIntent().getStringExtra("idEquipo");
        if (idEquipo != null) {
            String url = urlBase + idEquipo;
            obtenerDatos(url);
        } else {
            Toast.makeText(this, "No se recibió el ID del equipo", Toast.LENGTH_SHORT).show();
        }
    }

    private void obtenerDatos(String url) {
        RequestQueue cola = Volley.newRequestQueue(this);
        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray squad = response.getJSONArray("squad");
                        for (int i = 0; i < squad.length(); i++) {
                            JSONObject jugador = squad.getJSONObject(i);
                            String id = jugador.getString("id");
                            String nombre = jugador.getString("name");
                            String posicion = jugador.getString("position");
                            jugadores.add(new Jugador(id, nombre, posicion));
                        }
                        JugadorAdapter adapter = new JugadorAdapter(this, jugadores);
                        lstJugadores.setAdapter(adapter);
                    } catch (JSONException e) {
                        Toast.makeText(this, "Error al parsear JSON", Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(this, "Error en conexión: " + error.getMessage(), Toast.LENGTH_LONG).show()
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("X-Auth-Token", "df875ad8e5ac477cb91ca3687c170e6c");
                return headers;
            }
        };
        cola.add(peticion);
    }
}