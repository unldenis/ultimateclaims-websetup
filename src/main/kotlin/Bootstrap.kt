
package com.github.unldenis.ultimateclaims.websetup

import io.javalin.Javalin
import java.awt.Desktop
import java.net.URI

fun main() {
    start()
    Desktop.getDesktop().browse(URI("http://localhost:7070/"))
}

private var app : Javalin? = null

val isStarted : Boolean
    get() = app != null


fun start() {
    app = Javalin.create { config ->

        config.staticFiles.add("/public")
        config.spaRoot.addFile("/", "/public/index.html");   // Catch-all route for the single-page application


        config.bundledPlugins.enableCors {userConfig ->
            userConfig.addRule {rule ->
                rule.anyHost()
            }
        }
        config.showJavalinBanner = false
    }.start(7070)
}

fun stop() {
    app?.let {
        it.stop()
        app = null
    }
}