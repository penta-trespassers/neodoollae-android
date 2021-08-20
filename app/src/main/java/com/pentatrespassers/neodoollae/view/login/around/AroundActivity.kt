package com.pentatrespassers.neodoollae.view.login.around

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.pentatrespassers.neodoollae.R
import com.pentatrespassers.neodoollae.databinding.FragmentAroundBinding
import splitties.fragments.fragmentTransaction

class AroundActivity : AppCompatActivity() {

    //  var mapfragment: MapFragment = supportFragmentManager.findFragmentById(R.id.mapViewAround) as MapFragment

    private val bind by lazy {
        FragmentAroundBinding.inflate(layoutInflater)
    }

    private val mapfragment by lazy {
        MapFragment.newInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(bind) {
            setContentView(root)



        }
        bind.AroundLayout.setOnClickListener{
            bind.mapSearchViewAround.isVisible = true
        }
    }
}