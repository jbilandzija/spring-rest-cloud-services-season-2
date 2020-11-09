package com.bilandzija.restcloudservices.userservicedemo.restClient

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping


@FeignClient(name = "demo-mail-service", url="localhost:8883")
interface MailClient {

    @get:GetMapping("/send-mail")
    val sendMail: Unit
}
