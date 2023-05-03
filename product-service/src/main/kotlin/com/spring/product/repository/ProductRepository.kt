package com.spring.product.repository

import com.spring.product.model.Product_Details
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : MongoRepository<Product_Details, Long> {

    @Query("{productName: /?0/}")
    fun findProductDetailsByProductName(productName: String): List<Product_Details>

    fun findByManufacturerManufacturerName(name: String): List<Product_Details>

}