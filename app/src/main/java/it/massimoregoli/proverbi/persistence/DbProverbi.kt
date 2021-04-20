package it.massimoregoli.proverbi.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Suppress("SpellCheckingInspection")
@Database(entities = [Proverbi::class], version = 1, exportSchema = true)
abstract class DbProverbi : RoomDatabase() {
    companion object {
        private var db: DbProverbi? = null // Singleton
        fun getInstance(context: Context): DbProverbi {
            if (db == null)
                db = Room.databaseBuilder(
                    context.applicationContext,
                    DbProverbi::class.java,
                    "proverbi.db"
                )
                    .createFromAsset("databases/p2.db")
                    .build()
            return db as DbProverbi
        }
    }

    abstract fun proverdiDAO(): DaoProverbi
}