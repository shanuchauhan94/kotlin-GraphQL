package com.spring.user.util

interface ModelMapper<D, E> {

    fun fromEntity(entity: E): D
    fun toEntity(dto: D): E
}