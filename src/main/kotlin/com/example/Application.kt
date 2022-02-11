package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.context.ServerRequestContext
import io.micronaut.runtime.Micronaut.*
import reactor.core.publisher.Mono
import reactor.util.context.ContextView

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.example")
        .start()
}

@Controller
class Controller() {

    @Get("/test")
    fun test() =
        Mono.deferContextual { ctx: ContextView ->
            val hasKey = ctx.hasKey(ServerRequestContext.KEY)
            val size = ctx.size()
            Mono.just("contains ${ServerRequestContext.KEY}: $hasKey, size: $size")
        }
}
