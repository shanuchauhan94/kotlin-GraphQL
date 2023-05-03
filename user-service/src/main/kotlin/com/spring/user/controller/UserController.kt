package com.spring.user.controller

import com.google.gson.Gson
import com.spring.user.model.product.ManufacturerRequestDto
import com.spring.user.model.product.ProductRequestDto
import com.spring.user.model.user.SignInRequestDto
import com.spring.user.model.user.SignupRequestDto
import com.spring.user.service.ProductService
import com.spring.user.service.UserService
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/expedia")
@Validated
class UserController {

    companion object : KLogging()

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var productService: ProductService

    @PostMapping("/user/signup")
    fun savedSignUpUserData(@RequestBody @Valid requestedUser: SignupRequestDto): ResponseEntity<MutableMap<String, Any>> {

        logger.info("savedSignUpUserData : ${requestedUser.email}")
        val response = userService.saveUserRecord(requestedUser);

        if (response.isPresent) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.get())
        }
        throw RuntimeException("Invalid request! Try again.");
    }

    @PostMapping("/user/login")
    fun validateLoginUser(@RequestBody @Valid credentials: SignInRequestDto): ResponseEntity<Boolean> {

        val isValid = userService.authenticateLoginUser(credentials)
        if (isValid) {
            return ResponseEntity.status(HttpStatus.OK).body(true)
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false)
    }

    @GetMapping("/user/all")
    fun getAllRegisteredUsers(): ResponseEntity<List<SignupRequestDto>> {

        logger.info("getAllRegisteredUsers :")
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getAllUserRecords())
    }

    @PostMapping("/product/add")
    fun addNewProduct(@RequestBody product: ProductRequestDto, @RequestHeader(value = "userEmail") userEmail : String): ResponseEntity<ProductRequestDto> {
        logger.info("addNewProduct  json : ${Gson().toJson(product)}")
        val isValidUser = userService.validateUser(userEmail)
        return if (isValidUser){
            val response = productService.addNewProduct(product)
            ResponseEntity.status(HttpStatus.CREATED).body(response)
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }

    }

    @GetMapping("/product/{productName}")
    fun getProductDetailsByName(@PathVariable("productName") productName: String, @RequestHeader(value = "userEmail") userEmail : String): ResponseEntity<List<ProductRequestDto>> {

        logger.info("getProductDetailsByName  productName : $productName")
        val isValidUser = userService.validateUser(userEmail)
        return if (isValidUser){
             ResponseEntity.status(HttpStatus.CREATED).body(productService.findProductsByProductName(productName))
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    }

    @GetMapping("/manufacturer/{manufacturerName}")
    fun getManufacturerDetailsByName(@PathVariable("manufacturerName") manufacturer: String,@RequestHeader(value = "userEmail") userEmail : String): ResponseEntity<List<ManufacturerRequestDto>> {

        logger.info("getManufacturerDetailsByName  manufacturerName : $manufacturer")
        val isValidUser = userService.validateUser(userEmail)
        return if (isValidUser){
            ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.findManufacturerDetailsByName(manufacturer))
        }else{
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null)
        }
    }

}