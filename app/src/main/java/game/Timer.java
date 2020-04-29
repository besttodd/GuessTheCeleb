package game;

import androidx.annotation.NonNull;

public class Timer {
    private Boolean isRunning;
    private long seconds;
    private long minutes;

    public Timer() {
        isRunning = false;
        minutes = 0;
        seconds = 5;
    }

    public void setTimer(String current) {
        isRunning = false;
        seconds = Integer.parseInt(current.substring(current.length() - 2));
        minutes = Integer.parseInt(current.substring(1, 2));
    }

    public void tick() {
        if (seconds > 0) {
            seconds--;
        } else if (minutes > 0) {
            seconds = 59;
            minutes--;
        } else {
            stopTimer();
        }
    }

    public void startTimer() {
        isRunning = true;
    }

    public void stopTimer() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    @NonNull
    public String toString() {
        return String.format("%02d", minutes) + ":"
                + String.format("%02d", seconds);
    }
}
