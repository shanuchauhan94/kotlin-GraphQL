package com.spring.product.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.spring.product.model.ManufacturerRequestDto
import com.spring.product.model.ProductRequestDto
import com.spring.product.repository.ProductRepository
import com.spring.product.util.ManufacturerMapper
import com.spring.product.util.ProductMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors


@RestController
class QueryResolver : GraphQLQueryResolver {

    @Autowired
    lateinit var repo: ProductRepository

    @Autowired
    lateinit var productMapper: ProductMapper

    @Autowired
    lateinit var manufacturerMapper: ManufacturerMapper

    @QueryMapping
    fun getProductByName(@Argument name: String): MutableList<ProductRequestDto> {

        return repo.findProductDetailsByProductName(name)
            .stream()
            .map {
                productMapper.fromEntity(it)
            }.collect(Collectors.toList())
    }

    @QueryMapping
    fun getManufacturerDetailsByName(@Argument name: String): List<ManufacturerRequestDto> {

        return repo.findByManufacturerManufacturerName(name)
            .stream()
            .filter { Objects.nonNull(it) }
            .map {
                manufacturerMapper.fromEntity(it.manufacturer)
            }.collect(Collectors.toList())
    }

}



