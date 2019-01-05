package com.neobis.israil.infamily.utill

interface SuccessOrError<T> {
    fun onSuccess(result: T)
    fun onError(message: String?)
}