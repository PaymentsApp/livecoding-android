package io.dnatechnology.dnataskandroid.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.dnatechnology.dnataskandroid.R
import io.dnatechnology.dnataskandroid.ui.compose.RootComposeActivity
import io.dnatechnology.dnataskandroid.ui.viewbased.RootViewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.main_button_view_based).setOnClickListener {
            startActivity(Intent(this.baseContext, RootViewActivity::class.java))
        }

        findViewById<Button>(R.id.main_button_compose_based).setOnClickListener {
            startActivity(Intent(this.baseContext, RootComposeActivity::class.java))
        }
    }
}
