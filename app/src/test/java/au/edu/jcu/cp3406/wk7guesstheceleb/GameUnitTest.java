package au.edu.jcu.cp3406.wk7guesstheceleb;

import org.junit.Test;

import game.Game;
import game.Question;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void checkAnswer() {
        String[] options = new String[]{"sam", "frank", "sally"};
        Question question = new Question("sam", null, options);
        assertTrue(question.check("sam"));
        assertFalse(question.check("sally"));
    }

    @Test
    public void testGame() {
        Question question = null;
        Question[] questions = new Question[3];
        String[] answers = new String[]{"bob", "jane", "harry"};
        for (int i = 0; i < 3; i++) {
            questions[i] = new Question(answers[i], null, answers);
        }
        Game game = new Game(questions);

        while (!game.isGameOver()) {
            question = game.next();
            game.updateScore(question.check("bob"));
        }
        assertEquals("Score: 1/3", game.getScore());
    }
}