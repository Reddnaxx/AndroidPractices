package com.example.urfuandroidpractice.listWithDetails.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.urfuandroidpractice.listWithDetails.presentation.state.NavigationState
import com.example.urfuandroidpractice.ui.components.MainTabs
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.forward

class NavigationViewModel(
    private val navigation: StackNavModel
) : ViewModel() {

    private val mutableState = MutableNavigationState()
    val viewState = mutableState as NavigationState

    fun onNavigate(screen: Screen) {
        navigation.forward(screen)
        viewState.currentScreen = screen
    }

    class MutableNavigationState : NavigationState {
        override var currentScreen: Screen by mutableStateOf(MainTabs.LIST.screen)
    }
}