package com.spring.product.model

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "database_sequences")
class DatabaseSequence(
    var id: String,
    var seq: Long,
) {
}