package com.example.ares.footballclub

import android.content.Context
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.list_item.view.*

class Adapter(private val contex : Context, private val items: List<Item>,private val listener: (Item) -> Unit):RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
            ViewHolder(LayoutInflater.from(contex).inflate(R.layout.list_item,p0,false))


    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(items[p1],listener)
    }


    class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bindItem(item :Item, listener: (Item)-> Unit){

            itemView.name.text = item.name
            Glide.with(itemView.context).load(item.image).into(itemView.gambar)
            itemView.setOnClickListener{
                listener(item)
            }
        }

   }

}
