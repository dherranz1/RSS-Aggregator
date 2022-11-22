package com.dherranz1.app.domain

sealed class ErrorApp {
    class DataError() : ErrorApp()
    class DomainError() : ErrorApp()
}