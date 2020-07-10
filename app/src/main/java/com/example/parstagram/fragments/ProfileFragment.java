package com.example.parstagram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.parstagram.activities.LoginActivity;
import com.example.parstagram.databinding.FragmentProfileBinding;
import com.parse.ParseUser;

public class ProfileFragment extends Fragment {

  private static final String TAG = "ProfileFragment";

  FragmentProfileBinding binding;

  public ProfileFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater, container, false);
    View view = binding.getRoot();
    // Inflate the layout for this fragment using view binding
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    populateProfileData();

    setupSignOutButton();

  }

  private void setupSignOutButton() {
    binding.btnSignOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ParseUser.logOut();
        Intent logInIntent = new Intent(getContext(), LoginActivity.class);
        startActivity(logInIntent);
        getActivity().finish();
      }
    });
  }

  private void populateProfileData() {
    ParseUser currentUser = ParseUser.getCurrentUser();

    binding.tvUsername.setText(currentUser.getUsername());

    Glide.with(getContext())
      .load("https://i1.wp.com/acaweb.org/wp-content/uploads/2018/12/profile-placeholder.png?ssl=1")
      .circleCrop()
      .into(binding.ivProfile);
  }

}