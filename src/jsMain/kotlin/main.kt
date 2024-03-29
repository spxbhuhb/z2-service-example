import hu.simplexion.z2.commons.util.localLaunch
import hu.simplexion.z2.service.example.ClickService
import hu.simplexion.z2.service.ktor.client.BasicWebSocketServiceTransport
import hu.simplexion.z2.service.runtime.defaultServiceCallTransport
import hu.simplexion.z2.service.runtime.getService
import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement

val clicks = getService<ClickService>()

fun main() {
    defaultServiceCallTransport = BasicWebSocketServiceTransport(
        window.location.hostname,
        window.location.port.toInt(),
        "/z2/services"
    ).also {
        it.start()
    }

    val feedback = document.createElement("div") as HTMLElement
    val button = document.createElement("button") as HTMLButtonElement

    button.innerText = "Click Me!"
    button.addEventListener("click", {
        localLaunch {
            feedback.innerText = "Clicked ${clicks.click()} times"
        }
    })

    document.body!!.let {
        it.appendChild(button)
        it.appendChild(feedback)
    }
}