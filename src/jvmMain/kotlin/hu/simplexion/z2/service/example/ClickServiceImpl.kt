package hu.simplexion.z2.service.example

import hu.simplexion.z2.service.runtime.ServiceImpl
import java.util.concurrent.atomic.AtomicInteger

var clicked = AtomicInteger(0)

class ClickServiceImpl : ClickService, ServiceImpl {

    override suspend fun click(): Int {
        return clicked.incrementAndGet()
    }

}