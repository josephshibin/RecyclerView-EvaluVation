package com.example.recyclerviewassessment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewassessment.itemAdapter.itemAdapter
import com.example.recyclerviewassessment.model.itemData
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    lateinit var editItem:EditText
    lateinit var doneButton:Button
    lateinit var recyclerView: RecyclerView
    // private lateinit var myList:ArrayList<itemData>
    var myList: ArrayList<itemData> = ArrayList<itemData>()
       @RequiresApi(Build.VERSION_CODES.O)
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       recyclerView=findViewById(R.id.Recycler_View)
        editItem=findViewById(R.id.etItem)
        doneButton=findViewById(R.id.doneBtn)
           recyclerView.layoutManager= LinearLayoutManager(this@MainActivity)  // design of the recycler view
           val adapter=itemAdapter(this@MainActivity,myList) { index -> onclickDelete(index) }  //  assigning adapter class to flag
           recyclerView.adapter=adapter //connecting recycler view to adapter


        doneButton.setOnClickListener {
            addItemAndTime()
            editItem.setText("");
//          adapter.notifyDataSetChanged()



        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addItemAndTime() {
       val items=editItem.text.toString()
         // Log.i("item",items)
         val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatted = currentTime.format(formatter)
         // Log.i("time", formatted)
         myList.add(itemData(items,formatted))
        recyclerView.adapter?.notifyItemInserted(myList.size-1)
            // Log.i("my list", myList.toString())


    }

    fun onclickDelete(index:Int){
        myList.removeAt(index)
        // recyclerView.adapter?. notifyDataSetChanged()
        recyclerView.adapter?.notifyItemRemoved(index)

    }
}