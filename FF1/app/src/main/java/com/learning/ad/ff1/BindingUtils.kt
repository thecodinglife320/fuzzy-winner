package com.learning.ad.ff1

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.Locale

@BindingAdapter("bookApiStatus")
fun ProgressBar.setProgressBarState(state: BookApiStatus?) {
   state?.let {
      visibility = when (state) {
         BookApiStatus.LOADING -> View.VISIBLE
         BookApiStatus.ERROR -> View.GONE
         BookApiStatus.DONE -> View.GONE
         BookApiStatus.EMPTY -> View.GONE
      }
   }
}
@BindingAdapter("list_authors")
fun TextView.setAuthors(authors: List<String>?) {
   authors?.let {
      text = authors.joinToString(", ")
   }
}
// show only first 150 characters
@BindingAdapter("describe")
fun TextView.setDescribe(describe: String?) {
   describe?.let {
      text = if (describe.length < 150) {
         describe
      } else {
         describe.substring(0, 150) + "..."
      }
   }
}
@BindingAdapter("format_time")
fun formatTime(textView: TextView, time: Long) {
   val sdf = SimpleDateFormat("MMM dd,yy 'at' h:mm a", Locale.getDefault())
   val date = sdf.format(time)
   textView.text = date
}