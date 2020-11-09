package com.bilandzija.restcloudservices.userservicedemo.model

import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import javax.persistence.Embeddable


@Embeddable
class Email(@get:JsonValue val email: String): Serializable {
    init {
        require(email.isNotBlank())
    }

    override fun toString() = email
}

fun String.toEmail() = Email(this)
fun Email.toEmail() = Email(this.email)
