package com.tapok.core

import com.github.terrakok.cicerone.androidx.FragmentScreen

interface Screens {

    fun main(): FragmentScreen
    fun collections(): FragmentScreen
    fun photoDetail(): FragmentScreen
}