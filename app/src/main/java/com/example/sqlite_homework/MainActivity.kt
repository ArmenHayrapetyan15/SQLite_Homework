package com.example.sqlite_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.sqlite_homework.db.MyDbManager
import com.example.sqlite_homework.db.MyDbNameClass

class MainActivity : AppCompatActivity() {

    lateinit var edtTitle: EditText
    lateinit var edtContent: EditText
    lateinit var tvTest: TextView

    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtTitle = findViewById(R.id.edtTitle)
        edtContent = findViewById(R.id.edtContent)
        tvTest = findViewById(R.id.tvTest)
    }

    override fun onResume() {
        super.onResume()
        myDbManager.openDb()
        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            tvTest.append("\n")
            tvTest.append(item)
        }
    }

    fun onClickSave(view: View) {
        tvTest.text = ""

        myDbManager.openDb()
        myDbManager.insertToDb(edtTitle.text.toString(), edtContent.text.toString())
        val dataList = myDbManager.readDbData()
        for (item in dataList) {
            tvTest.append("\n")
            tvTest.append(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDbManager.closeDb()
    }
}