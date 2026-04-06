package com.example.quakewatch.data.source.network

import android.util.Log
import com.example.quakewatch.domain.util.NetworkError
import com.example.quakewatch.domain.util.Result
import okio.IOException
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class EarthquakeNetworkDataSource @Inject constructor(
    private val quakeWatchService: QuakeWatchService
) : NetworkDataSource {

    override suspend fun loadEarthquakes(): Result<List<NetworkEarthquake>, NetworkError> {
        try {
            val response = quakeWatchService.loadEarthquakes()
            if (response.isSuccessful) {
                Log.d("RETROFIT_REQUEST", "Success")
                return Result.Success(response.body()?.networkEarthquakes ?: emptyList())
            }
        } catch (e: HttpException) {
            // specifically catches non-2xx responses
            val errorBody = e.response()?.errorBody()?.string()
            val code = e.code()
            Log.d("API_ERROR", "Code: $code, Body: $errorBody")
            return Result.Error(NetworkError.API_ERROR)
        } catch (e: IOException) {
            // catches network/timeout issues
            Log.d("NETWORK_ERROR", "Check your internet connection")
            return Result.Error(NetworkError.NETWORK_ERROR)
        } catch (e: Exception) {
            // catches everything else (like parsing errors)
            Log.d("UNKNOWN_ERROR", e.localizedMessage ?: "")
            return Result.Error(NetworkError.UNKNOWN_ERROR)
        }
        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }
}