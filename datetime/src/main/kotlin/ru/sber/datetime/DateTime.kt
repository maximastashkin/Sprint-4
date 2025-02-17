package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.TemporalAdjusters
import java.util.Locale
import java.util.TimeZone

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> = ZoneId.getAvailableZoneIds()
    .filter { ZoneId.of(it).rules.getOffset(LocalDateTime.now()).totalSeconds % (60 * 60) != 0 }
    .toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> = Month.values().map {
    LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int = Month.values()
    .map { LocalDate.of(year, it, 13) }
    .count { it.dayOfWeek == DayOfWeek.FRIDAY }

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String =
    DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US).format(dateTime)


fun main() {
    println(getZonesWithNonDivisibleByHourOffset())
    println(getLastInMonthDayWeekList(2023))
    println(getNumberOfFridayThirteensInYear(2023))
    println(getFormattedDateTime(LocalDateTime.now()))
}
