package com.spring.user.util

import com.google.gson.Gson
import com.spring.user.model.user.SignupRequestDto
import com.spring.user.model.user.UserInfoDetails
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class UserMapper : ModelMapper<SignupRequestDto, UserInfoDetails> {

    companion object : KLogging()

    override fun fromEntity(entity: UserInfoDetails): SignupRequestDto {

        logger.info(" in mapper -> fromEntity : ${Gson().toJson(entity)}")

        with(entity) {
            return SignupRequestDto(
                firstName, lastName, gender, email, contact, register, login
            )
        }
    }

    override fun toEntity(dto: SignupRequestDto): UserInfoDetails {

        logger.info(" in mapper -> toEntity : ${Gson().toJson(dto)}")
        with(dto) {
            return UserInfoDetails(
                0, firstName, lastName, gender, email, contact, "$firstName$contact", false, false
            )
        }

    }

}