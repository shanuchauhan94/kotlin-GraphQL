package com.spring.user.repository

import com.spring.user.model.user.UserInfoDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserInfoDetails, Long> {

    fun findByEmail(email: String): UserInfoDetails

}
