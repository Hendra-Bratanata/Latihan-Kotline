package com.example.ares.footballclub

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.abc_activity_chooser_view.*
import org.jetbrains.anko.*

class Main2Activity : AppCompatActivity() {
    private var name: String = ""
    private var img: Int = 0
    private var info: String = ""
    lateinit var nameTextView: TextView
    lateinit var nameGambar: ImageView
    lateinit var nameInfo : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ID_name = 2
        val ID_GBR = 1
        val ID_Info = 3
        relativeLayout{
            padding = dip(16)

            nameGambar = imageView{
                id = ID_GBR }.lparams(width = 200,height = 200) {
                        alignParentTop()
                    centerHorizontally()}

            nameTextView = textView{
                id = ID_name
                textColor = Color.BLACK
                textSize = 16f}.lparams(width = wrapContent){
                below(ID_GBR)
                centerHorizontally() }

            nameInfo =  textView {
                id=ID_Info
            }.lparams( width= wrapContent){
                below(ID_name)
                topMargin = dip(10)

            }


        }

        val intent = intent
        name = intent.getStringExtra("name")
        img = intent.getIntExtra("gambar",0)
        info = intent.getStringExtra("info")
        nameTextView.text = name
        nameGambar.setImageResource(img)
        nameInfo.text = info
    }

}
