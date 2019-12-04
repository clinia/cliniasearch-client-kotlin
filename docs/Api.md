# API
## `ClientSearch`
### Initialization
```kotlin
val appId = ApplicationID("YourApplicationID")
val apiKey = ApiKey("YourAPIKey")

val clientSearch = ClientSearch(appId, apiKey)
```
```kotlin
val appId = ApplicationID("YourApplicationID")
val apiKey = ApiKey("YourAPIKey")

val clientSearch = ClientSearch(ConfigurationClient(appId, apiKey))
```

---
### `ClientSearch(applicationId, apiKey, logLevel)`
Constructor

#### Arguments
- **applicationId (_ApplicationID_)** -- Your application id.
- **apiKey (_ApiKey_)** -- Your api key.
- **logLevel (_io.ktor.client.features.logging.LogLevel_)** -- The log level. Default is `LogLevel.NONE` 

#### Returns
Returns an instance of `ClientSearch`. 

---

### `ClientSearch(configurationSearch)`
Constructor

#### Arguments
- **configurationSearch (_[ConfigurationSearch](#configurationsearch)_)** -- The configuration object. 

#### Returns
Returns an instance of `ClientSearch`. 

---
### `clientSearch.initIndex(indexName)`
Get an `Index` object initialized.

#### Arguments
- **indexName (_IndexName_)** -- Name of the targeted index. 

#### Returns
Returns an instance of `Index`.

---
### `clientSearch.suggest(query, requestOptions)`
Get query suggestions based on a query.

#### Arguments
- **query (_[SuggestionQuery](#suggestionquery)_)** -- The query to get suggestions for.
- **requestOptions (_RequestOptions_)** -- The options for this request.

#### Returns
Returns an instance of [`ResponseQuerySuggestions`](#responsequerysuggestions)

#### Example
```kotlin
val response = clientSearch.suggest(
    SuggestionQuery("query")
)
```

---
### `clientSearch.multipleQueries(queries, requestOptions)`
Triggers a multi-index search query.

#### Arguments
- **queries (_List\<[IndexQuery](#indexquery)\>_)** -- The search queries.
- **requestOptions (_RequestOptions_)** -- The options for this request.

#### Returns
Returns an instance of [`ResponseSearches`](#responsesearches)

#### Example
```kotlin
val response = clientSearch.multipleQueries(
    listOf(
        IndexQuery(indexName = IndexName("health_facility"), query = Query(query = "query")),
        IndexQuery(indexName = IndexName("professional"), query = Query(query = "query"))
    )
)
```

---
## `Index`
### Initialization
```kotlin
val indexName = IndexName("index")
var index = clientSearch.initIndex(indexName)
```

---
### `index.search(query)`
Triggers a search query on the given index.

#### Arguments
- **query (_[Query](#query)_)** -- The query

#### Returns
Returns an instance of [`ResponseSearch`](#responsesearch)

#### Example
```kotlin
val responseSearch = search(Query(query = "query"))
```

---
## `ClientPlaces`
### Initialization
```kotlin
val appId = ApplicationID("YourApplicationID")
val apiKey = ApiKey("YourAPIKey")

val clientPlaces = ClientPlaces(appId, apiKey)
```
```kotlin
val appId = ApplicationID("YourApplicationID")
val apiKey = ApiKey("YourAPIKey")

val clientPlaces = ClientPlaces(ConfigurationPlaces(appId, apiKey))
```

---
### `ClientPlaces(applicationId, apiKey, logLevel)`
Constructor

#### Arguments
- **applicationId (_ApplicationID_)** -- Your application id.
- **apiKey (_ApiKey_)** -- Your api key.
- **logLevel (_io.ktor.client.features.logging.LogLevel_)** -- The log level. Default is `LogLevel.NONE` 

#### Returns
Returns an instance of `ClientPlaces`. 

---

### `ClientPlaces(configurationPlaces)`
Constructor

#### Arguments
- **configurationPlaces (_[ConfigurationPlaces](#configurationplaces)_)** -- The configuration object. 

#### Returns
Returns an instance of `ClientPlaces`.

---
### `clientPlaces.searchPlaces(query)`
Get place suggestions based on a query.

#### Arguments
- **query (_[PlacesQuery](#placesquery)_)** -- The place query to get suggestions for. 

#### Returns
Returns an instance of [`ResponseSearchPlaces`](#responsesearchplaces).

#### Example
```kotlin
val response = places.searchPlaces(
    PlacesQuery("query", listOf(PlaceType.Place, PlaceType.PostCode), listOf("CA", "US"))
)
```

# Shared Models

### ConfigurationSearch
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `applicationId` | _ApplicationID_ | Your application id. |||
| `apiKey` | _ApiKey_ | Your api key. |||
| `writeTimeout` | _Long_ | Number of ms before a write request timeout. | `30000L` ||
| `readTimeout` | _Long_ | Number of ms before a read request timeout. | `5000L` ||
| `logLevel` | _io.ktor.client.features.logging.LogLevel_ | The log level. | `LogLevel.NONE` ||
| `engine` | _io.ktor.client.engine.HttpClientEngine?_ | The desired http engine. |||
<br/>

### ConfigurationPlaces
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `applicationId` | _ApplicationID_ | Your application id. |||
| `apiKey` | _ApiKey_ | Your api key. |||
| `writeTimeout` | _Long_ | Number of ms before a write request timeout. | `30000L` ||
| `readTimeout` | _Long_ | Number of ms before a read request timeout. | `5000L` ||
| `logLevel` | _io.ktor.client.features.logging.LogLevel_ | The log level. | `LogLevel.NONE` ||
| `engine` | _io.ktor.client.engine.HttpClientEngine?_ | The desired http engine. |||
<br/>

### Query
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `query` | _String?_ | The text to search in the index.|||
| `page` | _Int?_ | Specify the page to retrieve. | `0` ||
| `hitsPerPage` | _Int?_ | Set the number of hits per page. | `20` ||
| `location` | _String?_ | Set the location for a geo search. |||
| `aroundLatLng` | _Point?_ | Search for entries around a central geolocation, enabling a geo search within a circular area. |||
| `aroundRadius` | _AroundRadius?_ | Define the maximum radius for a geo search (in meters). |||
| `insideBoundingBox` | _BoundingBox?_ | Search inside a rectangular area (in geo coordinates). |||
| `queryType` | _QueryType?_ | Controls if and how query words are interpreted as prefixes. | `QueryType.PrefixLast` | `QueryType.PrefixLast`<br/>`QueryType.PrefixNone` |
<br/>

### IndexQuery
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `indexName` | _IndexName_ | Name of the targeted index |||
| `query` | _Query_ | Query |||
<br/>

### SuggestionQuery
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `query` | _String?_ | The query to get suggestions for. |||
| `highlightPreTag` | _String?_ | Tag to put at the start of the highlighted match. |||
| `highlightPostTag` | _String?_ | Tag to put at the end of the highlighted match. |||
| `size` | _Int?_ | Set the maximum number of suggestion to receive. | `5` ||
<br/>

### PlacesQuery
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `query` | _String?_ | The query to match places by name. |||
| `types` | _List\<PlaceType\>?_ | The type of places to look for | `listOf(PlaceType.PostCode, PlaceType.Place, PlaceType.Neighborhood)` | `PlaceType.Country`<br/>`PlaceType.Region`<br/>`PlaceType.PostCode`<br/>`PlaceType.District`<br/>`PlaceType.Place`<br/>`PlaceType.Locality`<br/>`PlaceType.Neighborhood`<br/>`PlaceType.Poi`<br/>`PlaceType.Route` |
| `country` | _List\<String\>?_ | The preferred language in which to get the results in. || (e.g. 'CA', 'US') |
| `locale` | _String?_ | The preferred language in which to get the results in. |||
| `size` | _Int?_ | The maximum number of results to return. |||
<br/>


### ResponseSearch
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `records` | _List\<Record\>?_ | The hits returned by the search. Hits are ordered according to the ranking or sorting of the index being queried. Hits are made of the schemaless JSON objects that you stored in the index. |||
| `meta` | _Meta?_ | Metadata of the query. |||
<br/>

### ResponseSearches
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `results` | _List\<ResponseSearch\>?_ | The responses. One for each index that was queried. |||
<br/>

### ResponseQuerySuggestions
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `suggestions` | _List\<QuerySuggestion\>?_ | List of query suggestions |||
<br/>

### ResponseSearchPlaces
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `suggestions` | _List\<Place\>?_ | List of place suggestions. |||
<br/>

### Metadata 
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `query` | _String?_ | Query. ||
| `page` | _Int?_ | Current page. ||
| `numPages` | _Int?_ | Total number of available pages. ||
| `perPage` | _Int?_ | Number of records per page. ||
| `total` | _Int?_ | Total number of records matching the query. ||
| `aroundLatLng` | _String?_ | Coordinate around which the search is geographically centered. | e.g. '45.5016889,-73.567256' |
| `insideBoundingBox` | _String?_ | Bounding box inside which the search was applied. | e.g. '45.739653,-73.472354,45.5016889,-73.567256' |
<br/>

# Models

### QuerySuggestion
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `suggestion` | _String?_ | Suggested query |||
| `facet` | _String?_ | Type of the suggestion |||
| `highlight` | _String?_ | Augmented suggestion |||
<br/>

### Place
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `id` | _ID?_ | Identifier. |||
| `type` | _PlaceType?_ | Type of location. || `PlaceType.Country`<br/>`PlaceType.Region`<br/>`PlaceType.PostCode`<br/>`PlaceType.District`<br/>`PlaceType.Place`<br/>`PlaceType.Locality`<br/>`PlaceType.Neighborhood`<br/>`PlaceType.Poi`<br/>`PlaceType.Route` |
| `formattedAddress` | _String?_ | Formatted address, ready to display. |||
| `suite` | _String?_ | Suite, door, appartment number. |||
| `route` | _String?_ | Street name of the location. |||
| `postalCode` | _String?_ | Postal code. |||
| `neighborhood` | _String?_ | Neighborhood. |||
| `locality` | _String?_ | Locality. |||
| `place` | _String?_ | City. |||
| `district` | _String?_ | District. |||
| `region` | _String?_ | Name of the region. |||
| `regionCode` | _String?_ | ISO 3166-2 region code. |||
| `country` | _String?_ | Name of the country. |||
| `countryCode` | _String?_ | ISO 3166 country code |||
| `geometry` | _Geometry?_ | Geographical information of the location. |||
<br/>

### Record (`health_facility`)
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `documentType` | _String?_ | Type of document. | `health_facility`||
| `id` | _String?_ | Identifier of the resource. |||
| `type` | _String?_ | Type of resource. |||
| `address` | _Address?_ | Address. |||
| `geoPoint` | _Point?_ | Coordinate of the resource, if applicable. |||
| `onlineBookingUrl` | _String?_ | Online booking url. |||
| `distance` | _Float?_ | Distance (in meters) from the center of the location search parameter. |||
| `openingHours` | _Map<String, List\<Interval\>>?_ | Opening hours. | The keys are strings from `1` to `7`.<br/>`1: Monday`<br/>`2: Tuesday`<br/>`3: Wednesday`<br/>`4: Thursday`<br/>`5: Friday`<br/>`6: Saturday`<br/>`7: Sunday` ||
| `name` | _String?_ | Name of the resource. |||
| `phones` | _List\<Phone\>?_ | Name of the resource. |||
| `owner` | _String?_ | Owner of the resource (mainly used internally) |||
<br/>

### Record (`professional`)
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `documentType` | _String?_ | Type of document. | `professional` ||
| `id` | _String?_ | Identifier of the resource. |||
| `title` | _String?_ | Title of the resource | `MR`<br/>`MS`<br/>`DR`<br/>`DRE`<br/>||
| `practiceNumber` | _String?_ | Practice number of the resource. |||
| `name` | _String?_ | Name. |||
| `phones` | _List\<Phone\>?_ | Phones. |||
| `owner` | _String?_ | Owner of the resource (mainly used internally) |||
<br/>

### Phone
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `countryCode` | _String?_ | Country code. |||
| `number` | _String?_ | Phone number. |||
| `extension` | _String?_ | Extension. |||
| `type` | _String?_ | Type of phone. | `UNKNOWN`<br/>`MAIN`<br/>`ALTERNATE`<br/>`RECEPTION`<br/>`FAX`<br/>`TEXT_TELEPHONE_TTY`<br/>`INFO`<br/>`TOOL_FREE`<br/>`PAGER`<br/>`MOBILE`<br/>`HOME`<br/>`WORK`<br/>`PERSONAL`<br/>`OTHER`<br/> ||
<br/>

### Address
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `streetAddress` | _String?_ | Street number plus route name. |||
| `suiteNumber` | _String?_ | Suite, door, appartment number. |||
| `postalCode` | _String?_ | Postal code. |||
| `neighborhood` | _String?_ | Neighborhood. |||
| `locality` | _String?_ | Locality. |||
| `place` | _String?_ | City. |||
| `region` | _String?_ | Name of the region. |||
| `regionCode` | _String?_ | ISO 3166-2 region code. |||
| `country` | _String?_ | Name of the country. |||
| `countryCode` | _String?_ | ISO 3166 country code |||
<br/>

### Interval
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `start` | _String?_ | Start time of the time interval. || Format is `HH:mm` |
| `end` | _String?_ | End time of the time interval. || Format is `HH:mm` |
<br/>

### Geometry
| Field name | Type | Description | Default Value | Possible Values |
|------------|------|-------------|---------------|-----------------|
| `bounds` | _Bounds?_ | Bounds of the location. |||
| `location` | _Point?_ | Best coordinate to locate the location. |||
<br/>

### Bounds
| Field name | Type | Description | Possible Values |
|------------|------|-------------|-----------------|
| `northEast` | _Point?_ | North-east coordinate delimiting the bounds of the location. ||
| `southWest` | _Point?_ | South-west coordinate delimiting the bounds of the location. ||
<br/>

### Point
| Field name | Type | Description | Possible Values |
|------------|------|-------------|-----------------|
| `lat` | _Float?_ | Latitude ||
| `lng` | _Float?_ | Longitude ||
<br/>