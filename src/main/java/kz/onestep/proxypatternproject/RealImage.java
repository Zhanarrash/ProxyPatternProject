
package kz.onestep.proxypatternproject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class RealImage implements Image {

    private final String filename;
    private BufferedImage image;

    // Конструктор
    public RealImage(String filename) {
        this.filename = filename;
        loadImage();
    }

    // Нақты суретті жүктеу
    private void loadImage() {

        System.out.println("\nСурет жүктелуде: " + filename);

        long startTime = System.currentTimeMillis();

        String path = "/images/" + filename;

        try (InputStream input =
                     getClass().getResourceAsStream(path)) {

            if (input == null) {
                throw new IOException(
                        "Сурет табылмады: " + path
                );
            }

            image = ImageIO.read(input);

            if (image == null) {
                throw new IOException(
                        "Суретті оқу мүмкін болмады: " + filename
                );
            }

            long endTime = System.currentTimeMillis();

            System.out.println("Сурет сәтті жүктелді!");

            System.out.println(
                    "Сурет өлшемі: "
                            + image.getWidth() + " x "
                            + image.getHeight()
            );

            System.out.println(
                    "Жүктелу уақыты: "
                            + (endTime - startTime) + " ms"
            );

        } catch (IOException e) {

            System.out.println("Қате: " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

    // Суретті экранда көрсету
    @Override
    public void display() {

        System.out.println(
                "Сурет көрсетілуде: " + filename
        );

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame(filename);

            frame.setDefaultCloseOperation(
                    JFrame.DISPOSE_ON_CLOSE
            );

            JLabel label = new JLabel(
                    new ImageIcon(image)
            );

            frame.add(new JScrollPane(label));

            frame.pack();

            // Терезенің экрандағы орны
            frame.setLocation(200, 100);

            frame.setVisible(true);
        });
    }
}
