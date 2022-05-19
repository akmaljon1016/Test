package com.test.com

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.test.com.databinding.ActivityMainBinding
import com.test.com.util.Constants
import com.test.com.util.Preferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var navController:NavController
    @Inject
    lateinit var sharedPreferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Constants.TOKEN_FOR_NETWORKING=sharedPreferences.readBearerToken()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController=findNavController(R.id.mainNavHostFragment)
        Toast.makeText(this, sharedPreferences.readBearerToken(), Toast.LENGTH_SHORT).show()
    }
}