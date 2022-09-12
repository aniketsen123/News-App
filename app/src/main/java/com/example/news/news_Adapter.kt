package com.example.news

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class news_Adapter (val context: Context , val articles: List<Article>):RecyclerView.Adapter<news_Adapter.Articleviewholder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articleviewholder {
       val view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return Articleviewholder(view)
    }

    override fun onBindViewHolder(holder: Articleviewholder, position: Int) {
       val article:Article=articles[position]

        holder.newstitle.text=article.title
        holder.newsDescription.text=article.description
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
        holder.itemView.setOnClickListener{
            Toast.makeText(context,article.title,Toast.LENGTH_SHORT).show()
            val intent=Intent(context,web_view::class.java)
            intent.putExtra("URL",article.url)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
         return articles.size
    }
    class Articleviewholder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        var newsImage=itemView.findViewById<ImageView>(R.id.newsimage1)
        var newstitle=itemView.findViewById<TextView>(R.id.newstile)
        val newsDescription=itemView.findViewById<TextView>(R.id.body)

    }
}