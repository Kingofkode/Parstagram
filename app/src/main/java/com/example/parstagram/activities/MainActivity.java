package com.example.parstagram.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.parstagram.R;
import com.example.parstagram.databinding.ActivityMainBinding;
import com.example.parstagram.fragments.ComposeFragment;
import com.example.parstagram.fragments.PostsFragment;
import com.example.parstagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";

  ActivityMainBinding binding;

  final FragmentManager fragmentManager = getSupportFragmentManager();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);
    // Done with view binding

    setupNavigationView();
  }

  private void setupNavigationView() {

    binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
      @Override
      public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment;
        switch (menuItem.getItemId()) {
          case R.id.action_compose:
            // Compose tab
            fragment = new ComposeFragment();
            break;
          case R.id.action_profile:
            // Profile tab
            fragment = new ProfileFragment();
            break;
          default:
            // Home tab
            fragment = new PostsFragment();
            break;
        }
        fragmentManager.beginTransaction().replace(binding.flContainer.getId(), fragment).commit();
        return true;
      }
    });

    // Set default selection
    binding.bottomNavigation.setSelectedItemId(R.id.action_home);
  }

}