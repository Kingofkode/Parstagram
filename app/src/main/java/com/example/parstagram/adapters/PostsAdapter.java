package com.example.parstagram.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parstagram.Post;
import com.example.parstagram.R;
import com.example.parstagram.Utils;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

  private Context context;
  private List<Post> posts;
  private Boolean showDetails;

  public PostsAdapter(Context context, List<Post> posts, Boolean showDetails) {
    this.context = context;
    this.posts = posts;
    this.showDetails = showDetails;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    int layout = showDetails ? R.layout.item_post : R.layout.item_square_post;
    View view = LayoutInflater.from(context).inflate(layout, parent, false);
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
    TextView tvDescription;
    TextView tvUsername;
    TextView tvDate;
    ImageView ivImage;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvDescription = itemView.findViewById(R.id.tvDescription);
      tvUsername = itemView.findViewById(R.id.tvUsername);
      tvDate = itemView.findViewById(R.id.tvDate);
      ivImage = itemView.findViewById(R.id.ivImage);
    }

    public void bind(Post postAtPosition) {

      if (showDetails) {
        tvDescription.setText(postAtPosition.getDescription());
        tvUsername.setText(postAtPosition.getUser().getUsername());
        tvDate.setText(Utils.getRelativeDate(postAtPosition.getCreatedAt()));
      }

      if (postAtPosition.getImage() != null) {
        Glide.with(context)
          .load(postAtPosition.getImage().getUrl())
          .placeholder(new ColorDrawable(ContextCompat.getColor(context, R.color.colorPlaceholderGray)))
          .into(ivImage);
      }

    }
  }

}
