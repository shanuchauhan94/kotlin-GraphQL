package com.spring.user.model.user

import com.spring.user.enums.GenderType
import jakarta.persistence.*

@Entity
@Table(name = "user_details")
data class UserInfoDetails(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,
    var firstName: String,
    var lastName: String,
    var gender: GenderType,
    var email: String,
    var contact: Long,
    var password: String,
    var register : Boolean,
    var login : Boolean
) {}