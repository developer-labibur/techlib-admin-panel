package com.rudhashi.techlibadminpanel.utils

object ConvertFB {
    fun covertDepNameToCode(name: Any): String {
        val codeStr = name.toString()  // Convert any type to string for matching
        return when (codeStr) {
            "61 => Architecture Technology" -> "61"
            "64 => Civil Technology" -> "64"
            "85 => Computer Science and Technology" -> "85"
            "67 => Electrical Technology" -> "67"
            "68 => Electronics Technology" -> "68"
            "69 => Food Technology" -> "69"
            "70 => Mechanical Technology" -> "70"
            "88 => Construction Technology" -> "88"
            "71 => Power Technology" -> "71"
            else -> ""
        }
    }

    fun convertDepToAbbr(name: Any): String {
        val codeStr = name.toString()  // Convert any type to string for matching
        return when (codeStr) {
            "Architecture Technology" -> "AT"
            "Civil Technology" -> "CT"
            "Computer Science and Technology" -> "CST"
            "Electrical Technology" -> "ET"
            "Electronics Technology" -> "ENT"
            "Food Technology" -> "FT"
            "Mechanical Technology" -> "MT"
            "Construction Technology" -> "CNT"
            "Power Technology" -> "PT"
            else -> ""
        }
    }

    fun convertSemNoToSemNo(name: Any): String {
        val codeStr = name.toString()  // Convert any type to string for matching
        return when (codeStr) {
            "1st Semester" -> "1"
            "2nd Semester" -> "2"
            "3rd Semester" -> "3"
            "4th Semester" -> "4"
            "5th Semester" -> "5"
            "6th Semester" -> "6"
            "7th Semester" -> "7"
            "8th Semester" -> "8"
            else -> ""
        }
    }

    fun covertDepCodeToName(code: Any): String {
        val codeStr = code.toString()  // Convert any type to string for matching
        return when (codeStr) {
            "61" -> "Architecture Technology"
            "64" -> "Civil Technology"
            "85" -> "Computer Science and Technology"
            "67" -> "Electrical Technology"
            "68" -> "Electronics Technology"
            "69" -> "Food Technology"
            "70" -> "Mechanical Technology"
            "88" -> "Construction Technology"
            "71" -> "Power Technology"
            else -> "Unknown Department"
        }
    }
    fun covertSemNameToTag(name: Any): String {
        val codeStr = name.toString()  // Convert any type to string for matching
        return when (codeStr) {
            "1st Semester => Architecture Technology" -> "AT1"
            "2nd Semester => Architecture Technology" -> "AT2"
            "3rd Semester => Architecture Technology" -> "AT3"
            "4th Semester => Architecture Technology" -> "AT4"
            "5th Semester => Architecture Technology" -> "AT5"
            "6th Semester => Architecture Technology" -> "AT6"
            "7th Semester => Architecture Technology" -> "AT7"
            "1st Semester => Civil Technology" -> "CT1"
            "2nd Semester => Civil Technology" -> "CT2"
            "3rd Semester => Civil Technology" -> "CT3"
            "4th Semester => Civil Technology" -> "CT4"
            "5th Semester => Civil Technology" -> "CT5"
            "6th Semester => Civil Technology" -> "CT6"
            "7th Semester => Civil Technology" -> "CT7"
            "1st Semester => Computer Science and Technology" -> "CST1"
            "2nd Semester => Computer Science and Technology" -> "CST2"
            "3rd Semester => Computer Science and Technology" -> "CST3"
            "4th Semester => Computer Science and Technology" -> "CST4"
            "5th Semester => Computer Science and Technology" -> "CST5"
            "6th Semester => Computer Science and Technology" -> "CST6"
            "7th Semester => Computer Science and Technology" -> "CST7"
            "1st Semester => Electrical Technology" -> "ET1"
            "2nd Semester => Electrical Technology" -> "ET2"
            "3rd Semester => Electrical Technology" -> "ET3"
            "4th Semester => Electrical Technology" -> "ET4"
            "5th Semester => Electrical Technology" -> "ET5"
            "6th Semester => Electrical Technology" -> "ET6"
            "7th Semester => Electrical Technology" -> "ET7"
            "1st Semester => Electronics Technology" -> "ENT1"
            "2nd Semester => Electronics Technology" -> "ENT2"
            "3rd Semester => Electronics Technology" -> "ENT3"
            "4th Semester => Electronics Technology" -> "ENT4"
            "5th Semester => Electronics Technology" -> "ENT5"
            "6th Semester => Electronics Technology" -> "ENT6"
            "7th Semester => Electronics Technology" -> "ENT7"
            "1st Semester => Mechanical Technology" -> "MT1"
            "2nd Semester => Mechanical Technology" -> "MT2"
            "3rd Semester => Mechanical Technology" -> "MT3"
            "4th Semester => Mechanical Technology" -> "MT4"
            "5th Semester => Mechanical Technology" -> "MT5"
            "6th Semester => Mechanical Technology" -> "MT6"
            "7th Semester => Mechanical Technology" -> "MT7"
            "1st Semester => Construction Technology" -> "CNT1"
            "2nd Semester => Construction Technology" -> "CNT2"
            "3rd Semester => Construction Technology" -> "CNT3"
            "4th Semester => Construction Technology" -> "CNT4"
            "5th Semester => Construction Technology" -> "CNT5"
            "6th Semester => Construction Technology" -> "CNT6"
            "7th Semester => Construction Technology" -> "CNT7"
            "1st Semester => Power Technology" -> "PT1"
            "2nd Semester => Power Technology" -> "PT2"
            "3rd Semester => Power Technology" -> "PT3"
            "4th Semester => Power Technology" -> "PT4"
            "5th Semester => Power Technology" -> "PT5"
            "6th Semester => Power Technology" -> "PT6"
            "7th Semester => Power Technology" -> "PT7"
            "1st Semester => Food Technology" -> "FT1"
            "2nd Semester => Food Technology" -> "FT2"
            "3rd Semester => Food Technology" -> "FT3"
            "4th Semester => Food Technology" -> "FT4"
            "5th Semester => Food Technology" -> "FT5"
            "6th Semester => Food Technology" -> "FT6"
            "7th Semester => Food Technology" -> "FT7"
            else -> ""
        }
    }
}