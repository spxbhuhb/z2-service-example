Example project for [Z2 RPC](https://github.com/spxbhuhb/z2-rpc).

```shell
./gradlew run
./gradlew jsBrowserRun
```

The example defines the [ClickApiSpec](src/commonMain/kotlin/hu/simplexion/z2/rpc/test/ClickApiSpec.kt) API:

```kotlin
@ApiSpec
interface ClickApiSpec {

    suspend fun click() : Int

}
```

The implementation of the API is in [ClickApiProvider](src/jvmMain/kotlin/hu/simplexion/z2/rpc/test/ClickApiProvider.kt):

```kotlin
class ClickApiProvider : ClickApiProviderBase {

    // The use of `AtomicInteger` makes this very simple implementation thread safe.
    // Z2 RPC does not guarantee thread safety.
    var clicked = AtomicInteger(0)

    override fun Api.click(): Int {
        return clicked.incrementAndGet()
    }

}
```

The use of the API is in [main](src/jsMain/kotlin/main.kt):

```kotlin
button.addEventListener("click", {
    localLaunch {
        feedback.innerText = "Clicked ${ClickApi.click()} times"
    }
})
```