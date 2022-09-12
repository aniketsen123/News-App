  package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

  class MainActivity : AppCompatActivity() {
      lateinit var adapter: news_Adapter
      private var mInterstitialAd: InterstitialAd? = null
      val layoutmanager=LinearLayoutManager(this)
private var articles= mutableListOf<Article>()
      var page=1;
      var totalresults=-1;
      var country="in"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                mInterstitialAd = interstitialAd
            }
        })
        adapter= news_Adapter(this@MainActivity,articles)
        // dont call it too early you will get a  exception
        var list=findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.news_list)
        list.adapter=adapter

        list.layoutManager = layoutmanager


        getNews()
    }

    private fun getNews() {
        val news:Call<News> =news_service.newsintentance.getHeadlines(country ,page)
        news.enqueue(object : retrofit2.Callback<News>{
            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                val news:News?= response?.body()
                if(news!=null)
                { totalresults=news.totalResults
                    if(totalresults>layoutmanager.itemCount && layoutmanager.findFirstVisibleItemPosition()>=layoutmanager.itemCount-5)
                    {
                        page++
                        getNews()
                    }
                    if (mInterstitialAd != null) {
                        mInterstitialAd?.show(this@MainActivity)
                    }
                  articles.addAll(news.articles)
                  adapter.notifyDataSetChanged()
                }
               else
                {
                    country ="us"
                    getNews()
                }
            }

            override fun onFailure(call: Call<News>?, t: Throwable?) {
                TODO("Not yet implemented")
            }
        });
        {

        }
    }
}