package com.example.p;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agrawalsuneet.dotsloader.loaders.AllianceLoader;
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
    private ArrayList<PokemonItem> mPokemonList = null;
    private RequestQueue mRequestQueue;
    private String mPokemonname;
    static String pokemonUrl;

    AllianceLoader allianceLoader ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_pokemons, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.pokemon_list_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allianceLoader = view.findViewById(R.id.progressLoader);

        mPokemonList = new ArrayList<PokemonItem>();


        mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
//        if (mPokemonList == null) {
//            parseJSON();
//        } else {
//            mPokemonItemAdapter = new PokemonItemAdapter(getActivity(), mPokemonList);
//            mRecyclerView.setAdapter(mPokemonItemAdapter);
//        }
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
                            allianceLoader.setVisibility(View.GONE);

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);


                                pokemonUrl = result.getString("url");
                                String pokemonName = result.getString("name");
                                String pokemonId = pokemonUrl.substring(34 , pokemonUrl.length() - 1);

                                String pokemonImageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonId + ".png" ;
                                mPokemonList.add(new PokemonItem(pokemonImageUrl , pokemonName , pokemonUrl));
//                                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, pokemonUrl, null,
//                                        new Response.Listener<JSONObject>() {
//                                            @Override
//                                            public void onResponse(JSONObject response1) {
//
//
//                                                try {
//
//                                                    String pokemonName = result.getString("name");
//                                                    mPokemonname = pokemonName;
//                                                    JSONObject sprites = response1.getJSONObject( "sprites" );
//                                                    String front_image = sprites.getString("front_default");
//                                                    finalimageurl = front_image;
//                                                    mPokemonList.add(new PokemonItem(finalimageurl, mPokemonname));
//
//                                                } catch (JSONException e) {
//                                                    e.printStackTrace();
//                                                }
//
//                                            }
//                                        }, new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//                                        error.printStackTrace();
//                                    }
//                                })
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
