package com.example.moviedb.infrastructure

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

internal abstract class BindingActivity<T : ViewBinding> : AppCompatActivity() {

    val binding: T get() = _binding

    private lateinit var _binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = onCreateBinding(layoutInflater)
        setContentView(binding.root)
    }

    abstract fun onCreateBinding(layoutInflater: LayoutInflater): T
}