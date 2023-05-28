import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

public class Ventana extends JFrame {
    public JPanel panel = null;
    private String anterior = "cargaPantalla";
    private String actual = "login";
    private Border roundedBorder = new RoundBorder(16, 1, Color.GRAY);
    ImageIcon logoEmpresa = new ImageIcon("company.png");
    public Ventana(){

        this.setVisible(true);
        this.setSize(1000,800);
        this.setTitle("Ramservice");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#FFFFFF"));
        this.setLayout(null);
        this.setResizable(false);
        setIconImage(logoEmpresa.getImage());

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
        if(actual.equals("consultarCliente")){
            panel = consultarCliente();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("crearCliente")){
            panel = crearCliente();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("editarCliente")){
            panel = editarCliente();

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
        if(actual.equals("consultarRenta")){
            panel = consultarRentas();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("crearRenta")){
            panel = crearRenta();

            this.add(panel);

            this.repaint();
            this.revalidate();
        }
        if(actual.equals("editarRenta")){
            panel = editarRenta();

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

        JPanel login = new JPanel();
        login.setSize(1000, 800);
        login.setLocation(0, 0);
        login.setLayout(null);
        login.setBackground(Color.decode("#FFFFFF"));

        JPanel loginPanel = new JPanel();
        loginPanel.setSize(450, 800);
        loginPanel.setLocation(0, 0);
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.white);
        login.add(loginPanel);

        JLabel bienvenido = new JLabel("Bienvenido",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,25));
        bienvenido.setSize(160,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.black);
        loginPanel.add(bienvenido);

        JLabel point = new JLabel(".",JLabel.CENTER);
        point.setFont(new Font("Arial",Font.BOLD,25));
        point.setSize(300,80);
        point.setLocation(130,10);
        point.setForeground(Color.decode("#38B6FF"));
        loginPanel.add(point);

        JLabel inicia = new JLabel("Inicia sesión",JLabel.CENTER);
        inicia.setFont(new Font("Arial",Font.BOLD,35));
        inicia.setSize(250,80);
        inicia.setLocation(88,80);
        inicia.setForeground(Color.black);
        loginPanel.add(inicia);

        JLabel emailText = new JLabel("Correo electrónico",JLabel.CENTER);
        emailText.setFont(new Font("Arial",Font.BOLD,15));
        emailText.setSize(250,80);
        emailText.setLocation(20,170);
        emailText.setForeground(Color.black);
        loginPanel.add(emailText);

        JTextField correo = new JTextField();
        correo.setSize(300,36);
        correo.setLocation(75,220);
        correo.setBorder(roundedBorder);
        loginPanel.add(correo);

        JLabel passwordText = new JLabel("Contraseña",JLabel.CENTER);
        passwordText.setFont(new Font("Arial",Font.BOLD,15));
        passwordText.setSize(250,80);
        passwordText.setLocation(-7,270);
        passwordText.setForeground(Color.black);
        loginPanel.add(passwordText);

        JTextField password = new JTextField();
        password.setSize(300,36);
        password.setLocation(75,320);
        password.setBorder(roundedBorder);
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
        loginBTN.setSize(300,36);
        loginBTN.setLocation(75,400);
        loginBTN.setFont(new Font("Arial", Font.BOLD,25));
        loginBTN.setForeground(Color.white);
        loginBTN.setBackground(Color.decode("#38B6FF"));
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

        JLabel imagen1 = new JLabel();
        imagen1.setSize(350, 350);
        ImageIcon imag1 = new ImageIcon("copy.png");
        ImageIcon icono1 = new ImageIcon(imag1.getImage().getScaledInstance(imagen1.getWidth(), imagen1.getHeight(), Image.SCALE_DEFAULT));
        imagen1.setIcon(icono1);
        imagen1 .setLocation(33, 430);
        loginPanel.add(imagen1);

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

        JButton consultarBTN = new JButton("Consultar clientes");
        consultarBTN.setSize(100,20);
        consultarBTN.setLocation(130,250);
        clientesPanel.add(consultarBTN);
        consultarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarCliente";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton crearBTN = new JButton("Crear clientes");
        crearBTN.setSize(100,20);
        crearBTN.setLocation(130,300);
        clientesPanel.add(crearBTN);
        crearBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "crearCliente";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton editarBTN = new JButton("Editar clientes");
        editarBTN.setSize(100,20);
        editarBTN.setLocation(130,350);
        clientesPanel.add(editarBTN);
        editarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarCliente";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        return clientesPanel;
    }
    public JPanel consultarCliente(){
        anterior = actual;
        actual = "consultarCliente";

        JPanel consultarClientePNL = new JPanel();
        consultarClientePNL.setSize(1000, 800);
        consultarClientePNL.setLocation(0, 0);
        consultarClientePNL.setLayout(null);
        consultarClientePNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Clientes",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarClientePNL.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        consultarClientePNL.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "clientes";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return consultarClientePNL;
    }
    public JPanel crearCliente(){
        anterior = actual;
        actual = "crearCliente";

        JPanel crearClientesPNL = new JPanel();
        crearClientesPNL.setSize(1000, 800);
        crearClientesPNL.setLocation(0, 0);
        crearClientesPNL.setLayout(null);
        crearClientesPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Crear Cliente",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        crearClientesPNL.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        crearClientesPNL.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "clientes";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return crearClientesPNL;
    }

