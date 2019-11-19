package serialize

import attributeA
import ca.clinia.search.model.Attribute
import kotlinx.serialization.json.JsonLiteral

internal class TestAttribute : TestSerializer<Attribute>(Attribute) {

    override val items = listOf(
        attributeA to JsonLiteral(attributeA.raw)
    )
}