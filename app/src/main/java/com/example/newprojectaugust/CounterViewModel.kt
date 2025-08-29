package com.example.newprojectaugust

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CounterViewModel(
    private val repository: CounterRepository
): ViewModel() {
    private val _count = MutableStateFlow(0)
    val count: StateFlow<Int> = _count.asStateFlow()

    init {
        viewModelScope.launch {
            repository.counter.collect { value ->  _count.value = value }
        }
    }
    fun increment(){
        val newValue = _count.value + 1
        _count.value = newValue
        viewModelScope.launch {
            repository.setCounter(newValue)
        }
    }

    fun reset(){
        _count.value = 0
        viewModelScope.launch {
            repository.setCounter(0)
        }
    }
}