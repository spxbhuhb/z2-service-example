package hu.simplexion.z2.service.example

import hu.simplexion.z2.service.ktor.server.basicWebsocketServiceCallTransport
import hu.simplexion.z2.service.runtime.defaultServiceProviderRegistry
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import java.time.Duration

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {

    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(20)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    val provider = ClickServiceProvider()
    defaultServiceProviderRegistry[provider.serviceName] = provider

    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        basicWebsocketServiceCallTransport("/z2/services")
    }
}