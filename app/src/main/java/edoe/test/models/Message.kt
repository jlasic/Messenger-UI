package edoe.test.models

import edoe.test.R
import java.util.*

data class Message(
    val id: Int, val text: String, val timestamp: String, val source: Source,
    val avatar: Int = R.drawable.avatar_strange // fake avatar
) {
    enum class Source { ME, OTHER }
}