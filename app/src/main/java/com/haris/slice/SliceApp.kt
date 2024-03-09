package com.haris.slice

import android.app.Application
import com.haris.data.SliceDatabase
import com.haris.data.restaurants
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class SliceApp : Application() {

    @Inject
    lateinit var db: SliceDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch(Dispatchers.IO) {
            db.restaurantDao().insertAll(restaurants)
        }
    }
}