package com.bilandzija.restcloudservices.userservicedemo.service

import com.bilandzija.restcloudservices.userservicedemo.restClient.MailClient
import com.bilandzija.restcloudservices.userservicedemo.dao.UserRespository
import com.bilandzija.restcloudservices.userservicedemo.model.Email
import com.bilandzija.restcloudservices.userservicedemo.model.UserEntity
import com.bilandzija.restcloudservices.userservicedemo.restClient.MailClientService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
@Transactional
class UserService(
        private val userRespository: UserRespository,
        private val mailClientService: MailClientService
) {

    // MOST SIMPLE LOGGING. NOT PERFORMANT, BUT FINE HERE.
    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun getUserByEmail(email: Email?): UserEntity {
        requireNotNull(email) { "Mandatory request param must be provided." }
        val user = userRespository.findById(email)
        if (user.isEmpty) {
            logger.info("No user found for id: $email")
            // TODO: Return NOT_FOUND here
            throw IllegalStateException("User not found by email: $email")
        }
        logger.info("User successfully found for id: $email")
        return user.get()
    }

    fun getAllUsers(): List<UserEntity> {
        logger.info("Fetching all users from DB.")
        return userRespository.findAll().toList()
    }

    fun createUser(userEntity: UserEntity?) {
        requireNotNull(userEntity) { "Mandatory request body must be provided." }
        check(userRespository.findById(userEntity.email).isEmpty) { "User already exists. Email: ${userEntity.email}." }
        userRespository.save(userEntity).run {
            logger.info("New user created. Email: ${this.email}.")
        }
        mailClientService.sendMail().also { logger.info("Mail service successfully triggered.") }
    }

    fun updateUser(userEntity: UserEntity?) {
        requireNotNull(userEntity) { "Mandatory request body must be provided." }
        check(userRespository.findById(userEntity.email).isPresent) { "User does exist. Update rejected. Email: ${userEntity.email}." }
        userRespository.save(userEntity).run {
            logger.info("User updated. Email: ${this.email}.")
        }
        mailClientService.sendMail().also { logger.info("Mail service successfully triggered.") }
    }

    fun deleteUser(email: Email?) {
        requireNotNull(email) { "Mandatory request param must be provided." }
        userRespository.findById(email).ifPresent {
            userRespository.delete(it).also {
                logger.info("User successfully deleted. Email: $email.")
                mailClientService.sendMail().also { logger.info("Mail service successfully triggered.") }
            }
        }
        logger.info("User not found. Delete rejected. Email: $email.")
    }
}
