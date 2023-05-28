import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

        if(actual.equals("home")){
            panel = home();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
    }

    public JPanel login(){
        anterior = actual;
        actual = "login";

        //Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fuentes/Lexend.ttf")).deriveFont(Font.PLAIN, 13);

        JPanel login = new JPanel();
        login.setSize(1000, 800);
        login.setLocation(0, 0);
        login.setLayout(null);
        login.setBackground(Color.decode("#FFFFFF"));

        JPanel loginPanel = new JPanel();
        loginPanel.setSize(450, 800);
        loginPanel.setLocation(0, 0);
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.decode("#95E799"));
        login.add(loginPanel);

        JLabel bienvenido = new JLabel("Biendosveni",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        loginPanel.add(bienvenido);

        JTextField correo = new JTextField();
        correo.setSize(100,20);
        correo.setLocation(130,150);
        loginPanel.add(correo);

        JTextField password = new JTextField();
        password.setSize(100,20);
        password.setLocation(130,200);
        loginPanel.add(password);

        JPanel loginIMG = new JPanel();
        loginIMG.setSize(550, 800);
        loginIMG.setLocation(450, 0);
        loginIMG.setLayout(null);
        loginIMG.setBackground(Color.decode("#005F04"));
        login.add(loginIMG);

        JLabel imagen2 = new JLabel();
        imagen2.setSize(550, 800);
        ImageIcon imag2 = new ImageIcon("car.png");
        ImageIcon icono2 = new ImageIcon(imag2.getImage().getScaledInstance(imagen2.getWidth(), imagen2.getHeight(), Image.SCALE_DEFAULT));
        imagen2.setIcon(icono2);
        imagen2.setLocation(0, 0);
        loginIMG.add(imagen2);

        JButton loginBTN = new JButton("Entrar");
        loginBTN.setSize(100,20);
        loginBTN.setLocation(130,250);
        loginPanel.add(loginBTN);

        loginBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "home";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

    return login;
    }

    public JPanel home(){
        anterior = actual;
        actual = "home";

        JPanel homePanel = new JPanel();
        homePanel.setSize(1000, 800);
        homePanel.setLocation(0, 0);
        homePanel.setLayout(null);
        homePanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Home",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        homePanel.add(bienvenido);

        return homePanel;
    }
}
