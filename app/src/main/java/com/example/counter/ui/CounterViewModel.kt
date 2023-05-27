package com.example.counter.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

const val MAX_COUNT = 5

class PlayViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CounterUiState());
    val uiState: StateFlow<CounterUiState> = _uiState.asStateFlow();

    var msg by mutableStateOf("")
        private set
    init {
        reset()
    }

    fun reset() {
        _uiState.value = CounterUiState(msg = msg)
    }

    fun updateMsg(newMsg: String ) {
        msg = newMsg
//      this also works
//        _uiState.update {
//            it.copy(scrambledWord = msg)
//        }
        _uiState.value = CounterUiState(msg = msg)
    }

    fun increaseCount() {

        if (_uiState.value.count >= MAX_COUNT) {
            _uiState.update {
                it.copy(done = true)
            }
        } else {
            _uiState.update {
                it.copy(count = it.count + 1)
            }
        }
    }

    fun decreaseCount() {
        _uiState.update {
            it.copy(count = it.count - 1)
        }
    }
}