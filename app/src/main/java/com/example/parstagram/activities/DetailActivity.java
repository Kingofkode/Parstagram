package com.example.parstagram.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.parstagram.Post;
import com.example.parstagram.Utils;
import com.example.parstagram.databinding.ActivityDetailBinding;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

  ActivityDetailBinding binding;

  Post post;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityDetailBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    // Done with view binding
    post = Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));

    binding.tvUsername.setText(post.getUser().getUsername());
    binding.tvDescription.setText(post.getDescription());
    binding.tvDate.setText(Utils.getRelativeTimeAgo(post.getCreatedAt()));

    Glide.with(this).load(post.getImage().getUrl()).into(binding.ivImage);

  }
}