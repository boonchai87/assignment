package com.example.demo.util

import java.text.SimpleDateFormat
import java.util.*

class Utilities {
    companion object {
        fun convertStringToDate(dateStr : String): Date {
            var datetimeStr=""

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

            if( dateStr.indexOf("+")>0 ){
                val datetimes = dateStr.split("+")
                datetimeStr = datetimes[0];
                dateFormat.timeZone = TimeZone.getTimeZone("GMT+"+datetimes[1])
            }else if( dateStr.indexOf("-")>0 ){
                val datetimes = dateStr.split("-")
                datetimeStr = datetimes[0];
                dateFormat.timeZone = TimeZone.getTimeZone("GMT-"+datetimes[1])
            }
            return dateFormat.parse(datetimeStr)
        }

    }

}