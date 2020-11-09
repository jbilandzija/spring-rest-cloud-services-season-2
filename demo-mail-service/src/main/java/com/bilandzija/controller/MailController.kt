package com.bilandzija.controller

import io.quarkus.mailer.Mail
import io.quarkus.mailer.Mailer
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("/send-mail")
class MailController(private val mailer: Mailer) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun sendMail(): Response {
        mailer.send(Mail.withText("example@foo.com", "A simple email from quarkus. Something has changed somewhere.", "This is my body."));
        return Response.accepted().build()
    }
}
