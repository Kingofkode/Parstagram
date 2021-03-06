package com.example.parstagram.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.parstagram.Post;
import com.example.parstagram.activities.LoginActivity;
import com.example.parstagram.adapters.PostsAdapter;
import com.example.parstagram.databinding.FragmentProfileBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

  private static final String TAG = "ProfileFragment";
  public static final String KEY_BIO  = "bio";
  public static final String PROFILE_PLACEHOLDER = "https://i1.wp.com/acaweb.org/wp-content/uploads/2018/12/profile-placeholder.png?ssl=1";

  FragmentProfileBinding binding;

  List<Post> myPosts;

  PostsAdapter adapter;

  // Required empty public constructor
  public ProfileFragment() {}

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = FragmentProfileBinding.inflate(inflater, container, false);
    // Inflate the layout for this fragment using view binding
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    populateProfileData();
    setupSignOutButton();

    myPosts = new ArrayList<>();
    adapter = new PostsAdapter(getContext(), myPosts, false);

    binding.rvMyPosts.setAdapter(adapter);
    binding.rvMyPosts.setLayoutManager(new GridLayoutManager(getContext(), 3));

    queryMyPosts();

  }

  private void setupSignOutButton() {
    binding.btnSignOut.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ParseUser.logOut();
        Intent logInIntent = new Intent(getContext(), LoginActivity.class);
        startActivity(logInIntent);

        if (getActivity() != null) {
          getActivity().finish();
        }

      }
    });
  }

  private void populateProfileData() {
    ParseUser currentUser = ParseUser.getCurrentUser();

    binding.tvUsername.setText(currentUser.getUsername());

    binding.tvBio.setText(currentUser.getString(KEY_BIO));

    if (getContext() != null) {
      Glide.with(getContext())
        .load(PROFILE_PLACEHOLDER)
        .circleCrop()
        .into(binding.ivProfile);
    }
  }

  private void queryMyPosts() {
    ParseUser currentUser = ParseUser.getCurrentUser();
    ParseQuery<Post> postParseQuery = ParseQuery.getQuery(Post.class);
    postParseQuery.include(Post.KEY_USER);
    postParseQuery.setLimit(20);
    postParseQuery.addDescendingOrder(Post.KEY_CREATED_AT);
    postParseQuery.whereMatches(Post.KEY_USER, currentUser.getObjectId());

    postParseQuery.findInBackground(new FindCallback<Post>() {
      @Override
      public void done(List<Post> posts, ParseException e) {
        if (e != null) {
          Log.e(TAG, "Issue with getting posts", e);
          return;
        }

        for (Post post : posts) {
          Log.i(TAG, "Post: " + post.getDescription() + " username: " + post.getUser().getUsername());
        }
        myPosts.addAll(posts);
        adapter.notifyDataSetChanged();

        // Update Post counter
        binding.tvPostCount.setText(String.valueOf(myPosts.size()));
      }
    });
  }

}