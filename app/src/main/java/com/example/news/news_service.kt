package com.example.news

import android.telecom.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
//
// https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=083f567f03ea4cec84cb4191b177c614
//https://newsapi.org/v2/everything?q=apple&from=2022-07-22&to=2022-07-22&sortBy=popularity&apiKey=083f567f03ea4cec84cb4191b177c614
const val baseurl="https://newsapi.org/"
const val api_key="083f567f03ea4cec84cb4191b177c614"
interface newsinterface {
      @GET("v2/top-headlines?apiKey=$api_key")
      fun getHeadlines(@Query("country") country:String,@Query("page") page:Int): retrofit2.Call<News>
}
object news_service{
      val newsintentance:newsinterface
      init {
          val retrofit=Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            newsintentance=retrofit.create(newsinterface::class.java)
      }
}