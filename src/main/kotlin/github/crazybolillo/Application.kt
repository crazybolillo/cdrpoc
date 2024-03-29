package github.crazybolillo

import github.crazybolillo.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases(environment.config)
    configureRouting()
    configureCors(environment.config)
}
