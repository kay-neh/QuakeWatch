package com.example.quakewatch.domain.util

sealed interface Error

enum class NetworkError : Error {
    API_ERROR,
    NETWORK_ERROR,
    UNKNOWN_ERROR
}