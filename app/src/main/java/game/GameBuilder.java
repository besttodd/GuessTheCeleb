package game;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import au.edu.jcu.cp3406.wk7guesstheceleb.Difficulty;

public class GameBuilder {
    private Question[] questions;
    private Game newGame;
    private CelebrityManager celebrityManager;

    public GameBuilder(CelebrityManager celebrityManager) {
            questions = null;
            newGame = null;
            this.celebrityManager = celebrityManager;
    }

    public Game create(Difficulty level) {

        switch (level) {
            case MEDIUM:
                newGame = populateGame(6);
                break;
            case HARD:
                newGame = populateGame(12);
                break;
            case EXPERT:
                newGame = populateGame(24);
                break;
            case EASY:
            default:
                newGame = populateGame(3);
        }

        return newGame;
    }

    private Game populateGame(int n) {
        questions = new Question[n];

        Random r = new Random();
        Set<String> answerIndexes = new HashSet<>();
        String[] answers;

        while (answerIndexes.size() != n) {
            String randName = celebrityManager.getName(r.nextInt(celebrityManager.count()));
            answerIndexes.add(randName);
        }
        answers = new String[answerIndexes.size()];
        answerIndexes.toArray(answers);

        for (int i = 0; i < n; i++) {
            int randIndex = r.nextInt(celebrityManager.count());
            questions[i] = new Question(celebrityManager.getName(randIndex), celebrityManager.get(randIndex), answers);
        }

        newGame = new Game(questions);

        return newGame;
    }
}
