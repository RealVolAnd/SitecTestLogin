package com.test.sitec.sitectestlogin.presentation.models

data class RequestSignIn(
    val uid: String,
    val pass: String,
    val copyFromDevice: Boolean,
    val nfc: String = ""
)