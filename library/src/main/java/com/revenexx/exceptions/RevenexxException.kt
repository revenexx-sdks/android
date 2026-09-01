package com.revenexx.exceptions

import java.lang.Exception

class RevenexxException(
    override val message: String? = null,
    val code: Int? = null,
    val type: String? = null,
    val response: String? = null
) : Exception(message)