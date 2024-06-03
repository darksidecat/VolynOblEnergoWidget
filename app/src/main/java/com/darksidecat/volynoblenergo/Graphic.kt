package com.darksidecat.volynoblenergo

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.widget.RemoteViews
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.AppWidgetTarget
import com.bumptech.glide.request.transition.Transition
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


/**
 * Implementation of App Widget functionality.
 */
class Graphic : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {

    val views = RemoteViews(context.packageName, R.layout.graphic)

    val c: Date = Calendar.getInstance().time
    val c2 = Calendar.getInstance()
    c2.add(Calendar.DATE, 1)
    val c2time = c2.time

    val df = SimpleDateFormat("dd-MM-yy", Locale.getDefault())
    val formattedDate: String = df.format(c)
    val formattedDate2: String = df.format(c2time)

    val awt: AppWidgetTarget =
        object : AppWidgetTarget(context.applicationContext, R.id.dataChart, views, appWidgetId) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                super.onResourceReady(resource, transition)
            }
        }

    Glide
        .with(context.applicationContext)
        .asBitmap()
        .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate-3.jpg")
        .error(
            Glide.with(context.applicationContext).asBitmap()
                .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate-2.jpg")
                .error(
                    Glide.with(context.applicationContext).asBitmap()
                        .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate-1.jpg")
                        .error(
                            Glide.with(context.applicationContext).asBitmap()
                                .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate.jpg")
                        )
                )
        )
        .into(awt)

    val awt2: AppWidgetTarget =
        object : AppWidgetTarget(context.applicationContext, R.id.dataChart2, views, appWidgetId) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                super.onResourceReady(resource, transition)
            }
        }

    Glide
        .with(context.applicationContext)
        .asBitmap()
        .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate2-3.jpg")
        .error(
            Glide.with(context.applicationContext).asBitmap()
                .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate2-2.jpg")
                .error(
                    Glide.with(context.applicationContext).asBitmap()
                        .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate2-1.jpg")
                        .error(
                            Glide.with(context.applicationContext).asBitmap()
                                .load("https://energy.volyn.ua/spozhyvacham/perervy-u-elektropostachanni/hrafik-vidkliuchen/!img/$formattedDate2.jpg")
                        )
                )
        )
        .into(awt2)
    appWidgetManager.updateAppWidget(appWidgetId, views)

}