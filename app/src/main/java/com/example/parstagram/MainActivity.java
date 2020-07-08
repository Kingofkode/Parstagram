package com.example.parstagram;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.parstagram.databinding.ActivityMainBinding;
import com.example.parstagram.fragments.ComposeFragment;
import com.example.parstagram.fragments.PostsFragment;
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
          case R.id.action_home:
            // TODO: Update fragment
            fragment = new PostsFragment();
            break;
          case R.id.action_compose:
            fragment = new ComposeFragment();
            break;
          case R.id.action_profile:
            // TODO: Update fragment
            fragment = new ComposeFragment();
            break;
          default:
            // TODO: Update fragment
            fragment = new ComposeFragment();
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