package com.nidhin.newsapp.utils

import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.mask(): String {
    val len = this.length
    var maskedString: String = ""
    for (i in 0 until len) {
        maskedString = "$maskedString*"
    }
    return maskedString
}

fun String.convertIsoStringToDate(): Date {
    return try {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        dateFormat.parse(this)
    } catch (e: Exception) {
        return Date()
    }
}

fun Calendar.getCurrentDate(): String {
    return SimpleDateFormat("dd-MM-yyyy").format(time).split(" ")
        .toTypedArray()[0]
}

fun Date.getFormattedDate(format: String): String {
    val sdf = SimpleDateFormat(format)
    return sdf.format(this)
}
fun Date.getFormattedDateInIso(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
    return sdf.format(this)
}
fun Date.getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy")
    return sdf.format(this)
}

fun String.convertToAnotherFormat(fromFormat:String,toFormat: String): String {
    return try {
        val dateFormat: DateFormat = SimpleDateFormat(fromFormat)
        val date = dateFormat.parse(this)
        date.getFormattedDate(toFormat)
    } catch (e: Exception) {
        return ""
    }
}

fun Exception.showMessage(): String {
    return when (this) {
        is UnknownHostException -> {
            "Internet not available"
        }
        is SocketTimeoutException -> {
            "Internet timed out"
        }
        else -> {
            "Something wrong with the request"
        }
    }
}