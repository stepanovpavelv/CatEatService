package local.home.cateat.authentication.core.exception

/**
 * Исключение, выбрасываемое при присутствии пользователя с логином в системе.
 */
class UserAlreadyExistsException(message: String) : Exception(message) {
    constructor() : this(DEFAULT_MESSAGE)

    companion object {
        private const val DEFAULT_MESSAGE = "Пользователь с таким именем уже зарегистрирован в системе."
    }
}