package com.example.quiknews

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.quiknews.presentation.NewsWire.NewsWireBottomBar
import com.example.quiknews.presentation.NewsWire.NewsWireScreen
import com.example.quiknews.presentation.NewsWireViewModel
import com.example.quiknews.ui.theme.OffWhite
import com.example.quiknews.ui.theme.QuikNewsTheme
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsWireViewModel by viewModels()

    var mInterstitialAd:InterstitialAd?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            this.setKeepOnScreenCondition {
                viewModel.isClearing.value
            }
        }
        setContent {

             val adRequest=AdRequest.Builder().build()

            InterstitialAd.load(this,"ca-app-pub-3940256099942544/6300978111",adRequest,object: InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    mInterstitialAd=null
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    mInterstitialAd=p0
                    mInterstitialAd?.show(this@MainActivity)
                }
            })
            QuikNewsTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = { NewsWireBottomBar()}
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize().background(OffWhite),
                    ) {
                        NewsWireScreen()
                    }
                }
            }
        }
    }


}


