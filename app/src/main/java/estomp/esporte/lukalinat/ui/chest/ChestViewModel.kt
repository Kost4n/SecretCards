package estomp.esporte.lukalinat.ui.chest

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import estomp.esporte.lukalinat.R
import estomp.esporte.lukalinat.database.RecordDatabase
import estomp.esporte.lukalinat.database.entity.Record
import estomp.esporte.lukalinat.haveFile
import estomp.esporte.lukalinat.saveFile
import estomp.esporte.lukalinat.textFile
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChestViewModel(application: Application) : AndroidViewModel(application) {
    private val tapsFile = "file_taps"
    private val numberChestFile = "file_number_chest"
    private val recordDao by lazy {
        RecordDatabase.getDatabase(application.applicationContext).getRecordDao()
    }
    private val listDatabase = mutableListOf<Int>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            recordDao.getRecords().forEach {
                listDatabase.add(it.imageId)
            }
        }
    }

    fun getTaps(context: Context): String {
        var taps = ""
        val st = context.getString(R.string.tap_to_open)
        taps = if (haveFile(tapsFile, context)) {
            textFile(tapsFile, context) + "   " + st
        } else {
            "10   $st"
        }
        return taps
    }

    fun saveTaps(taps: Int, context: Context) = saveFile(taps.toString(), tapsFile, context)

    fun saveNumberChest(number: Int, context: Context) =
        saveFile(number.toString(), numberChestFile, context)

    fun getNumberChest(context: Context): Int {
        var number = 1
        number = if (haveFile(numberChestFile, context)) {
            textFile(numberChestFile, context).toInt()
        } else {
            1
        }
        return number
    }


    private fun addRecord(random: Int) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("addRecord", "add")
        recordDao.addRecord(Record(random))
    }

    fun getRecords(): Int {
        val list = mutableListOf<Int>(
            R.drawable.kindpng_1, R.drawable.kindpng_2,
            R.drawable.kindpng_3, R.drawable.kindpng_4,
            R.drawable.kindpng_5, R.drawable.kindpng_7,
            R.drawable.kindpng_8, R.drawable.kindpng_9,
            R.drawable.kindpng_10,R.drawable.kindpng_27,
            R.drawable.kindpng_11, R.drawable.kindpng_12,
            R.drawable.kindpng_13, R.drawable.kindpng_14,
            R.drawable.kindpng_15, R.drawable.kindpng_16,
            R.drawable.kindpng_17, R.drawable.kindpng_18,
            R.drawable.kindpng_19, R.drawable.kindpng_20,
            R.drawable.kindpng_21, R.drawable.kindpng_22,
            R.drawable.kindpng_23, R.drawable.kindpng_24,
            R.drawable.kindpng_25, R.drawable.kindpng_26
            )
        var id = 0
        var random = list[list.indices.random()]

        if (!listDatabase.contains(id)) {
            id = random
            addRecord(id)
        } else {
            getRecords()
        }
//
//        TODO("Проверка: есть ли в базе этот id")

        Log.d("getRecords()", "$id")
        return id
    }


    fun fetchData(): CompletableDeferred<List<Record>> {
        val deferred = CompletableDeferred<List<Record>>()

        viewModelScope.launch(Dispatchers.IO) {
            val result = withContext(Dispatchers.IO) {
                recordDao.getRecords()
            }
            deferred.complete(result)
        }

        return deferred
    }
}