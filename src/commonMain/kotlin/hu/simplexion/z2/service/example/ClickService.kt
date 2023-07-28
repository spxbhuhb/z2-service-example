package hu.simplexion.z2.service.example

import hu.simplexion.z2.service.runtime.Service


interface ClickService : Service {

    suspend fun click() : Int

}