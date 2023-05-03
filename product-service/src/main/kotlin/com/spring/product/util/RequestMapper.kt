package com.spring.product.util

interface RequestMapper<D, E> {

    fun fromEntity(entity: E): D
    fun toEntity(dto: D): E
}