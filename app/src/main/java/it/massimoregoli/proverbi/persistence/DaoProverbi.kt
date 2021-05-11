@file:Suppress("SpellCheckingInspection")

package it.massimoregoli.proverbi.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoProverbi {
    @Insert
    fun insertAll(proverbi: List<Proverbi>)

    @Insert
    fun insert(proverbio: Proverbi)

    @Update
    fun update(proverbio: Proverbi)

    @Query("DELETE FROM Proverbi")
    fun deleteAll()

    @Delete
    fun delete(proverbio: Proverbi)

    @Query("SELECT * FROM Proverbi")
    fun loadAll(): LiveData<List<Proverbi>>

    @Query("SELECT * FROM Proverbi WHERE testo LIKE :search")
    fun loadAllByTag(search: String): MutableList<Proverbi>

    @Query("SELECT * FROM Proverbi WHERE preferito = :pref")
    fun loadAllByPreference(pref: Int) : LiveData<MutableList<Proverbi>>

    @Query("SELECT * FROM Proverbi WHERE lingua = :lang")
    fun loadAllByLanguage(lang: String) : MutableList<Proverbi>

    @Query("SELECT count(*) FROM Proverbi")
    fun records(): Int

}