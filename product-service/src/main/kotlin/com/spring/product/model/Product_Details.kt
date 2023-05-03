package com.spring.product.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "product_details")
data class Product_Details(

    @Id var id: Long,
    var productName: String,
    var productType: String,
    var unitSoldSoFar: Int,
    var price: Int,
    var manufacturer: Manufacture_Details

    ) {}