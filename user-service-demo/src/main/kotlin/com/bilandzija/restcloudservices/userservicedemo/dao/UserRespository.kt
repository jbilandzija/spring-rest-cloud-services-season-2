package com.bilandzija.restcloudservices.userservicedemo.dao

import com.bilandzija.restcloudservices.userservicedemo.model.Email
import com.bilandzija.restcloudservices.userservicedemo.model.UserEntity
import org.springframework.data.repository.CrudRepository


interface UserRespository: CrudRepository<UserEntity, Email>
