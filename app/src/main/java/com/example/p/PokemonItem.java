package com.example.p;

import androidx.annotation.Nullable;

public class PokemonItem {
    private String mImageUrl;
    private String mPokemonName;
    private String mPokemonUrl;

    public PokemonItem(String mImageUrl, String mPokemonName, String mPokemonUrl) {
        this.mImageUrl = mImageUrl;
        this.mPokemonName = mPokemonName;
        this.mPokemonUrl = mPokemonUrl;
    }

//    public PokemonItem( String mPokemonName, String mPokemonUrl) {
//        this.mPokemonName = mPokemonName;
//        this.mPokemonUrl = mPokemonUrl;
//    }


    public PokemonItem(String ImageUrl , String PokemonName) {
        mImageUrl = ImageUrl;
        mPokemonName = PokemonName;
    }



    public PokemonItem(String PokemonName) {
        mPokemonName = PokemonName;
    }

    public String getmPokemonUrl() {
        return mPokemonUrl;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getPokemonName() {
        return mPokemonName;
    }
}
