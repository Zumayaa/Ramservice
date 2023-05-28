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

        if(actual.equals("vehiculos")){
            panel = vehiculos();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }

        if(actual.equals("clientes")){
            panel = clientes();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("rentas")){
            panel = rentas();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("categorias")){
            panel = categorias();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("marcas")){
            panel = marcas();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("consultarVehiculo")){
            panel = consultarVehiculo();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("consultarCategorias")){
            panel = consultarCategorias();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("consultarMarcas")){
            panel = consultarMarcas();

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

        JButton logoutBTN = new JButton("Logout");
        logoutBTN.setSize(100,20);
        logoutBTN.setLocation(320,250);
        homePanel.add(logoutBTN);

        logoutBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "login";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton vehiculosBTN = new JButton("Vehículos");
        vehiculosBTN.setSize(100,20);
        vehiculosBTN.setLocation(130,250);
        homePanel.add(vehiculosBTN);

        vehiculosBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "vehiculos";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton clientesBTN = new JButton("Clientes");
        clientesBTN.setSize(100,20);
        clientesBTN.setLocation(130,290);
        homePanel.add(clientesBTN);
        clientesBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "clientes";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton rentasBTN = new JButton("Rentas");
        rentasBTN.setSize(100,20);
        rentasBTN.setLocation(130,320);
        homePanel.add(rentasBTN);
        rentasBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "rentas";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton categoriasBTN = new JButton("Categorias");
        categoriasBTN.setSize(100,20);
        categoriasBTN.setLocation(130,360);
        homePanel.add(categoriasBTN);
        categoriasBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "categorias";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton marcasBTN = new JButton("Marcas");
        marcasBTN.setSize(100,20);
        marcasBTN.setLocation(130,390);
        homePanel.add(marcasBTN);
        marcasBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "marcas";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        return homePanel;
    }

    public JPanel vehiculos(){
        anterior = actual;
        actual = "vehiculos";

        JPanel vehiculosPanel = new JPanel();
        vehiculosPanel.setSize(1000, 800);
        vehiculosPanel.setLocation(0, 0);
        vehiculosPanel.setLayout(null);
        vehiculosPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("vehiculos",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        vehiculosPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100,20);
        homeBTN.setLocation(130,390);
        vehiculosPanel.add(homeBTN);
        homeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "home";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton consultaBTN = new JButton("Consultar");
        consultaBTN.setSize(100,20);
        consultaBTN.setLocation(260,390);
        vehiculosPanel.add(consultaBTN);
        consultaBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarVehiculo";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return vehiculosPanel;
    }

    public JPanel consultarVehiculo(){
        anterior = actual;
        actual = "consultarVehiculo";

        JPanel consultarCar = new JPanel();
        consultarCar.setSize(1000, 800);
        consultarCar.setLocation(0, 0);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Vehiculos",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarCar.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        consultarCar.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "vehiculos";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return consultarCar;
    }

    public JPanel clientes(){
        anterior = actual;
        actual = "clientes";

        JPanel clientesPanel = new JPanel();
        clientesPanel.setSize(1000, 800);
        clientesPanel.setLocation(0, 0);
        clientesPanel.setLayout(null);
        clientesPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Clientes",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        clientesPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100,20);
        homeBTN.setLocation(130,390);
        clientesPanel.add(homeBTN);
        homeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "home";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });



        return clientesPanel;
    }

    public JPanel rentas(){
        anterior = actual;
        actual = "rentas";

        JPanel rentasPanel = new JPanel();
        rentasPanel.setSize(1000, 800);
        rentasPanel.setLocation(0, 0);
        rentasPanel.setLayout(null);
        rentasPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Rentas",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        rentasPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100,20);
        homeBTN.setLocation(130,390);
        rentasPanel.add(homeBTN);
        homeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "home";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return rentasPanel;
    }
    public JPanel categorias(){
        anterior = actual;
        actual = "categorias";

        JPanel categoriasPanel = new JPanel();
        categoriasPanel.setSize(1000, 800);
        categoriasPanel.setLocation(0, 0);
        categoriasPanel.setLayout(null);
        categoriasPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Categorias",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        categoriasPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100,20);
        homeBTN.setLocation(130,390);
        categoriasPanel.add(homeBTN);
        homeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "home";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton consultaBTN = new JButton("Consultar");
        consultaBTN.setSize(100,20);
        consultaBTN.setLocation(260,390);
        categoriasPanel.add(consultaBTN);
        consultaBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarCategorias";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        return categoriasPanel;
    }
    public JPanel consultarCategorias(){
        anterior = actual;
        actual = "consultarCategorias";

        JPanel consultarCar = new JPanel();
        consultarCar.setSize(1000, 800);
        consultarCar.setLocation(0, 0);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Categorias",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarCar.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        consultarCar.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "categorias";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return consultarCar;
    }
    public JPanel marcas(){
        anterior = actual;
        actual = "marcas";

        JPanel marcasPanel = new JPanel();
        marcasPanel.setSize(1000, 800);
        marcasPanel.setLocation(0, 0);
        marcasPanel.setLayout(null);
        marcasPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Marcas",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        marcasPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100,20);
        homeBTN.setLocation(130,390);
        marcasPanel.add(homeBTN);
        homeBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "home";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton consultaBTN = new JButton("Consultar");
        consultaBTN.setSize(100,20);
        consultaBTN.setLocation(260,390);
        marcasPanel.add(consultaBTN);
        consultaBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarMarcas";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        return marcasPanel;
    }
    public JPanel consultarMarcas(){
        anterior = actual;
        actual = "consultarMarcas";

        JPanel consultarCar = new JPanel();
        consultarCar.setSize(1000, 800);
        consultarCar.setLocation(0, 0);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Marcas",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarCar.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        consultarCar.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "marcas";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return consultarCar;
    }
}
