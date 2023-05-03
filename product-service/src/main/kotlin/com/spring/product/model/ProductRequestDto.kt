package com.spring.product.model

class ProductRequestDto(

    var productName: String,
    var productType: String,
    var manufacturer: ManufacturerRequestDto,
    var unitSoldSoFar: Int,
    var price: Int
) {}