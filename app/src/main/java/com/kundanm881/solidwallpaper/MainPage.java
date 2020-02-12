package com.kundanm881.solidwallpaper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.kundanm881.solidwallpaper.frags.AnimalFragment;
import com.kundanm881.solidwallpaper.frags.ArtFragment;
import com.kundanm881.solidwallpaper.frags.NatureFragment;

public class MainPage extends AppCompatActivity {
    private MaterialToolbar toolbar;
    private DrawerLayout drawerLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
        mAuth = FirebaseAuth.getInstance();
        toolbar = findViewById(R.id.MainToolbar);
        drawerLayout = findViewById(R.id.drawer_Layout);
        NavigationView navigationView = findViewById(R.id.navigation);

        ToolbarFun();
        navigationView.setNavigationItemSelectedListener(this::Navigation_Menu_Item);

        if (savedInstanceState == null) {
            getSupportActionBar().setTitle("Art");
            navigationView.setCheckedItem(R.id.menu_art);
            getSupportFragmentManager().beginTransaction().replace(R.id.Freame_Ctr, new ArtFragment()).commit();
        }
    }

    private boolean Navigation_Menu_Item(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_art:
                getSupportActionBar().setTitle("Art");
                getSupportFragmentManager().beginTransaction().replace(R.id.Freame_Ctr, new ArtFragment()).commit();
                break;
            case R.id.menu_Animal:
                getSupportActionBar().setTitle("Animal");
                getSupportFragmentManager().beginTransaction().replace(R.id.Freame_Ctr, new AnimalFragment()).commit();
                break;
            case R.id.menu_nature:
                getSupportActionBar().setTitle("Nature");
                getSupportFragmentManager().beginTransaction().replace(R.id.Freame_Ctr, new NatureFragment()).commit();
                break;
            case R.id.menu_settings:
                Toast.makeText(this, "seting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_Logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ToolbarFun() {
        setSupportActionBar(toolbar);
        //Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.Nav_drawer_Open,
                R.string.Nav_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
