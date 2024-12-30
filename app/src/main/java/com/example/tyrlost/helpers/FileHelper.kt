package com.example.tyrlost.helpers;

import android.content.Context
import android.net.Uri
import androidx.core.net.toFile
import androidx.core.net.toUri
import java.io.File
import java.util.UUID

class FileHelper(private val context: Context) {

    fun deleteAllImages() =
        File(context.filesDir, "images").listFiles()?.forEach(deleteFile)

    fun deleteImagesById(id: Int) =
        File(context.filesDir, "images/$id").let(deleteFile)

    fun deleteImageByUri(uri: Uri) = deleteFile(uri.toFile())

    private val deleteFile: (File) -> Unit = { dir ->
        dir.listFiles()?.forEach {
            it.delete()
        }
        dir.delete()
    }


    fun saveImagesToInternalStorage(tierListId: Int, uris: List<Uri>): List<Uri> {

        File(context.filesDir, "images/$tierListId").mkdirs()

        return uris.map { uri ->

            val fileName = "${UUID.randomUUID()}.jpg"
            val currentFile = File(context.filesDir, "images/$tierListId/$fileName")

            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream = currentFile.outputStream()

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            inputStream?.close()
            outputStream.close()
            currentFile.toUri()
        }
    }
}
