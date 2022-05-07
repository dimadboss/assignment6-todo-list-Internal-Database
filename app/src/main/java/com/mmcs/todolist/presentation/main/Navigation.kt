package com.mmcs.todolist.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.mmcs.todolist.presentation.todo_list.TodoListScreen


@ExperimentalAnimationApi
private fun slideInAnimation(): (AnimatedContentScope<NavBackStackEntry>.() -> EnterTransition?) =
    { slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000)) }

@ExperimentalAnimationApi
private fun slideOutAnimation(): (AnimatedContentScope<NavBackStackEntry>.() -> ExitTransition?) =
    { slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000)) }

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalUnitApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun Navigation(
    navController: NavHostController,
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = AppScreen.TodoListScreen.route
    ) {
        composable(
            route = AppScreen.TodoListScreen.route,
            enterTransition = slideInAnimation(),
            exitTransition = slideOutAnimation()
        ) {
            TodoListScreen()
        }
    }
}

