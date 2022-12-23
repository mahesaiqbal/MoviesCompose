package com.mahesaiqbal.moviescompose.utils

import org.joda.time.format.ISODateTimeFormat
import java.util.*

fun dateFormatter(date: String): String {
    val parser = ISODateTimeFormat.dateTime()
    return parser.parseDateTime(date).toString("dd MMM yyyy - HH : mm : ss", Locale("id"))
}
