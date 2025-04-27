package ru.netology

sealed class Attachment (
    val type: String
)

data class PhotoAttachment(
    val photo: Photo
) : Attachment ("photo")

data class Photo(
    val id: Int,
    val createdBy: Int,
    val text: String,
    val width: Int,
    val height: Int
)

data class AudioAttachment(
    val audio: Audio
) : Attachment ("audio")

data class Audio (
    val id: Int,
    val createdBy: Int,
    val title: String,
    val duration: Int,
    val url: String
)

data class VideoAttachment(
    val video: Video
) : Attachment ("video")

data class Video(
    val id: Int,
    val createdBy: Int,
    val description: String,
    val duration: Int,
    val views: Int
)

data class FileAttachment(
    val file: File
) : Attachment ("file")

data class File(
    val id: Int,
    val createdBy: Int,
    val title: String,
    val size: Int,
    val url: String
)

data class StickerAttachment(
    val sticker: Sticker
) : Attachment ("sticker")

data class Sticker(
    val stickerId: Int,
    val productId: Int,
    val isAllowed: Boolean
)
