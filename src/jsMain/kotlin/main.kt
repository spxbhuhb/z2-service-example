import hu.simplexion.z2.commons.util.localLaunch
import hu.simplexion.z2.service.example.ClickService
import hu.simplexion.z2.service.ktor.client.BasicWebSocketServiceTransport
import hu.simplexion.z2.service.runtime.ServiceProvider
import hu.simplexion.z2.service.runtime.defaultServiceCallTransport
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement

object Clicks : ClickService, ServiceProvider

fun main() {
    defaultServiceCallTransport = BasicWebSocketServiceTransport(
        window.location.host,
        window.location.port.toInt()
    ).also {
        it.start()
    }

    val feedback = document.createElement("div") as HTMLElement
    val button = document.createElement("button") as HTMLButtonElement

    button.innerText = "Click Me!"
    button.addEventListener("click", {
        localLaunch {
            feedback.innerText = "Clicked ${Clicks.click()} times"
        }
    })

    document.body!!.let {
        it.appendChild(button)
        it.appendChild(feedback)
    }
}