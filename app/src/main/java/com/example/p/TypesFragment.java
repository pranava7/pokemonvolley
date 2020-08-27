package com.example.p;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.sql.Types;
import java.util.ArrayList;
import java.util.Objects;

public class TypesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private TypesAdapter mTypesAdapter;
    private ArrayList<TypesItem> mTypesList = null;
    private RequestQueue mRequestQueue;
    private String mTypesname;
    static String mTypesUrl;
    AllianceLoader allianceLoader;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_types, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.pokemon_list_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        allianceLoader = view.findViewById(R.id.progressLoader);
        mTypesList = new ArrayList<TypesItem>();


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
        String url = "https://pokeapi.co/api/v2/type" ;

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            allianceLoader.setVisibility(View.GONE);
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0  ; i < jsonArray.length() ; i++) {
                                JSONObject result = jsonArray.getJSONObject(i);

                                mTypesUrl = result.getString("url");
                                mTypesname = result.getString("name");

                                mTypesList.add(new TypesItem(mTypesname , mTypesUrl));
                            }

                            mTypesAdapter = new TypesAdapter(mTypesList , getActivity());
                            mRecyclerView.setAdapter(mTypesAdapter);
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
