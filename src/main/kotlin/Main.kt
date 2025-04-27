package ru.netology

import ru.netology.WallService.postId
import ru.netology.WallService.posts

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

data class Comment(
    val id: Int, val date: Long, val text: String?
)

data class NoteComment(
    val id: Int, val date: Long = System.currentTimeMillis(), val text: String, var deleted: Boolean = false
)

data class Likes(
    val count: Int, val userLikes: Boolean, val canLikes: Boolean, val canPublish: Boolean
)

class PostNotFoundException(massage: String) : RuntimeException(massage)

object WallService {
    private var posts = emptyArray<Post>()

    private var comments = emptyArray<Comment>()

    fun createComment(postId: Int, comment: Comment): Comment {
        for ((Index, post) in posts.withIndex()) {
            if (post.id == postId) {
                comments += comment.copy()
                return comment
            }
        }
        return throw PostNotFoundException("Post with id: $postId is not found")
    }

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

data class Note(
    val id: Int,
    val date: Long,
    val text: String,
    val title: String,
    val privacy: Int,
    val commentPrivacy: Int,
    val privacyView: String,
    val privacyComment: String
)

object NoteService {
    private var notes = mutableListOf<Note>()
    private var noteComments = mutableListOf<NoteComment>()

    private var noteId = 0
    private var noteCommentId = 0

    fun add(
        title: String,
        text: String,
        privacy: Int = 0,
        commentPrivacy: Int = 0,
        privacyView: String = "all",
        privacyComment: String = "all"
    ): Note {
        notes.add(
            Note(
                noteId++,
                System.currentTimeMillis(),
                text,
                title,
                privacy,
                commentPrivacy,
                privacyView,
                privacyComment
            )
        )
        return notes.last()
    }

    fun createComment(noteId: Int, message: String): NoteComment {
        noteComments.add(NoteComment(noteCommentId++, System.currentTimeMillis(), message))
        return noteComments.last()
    }

    fun delete(noteId: Int): Boolean {
        if (!notes.removeIf{ it.id == noteId }) throw PostNotFoundException("Note with id: $noteId is not found")
        return true
    }

    fun deleteComment(commentId: Int): Boolean {
        val noteComment = noteComments.find { it.id == commentId }
        if (noteComment != null) {
            if (!noteComment.deleted) {
                noteComment.deleted = true
                return true
            }
            throw PostNotFoundException("Comment with id: $commentId is not found")
        }
        return false
    }

    fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacy: Int = 0,
        commentPrivacy: Int = 0,
        privacyView: String = "all",
        privacyComment: String = "all"
    ): Boolean {
        val size = notes.size
        if (noteId > notes.size) throw PostNotFoundException("Note with id: $noteId is not found")
        notes.removeIf{ it.id == noteId }
        notes.add(
            Note(
                noteId,
                System.currentTimeMillis(),
                text,
                title,
                privacy,
                commentPrivacy,
                privacyView,
                privacyComment
            )
        )
        return size == notes.size
    }

    fun editComment(commentId: Int, message: String): Boolean {
        val noteComment = noteComments.find { it.id == commentId }
        if (noteComment != null) {
            if (!noteComment.deleted) {
                noteComment.deleted = true
                noteComments.add(NoteComment(commentId, System.currentTimeMillis(), message))
                return true
            }
            throw PostNotFoundException("Comment with id: $commentId is deleted")
        }
        throw PostNotFoundException("Comment with id: $commentId is not found")
    }

    fun get(): MutableList<Note> {
        return notes
    }

    fun getById(noteId: Int): Note? {
        return notes.find { it.id == noteId } ?: throw PostNotFoundException("Note with id: $noteId is not found")
    }

    fun getComments(commentId: Int): NoteComment? {
        return noteComments.find { it.id == commentId } ?: throw PostNotFoundException("Note with id: $commentId is not found")
    }

    fun restoreComment(commentId: Int): Boolean {
        val deleted = noteComments.find { it.id == commentId }
        if (deleted != null) {
            if (deleted.deleted == true) {
                deleted.deleted = false
                return true
            }
            throw PostNotFoundException("Comment with id: $commentId is not deleted")
        }
        throw PostNotFoundException("Comment with id: $commentId is not found")
    }

    fun clear() {
        notes.removeAll(notes)
        noteComments.removeAll(noteComments)
        noteId = 0
        noteCommentId = 0
    }
}


fun main() {
//    val likes = Likes(0, false, true, true)
//
//    val photo: Photo = Photo(1, 1, "Это фотка", 1920, 1080)
//    val attachment: Attachment = PhotoAttachment(photo)
//
//    val post = Post(
//        0, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = likes, arrayOf(attachment)
//    )
//
//    println(WallService.add(post))
//
//    val post2 = Post(1, 3, System.currentTimeMillis(), "Это пост2", false, true, true, true, false, likes = likes, null)
//
//    println(WallService.add(post2))
//
//    println(WallService.update(post))
//
//    println(
//        WallService.createComment(
//            0, comment = Comment(0, System.currentTimeMillis(), "Привет, это комментарий")
//        )
//    )

    NoteService.add("Заголовок", "Текст")
    NoteService.add("Заголовок2", "Текст2")
    println(NoteService.get())
    println(NoteService.clear())
    println(NoteService.get())
}