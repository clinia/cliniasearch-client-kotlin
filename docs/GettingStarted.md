# Getting Started

## Init client and index

First off, you need to initialize the client. To do this, you need your **Application ID** and **API Key**.

```kotlin
val client = ClientSearch(
    applicationID = ApplicationID("YourApplicationID"),
    apiKey = APIKey("YourApiKey")
)
val indexName = IndexName("health_facility")
val index = client.initIndex(indexName)
```
Possible index names are currently `health_facility` and `professional`

## Search

You can now search for health facilities:

```kotlin
val query = Query("jean coutu")
val response = index.search(query)

response.records.deserialize(HealthFacility.serializer())
```