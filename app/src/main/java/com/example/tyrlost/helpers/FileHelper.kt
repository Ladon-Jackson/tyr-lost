package com.example.tyrlost.helpers;

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.net.toFile
import androidx.core.net.toUri
import java.io.File
import java.io.IOException
import java.time.Instant
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

        val imageDir = File(context.filesDir, "images/$tierListId")
        imageDir.mkdirs()

        return uris.map { uri ->

            val fileName = "${UUID.randomUUID()}.jpg"
            val currentFile = File(imageDir, fileName)

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

    fun saveTierListImage(tierListBitmap: Bitmap, fileName: String): Boolean {

        val resolver = context.contentResolver

        val imageCollection =
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "$fileName.jpeg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.WIDTH, tierListBitmap.width)
            put(MediaStore.Images.Media.HEIGHT, tierListBitmap.height)
        }

        resolver.insert(imageCollection, contentValues)?.let { uri ->
            resolver.openOutputStream(uri)?.let { os ->
                if (!tierListBitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)) return false
            }
        } ?: return false
        return true
    }
}
