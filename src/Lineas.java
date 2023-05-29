import javax.swing.*;
import java.awt.*;

public class Lineas extends JPanel {
    private int x1, y1, x2, y2;// Coordenadas de la línea
    private JPanel p = new JPanel();
    public Lineas(int x1, int y1, int x2, int y2) {
        // Establecer las coordenadas iniciales de la línea
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la línea
        g.drawLine(x1, y1, x2, y2);
    }
}
