package com.abdelrahmanmohamed728.loyaltypointsapp

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.abdelrahmanmohamed728.loyaltypointsapp.ILoyaltyInterface
import kotlin.math.max

class LoyaltyService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }


    private val binder = object : ILoyaltyInterface.Stub() {

        override fun getPriceAfterDiscount(
            price: Float,
            discount: Float,
            discountAppliedCallbacks: IDiscountCallback?
        ) {
           val newValue = max(0f,price - discount)
            if (newValue == 0f){
                discountAppliedCallbacks?.onNoRemainingAmount()
            }
            else {
                discountAppliedCallbacks?.onPriceCalculated(newValue)
            }
        }
    }
}
