package com.spring.product.util

import com.google.gson.Gson
import com.spring.product.model.ProductRequestDto
import com.spring.product.model.Product_Details
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductMapper : RequestMapper<ProductRequestDto, Product_Details> {

    companion object : KLogging() {
        val SEQUENCE_NAME: String = "users_sequence"
    }

    @Autowired
    lateinit var mapper: ManufacturerMapper

    @Autowired
    lateinit var seqGenerator: SequenceGeneratorService

    override fun fromEntity(entity: Product_Details): ProductRequestDto {

        logger.info(" in mapper -> fromEntity : ${Gson().toJson(entity)}")

        with(entity) {
            return ProductRequestDto(
                productName, productType, mapper.fromEntity(manufacturer), unitSoldSoFar, price
            )
        }
    }

    override fun toEntity(dto: ProductRequestDto): Product_Details {

        logger.info(" in mapper -> toEntity : ${Gson().toJson(dto)}")
        with(dto) {
            return Product_Details(
                seqGenerator.generateSequence(SEQUENCE_NAME),
                productName,
                productType,
                unitSoldSoFar,
                price,
                mapper.toEntity(manufacturer)
            )
        }

    }

}