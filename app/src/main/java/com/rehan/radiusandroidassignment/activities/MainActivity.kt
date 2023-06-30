package com.rehan.radiusandroidassignment.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rehan.radiusandroidassignment.R
import com.rehan.radiusandroidassignment.databinding.ActivityMainBinding
import com.rehan.radiusandroidassignment.utils.ConnectionLiveData
import com.rehan.radiusandroidassignment.utils.NetworkResult
import com.rehan.radiusandroidassignment.viewmodels.FacilityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var facilitiesViewModel: FacilityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        facilitiesViewModel = ViewModelProvider(this)[FacilityViewModel::class.java]
        observeLiveData()

        val cld = ConnectionLiveData(application)
        isConnected(cld)
    }

    private fun observeLiveData() {
        facilitiesViewModel.getFacilities()
        facilitiesViewModel.facilitiesLiveData.observe(this, Observer {
            binding.progressBar.visibility = View.GONE
            when(it){
                is NetworkResult.Success ->{
                    // Setting Type of Facility from API
                    binding.tvPropertyType.text = it.data?.facilities?.get(0)?.name.toString()
                    binding.tvNumberOfRooms.text = it.data?.facilities?.get(1)?.name.toString()
                    binding.tvOtherFacilities.text = it.data?.facilities?.get(2)?.name.toString()

                    // Setting Property Type Options from API
                    binding.radioBtnApartment.text = it.data?.facilities?.get(0)?.options?.get(0)?.name.toString()
                    binding.radioBtnCondo.text = it.data?.facilities?.get(0)?.options?.get(1)?.name.toString()
                    binding.radioBtnBoatHouse.text = it.data?.facilities?.get(0)?.options?.get(2)?.name.toString()
                    binding.radioBtnLand.text = it.data?.facilities?.get(0)?.options?.get(3)?.name.toString()

                    // Setting Number Of Rooms Options from API
                    binding.radioBtnOneToThreeRooms.text = it.data?.facilities?.get(1)?.options?.get(0)?.name.toString()
                    binding.radioBtnNoRooms.text = it.data?.facilities?.get(1)?.options?.get(1)?.name.toString()

                    // Setting Other Facilities Options from API
                    binding.radioBtnSwimmingPool.text = it.data?.facilities?.get(2)?.options?.get(0)?.name.toString()
                    binding.radioBtnGardenArea.text = it.data?.facilities?.get(2)?.options?.get(1)?.name.toString()
                    binding.radioBtnGarage.text = it.data?.facilities?.get(2)?.options?.get(2)?.name.toString()

                    // Exclusions Conditions

                    // When user selects "Land" radio button from first radio group,
                    // it should disable "1-3 Rooms" radio button from second radio group.
                    // Therefore not allowing user to select "1-3 Rooms" in second radio group.
                    binding.radioBtnLand.setOnClickListener {
                        binding.radioBtnOneToThreeRooms.isClickable = false
                    }
                    binding.radioBtnApartment.setOnClickListener {
                        binding.radioBtnOneToThreeRooms.isClickable = true
                    }
                    binding.radioBtnCondo.setOnClickListener {
                        binding.radioBtnOneToThreeRooms.isClickable = true
                    }
                    binding.radioBtnBoatHouse.setOnClickListener {
                        binding.radioBtnOneToThreeRooms.isClickable = true
                    }

                    // When user selects "Boat House" radio button from first radio group,
                    // it should disable "Garage" radio button from third radio group.
                    // Therefore not allowing user to select "Garage" in third radio group.
                    binding.radioBtnBoatHouse.setOnClickListener {
                        binding.radioBtnGarage.isClickable = false
                    }
                    binding.radioBtnApartment.setOnClickListener {
                        binding.radioBtnGarage.isClickable = true
                    }
                    binding.radioBtnCondo.setOnClickListener {
                        binding.radioBtnGarage.isClickable = true
                    }
                    binding.radioBtnLand.setOnClickListener {
                        binding.radioBtnGarage.isClickable = true
                    }

                    // When user selects "No Rooms" radio button from second radio group,
                    // it should disable "Garage" radio button from third radio group.
                    // Therefore not allowing user to select "Garage" in third radio group.
                    binding.radioBtnNoRooms.setOnClickListener {
                        binding.radioBtnGarage.isClickable = false
                    }
                    binding.radioBtnOneToThreeRooms.setOnClickListener {
                        binding.radioBtnGarage.isClickable = true
                    }
                }
                is NetworkResult.Error ->{
                    Toast.makeText(this@MainActivity, it.message.toString(), Toast.LENGTH_LONG).show()
                }
                is NetworkResult.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun isConnected(cld: ConnectionLiveData) {
        cld.observe(this) { isConnected ->
            if (!isConnected) {
                startActivity(Intent(this, InternetDisconnectedActivity::class.java))
            }
        }
    }

}