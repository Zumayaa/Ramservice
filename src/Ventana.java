import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Ventana extends JFrame {
    public JPanel panel = null;
    private String anterior = "cargaPantalla";
    private String actual = "login";
    private JLabel panelActualLbl = new JLabel(actual, JLabel.CENTER);
    private Border roundedBorder = new RoundBorder(16, 1, Color.GRAY);
    ArrayList<String> historialPaneles = new ArrayList<>();
    private int id_renta_editar;
    private int id_auto_consultar;
    private int id_cliente_a_consultar;
    private int id_cliente_a_editar;
    private int id_cliente_a_borrar;
    private int id_cliente_editando_renta;
    private String nombre_cliente;
    private String email_cliente;
    private String telefono_cliente;
    JPanel menuSuperiorPanel = new JPanel();

    ImageIcon logoEmpresa = new ImageIcon("src/img/company.png");

    public Ventana() throws SQLException {
        Conexion.crearBaseDeDatosSiNoExiste();
        Conexion.crearTablasSiNoExisten();

        this.setVisible(true);
        this.setSize(1000, 800);
        this.setTitle("Ramservice");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#FFFFFF"));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        setIconImage(logoEmpresa.getImage());

        ImageIcon logoRamsesIcon = new ImageIcon("src/img/logoRamservice.png");
        JLabel logoRamsesLbl = new JLabel();
        logoRamsesLbl.setIcon(logoRamsesIcon);
        logoRamsesLbl.setSize(logoRamsesIcon.getIconWidth(),logoRamsesIcon.getIconHeight());
        logoRamsesLbl.setLocation(0,-50);
        panelActualLbl.setSize(800, 80);
        panelActualLbl.setLocation(100, 0);
        panelActualLbl.setForeground(Color.black);
        panelActualLbl.setFont(new Font("Lexend-Regular.ttf", Font.BOLD, 35));
        menuSuperiorPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(20,panelActualLbl.getY()+panelActualLbl.getHeight()-10,
                        980,panelActualLbl.getY()+panelActualLbl.getHeight()-10);

                g.drawLine(logoRamsesLbl.getWidth()-35,panelActualLbl.getY()+20,
                        logoRamsesLbl.getWidth()-35, panelActualLbl.getHeight()-20);

                g.drawLine(930,panelActualLbl.getY()+20,930,panelActualLbl.getHeight()-20);
            }
        };

        JButton regresarBtn = new JButton();
        regresarBtn.setLocation(940,panelActualLbl.getY()+20);
        regresarBtn.setSize(40,40);
        ImageIcon regresarBotonIcon = new ImageIcon("src/img/botonRegresarIcon.png");
        regresarBtn.setIcon(regresarBotonIcon);
        regresarBtn.setVisible(true);
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                repaint();
                revalidate();
            }
        });
        menuSuperiorPanel.setPreferredSize(new Dimension(1000,80));
        menuSuperiorPanel.setBackground(Color.WHITE);
        menuSuperiorPanel.add(panelActualLbl);
        menuSuperiorPanel.add(logoRamsesLbl);
        menuSuperiorPanel.add(regresarBtn);
        menuSuperiorPanel.setLayout(null);
        menuSuperiorPanel.setVisible(false);

        this.add(menuSuperiorPanel, BorderLayout.NORTH);
        repaint();
        revalidate();


        limpiarVentana();

        this.repaint();
        this.revalidate();
    }

    public void limpiarVentana() throws SQLException {
        historialPaneles.add(actual);
        //pasenUbi = historialPaneles.size()-1;
        if (panel != null) {
            this.remove(panel);
        }

        if (actual.equals("login")) {
            panel = login();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("home")) {

            panelActualLbl.setText("Home");

            panel = home();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("vehiculos")) {

            panelActualLbl.setText("Vehículos");

            panel = vehiculos();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("clientes")) {

            panelActualLbl.setText("Clientes");

            panel = clientes();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarCliente")) {

            panelActualLbl.setText("Consultar cliente");

            panel = consultarCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarHistorialClienteSeleccionado")) {

            panelActualLbl.setText("Consultar historial del cliente");

            panel = historialClienteSeleccionado(id_cliente_a_consultar, nombre_cliente, email_cliente, telefono_cliente);

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("crearCliente")) {

            panelActualLbl.setText("Crear cliente");

            panel = crearCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarCliente")) {

            panelActualLbl.setText("Editar cliente");

            panel = editarCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarClienteSeleccionado")) {

            panelActualLbl.setText("Editar cliente seleccionado");

            panel = editarClienteSeleccionado(id_cliente_a_editar);

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("eliminarCliente")) {

            panelActualLbl.setText("Eliminar cuenta");

            panel = eliminarCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("rentas")) {

            panelActualLbl.setText("Rentas");

            panel = rentas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarRenta")) {
            panelActualLbl.setText("Consultar rentas");

            panel = consultarRentas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarAutomovilSeleccionado")){
            panelActualLbl.setText("Historial de auto seleccionado");

            panel = historialDeAutoSeleccionado(id_auto_consultar);

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();

        }
        if (actual.equals("crearRenta")) {

            panelActualLbl.setText("Crear renta");

            panel = crearRenta();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarRenta")) {

            panelActualLbl.setText("Editar renta");

            panel = editarRenta();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarRentaSeleccionada")){

            panelActualLbl.setText("Editar renta seleccionada");

            panel = editarRentaSeleccionada(id_renta_editar);

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("eliminarRenta")){

            panelActualLbl.setText("Eliminar renta");

            panel = eliminarRenta();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("categorias")) {

            panelActualLbl.setText("Categorías");

            panel = categorias();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("marcas")) {

            panelActualLbl.setText("Marcas");

            panel = marcas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarVehiculo")) {

            panelActualLbl.setText("Consultar vehículo");

            panel = consultarVehiculo();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarCategorias")) {

            panelActualLbl.setText("Consultar categorías");

            panel = consultarCategorias();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarMarcas")) {

            panelActualLbl.setText("Consultar marcas");

            panel = consultarMarcas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
    }

    /*public JPanel pintarMenuSuperior() {
        System.out.println("actual: " + actual + " anterior: " + anterior);

        ImageIcon logoRamsesIcon = new ImageIcon("logoRamservice.png");
        JLabel logoRamsesLbl = new JLabel();
            logoRamsesLbl.setIcon(logoRamsesIcon);
            logoRamsesLbl.setSize(logoRamsesIcon.getIconWidth(),logoRamsesIcon.getIconHeight());
            logoRamsesLbl.setLocation(0,-50);
        panelActualLbl.setSize(300, 80);
        panelActualLbl.setLocation(180, 0);
        panelActualLbl.setForeground(Color.blue);
        panelActualLbl.setFont(new Font("Arial", Font.BOLD, 35));
        JPanel menuSuperiorPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawLine(20,panelActualLbl.getY()+panelActualLbl.getHeight()-10,
                        980,panelActualLbl.getY()+panelActualLbl.getHeight()-10);

                g.drawLine(panelActualLbl.getX()-20,panelActualLbl.getY()+20,
                        panelActualLbl.getX()-20, panelActualLbl.getHeight()-20);

                g.drawLine(930,panelActualLbl.getY()+20,930,panelActualLbl.getHeight()-20);
            }
        };

        JButton regresarBtn = new JButton("<--");
        regresarBtn.setLocation(940,panelActualLbl.getY()+20);
        regresarBtn.setSize(50,40);
        regresarBtn.setVisible(true);
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("actual: " + actual + " anterior: " + anterior);
            }
        });
        menuSuperiorPanel.setPreferredSize(new Dimension(1000,80));
        menuSuperiorPanel.setBackground(Color.orange);
        menuSuperiorPanel.add(panelActualLbl);
        menuSuperiorPanel.add(logoRamsesLbl);
        menuSuperiorPanel.add(regresarBtn);
        menuSuperiorPanel.setLayout(null);
        repaint();
        revalidate();
        return menuSuperiorPanel;
    }*/

    public JPanel login() {
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

        JLabel bienvenido = new JLabel("Bienvenido", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 25));
        bienvenido.setSize(160, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.black);
        loginPanel.add(bienvenido);

        JLabel point = new JLabel(".", JLabel.CENTER);
        point.setFont(new Font("Arial", Font.BOLD, 25));
        point.setSize(300, 80);
        point.setLocation(130, 10);
        point.setForeground(Color.decode("#38B6FF"));
        loginPanel.add(point);

        JLabel inicia = new JLabel("Inicia sesión", JLabel.CENTER);
        inicia.setFont(new Font("Arial", Font.BOLD, 35));
        inicia.setSize(250, 80);
        inicia.setLocation(88, 80);
        inicia.setForeground(Color.black);
        loginPanel.add(inicia);

        JLabel emailText = new JLabel("Correo electrónico", JLabel.CENTER);
        emailText.setFont(new Font("Arial", Font.BOLD, 15));
        emailText.setSize(250, 80);
        emailText.setLocation(20, 170);
        emailText.setForeground(Color.black);
        loginPanel.add(emailText);

        JTextField correo = new JTextField();
        correo.setSize(300, 36);
        correo.setLocation(75, 220);
        correo.setBorder(roundedBorder);
        loginPanel.add(correo);

        JLabel passwordText = new JLabel("Contraseña", JLabel.CENTER);
        passwordText.setFont(new Font("Arial", Font.BOLD, 15));
        passwordText.setSize(250, 80);
        passwordText.setLocation(-7, 270);
        passwordText.setForeground(Color.black);
        loginPanel.add(passwordText);

        /*JTextField password = new JTextField();
        password.setSize(300,36);
        password.setLocation(75,320);
        password.setBorder(roundedBorder);
        loginPanel.add(password);*/

        JPasswordField password = new JPasswordField();
        password.setSize(300, 36);
        password.setLocation(75, 320);
        password.setBorder(roundedBorder);
        loginPanel.add(password);

        JPanel loginIMG = new JPanel();
        loginIMG.setSize(550, 800);
        loginIMG.setLocation(450, 0);
        loginIMG.setLayout(null);
        loginIMG.setBackground(Color.white);
        login.add(loginIMG);

        JLabel imagen2 = new JLabel();
        imagen2.setSize(550, 800);
        ImageIcon imag2 = new ImageIcon("src/img/car.png");
        ImageIcon icono2 = new ImageIcon(imag2.getImage().getScaledInstance(imagen2.getWidth(), imagen2.getHeight(), Image.SCALE_DEFAULT));
        imagen2.setIcon(icono2);
        imagen2.setLocation(10, 0);
        loginIMG.add(imagen2);

        JButton loginBTN = new JButton();
        loginBTN.setSize(226, 31);
        loginBTN.setLocation(105, 400);

        ImageIcon loginBotonIcon = new ImageIcon("src/img/loginBoton.png");
        loginBTN.setIcon(loginBotonIcon);
        loginPanel.add(loginBTN);

        loginBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "home";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        JLabel imagen1 = new JLabel();
        imagen1.setSize(350, 350);
        ImageIcon imag1 = new ImageIcon("src/img/copy.png");
        ImageIcon icono1 = new ImageIcon(imag1.getImage().getScaledInstance(imagen1.getWidth(), imagen1.getHeight(), Image.SCALE_DEFAULT));
        imagen1.setIcon(icono1);
        imagen1.setLocation(33, 430);
        loginPanel.add(imagen1);

        return login;
    }

    public JPanel home() {

        menuSuperiorPanel.setVisible(true);
        anterior = actual;
        actual = "home";
        JPanel homePanel = new JPanel();
        homePanel.setSize(1000, 800);
        homePanel.setLocation(0, 0);
        homePanel.setLayout(null);
        homePanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Bienvenido, ", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 25));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(230, 600-100);
        bienvenido.setForeground(Color.black);
        homePanel.add(bienvenido);

        //Cuando se hagan las validaciones, este texto será personalizado ok
        JLabel admin = new JLabel("Administrador.", JLabel.CENTER);
        admin.setFont(new Font("Arial", Font.BOLD, 25));
        admin.setSize(300, 80);
        admin.setLocation(397, 600-100);
        admin.setForeground(Color.decode("#38B6FF"));
        homePanel.add(admin);

        JButton logoutBTN = new JButton();
        logoutBTN.setSize(100, 25);
        logoutBTN.setLocation(55, 625-95);
        ImageIcon logoutBTNIMG = new ImageIcon("src/img/logout.png");
        logoutBTN.setIcon(logoutBTNIMG);
        homePanel.add(logoutBTN);

        logoutBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuSuperiorPanel.setVisible(false);
                anterior = actual;
                actual = "login";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        JButton vehiculosBTN = new JButton();
        vehiculosBTN.setSize(173, 390);
        vehiculosBTN.setLocation(50, 100);
        ImageIcon vehiculosBTNIMG = new ImageIcon("src/img/vehiculos-home.png");
        vehiculosBTN.setIcon(vehiculosBTNIMG);
        homePanel.add(vehiculosBTN);

        vehiculosBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "vehiculos";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        JButton clientesBTN = new JButton("Clientes");
        clientesBTN.setSize(160, 390);
        clientesBTN.setLocation(240, 100);
        ImageIcon clientesBTNIMG = new ImageIcon("src/img/clientes-home.png");
        clientesBTN.setIcon(clientesBTNIMG);
        homePanel.add(clientesBTN);
        clientesBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "clientes";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        JButton rentasBTN = new JButton("Rentas");
        rentasBTN.setSize(160, 390);
        rentasBTN.setLocation(415, 100);
        ImageIcon rentasBTNIMG = new ImageIcon("src/img/rentas-home.png");
        rentasBTN.setIcon(rentasBTNIMG);
        homePanel.add(rentasBTN);
        rentasBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "rentas";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        JButton categoriasBTN = new JButton("Categorias");
        categoriasBTN.setSize(160, 390);
        categoriasBTN.setLocation(590, 100);
        ImageIcon categoriasBTNIMG = new ImageIcon("src/img/categorias-home.png");
        categoriasBTN.setIcon(categoriasBTNIMG);
        homePanel.add(categoriasBTN);
        categoriasBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "categorias";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        JButton marcasBTN = new JButton("Marcas");
        marcasBTN.setSize(160, 390);
        marcasBTN.setLocation(770, 100);
        ImageIcon marcasBTNIMG = new ImageIcon("src/img/marcas-home.png");
        marcasBTN.setIcon(marcasBTNIMG);
        homePanel.add(marcasBTN);
        marcasBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "marcas";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        return homePanel;
    }

    public JPanel vehiculos() {
        anterior = "home";

        JPanel vehiculosPanel = new JPanel();
        vehiculosPanel.setSize(1000, 800);
        vehiculosPanel.setLocation(0, 0);
        vehiculosPanel.setLayout(null);
        vehiculosPanel.setBackground(Color.decode("#FFFFFF"));



        JLabel lblNewLabel_3_2_1_1 = new JLabel("");
        lblNewLabel_3_2_1_1.setIcon(new ImageIcon("src/img/Vector.png"));
        lblNewLabel_3_2_1_1.setBounds(377, 399, 48, 53);
        vehiculosPanel.add(lblNewLabel_3_2_1_1);

        JLabel lblNewLabel_3_2_2 = new JLabel("");
        lblNewLabel_3_2_2.setIcon(new ImageIcon("src/img/Local gas station.png"));
        lblNewLabel_3_2_2.setHorizontalTextPosition(SwingConstants.LEFT);
        lblNewLabel_3_2_2.setBounds(377, 552, 35, 53);
        vehiculosPanel.add(lblNewLabel_3_2_2);

        JLabel lblNewLabel_4_2 = new JLabel("BMW TQM JOP");
        lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_2.setForeground(Color.BLACK);
        lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_2.setBounds(133, 562, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_2);

        JLabel lblNewLabel_4_1_4 = new JLabel("Coupé");
        lblNewLabel_4_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_4.setForeground(Color.BLACK);
        lblNewLabel_4_1_4.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1_4.setBounds(360, 408, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1_4);

        JLabel lblNewLabel_4_1_2_1 = new JLabel("2021");
        lblNewLabel_4_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_2_1.setForeground(Color.BLACK);
        lblNewLabel_4_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1_2_1.setBounds(355, 511, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1_2_1);

        JLabel lblNewLabel_3_3 = new JLabel("");
        lblNewLabel_3_3.setIcon(new ImageIcon("src/img/Calendar today.png"));
        lblNewLabel_3_3.setBounds(377, 500, 35, 53);
        vehiculosPanel.add(lblNewLabel_3_3);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setIcon(new ImageIcon("src/img/image 9.png"));
        lblNewLabel_2_1.setBounds(109, 424, 226, 140);
        vehiculosPanel.add(lblNewLabel_2_1);

        JLabel lblNewLabel_4_1_3_1 = new JLabel("Gasolina");
        lblNewLabel_4_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_3_1.setForeground(Color.BLACK);
        lblNewLabel_4_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1_3_1.setBounds(370, 562, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1_3_1);

        JLabel lblNewLabel_5_1 = new JLabel("$204 USD por día");
        lblNewLabel_5_1.setOpaque(true);
        lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5_1.setForeground(Color.BLACK);
        lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_5_1.setBackground(new Color(56, 182, 255));
        lblNewLabel_5_1.setBounds(658, 404, 226, 55);
        vehiculosPanel.add(lblNewLabel_5_1);

        JLabel lblNewLabel_4_1_1_1 = new JLabel("Estándar");
        lblNewLabel_4_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1_1.setForeground(Color.BLACK);
        lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1_1_1.setBounds(370, 463, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1_1_1);

        JLabel lblNewLabel_3_1_1 = new JLabel("");
        lblNewLabel_3_1_1.setIcon(new ImageIcon("src/img/Gearbox.png"));
        lblNewLabel_3_1_1.setBounds(375, 451, 35, 53);
        vehiculosPanel.add(lblNewLabel_3_1_1);

        JButton btnEliminar = new JButton("Eliminar");

        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Color.decode("#BF0000"));
        btnEliminar.setBounds(658, 225, 226, 53);
        vehiculosPanel.add(btnEliminar);

        JButton btnNewButton = new JButton("Consultar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarVehiculo";

                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        btnNewButton.setFocusPainted(false);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setBounds(658, 156, 226, 55);
        vehiculosPanel.add(btnNewButton);

        JLabel lblNewLabel_5 = new JLabel("$204 USD por día");
        lblNewLabel_5.setForeground(Color.BLACK);
        lblNewLabel_5.setOpaque(true);
        lblNewLabel_5.setBackground(Color.decode("#38B6FF"));
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(658, 90, 226, 55);
        vehiculosPanel.add(lblNewLabel_5);

        JLabel lblNewLabel_4_1_2 = new JLabel("2021");
        lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_2.setForeground(Color.BLACK);
        lblNewLabel_4_1_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1_2.setBounds(355, 197, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1_2);

        JLabel lblNewLabel_4_1_3 = new JLabel("Gasolina");
        lblNewLabel_4_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_3.setForeground(Color.BLACK);
        lblNewLabel_4_1_3.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1_3.setBounds(370, 248, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1_3);

        JLabel lblNewLabel_4_1_1 = new JLabel("Estándar");
        lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1.setForeground(Color.BLACK);
        lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1_1.setBounds(370, 149, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1_1);

        JLabel lblNewLabel_4_1 = new JLabel("Coupé");
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setForeground(Color.BLACK);
        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_1.setBounds(360, 94, 166, 30);
        vehiculosPanel.add(lblNewLabel_4_1);

        JLabel lblNewLabel_3_1 = new JLabel("");
        lblNewLabel_3_1.setIcon(new ImageIcon("src/img/Gearbox.png"));
        lblNewLabel_3_1.setBounds(375, 137, 35, 53);
        vehiculosPanel.add(lblNewLabel_3_1);

        JLabel lblNewLabel_4 = new JLabel("BMW Rey X");
        lblNewLabel_4.setForeground(Color.BLACK);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4.setBounds(133, 248, 166, 30);
        vehiculosPanel.add(lblNewLabel_4);

        JLabel lblNewLabel_3_2_1 = new JLabel("");
        lblNewLabel_3_2_1.setIcon(new ImageIcon("src/img/Vector.png"));
        lblNewLabel_3_2_1.setBounds(377, 85, 48, 53);
        vehiculosPanel.add(lblNewLabel_3_2_1);

        JLabel lblNewLabel_3_2 = new JLabel("");
        lblNewLabel_3_2.setIcon(new ImageIcon("src/img/Local gas station.png"));
        lblNewLabel_3_2.setBounds(377, 238, 35, 53);
        vehiculosPanel.add(lblNewLabel_3_2);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("src/img/Calendar today.png"));
        lblNewLabel_3.setBounds(377, 186, 35, 53);
        vehiculosPanel.add(lblNewLabel_3);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("src/img/image 10.png"));
        lblNewLabel_2.setBounds(109, 110, 226, 140);
        vehiculosPanel.add(lblNewLabel_2);





        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setOpaque(true);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(Color.LIGHT_GRAY);
        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel.setBounds(84, 75, 826, 232);
        vehiculosPanel.add(lblNewLabel);

        JButton btnEliminar_1 = new JButton("Eliminar");
        btnEliminar_1.setForeground(Color.WHITE);
        btnEliminar_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar_1.setFocusPainted(false);
        btnEliminar_1.setBorderPainted(false);
        btnEliminar_1.setBackground(new Color(191, 0, 0));
        btnEliminar_1.setBounds(658, 539, 226, 53);
        vehiculosPanel.add(btnEliminar_1);

        JButton btnNewButton_1 = new JButton("Consultar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.setBounds(658, 470, 226, 55);
        vehiculosPanel.add(btnNewButton_1);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_1.setBounds(84, 389, 826, 232);
        vehiculosPanel.add(lblNewLabel_1);

        return vehiculosPanel;
    }

    public JPanel consultarVehiculo() {
        anterior = "vehiculos";

        JPanel consultarVehi = new JPanel();
        consultarVehi.setFont(new Font("Tahoma", Font.BOLD, 11));
        consultarVehi.setRequestFocusEnabled(false);
        consultarVehi.setForeground(Color.BLACK);
        consultarVehi.setSize(1000, 681);
        consultarVehi.setLocation(0, 80);
        consultarVehi.setLayout(null);
        consultarVehi.setBackground(Color.decode("#FFFFFF"));

        JTextField textField_3 = new JTextField();
        textField_3.setOpaque(false);
        textField_3.setHorizontalAlignment(SwingConstants.LEFT);
        textField_3.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField_3.setColumns(10);
        textField_3.setBorder(null);
        textField_3.setBackground(SystemColor.menu);
        textField_3.setBounds(813, 83, 93, 29);
        consultarVehi.add(textField_3);

        JLabel lblNewLabel_4_3 = new JLabel("");
        lblNewLabel_4_3.setIcon(new ImageIcon("src/img/gasg.png"));
        lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_3.setBounds(401, 409, 58, 38);
        consultarVehi.add(lblNewLabel_4_3);

        JLabel lblNewLabel_4_2 = new JLabel("");
        lblNewLabel_4_2.setIcon(new ImageIcon("src/img/calendarg.png"));
        lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_2.setBounds(276, 409, 58, 38);
        consultarVehi.add(lblNewLabel_4_2);

        JLabel lblNewLabel_4_1 = new JLabel("");
        lblNewLabel_4_1.setIcon(new ImageIcon("src/img/gearg.png"));
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setBounds(135, 409, 58, 38);
        consultarVehi.add(lblNewLabel_4_1);

        JLabel lblNewLabel_2_3_1 = new JLabel("Transmisión");
        lblNewLabel_2_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3_1.setForeground(Color.BLACK);
        lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_3_1.setBounds(787, 148, 119, 23);
        consultarVehi.add(lblNewLabel_2_3_1);

        JComboBox comboBox_2 = new JComboBox();
        comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Gasolina"}));
        comboBox_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBox_2.setBounds(670, 331, 236, 29);
        consultarVehi.add(comboBox_2);

        JTextField textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBorder(null);
        textField_1.setBackground(SystemColor.menu);
        textField_1.setBounds(670, 257, 236, 29);
        consultarVehi.add(textField_1);

        JLabel lblNewLabel_2_2_2 = new JLabel("Combustible");
        lblNewLabel_2_2_2.setForeground(Color.BLACK);
        lblNewLabel_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_2_2.setBounds(670, 308, 154, 23);
        consultarVehi.add(lblNewLabel_2_2_2);

        JLabel lblNewLabel_2_2_1 = new JLabel("Año");
        lblNewLabel_2_2_1.setForeground(Color.BLACK);
        lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_2_1.setBounds(670, 235, 154, 23);
        consultarVehi.add(lblNewLabel_2_2_1);

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        comboBox_1.setBounds(806, 175, 100, 29);
        consultarVehi.add(comboBox_1);

        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"BMW"}));
        comboBox.setBounds(670, 175, 100, 29);
        consultarVehi.add(comboBox);

        JTextField textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setHorizontalAlignment(SwingConstants.LEFT);
        textField_2.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField_2.setText("$");
        textField_2.setColumns(10);
        textField_2.setBorder(null);
        textField_2.setBackground(SystemColor.menu);
        textField_2.setBounds(806, 83, 100, 29);
        consultarVehi.add(textField_2);

        JLabel lblNewLabel_2_3 = new JLabel("Costo por día");
        lblNewLabel_2_3.setForeground(Color.BLACK);
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_3.setBounds(787, 59, 119, 23);
        consultarVehi.add(lblNewLabel_2_3);

        JButton lblNewLabel_3 = new JButton("");
        lblNewLabel_3.setBorderPainted(false);
        lblNewLabel_3.setRequestFocusEnabled(false);
        lblNewLabel_3.setFocusPainted(false);
        lblNewLabel_3.setContentAreaFilled(false);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setIcon(new ImageIcon("src/img/Group 56 (1).png"));
        lblNewLabel_3.setBounds(652, 424, 271, 92);
        consultarVehi.add(lblNewLabel_3);

        JTextField textField = new JTextField();
        textField.setBorder(null);
        textField.setBackground(SystemColor.menu);
        textField.setBounds(670, 84, 100, 29);
        consultarVehi.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2_2 = new JLabel("Categoría");
        lblNewLabel_2_2.setForeground(Color.BLACK);
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_2.setBounds(670, 148, 154, 23);
        consultarVehi.add(lblNewLabel_2_2);

        JLabel lblNewLabel_2 = new JLabel("Modelo");
        lblNewLabel_2.setForeground(Color.BLACK);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(670, 59, 154, 23);
        consultarVehi.add(lblNewLabel_2);

        JTextArea txtrLosSuvsDeportivos = new JTextArea();
        txtrLosSuvsDeportivos.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed\r\ndo eiusmod tempor incididunt ut labore et dolore magna \r\naliqua. Ut enim ad minim veniam, quis nostrud exercitation\r\n ullamco laboris nisi ut aliquip ex ea commodo consequat. \r\nDuis aute irure dolor in reprehenderit in voluptate velit esse \r\ncillum dolore eu fugiat nulla pariatur.");
        txtrLosSuvsDeportivos.setOpaque(false);
        txtrLosSuvsDeportivos.setForeground(Color.BLACK);
        txtrLosSuvsDeportivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtrLosSuvsDeportivos.setBounds(24, 471, 487, 164);
        consultarVehi.add(txtrLosSuvsDeportivos);



        JButton btnEliminar_1 = new JButton("Editar");
        btnEliminar_1.setForeground(Color.WHITE);
        btnEliminar_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar_1.setFocusPainted(false);
        btnEliminar_1.setBorderPainted(false);
        btnEliminar_1.setBackground(Color.decode("#38B6FF"));
        btnEliminar_1.setBounds(657, 547, 260, 53);
        consultarVehi.add(btnEliminar_1);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(SystemColor.controlShadow);
        lblNewLabel_1.setBounds(632, 24, 307, 597);
        consultarVehi.add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("BMW Rey X");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(166, 24, 282, 58);
        consultarVehi.add(lblNewLabel);

        JLabel lblNewLabel_1_1 = new JLabel("");
        lblNewLabel_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1_1.setOpaque(true);
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBackground(SystemColor.controlShadow);
        lblNewLabel_1_1.setBounds(17, 467, 517, 154);
        consultarVehi.add(lblNewLabel_1_1);

        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon("src/img/Vectorg.png"));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(7, 409, 58, 38);
        consultarVehi.add(lblNewLabel_4);

        JLabel lblNewLabel_2_2_1_1 = new JLabel("Coupé");
        lblNewLabel_2_2_1_1.setForeground(Color.BLACK);
        lblNewLabel_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_2_2_1_1.setBounds(60, 409, 76, 38);
        consultarVehi.add(lblNewLabel_2_2_1_1);

        JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Estándar");
        lblNewLabel_2_2_1_1_1.setForeground(Color.BLACK);
        lblNewLabel_2_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_2_2_1_1_1.setBounds(185, 409, 93, 38);
        consultarVehi.add(lblNewLabel_2_2_1_1_1);

        JLabel lblNewLabel_2_2_1_1_2 = new JLabel("2021");
        lblNewLabel_2_2_1_1_2.setForeground(Color.BLACK);
        lblNewLabel_2_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_2_2_1_1_2.setBounds(329, 409, 76, 38);
        consultarVehi.add(lblNewLabel_2_2_1_1_2);

        JLabel lblNewLabel_2_2_1_1_3 = new JLabel("Gasolina");
        lblNewLabel_2_2_1_1_3.setForeground(Color.BLACK);
        lblNewLabel_2_2_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_2_2_1_1_3.setBounds(455, 409, 100, 38);
        consultarVehi.add(lblNewLabel_2_2_1_1_3);

        JLabel lblNewLabel_5 = new JLabel("$204 / día");
        lblNewLabel_5.setForeground(new Color(0, 128, 0));
        lblNewLabel_5.setBackground(Color.GREEN);
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_5.setBounds(195, 77, 216, 37);
        consultarVehi.add(lblNewLabel_5);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setIcon(new ImageIcon("src/img/carrograndeazul.png"));
        lblNewLabel_2_1.setBounds(22, 100, 552, 298);
        consultarVehi.add(lblNewLabel_2_1);

        return consultarVehi;
    }

    public JPanel clientes() {
        int xBtn = 230;
        int yBtn = 127;
        int widthBtn  = 124;
        int heightBtn = 170;
        anterior = "home";


        JPanel clientesPanel = new JPanel();
        clientesPanel.setSize(1000, 800);
        clientesPanel.setLocation(0, 0);
        clientesPanel.setLayout(null);
        clientesPanel.setBackground(Color.decode("#FFFFFF"));

        JButton consultarBTN = new JButton();
        consultarBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        consultarBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        consultarBTN.setFont(new Font("Arial", Font.BOLD, 14));
        consultarBTN.setSize(widthBtn, heightBtn);
        consultarBTN.setLocation(xBtn, yBtn);
        consultarBTN.setOpaque(true);
        consultarBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon consultarIcon = new ImageIcon("src/img/consultarIcono.png");
        consultarBTN.setIcon(consultarIcon);
        clientesPanel.add(consultarBTN);
        consultarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarCliente";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        xBtn += 150;
        JButton crearBTN = new JButton(); // para poner separadito el texto y que se centre ya en sus cosas neta no peude estar asi haciendo nada puesque se ponga las pilas
        crearBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        crearBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        crearBTN.setFont(new Font("Arial", Font.BOLD, 14));
        crearBTN.setSize(widthBtn, heightBtn);
        crearBTN.setLocation(xBtn, yBtn);
        crearBTN.setOpaque(true);
        crearBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon crearIcon = new ImageIcon("src/img/crearIcono.png");
        crearBTN.setIcon(crearIcon);
        clientesPanel.add(crearBTN);
        crearBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "crearCliente";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        xBtn += 150;
        JButton editarBTN = new JButton(); // para poner separadito el texto y que se centre ya en sus cosas neta no peude estar asi haciendo nada puesque se ponga las pilas
        editarBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        editarBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        editarBTN.setFont(new Font("Arial", Font.BOLD, 14));
        editarBTN.setSize(widthBtn, heightBtn);
        editarBTN.setLocation(xBtn, yBtn);
        editarBTN.setOpaque(true);
        editarBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon editarIcon = new ImageIcon("src/img/editarIcono.png");
        editarBTN.setIcon(editarIcon);
        clientesPanel.add(editarBTN);
        editarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarCliente";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        xBtn += 150;

        JButton borrarBTN = new JButton(); // para poner separadito el texto y que se centre ya en sus cosas neta no peude estar asi haciendo nada puesque se ponga las pilas
        borrarBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        borrarBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        borrarBTN.setFont(new Font("Arial", Font.BOLD, 14));
        borrarBTN.setSize(widthBtn, heightBtn);
        borrarBTN.setLocation(xBtn, yBtn);
        borrarBTN.setOpaque(true);
        borrarBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon borrarIcon = new ImageIcon("src/img/eliminarIcono.png");
        borrarBTN.setIcon(borrarIcon);
        clientesPanel.add(borrarBTN);
        borrarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual; //creo q nomas cambiando el actual jala mejor o no se xd
                actual = "eliminarCliente";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        String[] columnasTabla = {
                "<html><div style='text-align: center;'>Id<br>clientes</div></html>",
                "<html> <div style = 'text-align : center;'>Nombre</div></html>",
                "<html> <div style = 'text-align : center;'>Apellido</div></html>",
                "<html> <div style = 'text-align : center;'>Correo</div></html>",
                "<html> <div style = 'text-align : center;'>Telefóno</div></html>",
                "<html> <div style = 'text-align : center;'>Tarjeta<br>crédito</div></html>",
                "<html> <div style = 'text-align : center;'>Estado de <br> cuenta</div></html>"};

        JTable tabla_clientes = new JTable();

            DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTabla,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
            sp.setSize(700,250);
            sp.setLocation(165,400);
            sp.setVisible(true);

        clientesPanel.add(sp);
        return clientesPanel;
    }

    public JPanel consultarCliente() throws SQLException {
        anterior = "clientes";
        JPanel consultarClientePNL = new JPanel();
        consultarClientePNL.setSize(1000, 800);
        consultarClientePNL.setLocation(0, 0);
        consultarClientePNL.setLayout(null);
        consultarClientePNL.setBackground(Color.decode("#FFFFFF"));

        JLabel descripcionEditarCliente = new JLabel("ID del cliente a consultar");
        descripcionEditarCliente.setSize(400,100);
        descripcionEditarCliente.setLocation(370,20);
        descripcionEditarCliente.setFont(new Font("Arial", Font.BOLD, 24));
        consultarClientePNL.add(descripcionEditarCliente);
        // corregir URGE!!!
        JComboBox idClientesCB = new JComboBox(Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes"));
        idClientesCB.setSize(230,30);
        idClientesCB.setLocation(400,100);

        JButton consultarHistorialClienteBtn = new JButton();
        consultarHistorialClienteBtn.setSize(226,44);
        consultarHistorialClienteBtn.setLocation(400, 145);
        ImageIcon consultarHistorialIcon = new ImageIcon("src/img/consultarHistorialClienteIcon.png");
        consultarHistorialClienteBtn.setIcon(consultarHistorialIcon);
        consultarHistorialClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id_cliente_a_consultar = Integer.parseInt((String) idClientesCB.getSelectedItem());
                String [] datos = Clientes_Service.obtener_fila("SELECT * FROM clientes where id_de_cliente = " + id_cliente_a_consultar);
                nombre_cliente = datos[1];
                email_cliente = datos[3];
                telefono_cliente = datos[4];
                anterior = actual;
                actual = "consultarHistorialClienteSeleccionado";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });


        String[] columnasTabla = {"<html><div style='text-align: center;'>Id<br>clientes</div></html>",
                "<html> <div style = 'text-align : center;'>Nombre</div></html>",
                "<html> <div style = 'text-align : center;'>Apellido</div></html>",
                "<html> <div style = 'text-align : center;'>Correo</div></html>",
                "<html> <div style = 'text-align : center;'>Telefóno</div></html>",
                "<html> <div style = 'text-align : center;'>Tarjeta<br>crédito</div></html>",
                "<html> <div style = 'text-align : center;'>Estado de <br> cuenta</div></html>"};
        JTable tabla_clientes = new JTable();

        DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTabla,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
            sp.setSize(700,250);
            sp.setLocation(165,400);
            sp.setVisible(true);

        consultarClientePNL.add(idClientesCB);
        consultarClientePNL.add(sp);
        consultarClientePNL.add(consultarHistorialClienteBtn);
        return consultarClientePNL;
    }


    public JPanel historialDeAutoSeleccionado(int id_auto_consultar){
        JPanel historialClienteSeleccionadoPanel = new JPanel();
        historialClienteSeleccionadoPanel.setSize(1000, 800);
        historialClienteSeleccionadoPanel.setLocation(0, 0);
        historialClienteSeleccionadoPanel.setLayout(null);
        historialClienteSeleccionadoPanel.setBackground(Color.decode("#FFFFFF"));

        int xLbl = 100;
        JLabel autoLbl = new JLabel("ID de auto seleccionado: " + id_auto_consultar);
        autoLbl.setFont(new Font("Arial", Font.BOLD, 24));
        autoLbl.setSize(700,50);
        autoLbl.setLocation(50,75);

        String[] columnasTabla = {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html><div style='text-align: center;'>Id<br>cliente</div></html>",
                "<html><div style='text-align: center;'>Cliente</div></html>",
                "<html><div style='text-align: center;'>Id<br>auto</div></html>",
                "<html><div style='text-align: center;'>Automóvil</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>",
                "<html><div style='text-align: center;'>Tiempo</div></html>",
                "<html><div style='text-align: center;'>Número de<br>tarjeta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>caducidad</div></html>",
                "<html><div style='text-align: center;'>CVV</div></html>",
                "<html><div style='text-align: center;'>Costo</div></html>"};

        JTable tabla_autos = new JTable();

        DefaultTableModel dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla,"SELECT * FROM rentas WHERE identificador_auto = "+id_auto_consultar);
            tabla_autos.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_autos);

        JScrollPane sp = new JScrollPane(tabla_autos);
            sp.setSize(900,250);
            sp.setLocation(50,150);
            sp.setVisible(true);

        historialClienteSeleccionadoPanel.add(autoLbl);
        historialClienteSeleccionadoPanel.add(sp);

        return  historialClienteSeleccionadoPanel;
    }
    public JPanel historialClienteSeleccionado(int id_cliente_a_consultar, String nombre_cliente, String email_cliente, String telefono_cliente){
        JPanel historialClienteSeleccionadoPanel = new JPanel();
        historialClienteSeleccionadoPanel.setSize(1000, 800);
        historialClienteSeleccionadoPanel.setLocation(0, 0);
        historialClienteSeleccionadoPanel.setLayout(null);
        historialClienteSeleccionadoPanel.setBackground(Color.decode("#FFFFFF"));

        int xLbl = 100;
        JLabel idClienteLbl = new JLabel("Id Cliente: " + id_cliente_a_consultar);
        idClienteLbl.setFont(new Font("Arial", Font.BOLD, 24));
        idClienteLbl.setSize(500,50);
        idClienteLbl.setLocation(xLbl+20,50);
        historialClienteSeleccionadoPanel.add(idClienteLbl);

        JLabel nombreLbl = new JLabel("Nombre: " + nombre_cliente);
        nombreLbl.setFont(new Font("Arial", Font.BOLD, 24));
        nombreLbl.setSize(500,50);
        nombreLbl.setLocation(xLbl+20,130);
        historialClienteSeleccionadoPanel.add(nombreLbl);
        xLbl += 400;

        JLabel correoLbl = new JLabel("Email: " + email_cliente);
        correoLbl.setFont(new Font("Arial", Font.BOLD, 24));
        correoLbl.setSize(300,50);
        correoLbl.setLocation(xLbl,50);
        historialClienteSeleccionadoPanel.add(correoLbl);

        JLabel telefonoLbl = new JLabel("Teléfono: " + telefono_cliente);
        telefonoLbl.setFont(new Font("Arial", Font.BOLD, 24));
        telefonoLbl.setSize(300,50);
        telefonoLbl.setLocation(xLbl,130);
        historialClienteSeleccionadoPanel.add(telefonoLbl);

        String[] columnasTabla= {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html><div style='text-align: center;'>Id<br>cliente</div></html>",
                "<html><div style='text-align: center;'>Cliente</div></html>",
                "<html><div style='text-align: center;'>Id<br>auto</div></html>",
                "<html><div style='text-align: center;'>Automóvil</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>",
                "<html><div style='text-align: center;'>Tiempo</div></html>",
                "<html><div style='text-align: center;'>Número de<br>tarjeta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>caducidad</div></html>",
                "<html><div style='text-align: center;'>CVV</div></html>",
                "<html><div style='text-align: center;'>Costo</div></html>"};
        JTable tabla_autos = new JTable();

        DefaultTableModel dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla,"SELECT * FROM rentas WHERE identificador_cliente = "+id_cliente_a_consultar);
            tabla_autos.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_autos);

        JScrollPane sp = new JScrollPane(tabla_autos);
            sp.setSize(900,250);
            sp.setLocation(50,185);
            sp.setVisible(true);

        historialClienteSeleccionadoPanel.add(sp);

        return  historialClienteSeleccionadoPanel;
    }

    public JPanel crearCliente() {

        JPanel crearClientesPNL = new JPanel();
        crearClientesPNL.setSize(1000, 800);
        crearClientesPNL.setLocation(0, 0);
        crearClientesPNL.setLayout(null);
        crearClientesPNL.setBackground(Color.decode("#FFFFFF"));

        int x = 200;
        int distancia_botones_crear_cancelar = 100;
        int yOriginal = 50;
        int y = yOriginal;
        JLabel nombresLbl = new JLabel("Nombres");
        nombresLbl.setLocation(x,y);
        nombresLbl.setFont(new Font("Arial", Font.BOLD, 16));
        nombresLbl.setSize(200,40);
        crearClientesPNL.add(nombresLbl);
        y += 50;
        JTextField nombresTF = new JTextField();
        nombresTF.setBorder(roundedBorder);
        nombresTF.setLocation(x,y);
        nombresTF.setSize(200,30);
        crearClientesPNL.add(nombresTF);

        y += 50;
        JLabel ApellidosLbl = new JLabel("Apellidos");
        ApellidosLbl.setLocation(x,y);
        ApellidosLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ApellidosLbl.setSize(200,40);
        crearClientesPNL.add(ApellidosLbl);
        y += 50;
        JTextField apellidosTF = new JTextField();
        apellidosTF.setBorder(roundedBorder);
        apellidosTF.setLocation(x,y);
        apellidosTF.setSize(200,30);
        crearClientesPNL.add(apellidosTF);


        y += 50;
        JLabel telefonoLbl = new JLabel("Teléfono");
        telefonoLbl.setLocation(x,y);
        telefonoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        telefonoLbl.setSize(200,40);
        crearClientesPNL.add(telefonoLbl);
        y += 50;
        JTextField telefonoTF = new JTextField();
        telefonoTF.setBorder(roundedBorder);
        telefonoTF.setLocation(x,y);
        telefonoTF.setSize(200,30);
        crearClientesPNL.add(telefonoTF);

        y += 50;
        JLabel correoLbl = new JLabel("Correo electronico");
        correoLbl.setLocation(x,y);
        correoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        correoLbl.setSize(200,40);
        crearClientesPNL.add(correoLbl);
        y += 50;
        JTextField correoTF = new JTextField();
        correoTF.setBorder(roundedBorder);
        correoTF.setLocation(x,y);
        correoTF.setSize(200,30);
        crearClientesPNL.add(correoTF);

        x = x*3;
        y = yOriginal;

        JLabel passwordLbl = new JLabel("Contraseña");
        passwordLbl.setLocation(x,y);
        passwordLbl.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLbl.setSize(200,40);
        crearClientesPNL.add(passwordLbl);
        y += 50;
        JPasswordField passwordPF = new JPasswordField();
        passwordPF.setBorder(roundedBorder);
        passwordPF.setLocation(x,y);
        passwordPF.setSize(200,30);
        crearClientesPNL.add(passwordPF);

        y += 50;


        JLabel passwordConfLbl = new JLabel("Repetir contraseña");
        passwordConfLbl.setLocation(x,y);
        passwordConfLbl.setFont(new Font("Arial", Font.BOLD, 16));
        passwordConfLbl.setSize(200,40);
        crearClientesPNL.add(passwordConfLbl);
        y += 50;
        JPasswordField passwordConfPF = new JPasswordField();
        passwordConfPF.setBorder(roundedBorder);
        passwordConfPF.setLocation(x,y);
        passwordConfPF.setSize(200,30);
        crearClientesPNL.add(passwordConfPF);


        y += 50;
        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        crearClientesPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        crearClientesPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha de caducidad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaCadLbl.setSize(200,40);
        crearClientesPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField();
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        crearClientesPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        crearClientesPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField();
        cvvTF.setBorder(roundedBorder);
        cvvTF.setLocation(x,y);
        cvvTF.setSize(100,30);
        crearClientesPNL.add(cvvTF);


        JButton cancelarBtn = new JButton();
        cancelarBtn.setSize(230,35);
        cancelarBtn.setLocation(250,y+distancia_botones_crear_cancelar);
        ImageIcon cancelarIcon = new ImageIcon("src/img/cancelarBoton.png");
        cancelarBtn.setIcon(cancelarIcon);
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        crearClientesPNL.add(cancelarBtn);

        JButton guardarBtn = new JButton();
        guardarBtn.setSize(230,35);
        guardarBtn.setLocation(525,y+distancia_botones_crear_cancelar);
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombresTF.getText();
                String apellidos = apellidosTF.getText();
                String correo = correoTF.getText();
                String telefono = telefonoTF.getText();
                String numero_de_tarjeta = numTarjetaTF.getText();
                String fecha_de_caducidad = fechaCadTF.getText();
                String cvv = cvvTF.getText();
                String password = new String(passwordPF.getPassword());
                String passwordConf = new String(passwordConfPF.getPassword());
                if (password.equals(passwordConf) && Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
                    try {
                        Clientes_Service.crear_cliente(nombre, apellidos, correo, telefono, numero_de_tarjeta, fecha_de_caducidad, cvv, password);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    System.out.println("popo");
                }
            }
        });
        ImageIcon guardarIcon = new ImageIcon("src/img/crearCuentaBoton.png");
        guardarBtn.setIcon(guardarIcon);
        crearClientesPNL.add(guardarBtn);

        return crearClientesPNL;
    }

    public JPanel editarCliente() {
        anterior = "clientes";

        JPanel editarClientesPNL = new JPanel();
        editarClientesPNL.setSize(1000, 800);
        editarClientesPNL.setLocation(0, 0);
        editarClientesPNL.setLayout(null);
        editarClientesPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel descripcionEditarCliente = new JLabel("ID del cliente a editar");
        descripcionEditarCliente.setSize(400,100);
        descripcionEditarCliente.setLocation(395,20);
        descripcionEditarCliente.setFont(new Font("Arial", Font.BOLD, 24));
        editarClientesPNL.add(descripcionEditarCliente);

        JComboBox idClientesCB = new JComboBox(Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes"));
        idClientesCB.setSize(226,40);
        idClientesCB.setLocation(400,100);
        editarClientesPNL.add(idClientesCB);

        JButton editarClienteBtn = new JButton();
        editarClienteBtn.setSize(226,31);
        editarClienteBtn.setLocation(400, 140);

        ImageIcon editarClienteBotonIcon = new ImageIcon("src/img/editarClienteBoton.png");
        editarClienteBtn.setIcon(editarClienteBotonIcon);
        editarClientesPNL.add(editarClienteBtn);
        editarClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarClienteSeleccionado";
                id_cliente_a_editar = Integer.parseInt((String) idClientesCB.getSelectedItem());

                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });


        String[] columnasTablaClientes = {"<html><div style='text-align: center;'>Id<br>clientes</div></html>",
                "<html> <div style = 'text-align : center;'>Nombre</div></html>",
                "<html> <div style = 'text-align : center;'>Apellido</div></html>",
                "<html> <div style = 'text-align : center;'>Correo</div></html>",
                "<html> <div style = 'text-align : center;'>Telefóno</div></html>",
                "<html> <div style = 'text-align : center;'>Tarjeta<br>crédito</div></html>",
                "<html> <div style = 'text-align : center;'>Estado de <br> cuenta</div></html>"};
        JTable tabla_clientes = new JTable();

        DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTablaClientes,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);
        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
            sp.setSize(700,250);
            sp.setLocation(165,400);
            sp.setVisible(true);

        editarClientesPNL.add(sp);

        return editarClientesPNL;
    }
    public JPanel editarClienteSeleccionado(int id_cliente_a_editar){ //paneles llegados por medio de otro boton no deben de tener ele cambio nac recuerda!!!!!!

        JPanel editarClienteSeleccionadoPNL = new JPanel();
        editarClienteSeleccionadoPNL.setSize(1000, 800);
        editarClienteSeleccionadoPNL.setLocation(0, 0);
        editarClienteSeleccionadoPNL.setLayout(null);
        editarClienteSeleccionadoPNL.setBackground(Color.decode("#FFFFFF"));

        int x = 200;
        int distancia_vertical_botones = 100;
        int yOriginal = 50;
        int y = yOriginal;
        JLabel nombresLbl = new JLabel("Nombres");
        nombresLbl.setLocation(x,y);
        nombresLbl.setFont(new Font("Arial", Font.BOLD, 16));
        nombresLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(nombresLbl);
        y += 50;
        JTextField nombresTF = new JTextField();
        nombresTF.setBorder(roundedBorder);
        nombresTF.setLocation(x,y);
        nombresTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(nombresTF);

        y += 50;
        JLabel ApellidosLbl = new JLabel("Apellidos");
        ApellidosLbl.setLocation(x,y);
        ApellidosLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ApellidosLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(ApellidosLbl);
        y += 50;
        JTextField apellidosTF = new JTextField();
        apellidosTF.setBorder(roundedBorder);
        apellidosTF.setLocation(x,y);
        apellidosTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(apellidosTF);


        y += 50;
        JLabel telefonoLbl = new JLabel("Teléfono");
        telefonoLbl.setLocation(x,y);
        telefonoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        telefonoLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(telefonoLbl);
        y += 50;
        JTextField telefonoTF = new JTextField();
        telefonoTF.setBorder(roundedBorder);
        telefonoTF.setLocation(x,y);
        telefonoTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(telefonoTF);

        y += 50;
        JLabel correoLbl = new JLabel("Correo electrónico");
        correoLbl.setLocation(x,y);
        correoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        correoLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(correoLbl);
        y += 50;
        JTextField correoTF = new JTextField();
        correoTF.setBorder(roundedBorder);
        correoTF.setLocation(x,y);
        correoTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(correoTF);

        x = x*3;
        y = 50;

        JLabel passwordLbl = new JLabel("Contraseña");
        passwordLbl.setLocation(x,y);
        passwordLbl.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(passwordLbl);
        y += 50;
        JPasswordField passwordPF = new JPasswordField();
        passwordPF.setBorder(roundedBorder);
        passwordPF.setLocation(x,y);
        passwordPF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(passwordPF);

        y += 50;


        JLabel passwordConfLbl = new JLabel("Repetir contraseña");
        passwordConfLbl.setLocation(x,y);
        passwordConfLbl.setFont(new Font("Arial", Font.BOLD, 16));
        passwordConfLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(passwordConfLbl);
        y += 50;
        JPasswordField passwordConfPF = new JPasswordField();
        passwordConfPF.setBorder(roundedBorder);
        passwordConfPF.setLocation(x,y);
        passwordConfPF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(passwordConfPF);


        y += 50;
        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha de caducidad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaCadLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField();
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField();
        cvvTF.setBorder(roundedBorder);
        cvvTF.setLocation(x,y);
        cvvTF.setSize(100,30);
        editarClienteSeleccionadoPNL.add(cvvTF);

        JButton cancelarBtn = new JButton();
        cancelarBtn.setSize(230,35);
        cancelarBtn.setLocation(250,y+distancia_vertical_botones);
        ImageIcon cancelarIcon = new ImageIcon("src/img/cancelarBoton.png");
        cancelarBtn.setIcon(cancelarIcon);
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        editarClienteSeleccionadoPNL.add(cancelarBtn);

        JButton guardarBtn = new JButton();
        guardarBtn.setSize(230,35);
        guardarBtn.setLocation(525,y+distancia_vertical_botones);
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombresTF.getText();
                String apellidos = apellidosTF.getText();
                String correo = correoTF.getText();
                String telefono = telefonoTF.getText();
                String numero_de_tarjeta = numTarjetaTF.getText();
                String fecha_de_caducidad = fechaCadTF.getText();
                String cvv = cvvTF.getText();
                String password = new String(passwordPF.getPassword());
                String passwordConf = new String(passwordConfPF.getPassword());
                if (password.equals(passwordConf) && Fechas.verificarLegalidadDeFechas(Fechas.obtenerFechaActual(),fecha_de_caducidad, "CADUCIDAD")){
                    try {
                        Clientes_Service.editar_cliente(nombre, apellidos, correo, telefono, numero_de_tarjeta, fecha_de_caducidad, cvv, password, id_cliente_a_editar);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    System.out.println("popo");
                }
            }
        });
        ImageIcon guardarIcon = new ImageIcon("src/img/guardarCambiosBoton.png");
        guardarBtn.setIcon(guardarIcon);
        editarClienteSeleccionadoPNL.add(guardarBtn);

        return editarClienteSeleccionadoPNL;
    }
    /*
    * usar un arraylist que se vaya recorriendo mientras presiones el boton de regresar,
    *  si presionas un panel, el puntero va hacia el extremo del arraylist, es decir, el ultimo panel actual*/

    public JPanel eliminarCliente(){

        JPanel eliminarPanel = new JPanel();
        eliminarPanel.setSize(1000, 800);
        eliminarPanel.setLocation(0, 0);
        eliminarPanel.setLayout(null);
        eliminarPanel.setBackground(Color.decode("#FFFFFF"));
        JLabel descripcionLbl = new JLabel("ID de cliente a eliminar");
        descripcionLbl.setFont(new Font("Arial", Font.BOLD, 24));
        descripcionLbl.setSize(300,50);
        descripcionLbl.setLocation(380,50);

        JComboBox idClientesCB = new JComboBox(Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes"));
        idClientesCB.setSize(226,40);
        idClientesCB.setLocation(400,100);

        JButton eliminarClienteBtn = new JButton();
        eliminarClienteBtn.setSize(226,31);
        eliminarClienteBtn.setLocation(400, 150);

        ImageIcon eliminarIcon = new ImageIcon("src/img/eliminarClienteBoton.png");
        eliminarClienteBtn.setIcon(eliminarIcon);
        String[] columnasTablaClientes = {"<html> <div style = 'text-align : center;'>Id <br> cliente</div></html>"
                , "<html> <div style = 'text-align : center;'>Nombre</div></html>",
                "<html> <div style = 'text-align : center;'>Apellidos</div></html>",
                "<html> <div style = 'text-align : center;'>Correo</div></html>",
                "<html> <div style = 'text-align : center;'>Teléfono</div></html>",
                "<html> <div style = 'text-align : center;'>Tarjeta de <br> credito</div></html>",
                "<html> <div style = 'text-align : center;'>Estado de <br> cuenta</div></html>"};

        JTable tabla_clientes = new JTable();
            DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTablaClientes,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
            sp.setSize(700,250);
            sp.setLocation(165,400);
            sp.setVisible(true);
        eliminarClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clientes_Service.eliminar_cliente(Integer.parseInt((String)idClientesCB.getSelectedItem()));

                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) idClientesCB.getModel();
                    model.removeElement(idClientesCB.getSelectedItem());

                DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTablaClientes,"SELECT * FROM clientes");
                    tabla_clientes.setModel(dtm);
                    TablasRamservice.crear_tabla(tabla_clientes);
            }
        });
        eliminarPanel.add(idClientesCB);
        eliminarPanel.add(sp);
        eliminarPanel.add(eliminarClienteBtn);
        eliminarPanel.add(descripcionLbl);


        return eliminarPanel;
    }

    public JPanel rentas() {
        anterior = "home";
        int xBtn = 215;
        int yBtn = 127;
        int widthBtn  = 124;
        int heightBtn = 170;
        JPanel rentasPanel = new JPanel();
        rentasPanel.setSize(1000, 800);
        rentasPanel.setLocation(0, 0);
        rentasPanel.setLayout(null);
        rentasPanel.setBackground(Color.decode("#FFFFFF"));

        JButton consultarBTN = new JButton();
        consultarBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        consultarBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        consultarBTN.setFont(new Font("Arial", Font.BOLD, 14));
        consultarBTN.setSize(widthBtn, heightBtn);
        consultarBTN.setLocation(xBtn, yBtn);
        consultarBTN.setOpaque(true);
        consultarBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon consultarIcon = new ImageIcon("src/img/consultarIcono.png");
        consultarBTN.setIcon(consultarIcon);
        rentasPanel.add(consultarBTN);
        consultarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarRenta";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        xBtn += 150;
        JButton crearBTN = new JButton(); // para poner separadito el texto y que se centre ya en sus cosas neta no peude estar asi haciendo nada puesque se ponga las pilas
        crearBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        crearBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        crearBTN.setFont(new Font("Arial", Font.BOLD, 14));
        crearBTN.setSize(widthBtn, heightBtn);
        crearBTN.setLocation(xBtn, yBtn);
        crearBTN.setOpaque(true);
        crearBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon crearIcon = new ImageIcon("src/img/crearIcono.png");
        crearBTN.setIcon(crearIcon);
        rentasPanel.add(crearBTN);
        crearBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "crearRenta";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });

        xBtn += 150;
        JButton editarBTN = new JButton(); // para poner separadito el texto y que se centre ya en sus cosas neta no peude estar asi haciendo nada puesque se ponga las pilas
        editarBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        editarBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        editarBTN.setFont(new Font("Arial", Font.BOLD, 14));
        editarBTN.setSize(widthBtn, heightBtn);
        editarBTN.setLocation(xBtn, yBtn);
        editarBTN.setOpaque(true);
        editarBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon editarIcon = new ImageIcon("src/img/editarIcono.png");
        editarBTN.setIcon(editarIcon);
        rentasPanel.add(editarBTN);
        editarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarRenta";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        xBtn += 150;

        JButton borrarBTN = new JButton(); // para poner separadito el texto y que se centre ya en sus cosas neta no peude estar asi haciendo nada puesque se ponga las pilas
        borrarBTN.setVerticalTextPosition(SwingConstants.BOTTOM);
        borrarBTN.setHorizontalTextPosition(SwingConstants.CENTER);
        borrarBTN.setFont(new Font("Arial", Font.BOLD, 14));
        borrarBTN.setSize(widthBtn, heightBtn);
        borrarBTN.setLocation(xBtn, yBtn);
        borrarBTN.setOpaque(true);
        borrarBTN.setBackground(Color.decode("#D9D9D9"));
        ImageIcon borrarIcon = new ImageIcon("src/img/eliminarIcono.png");
        borrarBTN.setIcon(borrarIcon);
        rentasPanel.add(borrarBTN);
        borrarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual; //creo q nomas cambiando el actual jala mejor o no se xd
                actual = "eliminarRenta";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        String[] columnasTabla = {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html><div style='text-align: center;'>Id<br>cliente</div></html>",
                "<html><div style='text-align: center;'>Cliente</div></html>",
                "<html><div style='text-align: center;'>Id<br>auto</div></html>",
                "<html><div style='text-align: center;'>Automóvil</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>",
                "<html><div style='text-align: center;'>Tiempo</div></html>",
                "<html><div style='text-align: center;'>Número de<br>tarjeta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>caducidad</div></html>",
                "<html><div style='text-align: center;'>CVV</div></html>",
                "<html><div style='text-align: center;'>Costo</div></html>"};

        JTable tabla_rentas = new JTable();
            tabla_rentas.setModel(Renta_Service.crear_dtm_de_rentas(columnasTabla,"SELECT * FROM rentas"));

        TablasRamservice.modificar_dimensiones_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
            sp.setSize(900,250);
            sp.setLocation(50,400);
            sp.setVisible(true);

        rentasPanel.add(sp);
        return rentasPanel;
    }

    public JPanel consultarRentas() throws SQLException {
        anterior = "rentas";
        JPanel consultarCarPNL = new JPanel();
        consultarCarPNL.setSize(1000, 800);
        consultarCarPNL.setLocation(0, 0);
        consultarCarPNL.setLayout(null);
        consultarCarPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Automovil a consultar");
        bienvenido.setFont(new Font("Arial", Font.BOLD, 24));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(385, 40);
        consultarCarPNL.add(bienvenido);


        JComboBox id_auto_a_consultar_CB = new JComboBox(Renta_Service.obtener_columna("SELECT id_de_auto FROM autos"));
        id_auto_a_consultar_CB.setSize(226,40);
        id_auto_a_consultar_CB.setLocation(400,100);
        consultarCarPNL.add(id_auto_a_consultar_CB);

        JButton consultarHistorialClienteBtn = new JButton();
        consultarHistorialClienteBtn.setSize(226,44);
        consultarHistorialClienteBtn.setLocation(400, 150);
        ImageIcon consultarHistorialIcon = new ImageIcon("src/img/consultarHistorialClienteIcon.png");
        consultarHistorialClienteBtn.setIcon(consultarHistorialIcon);
        consultarCarPNL.add(consultarHistorialClienteBtn);
        consultarHistorialClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarAutomovilSeleccionado";
                id_auto_consultar = Integer.parseInt(String.valueOf(id_auto_a_consultar_CB.getSelectedItem()));
                System.out.println(id_auto_consultar);
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        // <html><div style='text-align: center;'>Id<br>clientes</div></html>
        String[] columnasTabla = {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html><div style='text-align: center;'>Identificador<br>de cliente</div></html>",
                "<html><div style='text-align: center;'>Cliente</div></html>",
                "<html><div style='text-align: center;'>Identificador<br>de automóvil</div></html>",
                "<html><div style='text-align: center;'>Automóvil</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>",
                "<html><div style='text-align: center;'>Tiempo de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Numero de<br>tarjeta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>caducidad</div></html>",
                "<html><div style='text-align: center;'>CVV</div></html>",
                "<html><div style='text-align: center;'>Monto</div></html>"};
        JTable tabla_rentas = new JTable();

        DefaultTableModel dtm = new DefaultTableModel();
            dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
            tabla_rentas.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
        sp.setSize(900,250);
        sp.setLocation(50,300);
        sp.setVisible(true);
        consultarCarPNL.add(sp);

        return consultarCarPNL;
    }

    public JPanel crearRenta() throws SQLException {

        JPanel crearRentaPNL = new JPanel();
        crearRentaPNL.setSize(1000, 800);
        crearRentaPNL.setLocation(0, 0);
        crearRentaPNL.setLayout(null);
        crearRentaPNL.setBackground(Color.decode("#FFFFFF"));

        int x = 100;
        int y = 50;

        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.BOLD, 16));
        carroLbl.setSize(200,40);
        crearRentaPNL.add(carroLbl);
        y += 50;

        JComboBox carros_id_con_nombre_CB = new JComboBox();
        carros_id_con_nombre_CB.setLocation(x,y);
        Map<Integer,String> hashMapCarrosId = Autos_Service.obtener_id_nombre_auto();
        carros_id_con_nombre_CB.setModel(generar_combobox_contenido(hashMapCarrosId));
        carros_id_con_nombre_CB.setSize(200,30);
        crearRentaPNL.add(carros_id_con_nombre_CB);

        y += 50;
        JLabel fechaInicioLbl = new JLabel("Fecha de inicio");
        fechaInicioLbl.setLocation(x,y);
        fechaInicioLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaInicioLbl.setSize(200,40);
        crearRentaPNL.add(fechaInicioLbl);
        y += 50;
        JTextField fechaInicioTF = new JTextField("2023-01-01");
        fechaInicioTF.setBorder(roundedBorder);
        fechaInicioTF.setLocation(x,y);
        fechaInicioTF.setSize(200,30);
        crearRentaPNL.add(fechaInicioTF);

        y += 50;
        JLabel fechaDeDevolucionLbl = new JLabel("Fecha de devolución");
        fechaDeDevolucionLbl.setLocation(x,y);
        fechaDeDevolucionLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaDeDevolucionLbl.setSize(200,40);
        crearRentaPNL.add(fechaDeDevolucionLbl);
        y += 50;
        JTextField fechaDeDevolucionTF = new JTextField("2023-01-01");
        fechaDeDevolucionTF.setBorder(roundedBorder);
        fechaDeDevolucionTF.setLocation(x,y);
        fechaDeDevolucionTF.setSize(200,30);
        crearRentaPNL.add(fechaDeDevolucionTF);

        y += 50;
        JLabel idClienteLbl = new JLabel("Id Cliente");
        idClienteLbl.setLocation(x,y);
        idClienteLbl.setFont(new Font("Arial", Font.BOLD, 16));
        idClienteLbl.setSize(200,40);
        crearRentaPNL.add(idClienteLbl);
        y += 50;

        JComboBox id_cliente_con_nombre_CB = new JComboBox();
        Map<Integer,String> hashMapClientesId = Clientes_Service.seleccionar_clientes_map();
        id_cliente_con_nombre_CB.setModel(generar_combobox_contenido(hashMapClientesId));
        id_cliente_con_nombre_CB.setLocation(x,y);
        id_cliente_con_nombre_CB.setSize(200,30);
        crearRentaPNL.add(id_cliente_con_nombre_CB);

        x +=300;
        y = 50;

        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        crearRentaPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField("a");
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        crearRentaPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha de caducidad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaCadLbl.setSize(200,40);
        crearRentaPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField("2023-06-01");
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        crearRentaPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        crearRentaPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField("a");
        cvvTF.setBorder(roundedBorder);
        cvvTF.setLocation(x,y);
        cvvTF.setSize(100,30);
        crearRentaPNL.add(cvvTF);

        x += 200;
        y = 50;
        JLabel costoEstimadoLbl = new JLabel("Costo estimado", JLabel.CENTER);
        costoEstimadoLbl.setLocation(x,y);
        costoEstimadoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        costoEstimadoLbl.setSize(200,40);
        crearRentaPNL.add(costoEstimadoLbl);

        y += 50;

        JLabel costoEstimadoVisualLbl = new JLabel();
        costoEstimadoVisualLbl.setHorizontalAlignment(JLabel.CENTER);
        costoEstimadoVisualLbl.setOpaque(true);
        costoEstimadoVisualLbl.setBackground(Color.pink);
        costoEstimadoVisualLbl.setLocation(x+15,y);
        costoEstimadoVisualLbl.setSize(193,102);
        //ImageIcon facturaIcon = new ImageIcon("src/img/rentaFacturaIcon.png");
        //costoEstimadoVisualLbl.setIcon(facturaIcon);
        crearRentaPNL.add(costoEstimadoVisualLbl);

        y += costoEstimadoVisualLbl.getHeight()+10;

        JButton calcularCostoBtn = new JButton();
        calcularCostoBtn.setSize(226,31);
        calcularCostoBtn.setLocation(x,y);
        ImageIcon calcularCostoIcon = new ImageIcon("src/img/calcularCostoIcon.png");
        calcularCostoBtn.setIcon(calcularCostoIcon);
        crearRentaPNL.add(calcularCostoBtn);

        y += 50;

        JButton crearRentaBtn = new JButton();
        crearRentaBtn.setSize(226,31);
        crearRentaBtn.setLocation(x,y);
        ImageIcon crearRentaIcon = new ImageIcon("src/img/crearRentaBoton.png");
        crearRentaBtn.setIcon(crearRentaIcon);
        crearRentaPNL.add(crearRentaBtn);

        // poner formato de fechas

        fechaInicioTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroInicio = (AbstractDocument) fechaInicioTF.getDocument();
            documentoFiltroInicio.setDocumentFilter(new Fechas.FechaDocumentFilter());

        fechaDeDevolucionTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroFinal = (AbstractDocument) fechaDeDevolucionTF.getDocument();
            documentoFiltroFinal.setDocumentFilter(new Fechas.FechaDocumentFilter());

        calcularCostoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String auto_elegido = (String) carros_id_con_nombre_CB.getSelectedItem();
                String auto_elegido_id_nombre[] = auto_elegido.split(":");
                int id_auto = Integer.parseInt(auto_elegido_id_nombre[0]);
                double costo_auto = Double.parseDouble(Autos_Service.obtener_celda("SELECT costo FROM autos WHERE id_de_auto = " + id_auto));
                int dias = Fechas.getDias_De_Renta(fechaInicioTF.getText(), fechaDeDevolucionTF.getText());
                String costo_total = String.valueOf(costo_auto*dias);
                if (costo_auto*dias > 0 && Fechas.verificarLegalidadDeFechas(fechaInicioTF.getText(), fechaDeDevolucionTF.getText(), "RENTAR")){
                    costoEstimadoVisualLbl.setText(costo_total+ "Pesos MXN");
                }
            }
        });
        crearRentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cliente_elegido = (String) id_cliente_con_nombre_CB.getSelectedItem();

            String cliente_elegido_id_nombre [] = cliente_elegido.split(":");
                int identificador_cliente = Integer.parseInt(cliente_elegido_id_nombre[0]);
                String nombre_cliente = cliente_elegido_id_nombre[1];
                String cliente = nombre_cliente;
            String auto_elegido = (String) carros_id_con_nombre_CB.getSelectedItem();
                String auto_elegido_id_nombre[] = auto_elegido.split(":");
                int id_auto = Integer.parseInt(auto_elegido_id_nombre[0]);
                String nombre_auto = auto_elegido_id_nombre[1];
            String fecha_de_renta = fechaInicioTF.getText();
            String fecha_de_devolucion = fechaDeDevolucionTF.getText();
            String numero_tarjeta = numTarjetaTF.getText();
            String fecha_caducidad = fechaCadTF.getText();
            String cvv = cvvTF.getText();
                try {
                    String estado_de_registro = Renta_Service.comprobar_fechas(id_auto, fecha_de_renta, fecha_de_devolucion, fecha_caducidad);
                    switch (estado_de_registro){
                        case "Permitido":
                            Renta_Service.crearRenta(
                                identificador_cliente, cliente,
                                id_auto, nombre_auto, fecha_de_renta,
                                fecha_de_devolucion, numero_tarjeta, fecha_caducidad, cvv
                            );
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, estado_de_registro, "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        y += 50;

        JButton cancelarBtn = new JButton();
        cancelarBtn.setSize(226,31);
        cancelarBtn.setLocation(x,y);
        ImageIcon cancelarIcon = new ImageIcon("src/img/cancelarBoton.png");
        cancelarBtn.setIcon(cancelarIcon);
        crearRentaPNL.add(cancelarBtn);
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        crearRentaPNL.add(cancelarBtn);

        return crearRentaPNL;
    }

    public JPanel editarRenta() {

        anterior = "rentas";
        JPanel editarRentaPNL = new JPanel();
        editarRentaPNL.setSize(1000, 800);
        editarRentaPNL.setLocation(0, 0);
        editarRentaPNL.setLayout(null);
        editarRentaPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("ID renta a editar");
        bienvenido.setFont(new Font("Arial", Font.BOLD, 24));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(415, 30);
        editarRentaPNL.add(bienvenido);

        JComboBox id_rentas_CB = new JComboBox(Renta_Service.obtener_columna("SELECT id_de_renta FROM rentas"));
        id_rentas_CB.setSize(226,40);
        id_rentas_CB.setLocation(400,100);

        JButton editarRentaBtn = new JButton();
        editarRentaBtn.setSize(226,31);
        editarRentaBtn.setLocation(400, 150);
        ImageIcon editarIcon = new ImageIcon("src/img/editarRentaBoton.png");
        editarRentaBtn.setIcon(editarIcon);
        editarRentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarRentaSeleccionada";
                id_renta_editar = Integer.parseInt((String) id_rentas_CB.getSelectedItem());
                id_cliente_editando_renta = Integer.parseInt(Renta_Service.obtener_celda("SELECT * FROM rentas WHERE id_de_renta = "+ id_renta_editar));
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });


        String[] columnasTabla = {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html><div style='text-align: center;'>Identificador<br>de cliente</div></html>",
                "<html><div style='text-align: center;'>Cliente</div></html>",
                "<html><div style='text-align: center;'>Identificador<br>de automóvil</div></html>",
                "<html><div style='text-align: center;'>Automóvil</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>",
                "<html><div style='text-align: center;'>Tiempo de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Numero de<br>tarjeta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>caducidad</div></html>",
                "<html><div style='text-align: center;'>CVV</div></html>",
                "<html><div style='text-align: center;'>Monto</div></html>"};
        JTable tabla_rentas = new JTable();
        DefaultTableModel dtm = new DefaultTableModel();
            dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
            tabla_rentas.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
            sp.setSize(900,250);
            sp.setLocation(50,400);
            sp.setVisible(true);

        editarRentaPNL.add(id_rentas_CB);
        editarRentaPNL.add(sp);
        editarRentaPNL.add(editarRentaBtn);

        return editarRentaPNL;
    }

    public JPanel editarRentaSeleccionada(int id_de_renta_a_editar) throws SQLException {

        JPanel editarRentaSeleccionadaPNL = new JPanel();
        editarRentaSeleccionadaPNL.setSize(1000, 800);
        editarRentaSeleccionadaPNL.setLocation(0, 0);
        editarRentaSeleccionadaPNL.setLayout(null);
        editarRentaSeleccionadaPNL.setBackground(Color.decode("#FFFFFF"));

        int x = 100;
        int y = 20;
        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.BOLD, 16));
        carroLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(carroLbl);
        y += 50;
        JComboBox carros_id_con_nombre_CB = new JComboBox();
        carros_id_con_nombre_CB.setLocation(x,y);
        Map<Integer,String> hashMapCarrosId = Autos_Service.obtener_id_nombre_auto();
        carros_id_con_nombre_CB.setModel(generar_combobox_contenido(hashMapCarrosId));
        carros_id_con_nombre_CB.setSize(200,30);
        editarRentaSeleccionadaPNL.add(carros_id_con_nombre_CB);


        y += 50;
        JLabel fechaInicioLbl = new JLabel("Fecha de inicio");
        fechaInicioLbl.setLocation(x,y);
        fechaInicioLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaInicioLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(fechaInicioLbl);
        y += 50;
        JTextField fechaInicioTF = new JTextField();
        fechaInicioTF.setBorder(roundedBorder);
        fechaInicioTF.setLocation(x,y);
        fechaInicioTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(fechaInicioTF);

        y += 50;
        JLabel fechaDeDevolucionLbl = new JLabel("Fecha de devolución");
        fechaDeDevolucionLbl.setLocation(x,y);
        fechaDeDevolucionLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaDeDevolucionLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(fechaDeDevolucionLbl);
        y += 50;
        JTextField fechaDeDevolucionTF = new JTextField();
        fechaDeDevolucionTF.setBorder(roundedBorder);
        fechaDeDevolucionTF.setLocation(x,y);
        fechaDeDevolucionTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(fechaDeDevolucionTF);

        y += 50;
        JLabel idClienteLbl = new JLabel("Id Cliente");
        idClienteLbl.setLocation(x,y);
        idClienteLbl.setFont(new Font("Arial", Font.BOLD, 16));
        idClienteLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(idClienteLbl);
        y += 50;

        JComboBox id_cliente_con_nombre_CB = new JComboBox();
        Map<Integer,String> hashMapClientesId = Clientes_Service.seleccionar_clientes_map();
        id_cliente_con_nombre_CB.setModel(generar_combobox_contenido(hashMapClientesId));
        id_cliente_con_nombre_CB.setLocation(x,y);
        id_cliente_con_nombre_CB.setSize(200,30);
        editarRentaSeleccionadaPNL.add(id_cliente_con_nombre_CB);

        x +=300;
        y = 20;

        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(numTarjetaLbl);
        y += 50;

        String cliente_elegido = (String) id_cliente_con_nombre_CB.getSelectedItem();
        String cliente_elegido_id_nombre [] = cliente_elegido.split(":");
        int identificador_cliente = Integer.parseInt(cliente_elegido_id_nombre[0]);

        JTextField numTarjetaTF = new JTextField((Clientes_Service.obtener_celda("SELECT numero_de_tarjeta from clientes WHERE id_de_cliente = " + identificador_cliente)));
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha de caducidad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaCadLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField((Clientes_Service.obtener_celda("SELECT fecha_de_caducidad from clientes WHERE id_de_cliente = " + identificador_cliente)));
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField();
        cvvTF.setBorder(roundedBorder);
        cvvTF.setLocation(x,y);
        cvvTF.setSize(100,30);
        editarRentaSeleccionadaPNL.add(cvvTF);

        x += 200;
        y = 100;
        JLabel costoEstimadoLbl = new JLabel("Costo estimado", JLabel.CENTER);
        costoEstimadoLbl.setLocation(x,y);
        costoEstimadoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        costoEstimadoLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(costoEstimadoLbl);

        y += 50;

        JLabel costoEstimadoVisualLbl = new JLabel();
        costoEstimadoVisualLbl.setHorizontalAlignment(JLabel.CENTER);
        costoEstimadoVisualLbl.setBackground(Color.pink);
        costoEstimadoVisualLbl.setLocation(x+15,y);
        costoEstimadoVisualLbl.setSize(193,102);
        ImageIcon facturaIcon = new ImageIcon("src/img/rentaFacturaIcon.png");
        costoEstimadoVisualLbl.setIcon(facturaIcon);
        editarRentaSeleccionadaPNL.add(costoEstimadoVisualLbl);

        y += costoEstimadoVisualLbl.getHeight()+10;
        JButton calcularCostoBtn = new JButton();
        calcularCostoBtn.setSize(226,31);
        calcularCostoBtn.setLocation(x,y);
        ImageIcon calcularCostoIcon = new ImageIcon("src/img/calcularCostoIcon.png");
        calcularCostoBtn.setIcon(calcularCostoIcon);
        editarRentaSeleccionadaPNL.add(calcularCostoBtn);

        y += 50;

        JButton guardarBtn = new JButton();
        guardarBtn.setSize(226,31);
        guardarBtn.setLocation(x,y);
        ImageIcon crearRentaIcon = new ImageIcon("src/img/guardarCambiosBoton.png");
        guardarBtn.setIcon(crearRentaIcon);
        editarRentaSeleccionadaPNL.add(guardarBtn);
        id_cliente_con_nombre_CB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cliente_elegido = (String) id_cliente_con_nombre_CB.getSelectedItem();
                String cliente_elegido_id_nombre [] = cliente_elegido.split(":");
                int identificador_cliente = Integer.parseInt(cliente_elegido_id_nombre[0]);
                numTarjetaTF.setText((Clientes_Service.obtener_celda("SELECT numero_de_tarjeta from clientes WHERE id_de_cliente = " + identificador_cliente)));
            }
        });
        fechaInicioTF.setDocument(new Fechas.NumericDocument());
        AbstractDocument documentoFiltroInicio = (AbstractDocument) fechaInicioTF.getDocument();
        documentoFiltroInicio.setDocumentFilter(new Fechas.FechaDocumentFilter());

        fechaDeDevolucionTF.setDocument(new Fechas.NumericDocument());
        AbstractDocument documentoFiltroFinal = (AbstractDocument) fechaDeDevolucionTF.getDocument();
        documentoFiltroFinal.setDocumentFilter(new Fechas.FechaDocumentFilter());


        calcularCostoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String auto_elegido = (String) carros_id_con_nombre_CB.getSelectedItem();
                String auto_elegido_id_nombre[] = auto_elegido.split(":");
                int id_auto = Integer.parseInt(auto_elegido_id_nombre[0]);
                double costo_auto = Double.parseDouble(Autos_Service.obtener_celda("SELECT costo FROM autos WHERE id_de_auto = " + id_auto));
                int dias = Fechas.getDias_De_Renta(fechaInicioTF.getText(), fechaDeDevolucionTF.getText());
                String costo_total = String.valueOf(costo_auto*dias);
                if (costo_auto*dias > 0 && Fechas.verificarLegalidadDeFechas(fechaInicioTF.getText(), fechaDeDevolucionTF.getText(), "RENTAR")){
                    costoEstimadoVisualLbl.setText(costo_total+ "Pesos MXN");
                }
            }
        });
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_cliente = cliente_elegido_id_nombre[1];
                String cliente = nombre_cliente;
                String auto_elegido = (String) carros_id_con_nombre_CB.getSelectedItem();
                String auto_elegido_id_nombre[] = auto_elegido.split(":");
                int id_auto = Integer.parseInt(auto_elegido_id_nombre[0]);
                String nombre_auto = auto_elegido_id_nombre[1];
                String fecha_de_renta = fechaInicioTF.getText();
                String fecha_de_devolucion = fechaDeDevolucionTF.getText();
                String numero_tarjeta = numTarjetaTF.getText();
                String fecha_caducidad = fechaCadTF.getText();
                String cvv = cvvTF.getText();
                try {
                    String estado_de_registro = Renta_Service.comprobar_fechas_editar(id_de_renta_a_editar,id_auto, fecha_de_renta, fecha_de_devolucion,fecha_caducidad);
                    switch (estado_de_registro){
                        case "Permitido":
                            Renta_Service.editarRenta(id_de_renta_a_editar,
                                    identificador_cliente, cliente,
                                    id_auto, nombre_auto, fecha_de_renta,
                                    fecha_de_devolucion, numero_tarjeta, fecha_caducidad, cvv
                            );
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, estado_de_registro, "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        y += 50;

        JButton cancelarBtn = new JButton();
        cancelarBtn.setSize(226,31);
        cancelarBtn.setLocation(x,y);
        ImageIcon cancelarIcon = new ImageIcon("src/img/cancelarBoton.png");
        cancelarBtn.setIcon(cancelarIcon);
        editarRentaSeleccionadaPNL.add(cancelarBtn);
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });
        editarRentaSeleccionadaPNL.add(cancelarBtn);
        return editarRentaSeleccionadaPNL;

    }

    public JPanel eliminarRenta(){
        JPanel eliminarRentaPNL = new JPanel();
        eliminarRentaPNL.setSize(1000, 800);
        eliminarRentaPNL.setLocation(0, 0);
        eliminarRentaPNL.setLayout(null);
        eliminarRentaPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel descripcionLbl = new JLabel("ID de renta a eliminar");
        descripcionLbl.setFont(new Font("Arial", Font.BOLD, 24));
        descripcionLbl.setSize(300,50);
        descripcionLbl.setLocation(400,50);

        JComboBox idRentasCB = new JComboBox(Renta_Service.obtener_columna("SELECT id_de_renta FROM rentas"));
        idRentasCB.setSize(226,40);
        idRentasCB.setLocation(400,100);

        JButton eliminarRentaBtn = new JButton();
        eliminarRentaBtn.setSize(226,31);
        eliminarRentaBtn.setLocation(400, 150);
        ImageIcon eliminarIcon = new ImageIcon("src/img/eliminarClienteBoton.png");
        eliminarRentaBtn.setIcon(eliminarIcon);

        String[] columnasTabla = {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html><div style='text-align: center;'>Identificador<br>de cliente</div></html>",
                "<html><div style='text-align: center;'>Cliente</div></html>",
                "<html><div style='text-align: center;'>Identificador<br>de automóvil</div></html>",
                "<html><div style='text-align: center;'>Automóvil</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>",
                "<html><div style='text-align: center;'>Tiempo de<br>renta</div></html>",
                "<html><div style='text-align: center;'>Numero de<br>tarjeta</div></html>",
                "<html><div style='text-align: center;'>Fecha de<br>caducidad</div></html>",
                "<html><div style='text-align: center;'>CVV</div></html>",
                "<html><div style='text-align: center;'>Monto</div></html>"
        };
        JTable tabla_rentas = new JTable();

        DefaultTableModel dtm = new DefaultTableModel();
            dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
            tabla_rentas.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
            sp.setSize(900,250);
            sp.setLocation(50,400);
            sp.setVisible(true);

        eliminarRentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Renta_Service.borrar_renta(Integer.parseInt(String.valueOf(idRentasCB.getSelectedItem())));

                DefaultTableModel dtm = new DefaultTableModel();
                    dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
                    tabla_rentas.setModel(dtm);

                TablasRamservice.crear_tabla(tabla_rentas);

                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) idRentasCB.getModel();
                model.removeElement(idRentasCB.getSelectedItem());
                repaint();
                revalidate();
            }
        });

        eliminarRentaPNL.add(descripcionLbl);
        eliminarRentaPNL.add(idRentasCB);
        eliminarRentaPNL.add(sp);
        eliminarRentaPNL.add(eliminarRentaBtn);

        return eliminarRentaPNL;
    }
    public JPanel categorias() {
        anterior = "home";

        JPanel categoriasPanel = new JPanel();
        categoriasPanel.setForeground(Color.BLACK);
        categoriasPanel.setSize(1000, 800);
        categoriasPanel.setLocation(0, 80);
        categoriasPanel.setLayout(null);
        categoriasPanel.setBackground(Color.decode("#FFFFFF"));

        JTextArea txtrLosSuvsDeportivos = new JTextArea();
        txtrLosSuvsDeportivos.setText("Los SUVs deportivos son \r\nvehículos versátiles que \r\ncombinan el rendimiento\r\n deportivo con la practicidad\r\n y el espacio de carga adicional");
        txtrLosSuvsDeportivos.setOpaque(false);
        txtrLosSuvsDeportivos.setForeground(Color.BLACK);
        txtrLosSuvsDeportivos.setFont(new Font("Tahoma", Font.BOLD, 18));
        txtrLosSuvsDeportivos.setEditable(false);
        txtrLosSuvsDeportivos.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txtrLosSuvsDeportivos.setBounds(345, 449, 291, 143);
        categoriasPanel.add(txtrLosSuvsDeportivos);

        JTextArea txtrEstaCategoraIncluye = new JTextArea();
        txtrEstaCategoraIncluye.setForeground(Color.BLACK);
        txtrEstaCategoraIncluye.setOpaque(false);
        txtrEstaCategoraIncluye.setEditable(false);
        txtrEstaCategoraIncluye.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txtrEstaCategoraIncluye.setFont(new Font("Tahoma", Font.BOLD, 18));
        txtrEstaCategoraIncluye.setText("Esta categoría incluye \r\nvehículos elegantes y \r\ncómodos, ideales para\r\nclientes que buscan un\r\nviaje sofisticado y de alto nivel");
        txtrEstaCategoraIncluye.setBounds(345, 131, 291, 115);
        categoriasPanel.add(txtrEstaCategoraIncluye);

        JLabel lblNewLabel_4_2 = new JLabel("SUVs deportivos");
        lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_2.setForeground(Color.BLACK);
        lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4_2.setBounds(133, 562, 166, 30);
        categoriasPanel.add(lblNewLabel_4_2);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setIcon(new ImageIcon("src/img/image 11.png"));
        lblNewLabel_2_1.setBounds(109, 424, 226, 140);
        categoriasPanel.add(lblNewLabel_2_1);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Color.decode("#BF0000"));
        btnEliminar.setBounds(658, 206, 226, 53);
        categoriasPanel.add(btnEliminar);

        JButton btnNewButton = new JButton("Consultar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual ="consultarCategorias";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                revalidate();
                repaint();

            }
        });
        btnNewButton.setFocusPainted(false);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setBounds(658, 116, 226, 55);
        categoriasPanel.add(btnNewButton);

        JLabel lblNewLabel_4 = new JLabel("Sedanes de lujo");
        lblNewLabel_4.setForeground(Color.BLACK);
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_4.setBounds(133, 248, 166, 30);
        categoriasPanel.add(lblNewLabel_4);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("src/img/image 12.png"));
        lblNewLabel_2.setBounds(109, 110, 226, 140);
        categoriasPanel.add(lblNewLabel_2);


        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setOpaque(true);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(Color.LIGHT_GRAY);
        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel.setBounds(84, 75, 826, 232);
        categoriasPanel.add(lblNewLabel);

        JButton btnEliminar_1 = new JButton("Eliminar");
        btnEliminar_1.setForeground(Color.WHITE);
        btnEliminar_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar_1.setFocusPainted(false);
        btnEliminar_1.setBorderPainted(false);
        btnEliminar_1.setBackground(new Color(191, 0, 0));
        btnEliminar_1.setBounds(658, 520, 226, 53);
        categoriasPanel.add(btnEliminar_1);

        JButton btnNewButton_1 = new JButton("Consultar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.setBounds(658, 430, 226, 55);
        categoriasPanel.add(btnNewButton_1);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_1.setBounds(84, 389, 826, 232);
        categoriasPanel.add(lblNewLabel_1);

        return categoriasPanel;
    }

    public JPanel consultarCategorias() {
        anterior = "categorias";

        JPanel consultarCar = new JPanel();
        consultarCar.setForeground(Color.BLACK);
        consultarCar.setSize(1000, 681);
        consultarCar.setLocation(0, 80);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JButton lblNewLabel_3 = new JButton("");
        lblNewLabel_3.setBorderPainted(false);
        lblNewLabel_3.setContentAreaFilled(false);
        lblNewLabel_3.setFocusPainted(false);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setIcon(new ImageIcon("src/img/Group 56 (1).png"));
        lblNewLabel_3.setBounds(652, 424, 271, 92);
        consultarCar.add(lblNewLabel_3);

        JTextField textField_1 = new JTextField();
        textField_1.setBorder(null);
        textField_1.setColumns(10);
        textField_1.setBackground(SystemColor.menu);
        textField_1.setBounds(670, 173, 236, 219);
        consultarCar.add(textField_1);

        JTextField textField = new JTextField();
        textField.setBorder(null);
        textField.setBackground(SystemColor.menu);
        textField.setBounds(670, 84, 236, 29);
        consultarCar.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2_2 = new JLabel("Descripción");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_2.setBounds(670, 144, 154, 23);
        consultarCar.add(lblNewLabel_2_2);

        JLabel lblNewLabel_2 = new JLabel("Nombre de categoría");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(670, 59, 154, 23);
        consultarCar.add(lblNewLabel_2);

        JTextArea txtrLosSuvsDeportivos = new JTextArea();
        txtrLosSuvsDeportivos.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed\r\ndo eiusmod tempor incididunt ut labore et dolore magna \r\naliqua. Ut enim ad minim veniam, quis nostrud exercitation\r\n ullamco laboris nisi ut aliquip ex ea commodo consequat. \r\nDuis aute irure dolor in reprehenderit in voluptate velit esse \r\ncillum dolore eu fugiat nulla pariatur.");
        txtrLosSuvsDeportivos.setOpaque(false);
        txtrLosSuvsDeportivos.setForeground(Color.BLACK);
        txtrLosSuvsDeportivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtrLosSuvsDeportivos.setBounds(47, 469, 497, 152);
        consultarCar.add(txtrLosSuvsDeportivos);

        JLabel lblNewLabel_2_1 = new JLabel("");

        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setIcon(new ImageIcon("src/img/image 15.png"));
        lblNewLabel_2_1.setBounds(68, 134, 449, 269);
        consultarCar.add(lblNewLabel_2_1);

        getContentPane().add(consultarCar);

        JButton btnEliminar_1 = new JButton("Editar");
        btnEliminar_1.setForeground(Color.WHITE);
        btnEliminar_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar_1.setFocusPainted(false);
        btnEliminar_1.setBorderPainted(false);
        btnEliminar_1.setBackground(Color.decode("#38B6FF"));
        btnEliminar_1.setBounds(657, 547, 260, 53);
        consultarCar.add(btnEliminar_1);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(SystemColor.controlShadow);
        lblNewLabel_1.setBounds(632, 24, 307, 597);
        consultarCar.add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("Sedanes de lujo");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(170, 24, 282, 58);
        consultarCar.add(lblNewLabel);

        return consultarCar;
    }

    public JPanel marcas() {
        anterior = "home";

        JPanel marcasPanel = new JPanel();
        marcasPanel.setForeground(Color.BLACK);
        marcasPanel.setSize(1000, 800);
        marcasPanel.setLocation(0, 80);
        marcasPanel.setLayout(null);
        marcasPanel.setBackground(Color.decode("#FFFFFF"));

        JTextArea txtrLosSuvsDeportivos = new JTextArea();
        txtrLosSuvsDeportivos.setText("Mercedes-Benz es una \r\nmarca de automóviles \r\nconocida por su lujo, calidad \r\ny elegancia. Sus vehículos \r\n,ofrecen un alto nivel de confort\r\ntecnología avanzada y un diseño\r\n sofisticado");
        txtrLosSuvsDeportivos.setOpaque(false);
        txtrLosSuvsDeportivos.setForeground(Color.BLACK);
        txtrLosSuvsDeportivos.setFont(new Font("Tahoma", Font.BOLD, 18));
        txtrLosSuvsDeportivos.setEditable(false);
        txtrLosSuvsDeportivos.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txtrLosSuvsDeportivos.setBounds(341, 424, 307, 162);
        marcasPanel.add(txtrLosSuvsDeportivos);

        JTextArea txtrEstaCategoraIncluye = new JTextArea();
        txtrEstaCategoraIncluye.setForeground(Color.BLACK);
        txtrEstaCategoraIncluye.setOpaque(false);
        txtrEstaCategoraIncluye.setEditable(false);
        txtrEstaCategoraIncluye.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txtrEstaCategoraIncluye.setFont(new Font("Tahoma", Font.BOLD, 18));
        txtrEstaCategoraIncluye.setText("BMW es una marca de \r\nautomóviles de prestigio\r\nque se destaca por su \r\nrendimiento y estilo. Sus\r\n vehículos combinan la \r\ndeportividad con el lujo y \r\nla elegancia");
        txtrEstaCategoraIncluye.setBounds(345, 110, 291, 162);
        marcasPanel.add(txtrEstaCategoraIncluye);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setIcon(new ImageIcon("src/img/image 11 (1).png"));
        lblNewLabel_2_1.setBounds(109, 424, 226, 140);
        marcasPanel.add(lblNewLabel_2_1);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Color.decode("#BF0000"));
        btnEliminar.setBounds(658, 206, 226, 53);
        marcasPanel.add(btnEliminar);

        JButton btnNewButton = new JButton("Consultar");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarMarcas";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                revalidate();
                repaint();
            }
        });
        btnNewButton.setFocusPainted(false);
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setBackground(Color.BLACK);
        btnNewButton.setBounds(658, 116, 226, 55);
        marcasPanel.add(btnNewButton);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setIcon(new ImageIcon("src/img/13.png"));
        lblNewLabel_2.setBounds(109, 110, 226, 140);
        marcasPanel.add(lblNewLabel_2);


        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setOpaque(true);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBackground(Color.LIGHT_GRAY);
        lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel.setBounds(84, 75, 826, 232);
        marcasPanel.add(lblNewLabel);

        JButton btnEliminar_1 = new JButton("Eliminar");
        btnEliminar_1.setForeground(Color.WHITE);
        btnEliminar_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar_1.setFocusPainted(false);
        btnEliminar_1.setBorderPainted(false);
        btnEliminar_1.setBackground(new Color(191, 0, 0));
        btnEliminar_1.setBounds(658, 520, 226, 53);
        marcasPanel.add(btnEliminar_1);

        JButton btnNewButton_1 = new JButton("Consultar");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnNewButton_1.setForeground(Color.WHITE);
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.setFocusPainted(false);
        btnNewButton_1.setBorderPainted(false);
        btnNewButton_1.setBackground(Color.BLACK);
        btnNewButton_1.setBounds(658, 430, 226, 55);
        marcasPanel.add(btnNewButton_1);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_1.setBounds(84, 389, 826, 232);
        marcasPanel.add(lblNewLabel_1);

        return marcasPanel;
    }

    public JPanel consultarMarcas() {
        anterior = "marcas";

        JPanel consultarMarcas = new JPanel();
        consultarMarcas.setRequestFocusEnabled(false);
        consultarMarcas.setForeground(Color.BLACK);
        consultarMarcas.setSize(1000, 681);
        consultarMarcas.setLocation(0, 80);
        consultarMarcas.setLayout(null);
        consultarMarcas.setBackground(Color.decode("#FFFFFF"));

        JButton lblNewLabel_3 = new JButton("");
        lblNewLabel_3.setBorderPainted(false);
        lblNewLabel_3.setRequestFocusEnabled(false);
        lblNewLabel_3.setFocusPainted(false);
        lblNewLabel_3.setContentAreaFilled(false);
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setIcon(new ImageIcon("src/img/Group 56 (1).png"));
        lblNewLabel_3.setBounds(652, 424, 271, 92);
        consultarMarcas.add(lblNewLabel_3);

        JTextField textField_1 = new JTextField();
        textField_1.setHorizontalAlignment(SwingConstants.LEFT);
        textField_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        textField_1.setBorder(null);
        textField_1.setColumns(10);
        textField_1.setBackground(SystemColor.menu);
        textField_1.setBounds(670, 173, 236, 219);
        consultarMarcas.add(textField_1);

        JTextField textField = new JTextField();
        textField.setBorder(null);
        textField.setBackground(SystemColor.menu);
        textField.setBounds(670, 84, 236, 29);
        consultarMarcas.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2_2 = new JLabel("Descripción");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2_2.setBounds(670, 144, 154, 23);
        consultarMarcas.add(lblNewLabel_2_2);

        JLabel lblNewLabel_2 = new JLabel("Nombre de marca");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(670, 59, 154, 23);
        consultarMarcas.add(lblNewLabel_2);

        JTextArea txtrLosSuvsDeportivos = new JTextArea();
        txtrLosSuvsDeportivos.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed\r\ndo eiusmod tempor incididunt ut labore et dolore magna \r\naliqua. Ut enim ad minim veniam, quis nostrud exercitation\r\n ullamco laboris nisi ut aliquip ex ea commodo consequat. \r\nDuis aute irure dolor in reprehenderit in voluptate velit esse \r\ncillum dolore eu fugiat nulla pariatur.");
        txtrLosSuvsDeportivos.setOpaque(false);
        txtrLosSuvsDeportivos.setForeground(Color.BLACK);
        txtrLosSuvsDeportivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        txtrLosSuvsDeportivos.setBounds(59, 469, 497, 152);
        consultarMarcas.add(txtrLosSuvsDeportivos);

        JLabel lblNewLabel_2_1 = new JLabel("");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setIcon(new ImageIcon("src/img/logomercedes.png"));
        lblNewLabel_2_1.setBounds(79, 107, 456, 298);
        consultarMarcas.add(lblNewLabel_2_1);


        JButton btnEliminar_1 = new JButton("Editar");
        btnEliminar_1.setForeground(Color.WHITE);
        btnEliminar_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar_1.setFocusPainted(false);
        btnEliminar_1.setBorderPainted(false);
        btnEliminar_1.setBackground(Color.decode("#38B6FF"));
        btnEliminar_1.setBounds(657, 547, 260, 53);
        consultarMarcas.add(btnEliminar_1);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
        lblNewLabel_1.setOpaque(true);
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBackground(SystemColor.controlShadow);
        lblNewLabel_1.setBounds(632, 24, 307, 597);
        consultarMarcas.add(lblNewLabel_1);

        JLabel lblNewLabel = new JLabel("Mercedes-Benz");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(166, 24, 282, 58);
        consultarMarcas.add(lblNewLabel);

        return consultarMarcas;
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
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            table.setRowHeight(row,25);
            if (row % 2 == 0) {
                component.setBackground(Color.decode("#C3E6F5"));
            } else {
                // Restablecer el color de fondo para las demás filas
                component.setBackground(Color.decode("#E2E7F4"));
            }

            return component;
        }
    };
    public static DefaultComboBoxModel<String> generar_combobox_contenido(Map<Integer,String> hashMap) throws SQLException {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        // Iterar sobre las entradas del HashMap y agregarlas al modelo del ComboBox
        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            Integer clave = entry.getKey();
            String valor = entry.getValue();
            String item = clave + ":" + valor;
            comboBoxModel.addElement(item);
        }
        return comboBoxModel;
    }
    public static void main(String[] args) throws SQLException {
        Ventana screen = new Ventana();
    }
}

