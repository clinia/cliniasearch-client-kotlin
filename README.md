<div align="center">
  <img src=".github/clinia-logo.svg" width="250">
  <h1>Clinia Kotlin API Client</h1>
  <h4>Thin & minimal low-level HTTP client to interact with Clinia's API</h4>
  <a href="#features">Features</a> •
  <a href="#getting-started">Getting Started</a> •
  <a href="#api">API</a> •
  <a href="#guides">Guides</a> •
  <a href="#-license">License</a>
</div>

# Features

- The Kotlin client is compatible with Kotlin `1.3.41` and higher.
- It is compatible with Kotlin project on the JVM, such as backend and Android applications.
- It relies on the open source Kotlin libraries for seamless integration into Kotlin projects:
    - [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html)
    - [Kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization) for json parsing.
    - [Kotlinx coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations.
    - [Ktor](https://github.com/ktorio/ktor) HTTP client.
- The client is thread-safe. You can use `SearchClient` in a multithreaded environment.

# Getting Started

Install the Kotlin client by adding the following dependency to your `gradle.build` file:

```gradle
repositories {
    maven { url "https://dl.bintray.com/clinia/maven" }
}

dependencies {
    implementation "ca.clinia:cliniasearch-client-kotlin-jvm:$kotlin_client_version"
    // Choose one of the following http client
    implementation "io.ktor:ktor-client-apache:$ktor_version"
    implementation "io.ktor:ktor-client-okhttp:$ktor_version"
    implementation "io.ktor:ktor-client-android:$ktor_version"
    implementation "io.ktor:ktor-client-cio:$ktor_version"
    implementation "io.ktor:ktor-client-jetty:$ktor_version"
}
``` 

### Coroutines

All methodes performing HTTP calls in the Kotlin client are [suspending functions](https://kotlinlang.org/docs/reference/coroutines/composing-suspending-functions.html#composing-suspending-functions).
This means these functions can only be called from a coroutine.

In the example below, a coroutine is launched in the main thread.
The context is switched to a thread pool to perform the search HTTP call off the main thread.
The response can be manipulated from the main thread.

```kotlin
class Searcher : CoroutineScope {

    override val coroutineContext = Job()

    fun search() {
        launch(Dispatchers.Main) {
            val response = withContext(Dispatchers.Default) { index.search() }
        }
    }
}
```

The developer is responsible for implementing the asynchronous logic that matches their specific requirements.
The Kotlin client doesn't execute HTTP calls on any particular thread, it is up to the developer to define it explicitly using coroutines.
Learn more about [coroutines](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html).

### Waiting for operations

Waiting for an asynchronous server task is made available via a [function literal with receiver](https://kotlinlang.org/docs/reference/lambdas.html#function-literals-with-receiver).

Use the [apply](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/apply.html) or [run](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/run.html) functions on your index or client.

### Type safety

Response and parameters objects are typed to provide extensive compile time safety coverage.

Example for creating a Client instance without mixing the application ID and the API key.

```kotlin
val appID = ApplicationID("YourApplicationID")
val apiKey = APIKey("YourAdminAPIKey")

val client = ClientSearch(appID, apiKey)
```

### Proguard rules

When proguard `minifyEnabled` option is set to `true` , you might get this error:

```
Can't locate argument-less serializer for class e.a.b.g.n.c (Kotlin reflection is not available). For generic classes, such as lists, please provide serializer explicitly.
```

Add this proguard rule to solve it.

```
-keep class ca.clinia.search.model.** { *; }
```

# API

- [Documentation](https://github.com/clinia/cliniasearch-client-kotlin/tree/master/docs/Api.md)

# Guides

- [Getting started](https://github.com/clinia/cliniasearch-client-kotlin/tree/master/docs/GettingStarted.md)
- [Serialization](https://github.com/clinia/cliniasearch-client-kotlin/tree/master/docs/Serialization.md)
- [ExceptionHandling](https://github.com/clinia/cliniasearch-client-kotlin/tree/master/docs/ExceptionHandling.md)
- [Configure the HTTP client](https://github.com/clinia/cliniasearch-client-kotlin/tree/master/docs/HTTPClient.md)

# 📄 License

Clinia Kotlin API Client is an open-sourced software licensed under the [MIT license](LICENSE).