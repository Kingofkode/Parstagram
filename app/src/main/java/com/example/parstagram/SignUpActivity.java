package com.example.parstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.example.parstagram.databinding.ActivitySignUpBinding;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

  ActivitySignUpBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivitySignUpBinding.inflate(getLayoutInflater());
    View view = binding.getRoot();
    setContentView(view);
    // Done with view binding

    binding.etUsername.requestFocus();

    setupUsernameListener();

    setupPasswordListener();

    setupConfirmPasswordListener();

  }

  public void onSignUpClicked(View view) {
    String username = binding.etUsername.getText().toString();
    String password = binding.etPassword.getText().toString();

    ParseUser user = new ParseUser();
    user.setUsername(username);
    user.setPassword(password);
    final ProgressDialog dialog = ProgressDialog.show(this, "",
      "Signing up ...", true);
    user.signUpInBackground(new SignUpCallback() {
      @Override
      public void done(ParseException e) {
        dialog.dismiss();
        if (e != null) {
          // An error occurred
          return;
        }

        // We signed up a new user!
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
      }
    });
  }

  private Boolean canSignUp() {
    String username = binding.etUsername.getText().toString();
    String password = binding.etPassword.getText().toString();
    String confirmPassword = binding.etConfirmPassword.getText().toString();
    return !username.isEmpty() && !password.isEmpty() && password.equals(confirmPassword);
  }

  private void setupConfirmPasswordListener() {
    binding.etConfirmPassword.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        binding.btnSignUp.setEnabled(canSignUp());
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }

  private void setupPasswordListener() {
    binding.etPassword.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        binding.btnSignUp.setEnabled(canSignUp());
      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }

  private void setupUsernameListener() {
    binding.etUsername.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        binding.btnSignUp.setEnabled(canSignUp());
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }

}