import ca.clinia.search.model.Attribute
import ca.clinia.search.model.ID
import ca.clinia.search.model.IndexName
import kotlinx.serialization.json.jsonArray


internal const val unknown = "unknown"
internal const val int = 0
internal const val boolean = true
internal const val string = "string"
internal val attributeA = Attribute("attributeA")
internal val attributeB = Attribute("attributeB")
internal val indexA = IndexName("indexA")
internal val indexB = IndexName("indexB")
internal val recordIDA = ID("442854")
internal val recordIDB = ID("322601")
internal val nestedLists = listOf(listOf(string), listOf(string))
internal val attributes = listOf(attributeA, attributeB)

internal val nestedListsJson = jsonArray {
    +jsonArray { +string }
    +jsonArray { +string }
}

internal val attributesJson = jsonArray {
    +attributeA.raw
    +attributeB.raw
}