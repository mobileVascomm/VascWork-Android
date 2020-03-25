package com.vascomm.sample.viewstatelayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vascomm.sample.R
import kotlinx.android.synthetic.main.activity_view_state_layout.*

class ViewStateLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_state_layout)

        btn_loading.setOnClickListener {
            layout_viewstate.showLoading()
        }

        val listenerContent = View.OnClickListener {
            layout_viewstate.showContent()
        }

        btn_content.setOnClickListener { layout_viewstate.showContent() }

        btn_error.setOnClickListener {
            val img = getDrawable(R.drawable.ic_signal_wifi_off_black_24dp)!!
            layout_viewstate.showErrorWithImage(img,"Tidak ada Jaringan","Silakan cek kembali koneksi anda.", null, null)
        }

        btn_custom.setOnClickListener {
            layout_viewstate.customErrorView = R.layout.custom_error
            layout_viewstate.showError()
        }
    }
}
