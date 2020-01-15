package com.example.meteoParis.Main

import android.content.Context
import com.example.meteoParis.Model.ForecastModel

interface HomeContract  {
    interface View {
        fun onDataFetched(forecastModel: ForecastModel?)

        fun onStoredDataFetched(forecastModel: ForecastModel?)

        fun onError()

        fun getContext(): Context
    }

    interface Presenter {
        fun subscribe(view: View)

        fun unSubscribe()

        fun refresh(lat: Double, long: Double);
    }
}