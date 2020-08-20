package com.example.p;

import androidx.annotation.Nullable;

public class PokemonItem {
    private String mImageUrl;
    private String mPokemonName;

    public PokemonItem(String ImageUrl , String PokemonName) {
        mImageUrl = ImageUrl;
        mPokemonName = PokemonName;
    }

    public PokemonItem(String PokemonName) {
        mPokemonName = PokemonName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getPokemonName() {
        return mPokemonName;
    }
}
