package com.aamirashraf.onetimeevents_usingchannel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.aamirashraf.onetimeevents_usingchannel.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel=ViewModelProvider(this)[MainViewModel::class.java]
        binding.btn.setOnClickListener {
            viewModel.triggerEvent()
        }
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect{event->
                when(event){
                    is MainViewModel.MyEvent.ErrorEvent->{
                        Snackbar.make(binding.root,event.message,Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}