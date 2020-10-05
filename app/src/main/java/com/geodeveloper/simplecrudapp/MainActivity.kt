package com.geodeveloper.simplecrudapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geodeveloper.simplecrudapp.database.MyDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  val dao = MyDatabase.getInstance(application).subscriberDAO
    }
}