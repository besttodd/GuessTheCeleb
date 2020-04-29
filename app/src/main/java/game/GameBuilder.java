package game;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import au.edu.jcu.cp3406.wk7guesstheceleb.Difficulty;

public class GameBuilder {
    private Question[] questions;
    private Game newGame;
    private CelebrityManager celebrityManager;
    private Random r = new Random();

    public GameBuilder(CelebrityManager celebrityManager) {
        questions = null;
        newGame = null;
        this.celebrityManager = celebrityManager;
    }

    public Game create(Difficulty level) {

        switch (level) {
            case MEDIUM:
                newGame = populateGame(6);
                newGame.setTimeLimit("00.30");
                break;
            case HARD:
                newGame = populateGame(12);
                newGame.setTimeLimit("01:00");
                break;
            case EXPERT:
                newGame = populateGame(24);
                newGame.setTimeLimit("01.30");
                break;
            case EASY:
            default:
                newGame = populateGame(3);
                newGame.setTimeLimit("00.15");
        }

        return newGame;
    }

    private Game populateGame(int n) {
        Set<Integer> uniqueSet = new HashSet<>();

        //Generate an array of new questions
        while (uniqueSet.size() != n) {
            uniqueSet.add(r.nextInt(celebrityManager.count()));
        }
        questions = new Question[uniqueSet.size()];
        Iterator<Integer> it = uniqueSet.iterator();

        for (int i = 0; i < questions.length; i++) {
            int randIndex = it.next();
            questions[i] = new Question(celebrityManager.getName(randIndex), celebrityManager.get(randIndex), getNewAnswers(n));
        }
        newGame = new Game(questions);

        return newGame;
    }

    private String[] getNewAnswers(int n) {
        Set<String> answerIndexes = new HashSet<>();
        String[] answers;

        while (answerIndexes.size() != n) {
            String randName = celebrityManager.getName(r.nextInt(celebrityManager.count()));
            answerIndexes.add(randName);
        }
        answers = new String[answerIndexes.size()];
        answerIndexes.toArray(answers);

        return answers;
    }
}
