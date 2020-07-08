package com.example.parstagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.parstagram.Post;
import com.example.parstagram.PostsAdapter;
import com.example.parstagram.databinding.FragmentPostsBinding;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

  private static final String TAG = "PostsFragment";

  FragmentPostsBinding binding;

  private PostsAdapter adapter;
  private List<Post> allPosts;

  public PostsFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment using view binding
    binding = FragmentPostsBinding.inflate(inflater, container, false);
    View view = binding.getRoot();
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    allPosts = new ArrayList<>();
    adapter = new PostsAdapter(getContext(), allPosts);

    binding.rvPosts.setAdapter(adapter);
    binding.rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

    queryPosts();

  }

  // Download posts from Parse
  private void queryPosts() {
    ParseQuery<Post> postParseQuery = ParseQuery.getQuery(Post.class);
    postParseQuery.include(Post.KEY_USER);

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
        allPosts.addAll(posts);
        adapter.notifyDataSetChanged();
      }
    });
  }
}