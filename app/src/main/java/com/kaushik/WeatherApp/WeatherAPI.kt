package com.kaushik.WeatherApp

import okhttp3.OkHttpClient
import okhttp3.Request

// Create an object class to store the weather API key and base URL.
object WeatherAPI {

    // Create an OkHttpClient object to make HTTP requests.
    private val okHttpClient = OkHttpClient()

    // Define a function to get the weather response for a given city.
    fun getResponse(city: String): Pair<Boolean, String> {

        // Get the weather API key and base URL.

        val BASE_URL = "https://api.weatherapi.com/v1/"
            //"https://api.openweathermap.org/data/2.5/weather?"

        // Construct the full URL for the weather request.
        val API_KEY = "forecast.json?key=81d2f00f32eb4da4b4e45914210708%20&days=10&aqi=yes&alerts=yes"
        //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
        val url = BASE_URL + "appid=$API_KEY&q=$city"
        // Define API_KEY as string

        // Create a Request object for the weather request.
        val request = Request.Builder()
            .url(url)
            .build()

        // Execute the weather request and get the response.
        val response = okHttpClient.newCall(request).execute()

        // Check if the response was successful.
        if (response.isSuccessful) {

            // Get the JSON response body.
            val json = response.body?.string()

            // Return a pair indicating that the request was successful and the JSON response body.
            return Pair(true, json.toString())
        } else {

            // Return a pair indicating that the request failed and the error message.
            return Pair(false, response.message)
        }
    }
}
