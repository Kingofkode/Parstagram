package com.example.parstagram.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.parstagram.activities.DetailActivity;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

  private Context context;
  private List<Post> posts;

  // If set to true: shows feed view. If false: shows grid view
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

  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

      itemView.setOnClickListener(this);
    }

    public void bind(Post postAtPosition) {

      if (showDetails) {
        tvDescription.setText(postAtPosition.getDescription());
        tvUsername.setText(postAtPosition.getUser().getUsername());
        tvDate.setText(Utils.getRelativeTimeAgo(postAtPosition.getCreatedAt()));
      }

      if (postAtPosition.getImage() != null) {
        Glide.with(context)
          .load(postAtPosition.getImage().getUrl())
          .placeholder(new ColorDrawable(ContextCompat.getColor(context, R.color.colorPlaceholderGray)))
          .into(ivImage);
      }

    }

    @Override
    public void onClick(View view) {
      int position = getAdapterPosition();
      // Make sure position is valid
      if (position != RecyclerView.NO_POSITION) {
        // Get post at position
        Post movie = posts.get(position);
        // Create intent for new activity
        Intent postDetailIntent = new Intent(context, DetailActivity.class);
        // Serialize the movie using parceler, use its short name as a key
        postDetailIntent.putExtra(Post.class.getSimpleName(), Parcels.wrap(movie));
        // Show activity
        context.startActivity(postDetailIntent);
      }
    }
  }

}
