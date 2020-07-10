package com.example.parstagram;

import android.text.format.DateUtils;

import java.util.Date;

public class Utils {

  // Returns relative time in String format like: 5 hours ago
  public static String getRelativeDate(Date date) {
    long dateMillis = date.getTime();
    return DateUtils.getRelativeTimeSpanString(dateMillis,
      System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
  }
}
