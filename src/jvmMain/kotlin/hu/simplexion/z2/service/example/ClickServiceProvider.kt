package hu.simplexion.z2.service.example

import hu.simplexion.z2.service.runtime.ServiceProvider
import java.util.concurrent.atomic.AtomicInteger

class ClickServiceProvider : ClickService, ServiceProvider {

    var clicked = AtomicInteger(0)

    override suspend fun click(): Int {
        return clicked.incrementAndGet()
    }

}