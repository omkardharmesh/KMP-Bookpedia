package com.plcoding.bookpedia.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object BookGraph : Route {
        @Serializable
        data object BookList : Route

        @Serializable
        data class BookDetail(val id: String) : Route //2nd screen with some args
    } //bundle of multiple screens for one feature graphs into class

    @Serializable
    data object LoginGraph : Route {
        @Serializable
        data object EnterNum : Route

        @Serializable
        data class VerifyNum(val id: String) : Route
    }
}

