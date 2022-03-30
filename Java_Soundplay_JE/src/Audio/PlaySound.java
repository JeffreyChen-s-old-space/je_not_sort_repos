package Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class PlaySound implements Runnable {
    AudioInputStream audioInputStream;
    Clip clip;

    public PlaySound(File WavFile){
        try{
            audioInputStream = AudioSystem.getAudioInputStream(WavFile);
            clip = AudioSystem.getClip();
            clip.addLineListener(new LineListener()
            {
                @Override
                public void update(LineEvent event)
                {
                    if (event.getType() == LineEvent.Type.STOP)
                        clip.close();
                }
            });
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void Start(){
        Thread SoundThread = new Thread(this);
        SoundThread.start();
    }

    @Override
    public void run() {
        try {
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
        while (!clip.isRunning()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while (clip.isRunning()){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }

    }

