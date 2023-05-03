package com.spring.user.service

import com.google.gson.Gson
import com.spring.user.model.product.ManufacturerRequestDto
import com.spring.user.model.product.ProductRequestDto
import com.spring.user.util.GraphQLdocuments
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.client.HttpGraphQlClient
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


@Service
class ProductServiceImpl : ProductService {

    @Autowired
    lateinit var webClient: WebClient

    @Autowired
    lateinit var graphQlDocument: GraphQLdocuments

    @Autowired
    lateinit var graphQlClient: HttpGraphQlClient

    companion object : KLogging() {
        const val PRODUCT_SERVICE_ADD_FUNCTION = "/addNewProduct"
        const val PRODUCT_SERVICE_GET_PRODUCT_FUNCTION = "/getProductByName"
        const val PRODUCT_SERVICE_GET_MANUFACTURER_FUNCTION = "/getManufacturerDetailsByName"
    }

    override fun addNewProduct(product: ProductRequestDto): ProductRequestDto? {

        logger.info("[user service : addNewProduct ] dto : ${Gson().toJson(product)}")
        val mutationDoc = graphQlDocument.getAddProductMutationDocument(product)

        return graphQlClient
            .document(mutationDoc)
            .retrieve(PRODUCT_SERVICE_ADD_FUNCTION)
            .toEntity(ProductRequestDto::class.java)
            .block()
    }


    override fun findProductsByProductName(productName: String): MutableList<ProductRequestDto>? {

        logger.info("[user service : findProductsByProductName ] productName : $productName")
        val productListDocument = graphQlDocument.getProductListQueryDocument(productName)

        return graphQlClient.document(productListDocument)
            .retrieve(PRODUCT_SERVICE_GET_PRODUCT_FUNCTION)
            .toEntityList(ProductRequestDto::class.java)
            .block()

    }

    override fun findManufacturerDetailsByName(manufacturerName: String): MutableList<ManufacturerRequestDto>? {

        logger.info("[user service : findManufacturerDetailsByName ] manufacturerName : $manufacturerName")
        val manufactureListDocument = graphQlDocument.getManufacturerListQueryDocument(manufacturerName)

        return graphQlClient.document(manufactureListDocument)
            .retrieve(PRODUCT_SERVICE_GET_MANUFACTURER_FUNCTION)
            .toEntityList(ManufacturerRequestDto::class.java)
            .block()
    }
}