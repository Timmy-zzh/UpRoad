package com.timmy.javabasic._02annotation

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.SOURCE)
annotation class MyAnnotation(val txt: String)
