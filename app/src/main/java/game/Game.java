package game;

public class Game {
    private boolean gameOver;
    private Question[] questions;
    private int score;
    private int round;
    private String timeLimit;

    public Game(Question[] questions) {
        gameOver = false;
        this.questions = questions;
        score = 0;
        round = 1;
        timeLimit = "00.10";
    }

    public boolean gameOver() {
        return !gameOver;
    }

    public Question next() {
        if (round >= questions.length) {
            gameOver = true;
            return null;
        } else {
            round++;
            return questions[round - 1];
        }
    }

    public void updateScore(boolean guess) {
        if (guess) {
            score++;
        }
    }

    public String getScore() {
        return "Score: " + score + "/" + round;
    }

    public Question getQuestion(int questionNum) {
        return questions[questionNum];
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    void setTimeLimit(String time) {
        timeLimit = time;
    }

    public int count() {
        return score;
    }
}
