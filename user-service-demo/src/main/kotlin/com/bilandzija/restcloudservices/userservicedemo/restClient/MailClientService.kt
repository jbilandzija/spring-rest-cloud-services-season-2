package com.bilandzija.restcloudservices.userservicedemo.restClient

import org.springframework.stereotype.Service


@Service
class MailClientService(
        private val mailClient: MailClient
) {

    fun sendMail() {
        mailClient.sendMail
    }
}
