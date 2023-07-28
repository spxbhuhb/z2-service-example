Example project for [Z2 Service](https://github.com/spxbhuhb/z2-service).

```shell
./gradlew run
./gradlew jsBrowserRun
```

[ClickService](src/commonMain/kotlin/hu/simplexion/z2/service/example/ClickService.kt) defines the service:

```kotlin
interface ClickService : Service {

    suspend fun click() : Int

}
```

[ClickServiceProvider](src/jvmMain/kotlin/hu/simplexion/z2/service/example/ClickServiceProvider.kt) provides the service:

```kotlin
class ClickServiceProvider : ClickService, ServiceProvider {

    // The use of `AtomicInteger` makes this very simple implementation thread safe.
    // Z2 Service does not guarantee thread safety.
    var clicked = AtomicInteger(0)

    override suspend fun click() : Int {
        return clicked.incrementAndGet()
    }

}
```

[main](src/jsMain/kotlin/main.kt) uses the service:

```kotlin
val clicks = getService<ClickService>()
button.addEventListener("click", {
    localLaunch {
        feedback.innerText = "Clicked ${clicks.click()} times"
    }
})
```

