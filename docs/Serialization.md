# Json serialization

The Kotlin client relies on the kotlinx [serialization](https://github.com/Kotlin/kotlinx.serialization) library.

## Search

Deserialize hits from a search response using the `deserialize` extension functions.

```kotlin
@Serializable
data class HealthFacility(
    val name: String
)

val response = index.search()

val contacts: List<HealthFacility> = response.deserialize(HealthFacility.serializer)
```