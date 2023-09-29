package com.aamirashraf.onetimeevents_usingchannel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    sealed interface MyEvent{
        //we may use sealed class also
        //but sealed interface is more memory optimize than class
        //when there is no argument need to pass in the contructor
        //it is good practice to go with the sealed interface instead of the sealed class
        data class ErrorEvent(val message:String):MyEvent
    }
    private val channel= Channel<MyEvent>()
    val eventFlow=channel.receiveAsFlow()   //we can receive events as flows

    fun triggerEvent()=viewModelScope.launch {
        channel.send(MyEvent.ErrorEvent("This is the Error Event"))   //send is the suspend function
    }

}