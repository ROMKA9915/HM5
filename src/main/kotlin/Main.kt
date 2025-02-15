package ru.netology

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
    val likes: Likes,
    var attachment: Array<Attachment>? = emptyArray<Attachment>()
)

data class Likes(
    val count: Int, val userLikes: Boolean, val canLikes: Boolean, val canPublish: Boolean
)

object WallService {
    private var posts = emptyArray<Post>()

    var postId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = postId)
        postId++
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


fun main() {
    val likes = Likes(0, false, true, true)

    val photo: Photo = Photo(1, 1, "Это фотка", 1920, 1080)
    val attachment: Attachment = PhotoAttachment(photo)

    val post = Post(0, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = likes, arrayOf(attachment))

    println(WallService.add(post))

    val post2 = Post(1, 3, System.currentTimeMillis(), "Это пост2", false, true, true, true, false, likes = likes, null)

    println(WallService.add(post2))

    println(WallService.update(post))

}