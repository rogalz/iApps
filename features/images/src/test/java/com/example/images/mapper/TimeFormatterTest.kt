package com.example.images.mapper

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TimeFormatterTest {

    private val tested: TimeFormatter = TimeFormatter()

    @Test
    fun from() {
        val result = tested.from("2017-12-22T12:04:43-08:00")
        assertThat(result).isEqualTo("2017-12-22")
    }

    @Test
    fun fromFor1990year() {
        val result = tested.from("1990-02-28T02:04:00-01:00")
        assertThat(result).isEqualTo("1990-02-28")
    }
}
