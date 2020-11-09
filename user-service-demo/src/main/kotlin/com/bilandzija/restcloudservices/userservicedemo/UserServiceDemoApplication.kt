package com.bilandzija.restcloudservices.userservicedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.openfeign.EnableFeignClients


@EnableFeignClients
@SpringBootApplication
class UserServiceDemoApplication

fun main(args: Array<String>) {
	runApplication<UserServiceDemoApplication>(*args)
}
