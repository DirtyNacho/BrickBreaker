import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    File soundPad,brickHit;

    public Sound() {
        soundPad=new File("C:\\Users\\Fazakas Hunor\\IdeaProjects\\BrickBreaker\\src\\Sound1.wav");
        brickHit=new File("C:\\Users\\Fazakas Hunor\\IdeaProjects\\BrickBreaker\\src\\Sound2.wav");
    }

    public void playSoundPad() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip clip = AudioSystem.getClip();
        AudioInputStream stream = AudioSystem.getAudioInputStream(soundPad);
        clip.open(stream);
        clip.start();
    }

    public void playBrickHit() throws LineUnavailableException, IOException, InterruptedException, UnsupportedAudioFileException {
        Clip clip = AudioSystem.getClip();
        AudioInputStream stream= AudioSystem.getAudioInputStream(brickHit);
        clip.open(stream);
        clip.start();
    }

}
