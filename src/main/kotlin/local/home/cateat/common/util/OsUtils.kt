package local.home.cateat.common.util

/**
 * Вспомогательный класс для работы с операционной системой.
 */
class OsUtils {
    companion object {
        fun isWindowsOs(): Boolean {
            return System.getProperty("os.name").startsWith("Windows", false)
        }
    }
}