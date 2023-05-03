package com.spring.user.model.user

import javax.validation.constraints.NotBlank

class SignInRequestDto(

    @get: NotBlank var userName: String,
    @get: NotBlank var password: String,
) {}