package com.example.redditek.ui.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.redditek.R
import android.widget.Button
import androidx.core.view.isVisible
import com.example.redditek.services.Post.Children
import com.squareup.picasso.Picasso



class RecyclerAdapter(private val listener: onItemClickListener) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    interface onItemClickListener {
        fun onItemClick(position: Int, state: String, subredditName: String)
    }
    lateinit var posts: List<Children>

    //private var images = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.itemImage.setImageResource(images[position])

        var post = posts[position]
        holder.itemSubRedditName.text ="r/" + post.data.subreddit
        holder.itemDescription.text = post.data.selftext
        holder.itemTitle.text = post.data.title
        holder.itemSubRedditSubscriber.text = post.data.subreddit_subscribers.toString()
        if(post.data.preview!= null){
            var url =  post.data.preview.images[0].source.url.replace("amp;", "")
            Log.d("url","here the url :" + url)
            Picasso.get().load(url).into(holder.itemImage)
        }else{
            holder.itemImage.isVisible = false
        }

        //post.data.preview.images[0].source.url
        holder.unSubscribeButton.setOnClickListener{
            val state = holder.unSubscribeButton.text.toString().toLowerCase()
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position,state, post.data.subreddit)
            }

            if(state=="unsubscribe"){
                holder.unSubscribeButton.setText("Subscribre")
            }else{
                holder.unSubscribeButton.setText("Unsubscribe")
            }
        }

    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var unSubscribeButton: Button
        var itemSubRedditSubscriber: TextView
        var itemSubRedditName: TextView
        var itemTitle: TextView
        var itemDescription: TextView


        init {
           itemImage = itemView.findViewById(R.id.imageview)
               /*  itemSubRedditDescription = itemView.findViewById(R.id.descrit)*/
            itemSubRedditName = itemView.findViewById(R.id.sub)
            itemTitle = itemView.findViewById(R.id.title)
            itemDescription = itemView.findViewById(R.id.description)
            itemSubRedditSubscriber = itemView.findViewById(R.id.subscriber)
            unSubscribeButton = itemView.findViewById(R.id.unsubscribe)

        }


    }
}
