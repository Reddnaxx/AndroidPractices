package com.example.utils.mappers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateMapper {
    fun toLocalString(value: String?, fromFormatter: DateTimeFormatter, toPattern: String): String {
        return value?.let {
            val localDate = LocalDate.parse(value, fromFormatter)
            val formatter = DateTimeFormatter.ofPattern(toPattern, Locale("ru"))

            return localDate.format(formatter)
        } ?: ""
    }
}