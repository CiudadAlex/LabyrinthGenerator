package org.leviatanplatform.labyrinth.viewer;

import org.leviatanplatform.labyrinth.model.Labyrinth;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LabyrinthGraphicRepresentation {

    private JFrame frame;
    private JPanel canvas;
    private Labyrinth labyrinth;

    public void show(Labyrinth labyrinth) {

        this.labyrinth = labyrinth;

        if (frame == null) {

            frame = new JFrame("Labyrinth");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1600, 900);
            frame.setVisible(true);
            frame.setResizable(false);

            canvas = createCanvas();
            frame.add(canvas);
        }

        SwingUtilities.invokeLater(() -> {
            canvas.invalidate();
            canvas.validate();
            canvas.repaint();
        });
    }

    private JPanel createCanvas() {

        return new JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                LabyrinthGraphicRepresentationUtils.paintCurrentListOfStars(g, labyrinth, getWidth(), getHeight());
            }
        };
    }

    public void saveScreenShot(String name) throws IOException {

        BufferedImage bufferedImage = getScreenShot();

        File outputFile = new File("./out/" + name + ".jpg");
        ImageIO.write(bufferedImage, "jpg", outputFile);
    }

    private BufferedImage getScreenShot() {

        Component component = canvas;

        BufferedImage image = new BufferedImage(
                component.getWidth(),
                component.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        component.paint( image.getGraphics() );
        return image;
    }

}
