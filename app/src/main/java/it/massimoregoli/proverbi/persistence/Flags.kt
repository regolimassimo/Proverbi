package it.massimoregoli.proverbi.persistence

class Flags(country: String) {
    companion object {
        val COUNTRIES = mapOf<String, String>(
            "" to "\uD83C\uDDEE\uD83C\uDDF9",
            "it" to "\uD83C\uDDEE\uD83C\uDDF9",
            "fr" to "\uD83C\uDDEB\uD83C\uDDF7",
            "en" to "\uD83C\uDDEC\uD83C\uDDE7",
            "de" to "\uD83C\uDDE9\uD83C\uDDEA",
            "es" to "\uD83C\uDDEA\uD83C\uDDF8"
        )
    }
}