    public JPanel editarCliente(){
        anterior = actual;
        actual = "editarCliente";

        JPanel editarClientesPNL = new JPanel();
        editarClientesPNL.setSize(1000, 800);
        editarClientesPNL.setLocation(0, 0);
        editarClientesPNL.setLayout(null);
        editarClientesPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Editar Cliente",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        editarClientesPNL.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        editarClientesPNL.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "clientes";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return editarClientesPNL;
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
        JButton consultarBTN = new JButton("Consultar rentas");
        consultarBTN.setSize(100,20);
        consultarBTN.setLocation(130,250);
        rentasPanel.add(consultarBTN);
        consultarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarRenta";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton crearBTN = new JButton("Crear renta");
        crearBTN.setSize(100,20);
        crearBTN.setLocation(130,300);
        rentasPanel.add(crearBTN);
        crearBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "crearRenta";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        JButton editarBTN = new JButton("Editar renta");
        editarBTN.setSize(100,20);
        editarBTN.setLocation(130,350);
        rentasPanel.add(editarBTN);
        editarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarRenta";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return rentasPanel;
    }
    public JPanel consultarRentas(){
        anterior = actual;
        actual = "consultarRentas";

        JPanel consultarCar = new JPanel();
        consultarCar.setSize(1000, 800);
        consultarCar.setLocation(0, 0);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Rentas",JLabel.CENTER);
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
                actual = "rentas";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return consultarCar;
    }
    public JPanel crearRenta(){
        anterior = actual;
        actual = "crearRenta";

        JPanel crearRentaPNL = new JPanel();
        crearRentaPNL.setSize(1000, 800);
        crearRentaPNL.setLocation(0, 0);
        crearRentaPNL.setLayout(null);
        crearRentaPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Crear Renta",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        crearRentaPNL.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        crearRentaPNL.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "rentas";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return crearRentaPNL;
    }

    public JPanel editarRenta(){
        anterior = actual;
        actual = "editarRenta";

        JPanel editarRentaPNL = new JPanel();
        editarRentaPNL.setSize(1000, 800);
        editarRentaPNL.setLocation(0, 0);
        editarRentaPNL.setLayout(null);
        editarRentaPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Editar Renta",JLabel.CENTER);
        bienvenido.setFont(new Font("Arial",Font.BOLD,35));
        bienvenido.setSize(300,80);
        bienvenido.setLocation(130,10);
        bienvenido.setForeground(Color.decode("#005F04"));
        editarRentaPNL.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100,20);
        backBTN.setLocation(130,390);
        editarRentaPNL.add(backBTN);
        backBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "rentas";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        return editarRentaPNL;
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
    class RoundBorder implements Border {
        private int radius;
        private int grosor;
        private Color color;

        public RoundBorder(int radius, int thickness, Color color) {
            this.radius = radius;
            this.grosor = thickness;
            this.color = color;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(grosor));
            g2.setColor(color);
            g2.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
        }
        public Insets getBorderInsets(Component c) {
            int value = radius / 2 + grosor / 2;
            return new Insets(value, value, value, value);
        }
        public boolean isBorderOpaque() {
            return true;
        }
    }
}

