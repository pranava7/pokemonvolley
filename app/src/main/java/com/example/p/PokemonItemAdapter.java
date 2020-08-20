package com.example.p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PokemonItemAdapter extends RecyclerView.Adapter<PokemonItemAdapter.PokemonItemViewHolder> {

    private Context mContext ;
    private ArrayList<PokemonItem> mPokemonItemList ;

    public PokemonItemAdapter(Context context , ArrayList<PokemonItem> PokemonItemList) {
        mContext = context;
        mPokemonItemList = PokemonItemList;
    }

    @NonNull
    @Override
    public PokemonItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.pokemon_item , parent , false);
        return new PokemonItemViewHolder(v);
    }


    @Override
    public void onBindViewHolder(PokemonItemViewHolder holder, int position) {
        PokemonItem currentItem = mPokemonItemList.get(position);

        String pokemonImageUrl = currentItem.getImageUrl();
        String pokemonnName = currentItem.getPokemonName();

        holder.mPokemonNameTextView.setText(pokemonnName);

        Picasso.with(mContext).load(pokemonImageUrl).fit().centerInside().into(holder.mPokemonImageView);
    }

    @Override
    public int getItemCount() {
        return mPokemonItemList.size();
    }

    public static class PokemonItemViewHolder extends RecyclerView.ViewHolder {

        public ImageView mPokemonImageView ;
        public TextView mPokemonNameTextView ;

        public PokemonItemViewHolder(View itemView) {
            super(itemView);
            mPokemonImageView = itemView.findViewById(R.id.pokemon_image);
            mPokemonNameTextView = itemView.findViewById(R.id.pokemon_name);
        }
    }
}
