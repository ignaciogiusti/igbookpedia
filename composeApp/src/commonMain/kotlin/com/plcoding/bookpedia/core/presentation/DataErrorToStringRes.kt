package com.plcoding.bookpedia.core.presentation

import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.*
import com.plcoding.bookpedia.core.domain.DataError

fun DataError.toUiText(): UiText {
    val strinRes = when (this) {
        DataError.Local.DISK_FULL -> Res.string.disk_full
        DataError.Local.UNKNOWN_ERROR -> Res.string.unknown_error
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.too_many_requests
        DataError.Remote.NO_INTERNET_CONNECTION -> Res.string.no_internet_connection
        DataError.Remote.SERVER_ERROR -> Res.string.unknown_error
        DataError.Remote.SERIALIZATION_ERROR -> Res.string.serialization_error
        DataError.Remote.UNKNOWN_ERROR -> Res.string.unknown_error
    }

    return UiText.StringResourceId(strinRes)
}