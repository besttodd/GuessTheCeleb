package game;

import android.graphics.Bitmap;

import java.util.Random;

public class Question {
    private String celebrityName;
    private Bitmap celebrityImage;
    private String[] possibleNames;
    private Random r;

    public Question(String celebrityName, Bitmap celebrityImage, String[] possibleNames) {
        this.celebrityName = celebrityName;
        this.celebrityImage = celebrityImage;
        this.possibleNames = possibleNames;
        r = new Random();
    }

    public boolean check(String guess) { return guess.equals(celebrityName); }

    public Bitmap getCelebrityImage() { return celebrityImage; }

    public String[] getPossibleNames() {
        possibleNames[r.nextInt(possibleNames.length)] = celebrityName;
        return possibleNames;
    }

    public String getCelebrityName() { return celebrityName; }
}
