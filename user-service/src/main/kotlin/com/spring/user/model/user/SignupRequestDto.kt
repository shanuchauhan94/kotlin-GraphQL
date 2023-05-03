package com.spring.user.model.user

import com.spring.user.enums.GenderType
import javax.validation.constraints.NotBlank

class SignupRequestDto(

    @get: NotBlank var firstName: String,
    var lastName: String,
    var gender: GenderType,
    @get: NotBlank var email: String,
    @get: NotBlank var contact: Long,
    var register : Boolean,
    var login : Boolean
) {}