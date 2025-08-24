package com.acme.core.common

sealed class Either<out L, out R> {
    data class Left<out L>(val value: L) : Either<L, Nothing>()
    data class Right<out R>(val value: R) : Either<Nothing, R>()

    inline fun <T> fold(onLeft: (L) -> T, onRight: (R) -> T): T = when (this) {
        is Left -> onLeft(value)
        is Right -> onRight(value)
    }
}

sealed class ResultError(open val message: String? = null, open val cause: Throwable? = null) {
    data class Network(override val message: String? = null, override val cause: Throwable? = null) : ResultError(message, cause)
    data class Serialization(override val message: String? = null, override val cause: Throwable? = null) : ResultError(message, cause)
    data class Database(override val message: String? = null, override val cause: Throwable? = null) : ResultError(message, cause)
    data class Business(override val message: String? = null, override val cause: Throwable? = null) : ResultError(message, cause)
    data class Unknown(override val message: String? = null, override val cause: Throwable? = null) : ResultError(message, cause)
}