package com.simplethings.picamera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.simplethings.picamera.app.src.main.apollo.com.simplethings.picamera.GetPhotosQuery
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apolloClient: ApolloClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getPhotos()
    }

    private fun getPhotos() {

        val photos = GetPhotosQuery()

        apolloClient
            .query(photos)
            ?.enqueue(object : ApolloCall.Callback<GetPhotosQuery.Data>() {

                override fun onResponse(response: Response<GetPhotosQuery.Data>) {
                    if (!response.hasErrors()) {
                        runOnUiThread(Runnable {
                            findViewById<TextView>(R.id.text_view).text =
                                response.data?.photos?.get(0).toString()
                        })

                    }
                }

                override fun onFailure(e: ApolloException) {}
            })
    }
}