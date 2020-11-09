package com.bilandzija.restcloudservices.userservicedemo.model

import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity (

        @Id
        @Column(name = "email")
        val email: Email,

        @Column(name = "first_name")
        val firstName: String,

        @Column(name = "last_name")
        val lastName: String?,

        @Column(name = "age")
        val age: Int?,

        @Column(name = "gender")
        val gender: String?
)
