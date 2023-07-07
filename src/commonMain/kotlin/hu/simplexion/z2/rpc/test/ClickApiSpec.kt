package hu.simplexion.z2.rpc.test

import hu.simplexion.z2.rpc.runtime.ApiSpec

@ApiSpec
interface ClickApiSpec {

    suspend fun click() : Int

}