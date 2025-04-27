import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.*

class WallServiceTest {

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun add() {
        val likes = Likes(0, false, true, true)
        val post = Post(
            0, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = likes
        )
        val result = WallService.add(post)

        assertEquals(post, result)
    }

    @Test
    fun update_toTrue() {
        val likes = Likes(0, false, true, true)
        val post2 = Post(
            0, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = likes
        )
        WallService.add(post2)
        val result = WallService.update(post2)
        assertTrue( result)
    }

    @Test
    fun update_toFalse() {
        val likes = Likes(0, false, true, true)
        val post2 = Post(
            5, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = likes
        )
        WallService.add(post2)
        val result = WallService.update(post2)
        assertFalse( result)
    }

    @Test
    fun shouldNoThrow() {
        WallService.add(Post(
            0, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = Likes(0, false, true, true)
        ))
        val comment = Comment(0, System.currentTimeMillis(), "Привет, это комментарий")
        val result: Comment = WallService.createComment(0, comment)
        assertEquals(comment, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        val post = Post(
            0, 1, System.currentTimeMillis(), "Это пост", false, true, true, true, false, likes = Likes(0, false, true, true)
         )
        WallService.add(post)
        WallService.createComment(5, comment = Comment(0, System.currentTimeMillis(), "Привет, это комментарий"))
    }
}

class NoteServiceTest {
    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun addTest() {
        NoteService.add("title", "text")
        assertEquals(1, NoteService.get().size)
    }

    @Test
    fun createCommentTest() {
        val result = NoteService.createComment(0, "text")
        assertEquals(result, NoteService.getComments(0))
    }

    @Test
    fun deleteTest() {
        NoteService.add("title", "text")
        val result: Boolean = NoteService.delete(0)
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun deleteThrowExpTest() {
        NoteService.add("title", "text")
        NoteService.delete(5)
    }

    @Test
    fun deleteCommentTest() {
        NoteService.createComment(0, "text")
        val result = NoteService.deleteComment(0)
        assertTrue(result)
    }

    @Test
    fun editTest() {
        NoteService.add("title", "text")
        val result = NoteService.edit(0, "title2", "text2")
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun editThrowExpTest() {
        NoteService.add("title", "text")
        NoteService.edit(5, "title2", "text2")
    }

    @Test
    fun editCommentTest() {
        NoteService.createComment(0, "text")
        val result = NoteService.editComment(0, "text2")
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun editCommentThrowExpTest() {
        NoteService.createComment(0, "text")
        NoteService.editComment(5, "text2")
    }

    @Test(expected = PostNotFoundException::class)
    fun getByIdThrowExpTest() {
        NoteService.add("title", "text")
        NoteService.getById(5)
    }

    @Test(expected = PostNotFoundException::class)
    fun getCommentsThrowExpTest() {
        NoteService.createComment(0, "text")
        NoteService.getComments(5)
    }

    @Test
    fun restoreCommentTest() {
        NoteService.createComment(0, "text")
        NoteService.deleteComment(0)
        val result = NoteService.restoreComment(0)
        assertTrue(result)
    }

    @Test(expected = PostNotFoundException::class)
    fun restoreCommentThrowExpTest() {
        NoteService.createComment(0, "text")
            NoteService.deleteComment(0)
        val result = NoteService.restoreComment(0)
    }
}