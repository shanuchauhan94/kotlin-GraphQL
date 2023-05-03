package com.spring.user.util

import com.spring.user.model.product.ProductRequestDto
import org.springframework.stereotype.Component

@Component
class GraphQLdocuments {

    fun getAddProductMutationDocument(productRequest: ProductRequestDto): String {

        return """
            mutation{
                addNewProduct(
                  product : {
                    productName: "${productRequest.productName}",
                    productType : "${productRequest.productType}",
                    manufacturer : {
                      manufacturerName: "${productRequest.manufacturer.manufacturerName}",
                      manufacturerOrigin: "${productRequest.manufacturer.manufacturerOrigin}",
                      userRatings: ${productRequest.manufacturer.userRatings},
                      noOfProductAvailable: ${productRequest.manufacturer.noOfProductAvailable},
                      annualRevenue : ${productRequest.manufacturer.annualRevenue}
                    },
                    unitSoldSoFar : ${productRequest.unitSoldSoFar},
                    price : ${productRequest.price}
                  }
                )
                {
            productName
            productType 
            unitSoldSoFar
            price
            manufacturer{
                annualRevenue
                manufacturerName
                manufacturerOrigin
                noOfProductAvailable
                userRatings
            }
                }
            }
            
        """.trimIndent()
    }

    fun getProductListQueryDocument(productName: String): String {
        return """
        query {
            getProductByName(name : "$productName"){
              price
              productName
              productType
              unitSoldSoFar
              manufacturer{
                annualRevenue
                manufacturerName
                manufacturerOrigin
                noOfProductAvailable
                userRatings
              }
            }
        }
    """.trimIndent()
    }

    fun getManufacturerListQueryDocument(manufacturerName: String): String {

        return """
            query{
                getManufacturerDetailsByName(
                    name : "$manufacturerName"){
                    annualRevenue
                    manufacturerName
                    manufacturerOrigin
                    noOfProductAvailable
                    userRatings
                }
            }
        """.trimIndent()
    }

}