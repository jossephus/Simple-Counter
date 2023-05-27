package com.example.counter.ui;

data class CounterUiState (
        val msg: String = "",
        val count: Int = 0,
        val done: Boolean = false,
);