package view.utils;

public class MusicThread extends Thread{
    @Override
    public void run() {
        Music.musicplay();
    }
}