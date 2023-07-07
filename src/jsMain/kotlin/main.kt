import hu.simplexion.z2.rpc.ktor.client.WebsocketApiCallTransport
import hu.simplexion.z2.rpc.runtime.defaultApiCallTransport
import hu.simplexion.z2.rpc.test.ClickApi

import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLElement

fun main() {
    defaultApiCallTransport = WebsocketApiCallTransport("http://localhost:3000/z2/ws").also { it.start() }

    val feedback = document.createElement("div") as HTMLElement
    val button = document.createElement("button") as HTMLButtonElement

    button.innerText = "Click Me!"
    button.addEventListener("click", {
        CoroutineScope(Dispatchers.Default).launch {
            println("click")
            val clicked = ClickApi.click()
            feedback.innerText = "Clicked $clicked times"
        }
    })

    document.body!!.let {
        it.appendChild(button)
        it.appendChild(feedback)
    }
}