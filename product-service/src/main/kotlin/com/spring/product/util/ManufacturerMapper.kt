package com.spring.product.util

import com.google.gson.Gson
import com.spring.product.model.Manufacture_Details
import com.spring.product.model.ManufacturerRequestDto
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ManufacturerMapper : RequestMapper<ManufacturerRequestDto, Manufacture_Details> {

    companion object : KLogging() {
        val SEQUENCE_NAME: String = "users_sequence"
    }

    @Autowired
    lateinit var seqGenerator: SequenceGeneratorService

    override fun fromEntity(entity: Manufacture_Details): ManufacturerRequestDto {
        logger.info(" in mapper -> fromEntity : ${Gson().toJson(entity)}")

        with(entity) {
            return ManufacturerRequestDto(
                manufacturerName, manufacturerOrigin, userRatings, noOfProductAvailable, annualRevenue
            )
        }
    }

    override fun toEntity(dto: ManufacturerRequestDto): Manufacture_Details {
        logger.info(" in mapper -> toEntity : ${Gson().toJson(dto)}")
        with(dto) {
            return Manufacture_Details(
                seqGenerator.generateSequence(ProductMapper.SEQUENCE_NAME), manufacturerName, manufacturerOrigin, userRatings, noOfProductAvailable, annualRevenue
            )
        }

    }
}