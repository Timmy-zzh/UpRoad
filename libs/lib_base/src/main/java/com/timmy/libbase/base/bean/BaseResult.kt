package com.timmy.libbase.base.bean

/**
 * {"data":...,"errorCode":0,"errorMsg":""}
 */
class BaseResult<T> {
    var data: T? = null
    var errorCode: Int = -1
    var errorMsg: String = ""
}