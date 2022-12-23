package com.mahesaiqbal.moviescompose.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun dateFormat(dateString: String): String? {
    val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
    val outputFormat: DateFormat = SimpleDateFormat("dd MMMM yyyy")

    val date = inputFormat.parse(dateString)

    return date?.let { outputFormat.format(it) }
}
