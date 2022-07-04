package com.example.demo.basic

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


fun main(args: Array<String>) { 
    var data = "2019-10-05T14:48:01+01:00";
    //data = "my,name,is,khan";
    //val data.split(","))
    //StringTokenizer st = new StringTokenizer("my,name,is,khan");
    //data = "my name is khan";
    //println(data)
    //val array: Array<String> = data.split("+",false,-1)

    //var datetime = "";

    if( data.indexOf("+")>0 ){
            val datetimes = data.split("+")
            println(datetimes[0])
            println("GMT+"+datetimes[1])
    }else if( data.indexOf("-")>0 ){
        val datetimes = data.split("-")
        println(datetimes[0])
        println("GMT-"+datetimes[1])
    }

    /*for( datetime in datetimes){
        println(datetime)
    }*/
//    if( datetimes.countTokens()>1 ){
//        println(datetimes.nextToken())
//
////        while(datetimes.hasMoreTokens()){
////            println(datetimes.nextToken())
////        }
//    }else {
//
//    }


    //println(array)
    println(Date())
    println(Instant.now())
    println(LocalDate.now())
    println(LocalTime.now())
    println("LocalDateTime"+LocalDateTime.now())
    //var list = Arrays.asList(TimeZone.getAvailableIDs())
    ///println(list)
    val timezone = "+07:00"
    val tz = TimeZone.getTimeZone(timezone)
    println(tz)
    val date = Calendar.getInstance().time
    val date_format_gmt = SimpleDateFormat("'at' HH:mm a z 'on' MM/dd/yyyy")
    date_format_gmt.timeZone = tz
    println(date_format_gmt.format(date))
    /*for(i in  list){
        println(i)
    }*/
    val format = SimpleDateFormat(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US
    )

    format.timeZone = TimeZone.getTimeZone("UTC")
    println("iso format "+format.format(date))

    val f = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    f.timeZone = TimeZone.getTimeZone("GMT+07:00")
    println(f.format(Date()))
    println(f.parse("2019-10-05T14:48:01"))

    //f.parse(data);
//    val c = Calendar.getInstance()
//    val utcOffset = c[Calendar.ZONE_OFFSET] + c[Calendar.DST_OFFSET]
//    val utcMilliseconds = c.timeInMillis + utcOffset
//    println(utcMilliseconds)
}
