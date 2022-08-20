package view.utils;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Music {
    static File file = new File("music\\青春校园.mp3");
    public static Player player;
    public static int musicFlag = 1; //设置一个标记

    Music() {

    }

    public static void musicplay() {


        try {
            player = new Player(new FileInputStream(file));
            player.play();
            if (player.isComplete()) {
                player = new Player(new FileInputStream(file));
                player.play();
            }
        } catch (JavaLayerException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
