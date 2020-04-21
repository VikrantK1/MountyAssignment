package com.creater.themoviedatabase2.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creater.themoviedatabase2.R
import com.creater.themoviedatabase2.database.DatabaseClass

/** TopRater Movie Adapter wchich will be used in RecyclerView **/
class TopRatedMovies(context: Context, container1: List<DatabaseClass>) :
    RecyclerView.Adapter<TopRatedMovies.DataHolder>() {
    var mcontext: Context? = null
    var listdata = container1

    init {
        mcontext = context
        listdata = container1
    }

    class DataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView? = null
        var image: ImageView? = null

        init {
            text = itemView.findViewById(R.id.nameOfTopRatedMovie)
            image = itemView.findViewById(R.id.imageOfTopRatedMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovies.DataHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.ratedmovie, parent, false)
        return DataHolder(view)
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

    override fun onBindViewHolder(holder: TopRatedMovies.DataHolder, position: Int) {
        var byteArray: ByteArray = listdata.get(position).imgdata!!
        var data: Bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.count())
        holder.image?.setImageBitmap(data)
        holder.text?.setText(listdata.get(position).title)

    }
}