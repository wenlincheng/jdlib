
import com.emaraic.jdlib.Jdlib;
import com.emaraic.utils.FaceDescriptor;
import com.emaraic.utils.ImageUtils;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * This example based on C++ example from http://dlib.net/face_landmark_detection_ex.cpp.html
 * This example program shows how to find frontal human faces in an image and
 * estimate their pose. The pose takes the form of 68 landmarks. These are
 * points on the face such as the corners of the mouth, along the eyebrows, on
 * the eyes, and so forth.
 *
 * @author Taha Emara 
 * Website: http://www.emaraic.com 
 * Email : taha@emaraic.com
 * Created on: Dec 20, 2020
 */
public class FacialLandmardsExample {

    private static BufferedImage loadImage(String imagepath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(imagepath));
        } catch (IOException e) {
            System.err.println("Error During Loading File: " + imagepath);
        }
        return img;
    }

    public static BufferedImage resize(BufferedImage img, int w, int h) {
        Image tempimg = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.drawImage(tempimg, 0, 0, null);
        g2d.dispose();
        return image;
    }

    public static void main(String[] args) {

        String facialLandmarksModelPath = "D:\\Develop\\IdeaProjects\\jdlib-main\\examples\\shape_predictor_68_face_landmarks.dat";
        String imagepath = "D:\\Develop\\IdeaProjects\\jdlib-main\\examples\\test_img\\bald_guys.jpg";

        Jdlib jdlib = new Jdlib(facialLandmarksModelPath);

        BufferedImage img = loadImage(imagepath);

        ArrayList<FaceDescriptor> faces = jdlib.getFaceLandmarks(img);

        for (FaceDescriptor face : faces) {
            ImageUtils.drawFaceDescriptor(img, face);
        }

        img = resize(img, 800, 800);

        ImageUtils.showImage(img);
    }
}
