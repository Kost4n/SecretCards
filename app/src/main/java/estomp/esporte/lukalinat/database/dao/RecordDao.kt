package estomp.esporte.lukalinat.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import estomp.esporte.lukalinat.database.entity.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Insert
    fun addRecord(record: Record)

    @Query("SELECT * FROM records ORDER BY imageId DESC")
    fun getRecords(): List<Record>

    @Query("SELECT * FROM records ORDER BY imageId DESC")
    fun getRecordsF(): Flow<List<Record>>
}