package com.example.p;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class PokemonsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private PokemonItemAdapter mPokemonItemAdapter;
    private ArrayList<PokemonItem> mPokemonList;
    private RequestQueue mRequestQueue;
    private String mPokemonname;
    private String finalimageurl;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_pokemons, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.pokemon_list_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mPokemonList = new ArrayList<PokemonItem>();


        mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
        parseJSON();


        return view;

    }




    private void parseJSON() {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=900";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                final JSONObject result = jsonArray.getJSONObject(i);


                                String pokemonUrl = result.getString("url");


                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pokemonUrl, null,
                                        new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response1) {


                                                try {

                                                    String pokemonName = result.getString("name");
                                                    mPokemonname = pokemonName;
                                                    JSONObject sprites = response1.getJSONObject( "sprites" );
                                                    String front_image = sprites.getString("front_default");
                                                    finalimageurl = front_image;
                                                    mPokemonList.add(new PokemonItem(finalimageurl, mPokemonname));

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


                            mPokemonItemAdapter = new PokemonItemAdapter(getActivity(), mPokemonList);
                            mRecyclerView.setAdapter(mPokemonItemAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getActivity(), "errorbbbbbb", Toast.LENGTH_SHORT).show();
            }
        });

        mRequestQueue.add(request);


    }

}
