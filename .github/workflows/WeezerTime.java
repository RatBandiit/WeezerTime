import java.awt.AWTException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

public class WeezerTime {

    public static void main(String[] args) throws AWTException {
        
        // Create a Timer object to schedule the color scanning task
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new ColorScanningTask(), 0, 1000); // scan every second
        
        // Wait for the timer to finish
        try {
            Thread.sleep(60000); // stop after 60 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Stop the timer
        timer.cancel();
        timer.purge();
    }
    
    private static class ColorScanningTask extends TimerTask {
        
        // Create a Robot object to capture the screen
        private Robot robot;
        
        public ColorScanningTask() throws AWTException {
            this.robot = new Robot();
        }
        
        @Override
        public void run() {
            // Capture the screen as a BufferedImage
            Rectangle screenRect = new Rectangle(0, 0, 1920, 1080); // adjust the coordinates and size as needed
            BufferedImage screenImg = robot.createScreenCapture(screenRect);
            
            // Loop through each pixel in the image and check for a specific color
            for (int x = 0; x < screenImg.getWidth(); x++) {
                for (int y = 0; y < screenImg.getHeight(); y++) {
                    Color pixelColor = new Color(screenImg.getRGB(x, y));
                    String hexColor = "#00ace8"; //  weezer blue color in hexadecimal format
                    Color Weezer = Color.decode(hexColor);

                    if (pixelColor.equals(Weezer)) { // change the color to the one you want to find
                        // Open a website
                        try {
                            Desktop.getDesktop().browse(new URI("https://www.youtube.com/watch?v=vHwKTOzLTDY"));
                        } catch (IOException | URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
