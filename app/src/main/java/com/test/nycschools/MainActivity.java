package com.test.nycschools;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

import android.os.Bundle;

import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.test.nycschools.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    public NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolBar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navHostFragment);
        navController = navHostFragment.getNavController();
        setupActionBarWithNavController(this, navController);

        navController.addOnDestinationChangedListener((navController, destination, bundle) -> {
            if (destination.getId() == com.test.nycschools.home.R.id.schoolDetailsFragment) {
                getSupportActionBar().setTitle(bundle.getString("schoolName"));
            } else if (destination.getId() == com.test.nycschools.home.R.id.homeFragment) {
                getSupportActionBar().setTitle(getString(R.string.app_name));
            }
        });

        /* Given more I would handle back press of toolbar */
    }
}