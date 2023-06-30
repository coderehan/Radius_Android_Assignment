package com.rehan.radiusandroidassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rehan.radiusandroidassignment.repositories.FacilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacilityViewModel @Inject constructor(private val facilityRepository: FacilityRepository) : ViewModel() {

    val facilitiesLiveData get() = facilityRepository.facilitiesResponseLiveData

    fun getFacilities() {
        viewModelScope.launch {
            facilityRepository.getFacilities()
        }
    }
}