package com.example.p;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class PokemonDetailsActivity extends AppCompatActivity {

    String pokemonUrl;
    TextView t;
    ImageView img;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        pokemonUrl = getIntent().getStringExtra("pokemonUrl");
        t = findViewById(R.id.text);
        img = findViewById(R.id.imageView);
        mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getApplicationContext()));

        parseJSON();
    }

    private void parseJSON() {
        String url = pokemonUrl ;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int height = response.getInt("height");
                            int weight = response.getInt("weight");
                            int exp = response.getInt("base_experience");
                            t.setText("HEIGHT : " + height + " m\nWEIGHT : " + weight + "kg\nBASE_EXPERIENCE : " + exp );

                            String pokemonId = pokemonUrl.substring(34 , pokemonUrl.length() - 1);

                            String pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonId + ".png" ;
                            Picasso.with(getApplicationContext()).load(pokemonImageUrl).fit().centerInside().into(img);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mRequestQueue.add(request);

    }

}