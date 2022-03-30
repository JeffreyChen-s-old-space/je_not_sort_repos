import Audio.PlaySound;

import java.io.File;

public class Sound {

    public static void main(String[] argc) {
        String cwd = new File("").getAbsolutePath();
        PlaySound Soundplayer = new PlaySound(new File(cwd+"/test.wav"));
        Soundplayer.Start();
    }
}


