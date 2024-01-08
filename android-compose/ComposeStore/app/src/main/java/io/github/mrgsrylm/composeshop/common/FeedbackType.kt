package io.github.mrgsrylm.composeshop.common

sealed class FeedbackType(val msg: String) {
    data class Error(val errorMsg: String) : FeedbackType(errorMsg)
    data class Info(val infoMsg: String) : FeedbackType(infoMsg)
    data class Success(val successMsg: String) : FeedbackType(successMsg)
}