package com.vascomm.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vascomm.sample.module.ActivitySampleModule
import com.vascomm.sample.validation.ValidationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setView()
    }


    fun setView(){
        btn_sample_validation.setOnClickListener {
            val intent = Intent(this,ValidationActivity::class.java)
            startActivity(intent)
        }

        btn_sample_module.setOnClickListener {
            val intent  = Intent(this,ActivitySampleModule::class.java)
            startActivity(intent)
        }
    }
}
