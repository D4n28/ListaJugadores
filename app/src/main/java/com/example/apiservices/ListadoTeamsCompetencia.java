package com.example.apiservices;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class ListadoTeamsCompetencia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Teams> te = new ArrayList<>();
    private TeamsAdapter adapter;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_teams_competencia);

        recyclerView = findViewById(R.id.lstteams);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent i = getIntent();
        String code = i.getStringExtra("code");
        String fundadoFiltro = i.getStringExtra("fundado");

        url = "https://api.football-data.org/v4/competitions/" + code + "/teams";

        requestDatos(fundadoFiltro);  // ahora pasamos fundado como parámetro
    }

    public void requestDatos(String fundadoFiltro) {
        RequestQueue cola = Volley.newRequestQueue(this);

        JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> parserJson(response, fundadoFiltro),
                error -> Toast.makeText(getApplicationContext(), "Error en la conexión: " + error.getMessage(), Toast.LENGTH_LONG).show()
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

    public void parserJson(JSONObject response, String fundadoFiltro) {
        try {
            JSONArray teams = response.getJSONArray("teams");
            for (int i = 0; i < teams.length(); i++) {
                JSONObject com = teams.getJSONObject(i);
                String id = com.getString("id");
                String nombre = com.getString("name");
                String web = com.optString("website", "N/A");
                String fundado = com.optString("founded", "N/A");

                // Filtramos por año de fundación
                if (fundado.equals(fundadoFiltro)) {
                    Teams t = new Teams(id, nombre, web, fundado);
                    te.add(t);
                }
            }

            if (!te.isEmpty()) {
                adapter = new TeamsAdapter(te);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(this, "No hay equipos con ese año de fundación", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error al procesar JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
