package estomp.esporte.lukalinat

import android.content.Context
import java.io.File

fun haveFile(fileName: String, context: Context): Boolean =
    File(context.filesDir, fileName)
        .exists()

fun textFile(fileName: String, context: Context): String =
    File(context.filesDir, fileName)
        .bufferedReader()
        .use { it.readText(); }

fun saveFile(str: String, fileName: String, context: Context) {
    context.openFileOutput(
        fileName, Context.MODE_PRIVATE
    ).use {
        it.write(str.toByteArray())
    }
}