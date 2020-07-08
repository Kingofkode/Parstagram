package com.example.parstagram.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.example.parstagram.Post;
import com.example.parstagram.databinding.FragmentComposeBinding;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class ComposeFragment extends Fragment {

  private static final String TAG = "ComposeFragment";

  public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 4;
  public String photoFileName = "photo.jpg";
  private File photoFile;

  FragmentComposeBinding binding;

  public ComposeFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment using view binding
    binding = FragmentComposeBinding.inflate(inflater, container, false);
    View view = binding.getRoot();
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // Setup button listeners...

    // User pressed the "Take Picture" button
    binding.btnCaptureImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        launchCamera();
      }
    });

    // User pressed the "Post" button
    binding.btnPost.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String description = binding.etDescription.getText().toString();
        if (description.isEmpty()) {
          Toast.makeText(getContext(), "Description cannot be empty", Toast.LENGTH_SHORT).show();
          return;
        }
        if (photoFile == null || binding.ivPostImage.getDrawable() == null) {
          Toast.makeText(getContext(), "There is no image", Toast.LENGTH_SHORT).show();
          return;
        }
        ParseUser currentUser = ParseUser.getCurrentUser();
        savePost(description, photoFile, currentUser);
      }
    });

  }

  private void savePost(String description, File photoFile, ParseUser currentUser) {
    Post post = new Post();
    post.setDescription(description);
    post.setImage(new ParseFile(photoFile));
    post.setUser(currentUser);
    post.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if (e != null) {
          Log.e(TAG, "Error while saving", e);
          Toast.makeText(getContext(), "Error while saving", Toast.LENGTH_SHORT).show();
          return;
        }
        Log.i(TAG, "Post saved successful!");
        binding.etDescription.setText("");
        binding.ivPostImage.setImageResource(0);
      }
    });
  }

  private void launchCamera() {
    // create Intent to take a picture and return control to the calling application
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

    // Create a File reference for future access
    photoFile = getPhotoFileUri(photoFileName);

    // wrap File object into a content provider
    // required for API >= 24
    // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
    Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.codepath.fileprovider", photoFile);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

    // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
    // So as long as the result is not null, it's safe to use the intent.
    if (intent.resolveActivity(getContext().getPackageManager()) != null) {
      // Start the image capture intent to take photo
      startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);

    }
  }

  // Returns the File for a photo stored on disk given the fileName. URI = uniform resource identifier
  private File getPhotoFileUri(String photoFileName) {

    // Get safe storage directory for photos
    // Use `getExternalFilesDir` on Context to access package-specific directories.
    // This way, we don't need to request external read/write runtime permissions.
    File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);

    // Create the storage directory if it does not exist
    if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
      Log.d(TAG, "failed to create directory");
    }

    // Return the file target for the photo based on filename
    File file = new File(mediaStorageDir.getPath() + File.separator + photoFileName);

    return file;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        // by this point we have the camera photo on disk
        Bitmap takenImage = rotateBitmapOrientation(photoFile.getAbsolutePath());

        // RESIZE BITMAP, see section below
        // Load the taken image into a preview
        binding.ivPostImage.setImageBitmap(takenImage);
      } else { // Result was a failure
        Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
      }
    }
  }

  public Bitmap rotateBitmapOrientation(String photoFilePath) {
    // Create and configure BitmapFactory
    BitmapFactory.Options bounds = new BitmapFactory.Options();
    bounds.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(photoFilePath, bounds);
    BitmapFactory.Options opts = new BitmapFactory.Options();
    Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
    // Read EXIF Data
    ExifInterface exif = null;
    try {
      exif = new ExifInterface(photoFilePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
    int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
    int rotationAngle = 0;
    if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
    if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
    if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
    // Rotate Bitmap
    Matrix matrix = new Matrix();
    matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
    Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
    // Return result
    return rotatedBitmap;
  }


}