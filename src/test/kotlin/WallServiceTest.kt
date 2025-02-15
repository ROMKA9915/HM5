import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import ru.netology.Likes
import ru.netology.Post
import ru.netology.WallService

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
}