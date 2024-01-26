package com.timmy.upload.home.study

import okhttp3.Call
import okhttp3.Callback
import okhttp3.EventListener
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

object OkHttpS {

    /**
     * ***********************OkHttpClient******************************
     */
    private fun httpPost() {

        val okHttpClient = OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
            .eventListener(object : EventListener() {
                override fun callStart(call: Call) {
                    super.callStart(call)
                }
            }).build()

        val json = JSONObject()
        json.put("key1", "value")
        json.put("age", 18)

        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body: RequestBody = json.toString().toRequestBody(mediaType)

        val request: Request = Request.Builder().url("https://www.baidu.com").post(body).build()
        val newCall: Call = okHttpClient.newCall(request)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
            }
        })


    }

    private fun httpGet() {

        val okHttpClient = OkHttpClient()

        val request: Request = Request.Builder().url("https://www.baidu.com").build()

        val newCall: Call = okHttpClient.newCall(request)

        val res: Response = newCall.execute()
        println("res:${res.isSuccessful} , ${res.body.toString()}")
        res.close()


        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
            }
        })

    }
}