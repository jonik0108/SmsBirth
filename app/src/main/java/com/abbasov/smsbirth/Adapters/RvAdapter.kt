package com.abbasov.smsbirth.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.R
import kotlinx.android.synthetic.main.item_rv.view.*


class RvAdapter(val context: Context, val click: Click, val arrayList: ArrayList<Person>) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: View) : RecyclerView.ViewHolder(itemRv) {
        fun onBind(person: Person, position: Int) {
            itemRv.txt_name.text = person.name
            itemRv.txt_number.text=person.number
            itemRv.txt_data.text=person.data
            itemRv.nickk.text=person.group
            itemRv.time.text=person.time
            itemRv.animation = AnimationUtils.loadAnimation(context, R.anim.anim1)
            itemRv.setOnClickListener {
                click.onClick2(person, position)
            }
            itemRv.linear.setOnLongClickListener {
                click.onClick(person, position)
                true
            }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )
    }
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(arrayList[position], position)
    }
    override fun getItemCount(): Int = arrayList.size

    interface Click{
        fun onClick(person: Person, position: Int)
        fun onClick2(person: Person,position: Int)

    }
}
