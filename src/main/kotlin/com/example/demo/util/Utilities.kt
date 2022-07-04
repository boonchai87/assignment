package com.example.demo.util

import io.mockk.InternalPlatformDsl.toStr
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
        fun isValidDatePattern(dateStr : String): Boolean {
            if( dateStr.indexOf("+")>0 || dateStr.indexOf("-")>0){
                try{
                    convertStringToDate(dateStr)
                    return true
                }catch (e:Exception) {
                    println(e)
                    return false
                }
            }
            return false
        }
        fun checkStartDateMustLowerThanEndDate(startDate:String,endDate:String):Boolean{
            val startDate = convertStringToDate(startDate)
            val endDate = convertStringToDate(endDate)
            return startDate.before(endDate)
        }
        fun isValidAmount(amount:Double):Boolean {
            if( amount==null || amount.compareTo(0)==0
                || amount<0 ) {
                println(amount)
                return false
            }
            return true
        }
    }

}