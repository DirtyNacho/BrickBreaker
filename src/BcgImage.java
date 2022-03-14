import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BcgImage extends JPanel {
    private BufferedImage img;

    public BcgImage(String filename) {
        //load image
        if(img==null) {
            try {
                img = ImageIO.read(new File(filename));
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }
    public BufferedImage getImg() {
        return img;
    }
}
