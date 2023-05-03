package com.spring.product.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.google.gson.Gson
import com.spring.product.model.ProductRequestDto
import com.spring.product.repository.ProductRepository
import com.spring.product.util.ProductMapper
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MutationResolver : GraphQLMutationResolver {

    companion object : KLogging()

    @Autowired
    lateinit var repo: ProductRepository

    @Autowired
    lateinit var productMapper: ProductMapper


    @MutationMapping
    fun addNewProduct(@Argument product: ProductRequestDto): ProductRequestDto {

        logger.info { "request schema : ${Gson().toJson(product)}" }
        val entity = productMapper.toEntity(product)
        val data = repo.save(entity)

        return productMapper.fromEntity(data)
    }

}