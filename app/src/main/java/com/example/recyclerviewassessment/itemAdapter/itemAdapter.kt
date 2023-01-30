package com.example.recyclerviewassessment.itemAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewassessment.R
import com.example.recyclerviewassessment.model.itemData

class itemAdapter(private val context: Context, private  val dataOfItems :List<itemData>,val onclickDelete: (Int)->Unit):
    RecyclerView.Adapter<itemAdapter.ItemViewHolder>() {

     inner  class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {   //made inner class
        var itemsInTheList: TextView = view.findViewById(R.id.tvItem)
        var timeView: TextView = view.findViewById(R.id.tvTime)

        fun deleteItem(index: Int){
            var deleteButton:Button=view.findViewById(R.id.deleteBtn)
            deleteButton.setOnClickListener { onclickDelete(index) }   // calling lambda function from main class primary constructor
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)



        return ItemViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item=dataOfItems[position]
        holder.itemsInTheList.text=item.item
        holder.timeView.text=item.time
        holder.deleteItem(position)  //passing value of position to deleteItem function

    }


    override fun getItemCount(): Int {
        return  dataOfItems.size
    }



}