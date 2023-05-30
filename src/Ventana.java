import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Ventana extends JFrame {
    public JPanel panel = null;
    private String anterior = "cargaPantalla";
    private String actual = "home";
    private JLabel panelActualLbl = new JLabel(actual, JLabel.CENTER);
    private Border roundedBorder = new RoundBorder(16, 1, Color.GRAY);
    ArrayList<String> historialPaneles = new ArrayList<>();
    int pasenUbi = historialPaneles.size()-1;

    JPanel menuSuperiorPanel = new JPanel();

    ImageIcon logoEmpresa = new ImageIcon("src/img/company.png");

    public Ventana() {

        this.setVisible(true);
        this.setSize(1000, 800);
        this.setTitle("Ramservice");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.decode("#FFFFFF"));
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        setIconImage(logoEmpresa.getImage());
        System.out.println("actual: " + actual + " anterior: " + anterior);

        ImageIcon logoRamsesIcon = new ImageIcon("src/img/logoRamservice.png");
        JLabel logoRamsesLbl = new JLabel();
        logoRamsesLbl.setIcon(logoRamsesIcon);
        logoRamsesLbl.setSize(logoRamsesIcon.getIconWidth(),logoRamsesIcon.getIconHeight());
        logoRamsesLbl.setLocation(0,-50);
        panelActualLbl.setSize(800, 80);
        panelActualLbl.setLocation(180, 0);
        panelActualLbl.setFont(new Font("Lexend-Regular.ttf", Font.PLAIN, 35));
        menuSuperiorPanel = new JPanel(){
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

        JButton regresarBtn = new JButton();
        regresarBtn.setLocation(940,panelActualLbl.getY()+20);
        regresarBtn.setSize(40,40);
        ImageIcon regresarBotonIcon = new ImageIcon("src/img/botonRegresarIcon.png");
        regresarBtn.setIcon(regresarBotonIcon);
        regresarBtn.setVisible(true);
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (historialPaneles.size() != 0){
                    pasenUbi = historialPaneles.size()-1;
                    String tmp = historialPaneles.get(pasenUbi-1);
                    anterior = historialPaneles.get(pasenUbi);
                    actual = tmp;
                    pasenUbi--;
                }
                System.out.println(actual);
                limpiarVentana();

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

    public void limpiarVentana() {
        historialPaneles.add(actual);
        pasenUbi = historialPaneles.size()-1;
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

            panel = historialClienteSeleccionado();

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

            panel = editarClienteSeleccionado();

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

            panel = historialDeAutoSeleccionado();

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

            panel = editarRentaSeleccionada();

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

            panelActualLbl.setText("Categorias");

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
        loginIMG.setBackground(Color.decode("#005F04"));
        login.add(loginIMG);

        JLabel imagen2 = new JLabel();
        imagen2.setSize(550, 800);
        ImageIcon imag2 = new ImageIcon("src/img/car.png");
        ImageIcon icono2 = new ImageIcon(imag2.getImage().getScaledInstance(imagen2.getWidth(), imagen2.getHeight(), Image.SCALE_DEFAULT));
        imagen2.setIcon(icono2);
        imagen2.setLocation(0, 0);
        loginIMG.add(imagen2);

        JButton loginBTN = new JButton("Entrar");
        loginBTN.setSize(300, 36);
        loginBTN.setLocation(75, 400);
        loginBTN.setFont(new Font("Arial", Font.BOLD, 25));
        loginBTN.setForeground(Color.white);
        loginBTN.setBackground(Color.decode("#38B6FF"));
        loginPanel.add(loginBTN);

        loginBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("QUQWHJEJAHSKHJKL");
                anterior = actual;
                actual = "home";
                limpiarVentana();

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
        bienvenido.setLocation(230, 600);
        bienvenido.setForeground(Color.black);
        homePanel.add(bienvenido);

        //Cuando se hagan las validaciones, este texto será personalizado
        JLabel admin = new JLabel("Administrador.", JLabel.CENTER);
        admin.setFont(new Font("Arial", Font.BOLD, 25));
        admin.setSize(300, 80);
        admin.setLocation(397, 600);
        admin.setForeground(Color.decode("#38B6FF"));
        homePanel.add(admin);

        JButton logoutBTN = new JButton();
        logoutBTN.setSize(100, 25);
        logoutBTN.setLocation(250, 50);
        ImageIcon logoutBTNIMG = new ImageIcon("src/img/logout.png");
        logoutBTN.setIcon(logoutBTNIMG);
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
                limpiarVentana();

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
                limpiarVentana();

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
                limpiarVentana();

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
                limpiarVentana();

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
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        return homePanel;
    }

    public JPanel vehiculos() {
        anterior = actual;
        actual = "vehiculos";

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

                limpiarVentana();

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
        anterior = actual;
        actual = "consultarVehiculo";

        JPanel consultarCar = new JPanel();
        consultarCar.setSize(1000, 800);
        consultarCar.setLocation(0, 0);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Vehiculos", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarCar.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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

    public JPanel clientes() {
        int xBtn = 230;
        int yBtn = 127;
        int widthBtn  = 124;
        int heightBtn = 170;
        anterior = actual;
        actual = "clientes";


        JPanel clientesPanel = new JPanel();
        clientesPanel.setSize(1000, 800);
        clientesPanel.setLocation(0, 0);
        clientesPanel.setLayout(null);
        clientesPanel.setBackground(Color.decode("#FFFFFF"));


        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100, 20);
        homeBTN.setLocation(130, 390);
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
                limpiarVentana();

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
                limpiarVentana();

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
                limpiarVentana();

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
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        String[] columnasTablaClientes = {"<html><div style='text-align: center;'>Id<br>clientes</div></html>", "<html> <div style = 'text-align : center;'>Nombre</div></html>", "<html> <div style = 'text-align : center;'>Apellido</div></html>", "<html> <div style = 'text-align : center;'>Correo</div></html>", "<html> <div style = 'text-align : center;'>Telefóno</div></html>", "<html> <div style = 'text-align : center;'>Tarjeta<br>crédito</div></html>", "<html> <div style = 'text-align : center;'>Estado de <br> cuenta</div></html>"};

        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,10);
        //sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(700,500);
        sp.setLocation(165,400);
        sp.setVisible(true);
        clientesPanel.add(sp);
        return clientesPanel;
    }

    public JPanel consultarCliente() {

        JPanel consultarClientePNL = new JPanel();
        consultarClientePNL.setSize(1000, 800);
        consultarClientePNL.setLocation(0, 0);
        consultarClientePNL.setLayout(null);
        consultarClientePNL.setBackground(Color.decode("#FFFFFF"));

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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

        JLabel descripcionEditarCliente = new JLabel("ID del cliente a consultar");
        descripcionEditarCliente.setSize(400,100);
        descripcionEditarCliente.setLocation(370,20);
        descripcionEditarCliente.setFont(new Font("Arial", Font.BOLD, 24));
        consultarClientePNL.add(descripcionEditarCliente);

        JComboBox idClientesCB = new JComboBox();
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
                anterior = actual;
                actual = "consultarHistorialClienteSeleccionado";
                limpiarVentana();

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
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,18);//sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(700,500);
        sp.setLocation(175,200);
        sp.setVisible(true);

        consultarClientePNL.add(idClientesCB);
        consultarClientePNL.add(sp);
        consultarClientePNL.add(consultarHistorialClienteBtn);
        return consultarClientePNL;
    }


    public JPanel historialDeAutoSeleccionado(){
        JPanel historialClienteSeleccionadoPanel = new JPanel();
        historialClienteSeleccionadoPanel.setSize(1000, 800);
        historialClienteSeleccionadoPanel.setLocation(0, 0);
        historialClienteSeleccionadoPanel.setLayout(null);
        historialClienteSeleccionadoPanel.setBackground(Color.decode("#FFFFFF"));

        int xLbl = 100;
        JLabel autoLbl = new JLabel("Auto seleccionado: ");
        autoLbl.setFont(new Font("Arial", Font.BOLD, 24));
        autoLbl.setSize(300,50);
        autoLbl.setLocation(xLbl+10,100);

        String[] columnasTablaClientes = {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html> <div style = 'text-align : center;'>Cliente</div></html>",
                "<html> <div style = 'text-align : center;'>Fecha<br> de renta</div></html>",
                "<html> <div style = 'text-align : center;'>Fecha<br> de devolución</div></html>",
                "<html> <div style = 'text-align : center;'>Costo</div></html>",
                "<html> <div style = 'text-align : center;'>Método de<br>pago</div></html>",
                "<html> <div style = 'text-align : center;'>Observaciones</div></html>"};
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,18);
        sp.setSize(800,500);
        sp.setLocation(100,200);
        sp.setVisible(true);
        historialClienteSeleccionadoPanel.add(autoLbl);
        historialClienteSeleccionadoPanel.add(sp);

        return  historialClienteSeleccionadoPanel;
    }
    public JPanel historialClienteSeleccionado(){
        JPanel historialClienteSeleccionadoPanel = new JPanel();
        historialClienteSeleccionadoPanel.setSize(1000, 800);
        historialClienteSeleccionadoPanel.setLocation(0, 0);
        historialClienteSeleccionadoPanel.setLayout(null);
        historialClienteSeleccionadoPanel.setBackground(Color.decode("#FFFFFF"));

        int xLbl = 100;
        JLabel idClienteLbl = new JLabel("Id cliente: ");
        idClienteLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        idClienteLbl.setSize(300,50);
        idClienteLbl.setLocation(xLbl,100);

        JLabel nombreLbl = new JLabel("Nombre: ");
        nombreLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        nombreLbl.setSize(300,50);
        nombreLbl.setLocation(xLbl,150);
        xLbl += 600;

        JLabel correoLbl = new JLabel("Correo:");
        correoLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        correoLbl.setSize(300,50);
        correoLbl.setLocation(xLbl,100);

        JLabel telefonoLbl = new JLabel("Teléfono: ");
        telefonoLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        telefonoLbl.setSize(300,50);
        telefonoLbl.setLocation(xLbl,150);

        String[] columnasTablaClientes = {
                "<html><div style='text-align: center;'>Id<br>renta</div></html>",
                "<html> <div style = 'text-align : center;'>Cliente</div></html>",
                "<html> <div style = 'text-align : center;'>Fecha<br> de renta</div></html>",
                "<html> <div style = 'text-align : center;'>Fecha<br> de devolución</div></html>",
                "<html> <div style = 'text-align : center;'>Costo</div></html>",
                "<html> <div style = 'text-align : center;'>Método de<br>pago</div></html>",
                "<html> <div style = 'text-align : center;'>Observaciones</div></html>"};
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,18);
        sp.setSize(800,500);
        sp.setLocation(100,200);
        sp.setVisible(true);
        historialClienteSeleccionadoPanel.add(idClienteLbl);
        historialClienteSeleccionadoPanel.add(nombreLbl);
        historialClienteSeleccionadoPanel.add(correoLbl);
        historialClienteSeleccionadoPanel.add(telefonoLbl);
        historialClienteSeleccionadoPanel.add(sp);

        return  historialClienteSeleccionadoPanel;
    }

    public JPanel crearCliente() {

        JPanel crearClientesPNL = new JPanel();
        crearClientesPNL.setSize(1000, 800);
        crearClientesPNL.setLocation(0, 0);
        crearClientesPNL.setLayout(null);
        crearClientesPNL.setBackground(Color.decode("#FFFFFF"));

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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


        int x = 200;
        int y = 50;
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
        y = 50;

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

        JTextField ccvTF = new JTextField();
        ccvTF.setBorder(roundedBorder);
        ccvTF.setLocation(x,y);
        ccvTF.setSize(100,30);
        crearClientesPNL.add(ccvTF);


        JButton cancelarBtn = new JButton();
        cancelarBtn.setSize(230,35);
        cancelarBtn.setLocation(250,y+125);
        ImageIcon cancelarIcon = new ImageIcon("src/img/cancelarBoton.png");
        cancelarBtn.setIcon(cancelarIcon);
        System.out.println("anterior: " + anterior);
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("anterior: " + anterior);
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        crearClientesPNL.add(cancelarBtn);

        JButton guardarBtn = new JButton();
        guardarBtn.setSize(230,35);
        guardarBtn.setLocation(525,y+125);
        ImageIcon guardarIcon = new ImageIcon("src/img/crearCuentaBoton.png");
        guardarBtn.setIcon(guardarIcon);
        crearClientesPNL.add(guardarBtn);

        return crearClientesPNL;
    }

    public JPanel editarCliente() {


        JPanel editarClientesPNL = new JPanel();
        editarClientesPNL.setSize(1000, 800);
        editarClientesPNL.setLocation(0, 0);
        editarClientesPNL.setLayout(null);
        editarClientesPNL.setBackground(Color.decode("#FFFFFF"));

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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

        JLabel descripcionEditarCliente = new JLabel("ID del cliente a editar");
        descripcionEditarCliente.setSize(400,100);
        descripcionEditarCliente.setLocation(395,20);
        descripcionEditarCliente.setFont(new Font("Arial", Font.BOLD, 24));
        editarClientesPNL.add(descripcionEditarCliente);

        JComboBox idClientesCB = new JComboBox();
        idClientesCB.setSize(226,40);
        idClientesCB.setLocation(400,90);
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
                limpiarVentana();

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
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes, 18);
        sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(800,500);
        sp.setLocation(100,200);
        sp.setVisible(true);
        editarClientesPNL.add(sp);

        return editarClientesPNL;
    }
    public JPanel editarClienteSeleccionado(){ //paneles llegados por medio de otro boton no deben de tener ele cambio nac recuerda!!!!!!

        JPanel editarClienteSeleccionadoPNL = new JPanel();
        editarClienteSeleccionadoPNL.setSize(1000, 800);
        editarClienteSeleccionadoPNL.setLocation(0, 0);
        editarClienteSeleccionadoPNL.setLayout(null);
        editarClienteSeleccionadoPNL.setBackground(Color.decode("#FFFFFF"));

        int x = 200;
        int y = 50;
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

        JTextField ccvTF = new JTextField();
        ccvTF.setBorder(roundedBorder);
        ccvTF.setLocation(x,y);
        ccvTF.setSize(100,30);
        editarClienteSeleccionadoPNL.add(ccvTF);

        JButton cancelarBtn = new JButton();
        cancelarBtn.setSize(230,35);
        cancelarBtn.setLocation(250,y+125);
        ImageIcon cancelarIcon = new ImageIcon("src/img/cancelarBoton.png");
        cancelarBtn.setIcon(cancelarIcon);
        System.out.println("anterior: " + anterior);
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("anterior: " + anterior);
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        editarClienteSeleccionadoPNL.add(cancelarBtn);

        JButton guardarBtn = new JButton();
        guardarBtn.setSize(230,35);
        guardarBtn.setLocation(525,y+125);
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

        JComboBox idClientesCB = new JComboBox();
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

        JScrollPane sp = scrollPaneDefault(columnasTablaClientes, 18);
        sp.setSize(800,500);
        sp.setLocation(100,200);
        sp.setVisible(true);

        eliminarPanel.add(idClientesCB);
        eliminarPanel.add(sp);
        eliminarPanel.add(eliminarClienteBtn);
        eliminarPanel.add(descripcionLbl);


        return eliminarPanel;
    }

    public JPanel rentas() {
        anterior = actual;
        actual = "rentas";
        int xBtn = 230;
        int yBtn = 127;
        int widthBtn  = 124;
        int heightBtn = 170;

        JPanel rentasPanel = new JPanel();
        rentasPanel.setSize(1000, 800);
        rentasPanel.setLocation(0, 0);
        rentasPanel.setLayout(null);
        rentasPanel.setBackground(Color.decode("#FFFFFF"));

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100, 20);
        homeBTN.setLocation(130, 390);
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
                limpiarVentana();

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
                limpiarVentana();

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
                limpiarVentana();

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
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        String[] columnasTablaClientes = {"<html><div style='text-align: center;'>Id<br>renta</div></html>", "<html><div style='text-align: center;'>Cliente</div></html>", "<html><div style='text-align: center;'>Automóvil</div></html>", "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>", "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>", "<html><div style='text-align: center;'>Costo</div></html>", "<html><div style='text-align: center;'>Método de<br>pago</div></html>", "<html><div style='text-align: center;'>Observaciones</div></html>"};
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,10);
        //sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(700,500);
        sp.setLocation(165,400);
        sp.setVisible(true);
        rentasPanel.add(sp);
        return rentasPanel;
    }

    public JPanel consultarRentas() {

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


        JComboBox idClientesCB = new JComboBox();
        idClientesCB.setSize(226,40);
        idClientesCB.setLocation(400,100);
        consultarCarPNL.add(idClientesCB);

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
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        // <html><div style='text-align: center;'>Id<br>clientes</div></html>
        String[] columnasTablaClientes = {"<html><div style='text-align: center;'>Id<br>renta</div></html>", "<html><div style='text-align: center;'>Cliente</div></html>", "<html><div style='text-align: center;'>Automóvil</div></html>", "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>", "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>", "<html><div style='text-align: center;'>Costo</div></html>", "<html><div style='text-align: center;'>Método de<br>pago</div></html>", "<html><div style='text-align: center;'>Observaciones</div></html>"};
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,18);
        sp.setSize(800,500);
        sp.setLocation(100,225);
        sp.setVisible(true);
        consultarCarPNL.add(sp);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
        consultarCarPNL.add(backBTN);


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

        return consultarCarPNL;
    }

    public JPanel crearRenta() {

        JPanel crearRentaPNL = new JPanel();
        crearRentaPNL.setSize(1000, 800);
        crearRentaPNL.setLocation(0, 0);
        crearRentaPNL.setLayout(null);
        crearRentaPNL.setBackground(Color.decode("#FFFFFF"));

        int x = 100;
        int y = 100;
        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.BOLD, 16));
        carroLbl.setSize(200,40);
        crearRentaPNL.add(carroLbl);
        y += 50;
        JTextField carroTF = new JTextField();
        carroTF.setBorder(roundedBorder);
        carroTF.setLocation(x,y);
        carroTF.setSize(200,30);
        crearRentaPNL.add(carroTF);

        y += 50;
        JLabel fechaInicioLbl = new JLabel("Fecha de inicio");
        fechaInicioLbl.setLocation(x,y);
        fechaInicioLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaInicioLbl.setSize(200,40);
        crearRentaPNL.add(fechaInicioLbl);
        y += 50;
        JTextField fechaInicioTF = new JTextField();
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
        JTextField fechaDeDevolucionTF = new JTextField();
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
        JTextField idClienteTF = new JTextField();
        idClienteTF.setBorder(roundedBorder);
        idClienteTF.setLocation(x,y);
        idClienteTF.setSize(200,30);
        crearRentaPNL.add(idClienteTF);

        x +=300;
        y = 100;

        JLabel nombresLbl = new JLabel("Nombres");
        nombresLbl.setLocation(x,y);
        nombresLbl.setFont(new Font("Arial", Font.BOLD, 16));
        nombresLbl.setSize(200,40);
        crearRentaPNL.add(nombresLbl);
        y += 50;
        JTextField nombresTF = new JTextField();
        nombresTF.setBorder(roundedBorder);
        nombresTF.setLocation(x,y);
        nombresTF.setSize(200,30);
        crearRentaPNL.add(nombresTF);

        y += 50;


        JLabel apellidosLbl = new JLabel("Apellidos");
        apellidosLbl.setLocation(x,y);
        apellidosLbl.setFont(new Font("Arial", Font.BOLD, 16));
        apellidosLbl.setSize(200,40);
        crearRentaPNL.add(apellidosLbl);
        y += 50;
        JTextField apellidosTF = new JTextField();
        apellidosTF.setBorder(roundedBorder);
        apellidosTF.setLocation(x,y);
        apellidosTF.setSize(200,30);
        crearRentaPNL.add(apellidosTF);


        y += 50;
        JLabel telefonoLbl = new JLabel("Número telefónico");
        telefonoLbl.setLocation(x,y);
        telefonoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        telefonoLbl.setSize(200,40);
        crearRentaPNL.add(telefonoLbl);
        y += 50;
        JTextField telefonoTF = new JTextField();
        telefonoTF.setBorder(roundedBorder);
        telefonoTF.setLocation(x,y);
        telefonoTF.setSize(200,30);
        crearRentaPNL.add(telefonoTF);

        y += 50;
        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        crearRentaPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
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
        JTextField fechaCadTF = new JTextField();
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

        JTextField ccvTF = new JTextField();
        ccvTF.setBorder(roundedBorder);
        ccvTF.setLocation(x,y);
        ccvTF.setSize(100,30);
        crearRentaPNL.add(ccvTF);

        x += 200;
        y = 100;
        JLabel costoEstimadoLbl = new JLabel("Costo estimado", JLabel.CENTER);
        costoEstimadoLbl.setLocation(x,y);
        costoEstimadoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        costoEstimadoLbl.setSize(200,40);
        crearRentaPNL.add(costoEstimadoLbl);

        y += 50;

        JLabel costoEstimadoVisualLbl = new JLabel();
        costoEstimadoVisualLbl.setHorizontalAlignment(JLabel.CENTER);
        costoEstimadoVisualLbl.setBackground(Color.pink);
        costoEstimadoVisualLbl.setLocation(x+15,y);
        costoEstimadoVisualLbl.setSize(193,102);
        ImageIcon facturaIcon = new ImageIcon("src/img/rentaFacturaIcon.png");
        costoEstimadoVisualLbl.setIcon(facturaIcon);
        crearRentaPNL.add(costoEstimadoVisualLbl);

        y += costoEstimadoVisualLbl.getHeight()+10;

        JButton crearRentaBtn = new JButton();
        crearRentaBtn.setSize(226,31);
        crearRentaBtn.setLocation(x,y);
        ImageIcon crearRentaIcon = new ImageIcon("src/img/crearRentaBoton.png");
        crearRentaBtn.setIcon(crearRentaIcon);
        crearRentaPNL.add(crearRentaBtn);

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
                System.out.println("anterior: " + anterior);
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        crearRentaPNL.add(cancelarBtn);


        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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

    public JPanel editarRenta() {

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

        JComboBox idRentasCB = new JComboBox();
        idRentasCB.setSize(226,40);
        idRentasCB.setLocation(400,100);

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
                limpiarVentana();

                repaint();
                revalidate();
            }
        });


        String[] columnasTablaClientes = {
                "<html><div style='text-align: center;'>Id<br>clientes</div></html>",
                "<html> <div style = 'text-align : center;'>Nombre</div></html>",
                "<html> <div style = 'text-align : center;'>Apellido</div></html>",
                "<html> <div style = 'text-align : center;'>Correo</div></html>",
                "<html> <div style = 'text-align : center;'>Telefóno</div></html>",
                "<html> <div style = 'text-align : center;'>Tarjeta<br>crédito</div></html>",
                "<html> <div style = 'text-align : center;'>Estado de <br> cuenta</div></html>"};
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,18);
        sp.setSize(800,500);
        sp.setLocation(100,200);
        sp.setVisible(true);

        editarRentaPNL.add(idRentasCB);
        editarRentaPNL.add(sp);
        editarRentaPNL.add(editarRentaBtn);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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

    public JPanel editarRentaSeleccionada(){

        JPanel editarRentaSeleccionadaPNL = new JPanel();
        editarRentaSeleccionadaPNL.setSize(1000, 800);
        editarRentaSeleccionadaPNL.setLocation(0, 0);
        editarRentaSeleccionadaPNL.setLayout(null);
        editarRentaSeleccionadaPNL.setBackground(Color.decode("#FFFFFF"));

        int x = 100;
        int y = 100;
        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.BOLD, 16));
        carroLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(carroLbl);
        y += 50;
        JTextField carroTF = new JTextField();
        carroTF.setBorder(roundedBorder);
        carroTF.setLocation(x,y);
        carroTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(carroTF);

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
        JTextField idClienteTF = new JTextField();
        idClienteTF.setBorder(roundedBorder);
        idClienteTF.setLocation(x,y);
        idClienteTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(idClienteTF);

        x +=300;
        y = 100;

        JLabel nombresLbl = new JLabel("Nombres");
        nombresLbl.setLocation(x,y);
        nombresLbl.setFont(new Font("Arial", Font.BOLD, 16));
        nombresLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(nombresLbl);
        y += 50;
        JTextField nombresTF = new JTextField();
        nombresTF.setBorder(roundedBorder);
        nombresTF.setLocation(x,y);
        nombresTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(nombresTF);

        y += 50;

        JLabel apellidosLbl = new JLabel("Apellidos");
        apellidosLbl.setLocation(x,y);
        apellidosLbl.setFont(new Font("Arial", Font.BOLD, 16));
        apellidosLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(apellidosLbl);
        y += 50;
        JTextField apellidosTF = new JTextField();
        apellidosTF.setBorder(roundedBorder);
        apellidosTF.setLocation(x,y);
        apellidosTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(apellidosTF);


        y += 50;
        JLabel telefonoLbl = new JLabel("Número telefónico");
        telefonoLbl.setLocation(x,y);
        telefonoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        telefonoLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(telefonoLbl);
        y += 50;
        JTextField telefonoTF = new JTextField();
        telefonoTF.setBorder(roundedBorder);
        telefonoTF.setLocation(x,y);
        telefonoTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(telefonoTF);

        y += 50;
        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
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
        JTextField fechaCadTF = new JTextField();
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        ccvLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(ccvLbl);

        x += 100;

        JTextField ccvTF = new JTextField();
        ccvTF.setBorder(roundedBorder);
        ccvTF.setLocation(x,y);
        ccvTF.setSize(100,30);
        editarRentaSeleccionadaPNL.add(ccvTF);

        x += 200;
        y = 100;
        JLabel costoEstimadoLbl = new JLabel("Costo estimado", JLabel.CENTER);
        costoEstimadoLbl.setLocation(x,y);
        costoEstimadoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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

        JButton guardarBtn = new JButton();
        guardarBtn.setSize(226,31);
        guardarBtn.setLocation(x,y);
        ImageIcon crearRentaIcon = new ImageIcon("src/img/guardarCambiosBoton.png");
        guardarBtn.setIcon(crearRentaIcon);
        editarRentaSeleccionadaPNL.add(guardarBtn);

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
                System.out.println("anterior: " + anterior);
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                limpiarVentana();

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
        descripcionLbl.setFont(new Font("Arial", Font.PLAIN, 24));
        descripcionLbl.setSize(300,50);
        descripcionLbl.setLocation(400,50);

        JComboBox idRentasCB = new JComboBox();
        idRentasCB.setSize(226,40);
        idRentasCB.setLocation(400,100);

        JButton eliminarRentaBtn = new JButton();
        eliminarRentaBtn.setSize(226,31);
        eliminarRentaBtn.setLocation(400, 150);
        ImageIcon eliminarIcon = new ImageIcon("src/img/eliminarClienteBoton.png");
        eliminarRentaBtn.setIcon(eliminarIcon);

        String[] columnasTablaClientes = {"<html><div style='text-align: center;'>Id<br>renta</div></html>", "<html><div style='text-align: center;'>Cliente</div></html>", "<html><div style='text-align: center;'>Automóvil</div></html>", "<html><div style='text-align: center;'>Fecha de<br>renta</div></html>", "<html><div style='text-align: center;'>Fecha de<br>devolución</div></html>", "<html><div style='text-align: center;'>Costo</div></html>", "<html><div style='text-align: center;'>Método de<br>pago</div></html>", "<html><div style='text-align: center;'>Observaciones</div></html>"};
        JScrollPane sp = scrollPaneDefault(columnasTablaClientes,18);
        sp.setSize(800,500);
        sp.setLocation(100,200);
        sp.setVisible(true);

        eliminarRentaPNL.add(descripcionLbl);
        eliminarRentaPNL.add(idRentasCB);
        eliminarRentaPNL.add(sp);
        eliminarRentaPNL.add(eliminarRentaBtn);

        return eliminarRentaPNL;
    }
    public JPanel categorias() {
        anterior = actual;
        actual = "categorias";

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
        anterior = actual;
        actual = "consultarCategorias";

        JPanel consultarCar = new JPanel();
        consultarCar.setSize(1000, 800);
        consultarCar.setLocation(0, 0);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Categorias", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarCar.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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

    public JPanel marcas() {
        anterior = actual;
        actual = "marcas";

        JPanel marcasPanel = new JPanel();
        marcasPanel.setSize(1000, 800);
        marcasPanel.setLocation(0, 0);
        marcasPanel.setLayout(null);
        marcasPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Marcas", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        marcasPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100, 20);
        homeBTN.setLocation(130, 390);
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
        consultaBTN.setSize(100, 20);
        consultaBTN.setLocation(260, 390);
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

    public JPanel consultarMarcas() {
        anterior = actual;
        actual = "consultarMarcas";

        JPanel consultarCar = new JPanel();
        consultarCar.setSize(1000, 800);
        consultarCar.setLocation(0, 0);
        consultarCar.setLayout(null);
        consultarCar.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Marcas", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarCar.add(bienvenido);

        JButton backBTN = new JButton("Regresar");
        backBTN.setSize(100, 20);
        backBTN.setLocation(130, 390);
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
    public JScrollPane scrollPaneDefault(String[] columnasTablaClientes, int filas){
        Object [][]datos = new Object[filas][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JTableHeader header = tablaClientes.getTableHeader();
        header.setBackground(Color.decode("#38B6FF"));
        header.setPreferredSize(new Dimension(700,40));
        header.setFont(new Font("Arial", Font.PLAIN, 15));
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        UIManager.put("TableHeader.cellBorder", BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
        headerRenderer.setOpaque(false);
        for (int i = 0; i < tablaClientes.getColumnCount(); i++) {
            tablaClientes.getColumnModel().getColumn(i).setPreferredWidth(75);
            tablaClientes.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        JScrollPane sp = new JScrollPane(tablaClientes);
        //sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(700,500);
        sp.setLocation(165,400);
        sp.setVisible(true);
        return sp;
    }

    public static void main(String[] args) {
        Ventana screen = new Ventana();
    }
}

