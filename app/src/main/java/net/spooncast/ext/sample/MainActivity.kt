package net.spooncast.ext.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import net.spooncast.ext.context.getCountryCode
import net.spooncast.ext.context.toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast(getCountryCode())
    }
}