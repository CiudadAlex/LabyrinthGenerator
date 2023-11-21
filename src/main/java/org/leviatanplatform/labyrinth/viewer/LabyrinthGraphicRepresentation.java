package org.leviatanplatform.labyrinth.viewer;

import org.leviatanplatform.labyrinth.model.Labyrinth;

import javax.swing.*;
import java.awt.*;

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

}
