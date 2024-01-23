package com.example.images.mapper

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class TimeFormatter @Inject constructor() {

    //"2017-12-22T12:04:43-08:00" -> 2017-12-22
    fun from(date: String): String {
        return ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME).toLocalDate().toString()
    }
}
