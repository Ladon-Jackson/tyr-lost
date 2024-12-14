package com.example.tyrlost.services;

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import java.io.File
import java.util.UUID

class FileService(private val context: Context) {

    fun saveImagesToInternalStorage(uris: List<Uri>): List<Uri> {

        File(context.filesDir, "images").mkdirs()

        return uris.map { uri ->

            val fileName = "${UUID.randomUUID()}.jpg"
            val currentFile = File(context.filesDir, "images/$fileName")

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

//    fun saveImageToInternalStorage(uri: Uri): String {
//
//        val fileName = "${UUID.randomUUID()}.jpg"
//        File(context.filesDir, "images").mkdirs()
//        val inputStream = context.contentResolver.openInputStream(uri)
//        val outputStream = File(context.filesDir, "images/$fileName").outputStream()
//
//        inputStream?.use { input ->
//            outputStream.use { output ->
//                input.copyTo(output)
//            }
//        }
//
//        inputStream?.close()
//        outputStream.close()
//        return fileName
//    }
}
