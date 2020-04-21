package com.creater.themoviedatabase2.util

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.creater.themoviedatabase2.MainActivity
import com.creater.themoviedatabase2.R
import com.creater.themoviedatabase2.database.DatabaseClass
import com.squareup.picasso.Picasso

class UpcomingMovies(var context: MainActivity, var list12:List<DatabaseClass>):
    RecyclerView.Adapter<UpcomingMovies.holder>() {
    var container1= mutableListOf<DatabaseClass>()
    var mcontext:Context?=null
    init {
        container1.addAll(list12)
       this.mcontext = context
    }


    class holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image:ImageView?=null
        init {
            this.image =itemView.findViewById(R.id.UpcomingMovies)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMovies.holder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.movielayout,parent,false)
       return holder(view)
    }

    override fun getItemCount(): Int {

        return container1.size

    }

    override fun onBindViewHolder(holder: holder, position: Int) {
             Log.i("vik","binding is going on")
        var byteArray:ByteArray= container1.get(position).imgdata!!
        var data: Bitmap =BitmapFactory.decodeByteArray(byteArray,0,byteArray.count())
        holder.image?.setImageBitmap(data)

    }


    }




