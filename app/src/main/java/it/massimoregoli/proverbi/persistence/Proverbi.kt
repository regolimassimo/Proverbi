package it.massimoregoli.proverbi.persistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("SpellCheckingInspection")
@Entity
data class Proverbi(@PrimaryKey(autoGenerate = true) var id: Int,
                    var testo: String,
                    var lingua: String,
                    var categoria: Int,
                    var preferito: Int) {
        constructor(value: String) : this(0, "", "", 0, 0) {
                testo = value
        }

}