package com.example.p;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar) ;
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout) ;


        NavigationView navigationView = findViewById(R.id.nav_view) ;
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this  , drawer ,
                toolbar ,  R.string.navigation_drawer_open , R.string.navigation_drawer_close ) ;

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PokemonsFragment()).commit();

            navigationView.setCheckedItem(R.id.nav_pokemon_name);
        }

    }



    @Override
    public void onBackPressed() {


        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        switch (Item.getItemId()) {
            case R.id.nav_pokemon_name:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new PokemonsFragment()).commit();
                break;
            case R.id.nav_locations:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new LocationsFragment()).commit();
                break;
            case R.id.nav_types:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,
                        new TypesFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

