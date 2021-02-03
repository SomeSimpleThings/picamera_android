package com.simplethings.picamera

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class NetworkService {

    fun getApolloClient(): ApolloClient {
        val okHttp = OkHttpClient
            .Builder()
            .build()

        return ApolloClient.builder()
            .serverUrl(BaseUrl.getUrl())
            .okHttpClient(okHttp)
            .build()
    }

    companion object {
        private var mInstance: NetworkService? = null

        fun getInstance(): NetworkService? {
            if (mInstance == null) {
                mInstance = NetworkService()
            }
            return mInstance
        }
    }
}

object BaseUrl {
    fun getUrl() = "http://192.168.1.17:8080/graphql"
}
