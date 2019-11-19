package serialize

import ca.clinia.search.serialize.*
import shouldEqual
import kotlin.test.Test


internal class TestLanguages {

    @Test
    fun test() {
        KeyEnglish shouldEqual "en"
        KeyFrench shouldEqual "fr"
    }
}