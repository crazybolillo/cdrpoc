package github.crazybolillo.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.cors.routing.*

fun Application.configureCors(config: ApplicationConfig) {
    install(CORS) {
        exposeHeader(HttpHeaders.XTotalCount)
        allowHost(config.property("cors.host").getString())
    }
}
