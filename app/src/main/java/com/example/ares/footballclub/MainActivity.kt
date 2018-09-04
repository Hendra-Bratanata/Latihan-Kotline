package com.example.ares.footballclub

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items :MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        clubList.layoutManager = LinearLayoutManager(this)
        clubList.adapter = Adapter(this, items) {
            //            val toast = Toast.makeText(applicationContext, it.name,Toast.LENGTH_SHORT)
//            toast.show
          startActivity<Main2Activity>("name" to it.name,"gambar" to it.image,"info" to it.info)


        }
    }

    private fun initData(){
        val info = resources.getStringArray(R.array.club_info)
        val name = resources.getStringArray(R.array.club_name)
        val gambar = resources.obtainTypedArray(R.array.club_image)
        items.clear()

        for (i in name.indices){
            items.add(Item(name[i],gambar.getResourceId(i,0),info[i]))
        }
        gambar.recycle()
    }
}
