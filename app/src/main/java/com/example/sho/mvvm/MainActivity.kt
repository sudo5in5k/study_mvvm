package com.example.sho.mvvm

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sho.mvvm.dagger.ChocolateCake
import com.example.sho.mvvm.dagger.DaggerCakeComponent
import com.example.sho.mvvm.rxkotlin.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var cake: ChocolateCake

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerCakeComponent.create().inject(this)
        this.cake.make()

        to_login_activity.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
