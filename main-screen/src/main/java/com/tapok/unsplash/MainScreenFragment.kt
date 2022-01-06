package com.tapok.unsplash

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.tapok.unsplash.databinding.MainScreenBinding

class MainScreenFragment: Fragment(R.layout.main_screen) {
    private val binding by viewBinding(MainScreenBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}