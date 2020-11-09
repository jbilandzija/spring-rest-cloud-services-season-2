package com.bilandzija.restcloudservices.userservicedemo.controller

import com.bilandzija.restcloudservices.userservicedemo.model.Email
import com.bilandzija.restcloudservices.userservicedemo.model.UserEntity
import com.bilandzija.restcloudservices.userservicedemo.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


private const val USER_URL = "/user-api"

@RestController
@RequestMapping(USER_URL)
class UserController(private val userService: UserService) {

    @GetMapping("/user")
    fun getUserByEmail(@RequestParam email: Email?): UserEntity? {
        return userService.getUserByEmail(email)
    }

    @GetMapping("/users")
    fun getUsers(): List<UserEntity> {
        return userService.getAllUsers()
    }

    @PostMapping("/signup")
    fun createUser(@RequestBody userEntity: UserEntity?): ResponseEntity<UserEntity> {
        return userService.createUser(userEntity).let {
            ResponseEntity.status(HttpStatus.CREATED).build()
        }
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody userEntity: UserEntity?): ResponseEntity<UserEntity> {
        return userService.updateUser(userEntity).let {
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }
    }

    @DeleteMapping("/delete")
    fun deleteUser(@RequestParam email: Email): ResponseEntity<UserEntity> {
        return userService.deleteUser(email).let {
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        }
    }
}