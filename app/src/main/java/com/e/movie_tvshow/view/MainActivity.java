package com.e.movie_tvshow.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.e.movie_tvshow.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bn_main);
        loadFragment(new MovieFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.film_menu:
                fragment = new MovieFragment();
                break;
            case R.id.tv_menu:
                fragment = new TvFragment();
                break;
            case R.id.favorite_menu:
                fragment = new FavoriteFragment();
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Confirm Exit");
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        alertDialogBuilder.setMessage("Apa anda yakin ingin keluar dari aplikasi ?");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setPositiveButton("Ya", (arg0, arg1) -> {
            finish();
        });

        alertDialogBuilder.setNegativeButton("Tidak", (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}