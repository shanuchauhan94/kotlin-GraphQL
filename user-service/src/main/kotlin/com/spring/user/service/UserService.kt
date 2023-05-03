package com.spring.user.service

import com.spring.user.model.user.SignInRequestDto
import com.spring.user.model.user.SignupRequestDto
import java.util.*

interface UserService {

    fun saveUserRecord(requestedUser: SignupRequestDto): Optional<MutableMap<String, Any>>
    fun authenticateLoginUser(credentials: SignInRequestDto): Boolean
    fun getAllUserRecords(): List<SignupRequestDto>
    fun validateUser(userName: String): Boolean

}
