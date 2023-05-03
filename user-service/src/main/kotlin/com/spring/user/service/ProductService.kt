package com.spring.user.service

import com.spring.user.model.product.ManufacturerRequestDto
import com.spring.user.model.product.ProductRequestDto

interface ProductService {

    fun addNewProduct(product: ProductRequestDto): ProductRequestDto?
    fun findProductsByProductName(productName: String): MutableList<ProductRequestDto>?
    fun findManufacturerDetailsByName(manufacturerName: String): MutableList<ManufacturerRequestDto>?

}