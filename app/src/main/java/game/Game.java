package game;

public class Game {
    private boolean gameOver;
    private Question[] questions;
    private int score;
    private int round;

    public Game(Question[] questions) {
        gameOver = false;
        this.questions = questions;
        score = 0;
        round = 1;
        System.out.println("Constructor"+round+"-------------------------------------");
    }

    public boolean isGameOver() { return gameOver; }

    public Question next() {
        if (round >= questions.length) {
            gameOver = true;
            System.out.println("GAME OVER-------------------------------------------------------------");
            return null;
        } else {
            round++;
            System.out.println("NEXT()-----------------------------------------------" + round + "->" + questions[round - 1].getCelebrityName());
            return questions[round - 1];
        }
    }

    public void updateScore(boolean guess) {
        if (guess) { score++; }
    }

    public String getScore() { return "Score: " + score + "/" + round; }

    public Question getQuestion(int questionNum) { return questions[questionNum]; }

    public int getRound() { return round; }

    public void setRound() { round++;
        System.out.println("setRound"+round+"-------------------------------------");}

    public int count() { return score; }
}
