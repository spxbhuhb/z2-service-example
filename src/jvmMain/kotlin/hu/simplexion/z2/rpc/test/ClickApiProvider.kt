package hu.simplexion.z2.rpc.test

import hu.simplexion.z2.rpc.runtime.Api
import java.util.concurrent.atomic.AtomicInteger

class ClickApiProvider : ClickApiProviderBase {

    var clicked = AtomicInteger(0)

    override fun Api.click(): Int {
        return clicked.incrementAndGet()
    }

}