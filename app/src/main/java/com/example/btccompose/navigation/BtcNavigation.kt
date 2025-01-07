package com.example.btccompose.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.btccompose.presentation.activity.BtcTopBar
import com.example.btccompose.presentation.screen.DetailScreen
import com.example.btccompose.presentation.screen.HomeScreen
import com.example.btccompose.presentation.screen.SplashScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BtcNavigation(navController: NavHostController = rememberNavController()){

    NavHost(navController = navController, startDestination = SplashNavigate, builder = {
        composable<SplashNavigate> {
            SplashScreen(onSplashFinished = {
                navController.navigate(HomeNavigate){
                    popUpTo(SplashNavigate) { inclusive = true }
                }
            })
        }
        composable<HomeNavigate> {
            HomeScreen(goDetailClick = {
                navController.navigate(DetailScreenModel(it.denominatorSymbol, it.numeratorSymbol, it.symbol))
            })
        }
        composable<DetailScreenModel>{
            val args = it.toRoute<DetailScreenModel>()
            val chart = "${args.denominatorSymbol}/${args.symbol} Chart"
            Scaffold(
                topBar = {
                    BtcTopBar(backScreenClick = { navController.popBackStack() },chart)
                }
            ) {

                DetailScreen(args)
            }
        }

    })

}