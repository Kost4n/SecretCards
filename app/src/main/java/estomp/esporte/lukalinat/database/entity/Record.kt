package estomp.esporte.lukalinat.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "records"
)
data class Record(
    @PrimaryKey
    val imageId: Int
)