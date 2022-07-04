package com.example.demo.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UtilitiesTest{
    @Test
    fun testValidAmount(){
        assertFalse(Utilities.isValidAmount(0.0));
        assertTrue(Utilities.isValidAmount(0.1));
        assertTrue(Utilities.isValidAmount(5.0));
        assertTrue(Utilities.isValidAmount(0.1));
        assertFalse(Utilities.isValidAmount(-0.1));
    }
    @Test
    fun testCheckStartDateMustLowerThanEndDate(){
        assertTrue(Utilities.checkStartDateMustLowerThanEndDate("2022-10-05T10:48:01+00:00","2022-10-05T11:48:01+00:00"))
        assertFalse(Utilities.checkStartDateMustLowerThanEndDate("2022-10-05T11:48:01+00:00","2022-10-05T10:48:01+00:00"))
        assertFalse(Utilities.checkStartDateMustLowerThanEndDate("2022-10-05T11:48:01+00:00","2022-10-05T11:48:01+00:00"))
    }
    @Test
    fun testIsValidDatePattern(){
        assertTrue(Utilities.isValidDatePattern("2022-10-05T10:48:01+00:00"))
        assertFalse(Utilities.isValidDatePattern("2022-10-05T11:48:0100:00"))
        assertFalse(Utilities.isValidDatePattern("+00:00"))
    }
}