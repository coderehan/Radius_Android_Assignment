package com.rehan.radiusandroidassignment.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rehan.radiusandroidassignment.api.FacilitiesAPI
import com.rehan.radiusandroidassignment.models.Facilities
import com.rehan.radiusandroidassignment.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class FacilityRepository @Inject constructor(private val facilitiesAPI: FacilitiesAPI) {

    private val _facilitiesResponseMutableLiveData = MutableLiveData<NetworkResult<Facilities>>()
    val facilitiesResponseLiveData: LiveData<NetworkResult<Facilities>>
        get() = _facilitiesResponseMutableLiveData

    suspend fun getFacilities() {
        _facilitiesResponseMutableLiveData.postValue(NetworkResult.Loading())
        val response = facilitiesAPI.getFacilities()

        if (response.isSuccessful && response.body() != null) {
            _facilitiesResponseMutableLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObject = JSONObject(response.errorBody()!!.charStream().readText())
            _facilitiesResponseMutableLiveData.postValue(NetworkResult.Error(errorObject.getString("message")))
        } else {
            _facilitiesResponseMutableLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}