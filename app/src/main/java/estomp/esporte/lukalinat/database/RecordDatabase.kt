package estomp.esporte.lukalinat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import estomp.esporte.lukalinat.database.dao.RecordDao
import estomp.esporte.lukalinat.database.entity.Record

@Database(
    version = 1,
    entities = [
        Record::class
    ],
    exportSchema = true
)
abstract class RecordDatabase: RoomDatabase() {
    abstract fun getRecordDao(): RecordDao

    companion object {
        @Volatile
        private var INSTANCE: RecordDatabase? = null

        fun getDatabase(context: Context): RecordDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): RecordDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                RecordDatabase::class.java,
                "database"
            ).build()
    }
}