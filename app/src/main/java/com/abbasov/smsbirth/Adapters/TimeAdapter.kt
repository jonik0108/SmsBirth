package com.abbasov.smsbirth.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.Person2
import com.abbasov.smsbirth.databinding.ItemRv3Binding

class TimeAdapter(val context: Context, val click: onClck, val list:List<Person2>): RecyclerView.Adapter<TimeAdapter.Vh>(){
    inner class Vh(var itemRv:ItemRv3Binding):RecyclerView.ViewHolder(itemRv.root){
        fun onBind(person2: Person2,position: Int){
            itemRv.tame.text=person2.time
            itemRv.root.setOnClickListener {
                click.onTimeClik(person2,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeAdapter.Vh {
        return Vh(ItemRv3Binding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: TimeAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)

    }

    override fun getItemCount(): Int = list.size
    interface onClck{
        fun onTimeClik(person2: Person2, position: Int)
    }

}