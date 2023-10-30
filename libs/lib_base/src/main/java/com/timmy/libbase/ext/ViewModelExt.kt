package com.timmy.libbase.ext

import java.lang.reflect.ParameterizedType


fun <VM> getVmClazz(obj: Any): VM {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as VM
}