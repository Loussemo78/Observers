package com.example.observers

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _stringListLiveData = StringListLiveData()
    val stringListLiveData:LiveData<List<String>>
    get() = _stringListLiveData


    fun addString(string: String) {
        _stringListLiveData.addString(string)
    }
}