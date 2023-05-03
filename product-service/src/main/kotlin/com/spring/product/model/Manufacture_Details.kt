package com.spring.product.model

data class Manufacture_Details(

    var id: Long,
    var manufacturerName: String,
    var manufacturerOrigin: String,
    var userRatings: Int,
    var noOfProductAvailable: Int,
    var annualRevenue: Int

    ) {}