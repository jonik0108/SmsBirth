package com.abbasov.smsbirth.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.databinding.ItemRv2Binding

class UserAdapter(val context: Context, val click: onClick2, val list:List<Person>): RecyclerView.Adapter<UserAdapter.Vh>(){
    inner class Vh(var itemRv2:ItemRv2Binding):RecyclerView.ViewHolder(itemRv2.root){
        fun onBind(person: Person,position: Int){
            itemRv2.fioT.text=person.name
            itemRv2.nick.text=person.nick
            itemRv2.numberT.text=person.number
            itemRv2.root.setOnClickListener {
                click.Click2(person,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.Vh {
        return Vh(ItemRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface onClick2{
        fun Click2(person: Person, position: Int)

    }
}