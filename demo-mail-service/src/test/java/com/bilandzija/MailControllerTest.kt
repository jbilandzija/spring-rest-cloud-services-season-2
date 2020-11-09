package com.bilandzija

import io.quarkus.mailer.MockMailbox
import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject


@QuarkusTest
open class MailControllerTest {
    private val target = "foo@quarkus.io"

    @Inject
    lateinit var mailbox: MockMailbox

    @BeforeEach
    open fun init() {
        mailbox.clear()
    }

    @Test
    open fun testTextMail() {
        given()
                .`when`()
                .get("/send-mail")
                .then()
                .statusCode(202)
    }
}