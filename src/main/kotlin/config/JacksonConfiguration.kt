package config

import com.fasterxml.jackson.databind.*

fun ObjectMapper.jacksonConfiguration() =
    apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        enable(SerializationFeature.INDENT_OUTPUT)
    }
