package github.crazybolillo.plugins

import github.crazybolillo.controllers.CallDetailController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/cdr") {
            get {
                CallDetailController.list(call)
            }
            get ("/{id}") {
                CallDetailController.get(call)
            }
        }
    }
}
