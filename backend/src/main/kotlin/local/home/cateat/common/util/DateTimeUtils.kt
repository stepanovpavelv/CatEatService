package local.home.cateat.common.util

import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Вспомогательный класс для работы с датами и временем.
 */
class DateTimeUtils {
    companion object {
        private const val DATE_TIME_DB_FORMAT = "yyyy-MM-dd HH:mm:ss.S"

        fun toInstant(dateString: String) : Instant {
            return LocalDateTime
                .parse(dateString, DateTimeFormatter.ofPattern(DATE_TIME_DB_FORMAT))
                .toInstant(ZoneOffset.UTC)
        }

        fun fromInstant(date: Instant) : OffsetDateTime {
            return OffsetDateTime.ofInstant(date, ZoneOffset.UTC)
        }
    }
}