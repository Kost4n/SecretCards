package estomp.esporte.lukalinat.ui.open

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import estomp.esporte.lukalinat.database.RecordDatabase
import estomp.esporte.lukalinat.database.entity.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OpenViewModel(application: Application) : AndroidViewModel(application) {
    private val recordDao by lazy {
        RecordDatabase.getDatabase(application.applicationContext).getRecordDao()
    }



    fun getRecords(): MutableList<Record> {
        val records = mutableListOf<Record>()
        viewModelScope.launch(Dispatchers.IO) {
            val DBrecods = recordDao.getRecords()
            for (i in DBrecods) {
                records.add(i)
            }
        }
        return records
    }
}