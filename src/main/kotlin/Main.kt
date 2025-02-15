package ru.netology

import ru.netology.WallService.postId
import ru.netology.WallService.posts
import java.time.Clock

data class Post(
    val id: Int,
    val createdBy: Int?,
    val date: Long,
    val text: String?,
    val friendsOnly: Boolean,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val likes: Likes
)

data class Likes(
    val count: Int, val userLikes: Boolean, val canLikes: Boolean, val canPublish: Boolean
)

object WallService {
    private var posts = emptyArray<Post>()

    var postId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = postId)
        postId ++
        return posts.last()
    }

    fun update(postIn: Post): Boolean {
        for ((Index, post) in posts.withIndex()) {
            if (post.id == postIn.id) {
                posts[Index] = postIn.copy()
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        postId = 0
    }
}

object AttachmentService {
    private var attachments = emptyArray<Attachment>()

    fun add(attachment: Attachment): Attachment {
        attachments += attachment
        return attachments.last()
    }
}

fun main() {
    val likes = Likes(0, false, true, true)
    val post = Post(
        0, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = likes
    )

    println(WallService.add(post))

    val post2 = Post(
        1, 3, System.currentTimeMillis(), "Это пост2", false, true, true, true, false, likes = likes
    )
    println(WallService.add(post2))

    println(WallService.update(post))

    val photo: Attachment = Photo(1, 1, "Фото", 1920, 1080)

    println(AttachmentService.add(photo))
}