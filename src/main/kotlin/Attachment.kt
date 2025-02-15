package ru.netology

sealed class Attachment (
    val type: String
)

data class Photo(
    val id: Int,
    val createdBy: Int,
    val text: String,
    val width: Int,
    val height: Int
) : Attachment ("photo")

data class Audio(
    val id: Int,
    val createdBy: Int,
    val title: String,
    val duration: Int,
    val url: String
) : Attachment ("audio")

data class Video(
    val id: Int,
    val createdBy: Int,
    val description: String,
    val duration: Int,
    val views: Int
) : Attachment ("video")

data class File(
    val id: Int,
    val createdBy: Int,
    val title: String,
    val size: Int,
    val url: String
) : Attachment ("file")

data class Sticker(
    val stickerId: Int,
    val productId: Int,
    val isAllowed: Boolean
) : Attachment ("sticker")