package com.example.parstagram.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.parstagram.Post;
import com.example.parstagram.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

  ActivityDetailBinding binding;

  Post post;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityDetailBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    // Done with view binding


  }
}