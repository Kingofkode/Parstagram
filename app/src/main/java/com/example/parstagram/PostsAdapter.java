package com.example.parstagram;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parstagram.databinding.ItemPostBinding;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

  private Context context;
  private List<Post> posts;

  public PostsAdapter(Context context, List<Post> posts) {
    this.context = context;
    this.posts = posts;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Post postAtPosition = posts.get(position);
    holder.bind(postAtPosition);
  }

  @Override
  public int getItemCount() {
    return posts.size();
  }

  // View holder declaration

  class ViewHolder extends RecyclerView.ViewHolder {

    ItemPostBinding binding;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      binding = ItemPostBinding.bind(itemView);
    }

    public void bind(Post postAtPosition) {
      binding.tvDescription.setText(postAtPosition.getDescription());
      binding.tvUsername.setText(postAtPosition.getUser().getUsername());

      if (postAtPosition.getImage() != null) {
        Glide.with(context)
          .load(postAtPosition.getImage().getUrl())
          .placeholder(new ColorDrawable(ContextCompat.getColor(context, R.color.colorPlaceholderGray)))
          .into(binding.ivImage);
      }

    }
  }

}
