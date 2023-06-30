package com.rehan.radiusandroidassignment.models

data class Facilities(
    val exclusions: List<List<Exclusion>>,
    val facilities: List<Facility>
)