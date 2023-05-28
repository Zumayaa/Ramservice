import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    public JPanel panel = null;
    private String anterior = "cargaPantalla";
    private String actual = "login";

    public Ventana(){

        this.setVisible(true);
        this.setSize(1000,800);
        this.setTitle("Ramservice");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#FFFFFF"));
        this.setLayout(null);
        this.setResizable(false);

        limpiarVentana();

        this.repaint();
        this.revalidate();
    }
    public void limpiarVentana(){

        if(panel!= null) {
            this.remove(panel);
        }

        if(actual.equals("login")){
            panel = login();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
    }

    public JPanel login(){
        anterior = actual;
        actual = "login";

        JPanel loginPanel = new JPanel();
        loginPanel.setSize(500, 800);
        loginPanel.setLocation(0, 0);
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.decode("#95E799"));

        JLabel panel = new JLabel("Biendosveni",JLabel.CENTER);
        panel.setFont(new Font("Arial",Font.BOLD,35));
        panel.setSize(300,80);
        panel.setLocation(130,10);
        panel.setForeground(Color.decode("#005F04"));
        loginPanel.add(panel);

    return loginPanel;
    }
}
