package com.example.urfuandroidpractice.listWithDetails.presentation.screens

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.NavigationViewModel
import com.example.urfuandroidpractice.ui.components.BottomNavBar
import com.github.terrakok.modo.animation.ScreenTransition
import com.github.terrakok.modo.animation.StackTransitionType
import com.github.terrakok.modo.animation.calculateStackTransitionType
import com.github.terrakok.modo.stack.StackNavModel
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Parcelize
class LayoutScreen(
    private val navModel: StackNavModel
) : StackScreen(navModel) {

    @Composable
    override fun Content(modifier: Modifier) {
        val viewModel = koinViewModel<NavigationViewModel> { parametersOf(navModel) }
        val state = viewModel.viewState

        Scaffold(modifier = modifier, topBar = {
            Box {}
        }, bottomBar = {
            BottomNavBar(
                modifier = Modifier,
                currentScreen = state.currentScreen,
                onTabClick = { viewModel.onNavigate(it) }
            )
        }) { innerPadding ->
            TopScreenContent(modifier = Modifier.padding(innerPadding)) { screenModifier ->
                ScreenTransition(
                    modifier = screenModifier,
                    transitionSpec = {
                        val screenTransitionType = calculateStackTransitionType()
                        when (screenTransitionType) {
                            StackTransitionType.Push -> {
                                slideInHorizontally(initialOffsetX = { it }) togetherWith
                                        slideOutHorizontally(targetOffsetX = { -it })
                            }

                            StackTransitionType.Pop -> {
                                slideInHorizontally(initialOffsetX = { -it }) togetherWith
                                        slideOutHorizontally(targetOffsetX = { it })
                            }

                            StackTransitionType.Replace, StackTransitionType.Idle -> fadeIn() togetherWith fadeOut()
                        }
                    },
                )
            }

        }
    }
}