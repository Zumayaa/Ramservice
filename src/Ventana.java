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
import java.util.List;
import java.util.Map;

public class Ventana extends JFrame {


    public ArrayList<JPanel> autos = new ArrayList<>();

    public ArrayList<JPanel> marcas = new ArrayList<>();

    public ArrayList<JPanel> categorias = new ArrayList<>();

    public ArrayList<JPanel> autosEditar = new ArrayList<>();

    public ArrayList<JPanel> marcasEditar = new ArrayList<>();

    public ArrayList<JPanel> categoriasEditar = new ArrayList<>();

    public ArrayList<JButton> botoneslista = new ArrayList<>();

    public ArrayList<JTextField> textosAutos = new ArrayList<>();

    public ArrayList<JPanel> autosEdistar = new ArrayList<>();

    public ArrayList<JPanel> autosEditdar = new ArrayList<>();

    private int panelCatX=84,panelCatY=75,panelMarcX=84,panelMarcY=75,panelCarrX=84,panelCarrY=84;
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
        regresarBtn.setBackground(Color.white);
        ImageIcon regresarBotonIcon = new ImageIcon("src/img/botonRegresarIcon.png");
        regresarBtn.setIcon(regresarBotonIcon);
        regresarBtn.setVisible(true);
        regresarBtn.setFocusPainted(false);
        regresarBtn.setBorderPainted(false);
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

        int adicion_a_x = 25;
        JLabel bienvenido = new JLabel("Bienvenido, ", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 25));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(230+adicion_a_x, 0);
        bienvenido.setForeground(Color.black);
        homePanel.add(bienvenido);

        //Cuando se hagan las validaciones, este texto será personalizado ok
        JLabel admin = new JLabel("Administrador.", JLabel.CENTER);
        admin.setFont(new Font("Arial", Font.BOLD, 25));
        admin.setSize(300, 80);
        admin.setLocation(397+adicion_a_x, 0);
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

        JButton crearVehiculos = new JButton("Crear vehículos");
        crearVehiculos.setBackground(Color.decode("#38b6ff"));
        crearVehiculos.setSize(300,30);
        crearVehiculos.setLocation(350,20);
        crearVehiculos.setFocusPainted(false);
        crearVehiculos.setForeground(Color.white);
        crearVehiculos.setBorderPainted(false);
        crearVehiculos.setFont(new Font("Tahoma", Font.BOLD, 18));
        vehiculosPanel.add(crearVehiculos);

        JPanel fondoCarr = new JPanel();
        fondoCarr.setOpaque(true);
        fondoCarr.setLayout(null);
        fondoCarr.setBackground(Color.LIGHT_GRAY);
        fondoCarr.setBounds(panelCarrX, panelCarrY, 826, 232);
        vehiculosPanel.add(fondoCarr);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Color.decode("#BF0000"));
        btnEliminar.setBounds(570, 151, 226, 53);
        fondoCarr.add(btnEliminar);

        JButton consultarCarr = new JButton("Consultar");

        consultarCarr.setFocusPainted(false);
        consultarCarr.setForeground(Color.WHITE);
        consultarCarr.setFont(new Font("Tahoma", Font.BOLD, 16));
        consultarCarr.setBorderPainted(false);
        consultarCarr.setBackground(Color.BLACK);
        consultarCarr.setBounds(570, 82, 226, 55);
        fondoCarr.add(consultarCarr);

        JLabel precioCarr = new JLabel("$204 USD por día");
        precioCarr.setForeground(Color.BLACK);
        precioCarr.setOpaque(true);
        precioCarr.setBackground(Color.decode("#38B6FF"));
        precioCarr.setFont(new Font("Tahoma", Font.BOLD, 16));
        precioCarr.setHorizontalAlignment(SwingConstants.CENTER);
        precioCarr.setBounds(570, 16, 226, 55);
        fondoCarr.add(precioCarr);

        JLabel añoCarr = new JLabel("2021");
        añoCarr.setHorizontalAlignment(SwingConstants.CENTER);
        añoCarr.setForeground(Color.BLACK);
        añoCarr.setFont(new Font("Tahoma", Font.BOLD, 17));
        añoCarr.setBounds(267, 123, 166, 30);
        fondoCarr.add(añoCarr);

        JLabel gasCarr = new JLabel("Gasolina");
        gasCarr.setHorizontalAlignment(SwingConstants.CENTER);
        gasCarr.setForeground(Color.BLACK);
        gasCarr.setFont(new Font("Tahoma", Font.BOLD, 17));
        gasCarr.setBounds(282, 174, 166, 30);
        fondoCarr.add(gasCarr);

        JLabel modoCarr = new JLabel("Estándar");
        modoCarr.setHorizontalAlignment(SwingConstants.CENTER);
        modoCarr.setForeground(Color.BLACK);
        modoCarr.setFont(new Font("Tahoma", Font.BOLD, 17));
        modoCarr.setBounds(282, 75, 166, 30);
        fondoCarr.add(modoCarr);

        JLabel tipoCarr = new JLabel("Coupé");
        tipoCarr.setHorizontalAlignment(SwingConstants.CENTER);
        tipoCarr.setForeground(Color.BLACK);
        tipoCarr.setFont(new Font("Tahoma", Font.BOLD, 17));
        tipoCarr.setBounds(272, 20, 166, 30);
        fondoCarr.add(tipoCarr);

        JLabel cajaCarr = new JLabel("");
        cajaCarr.setIcon(new ImageIcon("src/img/Gearbox.png"));
        cajaCarr.setBounds(287, 63, 35, 53);
        fondoCarr.add(cajaCarr);

        JLabel nomCarr = new JLabel("BMW Rey X");
        nomCarr.setForeground(Color.BLACK);
        nomCarr.setHorizontalAlignment(SwingConstants.CENTER);
        nomCarr.setFont(new Font("Tahoma", Font.BOLD, 17));
        nomCarr.setBounds(45, 174, 166, 30);
        fondoCarr.add(nomCarr);

        JLabel vectCarr = new JLabel("");
        vectCarr.setIcon(new ImageIcon("src/img/Vector.png"));
        vectCarr.setBounds(289, 11, 48, 53);
        fondoCarr.add(vectCarr);

        JLabel gasImgCarr = new JLabel("");
        gasImgCarr.setIcon(new ImageIcon("src/img/Local gas station.png"));
        gasImgCarr.setBounds(289, 164, 35, 53);
        fondoCarr.add(gasImgCarr);

        JLabel calCarr = new JLabel("");
        calCarr.setIcon(new ImageIcon("src/img/Calendar today.png"));
        calCarr.setBounds(289, 112, 35, 53);
        fondoCarr.add(calCarr);

        JLabel imgCarr = new JLabel("");
        imgCarr.setIcon(new ImageIcon("src/img/image 10.png"));
        imgCarr.setBounds(35, 24, 226, 140);
        fondoCarr.add(imgCarr);

        consultarCarr.addActionListener(new ActionListener() {
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

        JTextField precioNuevoCarr = new JTextField();
        precioNuevoCarr.setOpaque(false);
        precioNuevoCarr.setHorizontalAlignment(SwingConstants.LEFT);
        precioNuevoCarr.setFont(new Font("Tahoma", Font.BOLD, 13));
        precioNuevoCarr.setColumns(10);
        precioNuevoCarr.setBorder(null);
        precioNuevoCarr.setBackground(SystemColor.menu);
        precioNuevoCarr.setBounds(813, 83, 93, 29);
        consultarVehi.add(precioNuevoCarr);

        JLabel gasImg = new JLabel("");
        gasImg.setIcon(new ImageIcon("src/img/gasg.png"));
        gasImg.setHorizontalAlignment(SwingConstants.CENTER);
        gasImg.setBounds(401, 409, 58, 38);
        consultarVehi.add(gasImg);

        JLabel calImg = new JLabel("");
        calImg.setIcon(new ImageIcon("src/img/calendarg.png"));
        calImg.setHorizontalAlignment(SwingConstants.CENTER);
        calImg.setBounds(276, 409, 58, 38);
        consultarVehi.add(calImg);

        JLabel gearImg = new JLabel("");
        gearImg.setIcon(new ImageIcon("src/img/gearg.png"));
        gearImg.setHorizontalAlignment(SwingConstants.CENTER);
        gearImg.setBounds(135, 409, 58, 38);
        consultarVehi.add(gearImg);

        JLabel transmisionCarr = new JLabel("Transmisión");
        transmisionCarr.setHorizontalAlignment(SwingConstants.RIGHT);
        transmisionCarr.setForeground(Color.BLACK);
        transmisionCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        transmisionCarr.setBounds(787, 148, 119, 23);
        consultarVehi.add(transmisionCarr);

        JComboBox gasTipoCarr = new JComboBox();
        gasTipoCarr.setModel(new DefaultComboBoxModel(new String[] {"Gasolina"}));
        gasTipoCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        gasTipoCarr.setBounds(670, 331, 236, 29);
        consultarVehi.add(gasTipoCarr);

        JTextField añoCarrCamb = new JTextField("");
        añoCarrCamb.setBorder(null);
        añoCarrCamb.setBackground(SystemColor.menu);
        añoCarrCamb.setBounds(670, 257, 236, 29);
        consultarVehi.add(añoCarrCamb);

        JLabel combCarr = new JLabel("Combustible");
        combCarr.setForeground(Color.BLACK);
        combCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        combCarr.setBounds(670, 308, 154, 23);
        consultarVehi.add(combCarr);

        JLabel añoCarr = new JLabel("Año");
        añoCarr.setForeground(Color.BLACK);
        añoCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        añoCarr.setBounds(670, 235, 154, 23);
        consultarVehi.add(añoCarr);

        JComboBox transmisionCambCarr = new JComboBox();
        transmisionCambCarr.setFont(new Font("Tahoma", Font.BOLD, 11));
        transmisionCambCarr.setBounds(806, 175, 100, 29);
        consultarVehi.add(transmisionCambCarr);

        JComboBox marcasCambCarr = new JComboBox();
        marcasCambCarr.setForeground(Color.BLACK);
        marcasCambCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        marcasCambCarr.setModel(new DefaultComboBoxModel(new String[] {"BMW"}));
        marcasCambCarr.setBounds(670, 175, 100, 29);
        consultarVehi.add(marcasCambCarr);

        JTextField signoPesos = new JTextField();
        signoPesos.setEditable(false);
        signoPesos.setHorizontalAlignment(SwingConstants.LEFT);
        signoPesos.setFont(new Font("Tahoma", Font.BOLD, 13));
        signoPesos.setText("$");
        signoPesos.setColumns(10);
        signoPesos.setBorder(null);
        signoPesos.setBackground(SystemColor.menu);
        signoPesos.setBounds(806, 83, 100, 29);
        consultarVehi.add(signoPesos);

        JLabel costoDiaCarr = new JLabel("Costo por día");
        costoDiaCarr.setForeground(Color.BLACK);
        costoDiaCarr.setHorizontalAlignment(SwingConstants.RIGHT);
        costoDiaCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        costoDiaCarr.setBounds(787, 59, 119, 23);
        consultarVehi.add(costoDiaCarr);

        JButton cambiarImgCarr = new JButton("");
        cambiarImgCarr.setBorderPainted(false);
        cambiarImgCarr.setRequestFocusEnabled(false);
        cambiarImgCarr.setFocusPainted(false);
        cambiarImgCarr.setContentAreaFilled(false);
        cambiarImgCarr.setHorizontalAlignment(SwingConstants.CENTER);
        cambiarImgCarr.setIcon(new ImageIcon("src/img/Group 56 (1).png"));
        cambiarImgCarr.setBounds(652, 424, 271, 92);
        consultarVehi.add(cambiarImgCarr);

        JTextField cambModeloCarr = new JTextField("");
        cambModeloCarr.setBorder(null);
        cambModeloCarr.setBackground(SystemColor.menu);
        cambModeloCarr.setBounds(670, 84, 100, 29);
        consultarVehi.add(cambModeloCarr);


        JLabel catCarr = new JLabel("Categoría");
        catCarr.setForeground(Color.BLACK);
        catCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        catCarr.setBounds(670, 148, 154, 23);
        consultarVehi.add(catCarr);

        JLabel modCarr = new JLabel("Modelo");
        modCarr.setForeground(Color.BLACK);
        modCarr.setFont(new Font("Tahoma", Font.BOLD, 14));
        modCarr.setBounds(670, 59, 154, 23);
        consultarVehi.add(modCarr);

        JTextArea descActCarr = new JTextArea();
        descActCarr.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        descActCarr.setOpaque(false);
        descActCarr.setLineWrap(true);
        descActCarr.setForeground(Color.BLACK);
        descActCarr.setFont(new Font("Tahoma", Font.PLAIN, 18));
        descActCarr.setBounds(24, 471, 487, 164);
        consultarVehi.add(descActCarr);


        JButton editBoton = new JButton("Editar");
        editBoton.setForeground(Color.WHITE);
        editBoton.setFont(new Font("Tahoma", Font.BOLD, 16));
        editBoton.setFocusPainted(false);
        editBoton.setBorderPainted(false);
        editBoton.setBackground(Color.decode("#38B6FF"));
        editBoton.setBounds(657, 547, 260, 53);
        consultarVehi.add(editBoton);

        JLabel fondoeditCarr = new JLabel("");
        fondoeditCarr.setHorizontalTextPosition(SwingConstants.CENTER);
        fondoeditCarr.setVerticalAlignment(SwingConstants.BOTTOM);
        fondoeditCarr.setOpaque(true);
        fondoeditCarr.setHorizontalAlignment(SwingConstants.CENTER);
        fondoeditCarr.setBackground(SystemColor.controlShadow);
        fondoeditCarr.setBounds(632, 24, 307, 597);
        consultarVehi.add(fondoeditCarr);

        JLabel nombActCarr = new JLabel("BMW Rey X");
        nombActCarr.setForeground(Color.BLACK);
        nombActCarr.setFont(new Font("Tahoma", Font.BOLD, 25));
        nombActCarr.setHorizontalAlignment(SwingConstants.CENTER);
        nombActCarr.setBounds(166, 24, 282, 58);
        consultarVehi.add(nombActCarr);

        JLabel fondoActDesc = new JLabel("");
        fondoActDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        fondoActDesc.setOpaque(true);
        fondoActDesc.setHorizontalAlignment(SwingConstants.CENTER);
        fondoActDesc.setBackground(SystemColor.controlShadow);
        fondoActDesc.setBounds(17, 467, 517, 154);
        consultarVehi.add(fondoActDesc);

        JLabel vectorImg = new JLabel("");
        vectorImg.setIcon(new ImageIcon("src/img/Vectorg.png"));
        vectorImg.setHorizontalAlignment(SwingConstants.CENTER);
        vectorImg.setBounds(7, 409, 58, 38);
        consultarVehi.add(vectorImg);

        JLabel tipoActCarr = new JLabel("Coupé");
        tipoActCarr.setForeground(Color.BLACK);
        tipoActCarr.setFont(new Font("Tahoma", Font.BOLD, 18));
        tipoActCarr.setBounds(60, 409, 76, 38);
        consultarVehi.add(tipoActCarr);

        JLabel modoActCarr = new JLabel("Estándar");
        modoActCarr.setForeground(Color.BLACK);
        modoActCarr.setFont(new Font("Tahoma", Font.BOLD, 18));
        modoActCarr.setBounds(185, 409, 93, 38);
        consultarVehi.add(modoActCarr);

        JLabel añoActCarr = new JLabel("2021");
        añoActCarr.setForeground(Color.BLACK);
        añoActCarr.setFont(new Font("Tahoma", Font.BOLD, 18));
        añoActCarr.setBounds(329, 409, 76, 38);
        consultarVehi.add(añoActCarr);

        JLabel gasActCarr = new JLabel("Gasolina");
        gasActCarr.setForeground(Color.BLACK);
        gasActCarr.setFont(new Font("Tahoma", Font.BOLD, 18));
        gasActCarr.setBounds(455, 409, 100, 38);
        consultarVehi.add(gasActCarr);

        JLabel precioActCarr = new JLabel("$204 / día");
        precioActCarr.setForeground(new Color(0, 128, 0));
        precioActCarr.setBackground(Color.GREEN);
        precioActCarr.setHorizontalAlignment(SwingConstants.CENTER);
        precioActCarr.setFont(new Font("Tahoma", Font.BOLD, 18));
        precioActCarr.setBounds(195, 77, 216, 37);
        consultarVehi.add(precioActCarr);

        JLabel imgCarr = new JLabel("");
        imgCarr.setHorizontalAlignment(SwingConstants.CENTER);
        imgCarr.setIcon(new ImageIcon("src/img/carrograndeazul.png"));
        imgCarr.setBounds(22, 100, 552, 298);
        consultarVehi.add(imgCarr);

        return consultarVehi;
    }

    public JPanel clientes() {
        int xBtn = 215;
        int yBtn = 50;
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
        String[] columnasTabla = TablasRamservice.obtener_nombres_columnas("CLIENTES");

        JTable tabla_clientes = new JTable();

            DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTabla,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
        sp.setSize(900,400);
        sp.setLocation(50,heightBtn+yBtn+25);
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
        descripcionEditarCliente.setLocation(385, 15);
        descripcionEditarCliente.setFont(new Font("Arial", Font.BOLD, 24));
        consultarClientePNL.add(descripcionEditarCliente);
        // corregir URGE!!! ya no jeje o si quien sabe
        String nombres_Clientes[] = concatenarArreglos
                (Clientes_Service.obtener_columna("SELECT nombre FROM clientes"),
                 Clientes_Service.obtener_columna("SELECT apellido FROM clientes"), " ");
        String id_cliente [] = Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes ORDER BY id_de_cliente");
        String id_nombre [] = concatenarArreglos(id_cliente,nombres_Clientes, " : ");
        JComboBox idClientesCB = new JComboBox(id_nombre);
        idClientesCB.setSize(230,30);
        idClientesCB.setLocation(400,95);

        JButton consultarHistorialClienteBtn = new JButton();
        consultarHistorialClienteBtn.setSize(226,44);
        consultarHistorialClienteBtn.setLocation(400, 150);
        ImageIcon consultarHistorialIcon = new ImageIcon("src/img/consultarHistorialClienteIcon.png");
        consultarHistorialClienteBtn.setIcon(consultarHistorialIcon);
        consultarHistorialClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id_cliente_a_consultar = Integer.parseInt(id_cliente[idClientesCB.getSelectedIndex()]);
                String [] datos = Clientes_Service.obtener_fila("SELECT * FROM clientes WHERE id_de_cliente = " + id_cliente_a_consultar);
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


        String[] columnasTabla = TablasRamservice.obtener_nombres_columnas("CLIENTES");;
        JTable tabla_clientes = new JTable();

        DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTabla,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
            sp.setSize(900,400);
            sp.setLocation(50,200+44);
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
        JLabel autoIdLbl = new JLabel("ID de auto seleccionado: " + id_auto_consultar);
        autoIdLbl.setFont(new Font("Arial", Font.BOLD, 24));
        autoIdLbl.setSize(700,50);
        autoIdLbl.setLocation(50,75);

        JLabel autoNombreLbl = new JLabel(" Auto: " + Autos_Service.obtener_celda("SELECT nombre_auto FROM AUTOS WHERE id_de_auto = " + id_auto_consultar));
        autoNombreLbl.setFont(new Font("Arial", Font.BOLD, 24));
        autoNombreLbl.setSize(700,50);
        autoNombreLbl.setLocation(750,75);


        String[] columnasTabla = TablasRamservice.obtener_nombres_columnas("RENTAS");

        JTable tabla_autos = new JTable();

        DefaultTableModel dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla,"SELECT * FROM rentas WHERE identificador_auto = "+id_auto_consultar);
            tabla_autos.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_autos);

        JScrollPane sp = new JScrollPane(tabla_autos);
            sp.setSize(900,400);
            sp.setLocation(50,185);
            sp.setVisible(true);



        historialClienteSeleccionadoPanel.add(autoIdLbl);
        historialClienteSeleccionadoPanel.add(autoNombreLbl);
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
        correoLbl.setSize(600,50);
        correoLbl.setLocation(xLbl,50);
        historialClienteSeleccionadoPanel.add(correoLbl);

        JLabel telefonoLbl = new JLabel("Teléfono: " + telefono_cliente);
        telefonoLbl.setFont(new Font("Arial", Font.BOLD, 24));
        telefonoLbl.setSize(300,50);
        telefonoLbl.setLocation(xLbl,130);
        historialClienteSeleccionadoPanel.add(telefonoLbl);

        String[] columnasTabla= TablasRamservice.obtener_nombres_columnas("RENTAS");;
        JTable tabla_autos = new JTable();

        DefaultTableModel dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla,"SELECT * FROM rentas WHERE identificador_cliente = "+id_cliente_a_consultar);
            tabla_autos.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_autos);

        JScrollPane sp = new JScrollPane(tabla_autos);
            sp.setSize(900,400);
            sp.setLocation(50,233);
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
        y = 180;

        JLabel fondoDatosDeTarjetaLbl = new JLabel();
        fondoDatosDeTarjetaLbl.setLocation(x-25,y);
        fondoDatosDeTarjetaLbl.setIcon(new ImageIcon("src/img/fondoTarjetaInformacionIcon.png"));
        fondoDatosDeTarjetaLbl.setSize(263,250);

        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        crearClientesPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
        //numTarjetaTF.setBorder(roundedBorder);
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
        //fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);

        fechaCadTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroInicio = (AbstractDocument) fechaCadTF.getDocument();
            documentoFiltroInicio.setDocumentFilter(new Fechas.FechaDocumentFilter());

        crearClientesPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y-5);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        crearClientesPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField();
        //cvvTF.setBorder(roundedBorder);
        cvvTF.setLocation(x,y);
        cvvTF.setSize(100,30);
        crearClientesPNL.add(cvvTF);


        JButton cancelarBtn = new JButton();
        cancelarBtn.setSize(230,35);
        cancelarBtn.setLocation(250,y+distancia_botones_crear_cancelar);
        ImageIcon cancelarIcon = new ImageIcon("src/img/cancelarBoton.png");
        crearClientesPNL.add(fondoDatosDeTarjetaLbl);
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
                String mensaje = Clientes_Service.verificar_campos_de_registro(
                        nombre, apellidos, correo, telefono,
                        numero_de_tarjeta, fecha_de_caducidad,
                        cvv, "");
                if (mensaje.equals("Permitido")){
                    try {
                        Clientes_Service.crear_cliente(nombre, apellidos, correo, telefono);
                        Tarjetas_Service.crear_tarjeta(nombre, apellidos, Clientes_Service.obtener_celda
                                ("SELECT id_de_cliente FROM clientes WHERE correo = '" +correo +"'"),
                                numero_de_tarjeta, fecha_de_caducidad, cvv);

                        JOptionPane.showMessageDialog(null, "Registro exitoso", "Registrado", JOptionPane.INFORMATION_MESSAGE);

                        anterior = actual;
                        actual = "clientes";
                        try {
                            limpiarVentana();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if (mensaje.equals("Permitido sin tarjeta")){
                    try {
                        Clientes_Service.crear_cliente(nombre, apellidos, correo, telefono);
                        JOptionPane.showMessageDialog(null, "Registro exitoso sin tarjeta", "Registrado", JOptionPane.INFORMATION_MESSAGE);

                        anterior = actual;
                        actual = "clientes";
                        try {
                            limpiarVentana();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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

        String nombres_completos [] = concatenarArreglos(Clientes_Service.obtener_columna("SELECT nombre FROM clientes"),Clientes_Service.obtener_columna("SELECT apellido FROM clientes"), " ");
        String ids[] = Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes ORDER BY id_de_cliente");
        String id_nombres[] = concatenarArreglos(ids, nombres_completos, ":");
        for(String idn : ids){
            System.out.println(idn);
        }
        JComboBox idClientesCB = new JComboBox(id_nombres);
        idClientesCB.setSize(226,40);
        idClientesCB.setLocation(400,100);
        editarClientesPNL.add(idClientesCB);

        JButton editarClienteBtn = new JButton();
        editarClienteBtn.setSize(226,31);
        editarClienteBtn.setLocation(400, 150);

        ImageIcon editarClienteBotonIcon = new ImageIcon("src/img/editarClienteBoton.png");
        editarClienteBtn.setIcon(editarClienteBotonIcon);
        editarClientesPNL.add(editarClienteBtn);
        editarClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarClienteSeleccionado";
                id_cliente_a_editar = Integer.parseInt(ids[idClientesCB.getSelectedIndex()]);

                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                repaint();
                revalidate();
            }
        });


        String[] columnasTablaClientes = TablasRamservice.obtener_nombres_columnas("CLIENTES");;
        JTable tabla_clientes = new JTable();

        DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTablaClientes,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);
        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
            sp.setSize(900,400);
            sp.setLocation(50,233);
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
        int distancia_vertical_botones = 300;
        int yOriginal = 50;
        int y = yOriginal;
        JLabel nombresLbl = new JLabel("Nombres");
        nombresLbl.setLocation(x,y);
        nombresLbl.setFont(new Font("Arial", Font.BOLD, 16));
        nombresLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(nombresLbl);
        y += 50;
        JTextField nombresTF = new JTextField(Clientes_Service.obtener_celda("SELECT nombre FROM clientes WHERE id_de_cliente = " + id_cliente_a_editar));
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
        JTextField apellidosTF = new JTextField(Clientes_Service.obtener_celda("SELECT apellido FROM clientes WHERE id_de_cliente = " + id_cliente_a_editar));
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
        JTextField telefonoTF = new JTextField(Clientes_Service.obtener_celda("SELECT telefono FROM clientes WHERE id_de_cliente = " + id_cliente_a_editar));
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
        JTextField correoTF = new JTextField(Clientes_Service.obtener_celda("SELECT correo FROM clientes WHERE id_de_cliente = " + id_cliente_a_editar));
        correoTF.setBorder(roundedBorder);
        correoTF.setLocation(x,y);
        correoTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(correoTF);

        x = x*3;
        y = 50;

        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField(Tarjetas_Service.obtener_celda("SELECT numero_de_tarjeta FROM tarjetas_de_clientes WHERE identificador_cliente = " + id_cliente_a_editar));
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
        fechaCadTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroInicio = (AbstractDocument) fechaCadTF.getDocument();
            documentoFiltroInicio.setDocumentFilter(new Fechas.FechaDocumentFilter());
            editarClienteSeleccionadoPNL.add(fechaCadTF);
        fechaCadTF.setText(Tarjetas_Service.obtener_celda("SELECT fecha_de_caducidad FROM tarjetas_de_clientes WHERE identificador_cliente = " + id_cliente_a_editar));

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField(Tarjetas_Service.obtener_celda("SELECT cvv FROM tarjetas_de_clientes WHERE identificador_cliente = " + id_cliente_a_editar));
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
                String mensaje = Clientes_Service.verificar_campos_de_registro(
                        nombre, apellidos, correo, telefono,
                        numero_de_tarjeta, fecha_de_caducidad,
                        cvv, " WHERE id_de_cliente <> " + id_cliente_a_editar);
                if (mensaje.equals("Permitido")){
                    try {

                        Clientes_Service.editar_cliente(nombre, apellidos, correo, telefono, id_cliente_a_editar);
                        // agregar una funcion que detecte que si existe un tarjeta registrada con ese id de usuario se edite la tarjeta
                        // jeje es tu chamba yo de mañana q estara unu porq la dopamina se le acabo mientras dormia
                        if (Tarjetas_Service.existencia_de_tarjeta_con_usuario(id_cliente_a_editar)){
                            Tarjetas_Service.editar_tarjeta(nombre, apellidos, String.valueOf(id_cliente_a_editar),
                                    numero_de_tarjeta, fecha_de_caducidad, cvv);
                        }
                        else{
                            Tarjetas_Service.crear_tarjeta(nombre, apellidos, String.valueOf(id_cliente_a_editar),
                                    numero_de_tarjeta, fecha_de_caducidad, cvv);
                        }
                        JOptionPane.showMessageDialog(null, "Edición exitosa", "Registrado", JOptionPane.INFORMATION_MESSAGE);
                        anterior = actual;
                        actual = "clientes";
                        try {
                            limpiarVentana();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(mensaje.equals("Permitido sin tarjeta")){
                    try {

                        Clientes_Service.editar_cliente(nombre, apellidos, correo, telefono, id_cliente_a_editar);
                        JOptionPane.showMessageDialog(null, "Edición exitosa a cliente sin tarjeta", "Registrado", JOptionPane.INFORMATION_MESSAGE);
                        anterior = actual;
                        actual = "clientes";
                        try {
                            limpiarVentana();
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
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

        String nombres_Clientes[] = concatenarArreglos
                (Clientes_Service.obtener_columna("SELECT nombre FROM clientes"),
                        Clientes_Service.obtener_columna("SELECT apellido FROM clientes"), " ");
        String id_cliente [] = Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes  ORDER BY id_de_cliente");
        String id_nombres [] = concatenarArreglos(id_cliente,nombres_Clientes, " : ");
        JComboBox idClientesCB = new JComboBox(id_nombres);
        idClientesCB.setSize(226,40);
        idClientesCB.setLocation(400,100);

        JButton eliminarClienteBtn = new JButton();
        eliminarClienteBtn.setSize(226,31);
        eliminarClienteBtn.setLocation(400, 150);

        ImageIcon eliminarIcon = new ImageIcon("src/img/eliminarClienteBoton.png");
        eliminarClienteBtn.setIcon(eliminarIcon);
        String[] columnasTablaClientes = TablasRamservice.obtener_nombres_columnas("CLIENTES");;

        JTable tabla_clientes = new JTable();
            DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTablaClientes,"SELECT * FROM clientes");
            tabla_clientes.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_clientes);

        JScrollPane sp = new JScrollPane(tabla_clientes);
            sp.setSize(900,400);
            sp.setLocation(50,233);
            sp.setVisible(true);

        eliminarClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_cl [] = (String.valueOf(idClientesCB.getSelectedItem()).split(":"));
                id_cl[0] = id_cl[0].replace(" ", "");
                Clientes_Service.eliminar_cliente(Integer.parseInt(id_cl[0]));
                try {
                    if (Tarjetas_Service.existencia_de_tarjeta_con_usuario(Integer.parseInt(id_cl[0]))){
                        Tarjetas_Service.eliminar_tarjeta(Integer.parseInt(Tarjetas_Service.obtener_celda("SELECT id_de_tarjeta FROM tarjetas_de_clientes WHERE identificador_cliente = '" + id_cl[0] + "'")));
                    };
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                //idClientesCB.removeItem(idClientesCB.getSelectedItem());
                DefaultTableModel dtm = Clientes_Service.crear_dtm_de_clientes(columnasTablaClientes,"SELECT * FROM clientes");
                    tabla_clientes.setModel(dtm);
                    TablasRamservice.crear_tabla(tabla_clientes);
                    idClientesCB.removeItemAt(idClientesCB.getSelectedIndex());
                repaint();
                revalidate();

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
        int yBtn = 50;
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
        String[] columnasTabla =TablasRamservice.obtener_nombres_columnas("RENTAS");;

        JTable tabla_rentas = new JTable();
            tabla_rentas.setModel(Renta_Service.crear_dtm_de_rentas(columnasTabla,"SELECT * FROM rentas"));

        TablasRamservice.modificar_dimensiones_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
            sp.setSize(900,400);
            sp.setLocation(50,heightBtn+yBtn+25);
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
        bienvenido.setLocation(385, 20);
        consultarCarPNL.add(bienvenido);


        String nombres_completos [] = Clientes_Service.obtener_columna("SELECT nombre_auto FROM autos");
        String ids_renta [] = Clientes_Service.obtener_columna("SELECT id_de_auto FROM autos");
        String id_nombre_concatenado[] = concatenarArreglos(ids_renta, nombres_completos, " : ");
        JComboBox id_auto_a_consultar_CB = new JComboBox(id_nombre_concatenado);
        id_auto_a_consultar_CB.setSize(226,40);
        id_auto_a_consultar_CB.setLocation(400,100);
        consultarCarPNL.add(id_auto_a_consultar_CB);

        JButton consultarHistorialAutoBtn = new JButton();
        consultarHistorialAutoBtn.setSize(226,31);
        consultarHistorialAutoBtn.setLocation(400, 150);
        ImageIcon consultarHistorialIcon = new ImageIcon("src/img/consultarHistorialAutoIcon.png");
        consultarHistorialAutoBtn.setIcon(consultarHistorialIcon);
        consultarCarPNL.add(consultarHistorialAutoBtn);
        consultarHistorialAutoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "consultarAutomovilSeleccionado";
                id_auto_consultar = Integer.parseInt(ids_renta[id_auto_a_consultar_CB.getSelectedIndex()]);
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
        String[] columnasTabla = TablasRamservice.obtener_nombres_columnas("RENTAS");;
        JTable tabla_rentas = new JTable();

        DefaultTableModel dtm = new DefaultTableModel();
            dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
            tabla_rentas.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
        sp.setSize(900,400);
        sp.setLocation(50,244);
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
        int y = 20;

        int xSeparador = 340;
        int ySeparador = 50;

        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.BOLD, 16));
        carroLbl.setSize(200,40);
        crearRentaPNL.add(carroLbl);

        JLabel separadorLbl = new JLabel();
        separadorLbl.setSize(10,200);
        separadorLbl.setLocation(xSeparador,ySeparador);
        separadorLbl.setIcon(new ImageIcon("src/img/lineaSeparadorIcon.png"));
        crearRentaPNL.add(separadorLbl);

        y += 50;

        String nombres_autos [] = Autos_Service.obtener_columna("SELECT nombre_auto FROM autos");
        String ids_autos[] = Autos_Service.obtener_columna("SELECT id_de_auto FROM autos ORDER BY id_de_auto");
        String id_nombres_autos[] = concatenarArreglos(ids_autos, nombres_autos, " : ");

        JComboBox carros_id_con_nombre_CB = new JComboBox(id_nombres_autos);
        carros_id_con_nombre_CB.setLocation(x,y);
        //Map<Integer,String> hashMapCarrosId = Autos_Service.obtener_id_nombre_auto();
        //carros_id_con_nombre_CB.setModel(generar_combobox_contenido(hashMapCarrosId));
        carros_id_con_nombre_CB.setSize(200,30);
        crearRentaPNL.add(carros_id_con_nombre_CB);

        y += 50;
        JLabel idClienteLbl = new JLabel("Id Cliente");
        idClienteLbl.setLocation(x,y);
        idClienteLbl.setFont(new Font("Arial", Font.BOLD, 16));
        idClienteLbl.setSize(200,40);
        crearRentaPNL.add(idClienteLbl);
        y += 50;

        String nombres_completos [] = concatenarArreglos(Clientes_Service.obtener_columna("SELECT nombre FROM clientes"),Clientes_Service.obtener_columna("SELECT apellido FROM clientes"), " ");
        String ids[] = Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes ORDER BY id_de_cliente");
        String id_nombres[] = concatenarArreglos(ids, nombres_completos, " : ");

        JComboBox id_cliente_con_nombre_CB = new JComboBox(id_nombres);
        //Map<Integer,String> hashMapClientesId = Clientes_Service.seleccionar_clientes_map();
        //id_cliente_con_nombre_CB.setModel(hashMapClientesId);
        id_cliente_con_nombre_CB.setLocation(x,y);
        id_cliente_con_nombre_CB.setSize(200,30);
        crearRentaPNL.add(id_cliente_con_nombre_CB);

        x +=300;
        y = 20;

        JLabel separadorDosLbl = new JLabel();
        separadorDosLbl.setSize(10,200);
        separadorDosLbl.setLocation(xSeparador+300,ySeparador);
        separadorDosLbl.setIcon(new ImageIcon("src/img/lineaSeparadorIcon.png"));
        crearRentaPNL.add(separadorDosLbl);

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

        x +=300;
        y = 20;

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
        ccvLbl.setLocation(x,y-10);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        crearRentaPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField("");
        cvvTF.setBorder(roundedBorder);
        cvvTF.setLocation(x,y);
        cvvTF.setSize(100,30);
        crearRentaPNL.add(cvvTF);

        x = 250;
        y = 300;

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


        x = costoEstimadoVisualLbl.getWidth()+x+50;
        y = 320;

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

        fechaCadTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroCad = (AbstractDocument) fechaCadTF.getDocument();
            documentoFiltroCad.setDocumentFilter(new Fechas.FechaDocumentFilter());


        id_cliente_con_nombre_CB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int identificador_cliente = Integer.parseInt(ids[id_cliente_con_nombre_CB.getSelectedIndex()]);
                numTarjetaTF.setText(Tarjetas_Service.obtener_celda("SELECT numero_de_tarjeta FROM tarjetas_de_clientes WHERE identificador_cliente = '" + identificador_cliente +"'"));
                fechaCadTF.setText(
                        Tarjetas_Service.obtener_celda("SELECT fecha_de_caducidad FROM tarjetas_de_clientes WHERE identificador_cliente = '" +identificador_cliente+"'"));
            }
        });
        calcularCostoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id_auto = Integer.parseInt(ids_autos[carros_id_con_nombre_CB.getSelectedIndex()]);
                double costo_auto = Double.parseDouble(Autos_Service.obtener_celda("SELECT costo FROM autos WHERE id_de_auto = " + id_auto));
                int dias = Fechas.getDias_De_Renta(fechaInicioTF.getText(), fechaDeDevolucionTF.getText());
                String costo_total = String.valueOf(costo_auto*dias);
                if (costo_auto*dias > 0 && Fechas.verificarLegalidadDeFechas(fechaInicioTF.getText(), fechaDeDevolucionTF.getText(), "RENTAR")){
                    costoEstimadoVisualLbl.setText(costo_total+ " Pesos MXN");
                }
            }
        });
        crearRentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cliente_elegido = (String) id_cliente_con_nombre_CB.getSelectedItem();

            String cliente_elegido_id_nombre [] = cliente_elegido.split(":");
                int identificador_cliente = Integer.parseInt(ids[id_cliente_con_nombre_CB.getSelectedIndex()]);
                String nombre_cliente = cliente_elegido_id_nombre[1];
                String cliente = nombre_cliente;
                int id_auto = Integer.parseInt(ids_autos[carros_id_con_nombre_CB.getSelectedIndex()]);
                String nombre_auto = nombres_autos[carros_id_con_nombre_CB.getSelectedIndex()];
            String fecha_de_renta = fechaInicioTF.getText();
            String fecha_de_devolucion = fechaDeDevolucionTF.getText();
            String numero_tarjeta = numTarjetaTF.getText();
            String fecha_caducidad = fechaCadTF.getText();
            String cvv = cvvTF.getText();
                try {
                    String estado_de_registro = Renta_Service.comprobar_fechas(id_auto, fecha_de_renta, fecha_de_devolucion, fecha_caducidad, numero_tarjeta, cvv);
                    switch (estado_de_registro){
                        case "Permitido":
                            Renta_Service.crearRenta(
                                identificador_cliente, cliente,
                                id_auto, nombre_auto, fecha_de_renta,
                                fecha_de_devolucion, numero_tarjeta, fecha_caducidad, cvv
                            );
                            JOptionPane.showMessageDialog(null, "Renta creada", "Bien!", JOptionPane.INFORMATION_MESSAGE);
                            anterior = actual;
                            actual = "rentas";
                            try {
                                limpiarVentana();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
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
        bienvenido.setLocation(415, 20);
        editarRentaPNL.add(bienvenido);

        String nombres_completos [] = Clientes_Service.obtener_columna("SELECT cliente FROM rentas");
        String ids[] = Renta_Service.obtener_columna("SELECT id_de_renta FROM rentas ORDER BY id_de_renta");
        String id_nombres[] = concatenarArreglos(ids, nombres_completos, " : ");

        JComboBox id_rentas_CB = new JComboBox(id_nombres);
        id_rentas_CB.setSize(226,40);
        id_rentas_CB.setLocation(400,90);

        JButton editarRentaBtn = new JButton();
        editarRentaBtn.setSize(226,31);
        editarRentaBtn.setLocation(400, 140);
        ImageIcon editarIcon = new ImageIcon("src/img/editarRentaBoton.png");
        editarRentaBtn.setIcon(editarIcon);
        editarRentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarRentaSeleccionada";
                id_renta_editar = Integer.parseInt(ids[id_rentas_CB.getSelectedIndex()]);
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


        String[] columnasTabla = TablasRamservice.obtener_nombres_columnas("RENTAS");;
        JTable tabla_rentas = new JTable();
        DefaultTableModel dtm = new DefaultTableModel();
            dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
            tabla_rentas.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
            sp.setSize(900,400);
            sp.setLocation(50,233);
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
        int xSeparador = 340;
        int ySeparador = 50;
        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.BOLD, 16));
        carroLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(carroLbl);


        JLabel separadorLbl = new JLabel();
        separadorLbl.setSize(10,200);
        separadorLbl.setLocation(xSeparador,ySeparador);
        separadorLbl.setIcon(new ImageIcon("src/img/lineaSeparadorIcon.png"));
        editarRentaSeleccionadaPNL.add(separadorLbl);

        y += 50;

        String nombres_autos [] = Autos_Service.obtener_columna("SELECT nombre_auto FROM autos");
        String ids_autos[] = Autos_Service.obtener_columna("SELECT id_de_auto FROM autos ORDER BY id_de_auto");
        String id_nombres_autos[] = concatenarArreglos(ids_autos, nombres_autos, " : ");
        String id_auto = Renta_Service.obtener_celda("SELECT identificador_auto FROM rentas WHERE id_de_renta = " + id_de_renta_a_editar);
        int index_carros = indexSeleccionado(ids_autos, String.valueOf(id_auto));
        JComboBox carros_id_con_nombre_CB = new JComboBox(id_nombres_autos);
        carros_id_con_nombre_CB.setLocation(x,y);
        carros_id_con_nombre_CB.setSelectedIndex(index_carros);
        //Map<Integer,String> hashMapCarrosId = Autos_Service.obtener_id_nombre_auto();
        //carros_id_con_nombre_CB.setModel(generar_combobox_contenido(hashMapCarrosId));
        carros_id_con_nombre_CB.setSize(200,30);
        editarRentaSeleccionadaPNL.add(carros_id_con_nombre_CB);

        y += 50;

        JLabel idClienteLbl = new JLabel("Id Cliente");
        idClienteLbl.setLocation(x,y);
        idClienteLbl.setFont(new Font("Arial", Font.BOLD, 16));
        idClienteLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(idClienteLbl);

        y += 50;

        String nombres_completos [] = concatenarArreglos
                (Clientes_Service.obtener_columna("SELECT nombre FROM clientes"),
                Clientes_Service.obtener_columna("SELECT apellido FROM clientes"), " ");
        String ids[] = Clientes_Service.obtener_columna("SELECT id_de_cliente FROM clientes ORDER BY id_de_cliente");
        String id_nombres[] = concatenarArreglos(ids, nombres_completos, " : ");
        String id_cliente = Clientes_Service.obtener_celda("SELECT identificador_cliente FROM rentas WHERE id_de_renta = " + id_de_renta_a_editar);
        int index_cliente = indexSeleccionado(ids,String.valueOf(id_cliente));
        JComboBox id_cliente_con_nombre_CB = new JComboBox(id_nombres);

        id_cliente_con_nombre_CB.setSelectedIndex(index_cliente);
        id_cliente_con_nombre_CB.setLocation(x,y);
        id_cliente_con_nombre_CB.setSize(200,30);
        editarRentaSeleccionadaPNL.add(id_cliente_con_nombre_CB);

        x +=300;
        y = 20;

        JLabel separadorDosLbl = new JLabel();
        separadorDosLbl.setSize(10,200);
        separadorDosLbl.setLocation(xSeparador+300,ySeparador);
        separadorDosLbl.setIcon(new ImageIcon("src/img/lineaSeparadorIcon.png"));
        editarRentaSeleccionadaPNL.add(separadorDosLbl);

        JLabel fechaInicioLbl = new JLabel("Fecha de inicio");
        fechaInicioLbl.setLocation(x,y);
        fechaInicioLbl.setFont(new Font("Arial", Font.BOLD, 16));
        fechaInicioLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(fechaInicioLbl);
        y += 50;
        JTextField fechaInicioTF = new JTextField(Renta_Service.obtener_celda("SELECT fecha_de_renta FROM rentas WHERE id_de_renta = " + id_de_renta_a_editar));
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
        JTextField fechaDeDevolucionTF = new JTextField("qe");
        fechaDeDevolucionTF.setBorder(roundedBorder);
        fechaDeDevolucionTF.setLocation(x,y);
        fechaDeDevolucionTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(fechaDeDevolucionTF);

        x += 300;
        y = 20;

        JLabel numTarjetaLbl = new JLabel("Número de tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.BOLD, 16));
        numTarjetaLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(numTarjetaLbl);
        y += 50;

        //String cliente_elegido = (String) id_cliente_con_nombre_CB.getSelectedItem();
        //String cliente_elegido_id_nombre [] = cliente_elegido.split(":");

        String identificador_cliente = Renta_Service.obtener_celda("SELECT identificador_cliente FROM rentas WHERE id_de_renta = " + id_de_renta_a_editar);

        JTextField numTarjetaTF = new JTextField((Clientes_Service.obtener_celda("SELECT numero_tarjeta from rentas WHERE id_de_renta = " + id_de_renta_a_editar)));
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
        JTextField fechaCadTF = new JTextField((Renta_Service.obtener_celda("SELECT fecha_caducidad from rentas WHERE id_de_renta = " + id_de_renta_a_editar)));
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CVV");
        ccvLbl.setLocation(x,y-10);
        ccvLbl.setFont(new Font("Arial", Font.BOLD, 16));
        ccvLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(ccvLbl);

        x += 100;

        JTextField cvvTF = new JTextField(Renta_Service.obtener_celda("SELECT cvv from rentas WHERE id_de_renta = " + id_de_renta_a_editar));
        cvvTF.setBorder(roundedBorder);
        cvvTF.setLocation(x,y);
        cvvTF.setSize(100,30);
        editarRentaSeleccionadaPNL.add(cvvTF);


        x = 250;
        y = 300;

        JLabel costoEstimadoLbl = new JLabel("Costo estimado", JLabel.CENTER);
        costoEstimadoLbl.setLocation(x,y);
        costoEstimadoLbl.setFont(new Font("Arial", Font.BOLD, 16));
        costoEstimadoLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(costoEstimadoLbl);

        y += 50;

        JLabel costoEstimadoVisualLbl = new JLabel((Clientes_Service.obtener_celda
                ("SELECT costo from rentas WHERE id_de_renta = " + id_de_renta_a_editar)) + " Pesos MXN");
        costoEstimadoVisualLbl.setHorizontalAlignment(JLabel.CENTER);
        costoEstimadoVisualLbl.setOpaque(true);
        costoEstimadoVisualLbl.setBackground(Color.pink);
        costoEstimadoVisualLbl.setLocation(x+15,y);
        costoEstimadoVisualLbl.setSize(193,102);
        editarRentaSeleccionadaPNL.add(costoEstimadoVisualLbl);

        x = costoEstimadoVisualLbl.getWidth()+x+50;
        y = 320;

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
                int identificador_cliente = Integer.parseInt(ids[id_cliente_con_nombre_CB.getSelectedIndex()]);
                numTarjetaTF.setText(Tarjetas_Service.obtener_celda("SELECT numero_de_tarjeta FROM tarjetas_de_clientes WHERE identificador_cliente = '" + identificador_cliente +"'"));
                fechaCadTF.setText(
                        Tarjetas_Service.obtener_celda("SELECT fecha_de_caducidad FROM tarjetas_de_clientes WHERE identificador_cliente = '" +identificador_cliente+"'"));
            }
        });
        fechaInicioTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroInicio = (AbstractDocument) fechaInicioTF.getDocument();
            documentoFiltroInicio.setDocumentFilter(new Fechas.FechaDocumentFilter());

        fechaInicioTF.setText((Clientes_Service.obtener_celda
                ("SELECT fecha_de_renta from rentas WHERE id_de_renta = " + id_de_renta_a_editar)));

        fechaDeDevolucionTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroFinal = (AbstractDocument) fechaDeDevolucionTF.getDocument();
            documentoFiltroFinal.setDocumentFilter(new Fechas.FechaDocumentFilter());

        fechaDeDevolucionTF.setText((Clientes_Service.obtener_celda
                ("SELECT fecha_de_devolucion from rentas WHERE id_de_renta = " + id_de_renta_a_editar)));
        fechaCadTF.setDocument(new Fechas.NumericDocument());
            AbstractDocument documentoFiltroCad = (AbstractDocument) fechaCadTF.getDocument();
            documentoFiltroCad.setDocumentFilter(new Fechas.FechaDocumentFilter());

        fechaCadTF.setText((Tarjetas_Service.obtener_celda
                ("SELECT fecha_caducidad FROM rentas WHERE id_de_renta = " + id_de_renta_a_editar)));
        calcularCostoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String auto_elegido = nombres_autos[carros_id_con_nombre_CB.getSelectedIndex()];
                int id_auto = Integer.parseInt(ids_autos[carros_id_con_nombre_CB.getSelectedIndex()]);
                double costo_auto = Double.parseDouble(Autos_Service.obtener_celda("SELECT costo FROM autos WHERE id_de_auto = " + id_auto));
                int dias = Fechas.getDias_De_Renta(fechaInicioTF.getText(), fechaDeDevolucionTF.getText());
                String costo_total = String.valueOf(costo_auto*dias);
                if (costo_auto*dias > 0 && Fechas.verificarLegalidadDeFechas(fechaInicioTF.getText(), fechaDeDevolucionTF.getText(), "RENTAR")){
                    costoEstimadoVisualLbl.setText(costo_total+ " Pesos MXN");
                }
                System.out.println(costo_total);
            }
        });
        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre_cliente = nombres_completos[id_cliente_con_nombre_CB.getSelectedIndex()];
                String cliente = nombre_cliente;
                String auto_elegido = (String) carros_id_con_nombre_CB.getSelectedItem();
                int identificador_cliente = Integer.parseInt(ids[id_cliente_con_nombre_CB.getSelectedIndex()]);
                int id_auto = Integer.parseInt(ids[carros_id_con_nombre_CB.getSelectedIndex()]);
                String nombre_auto = nombres_autos[carros_id_con_nombre_CB.getSelectedIndex()];
                String fecha_de_renta = fechaInicioTF.getText();
                String fecha_de_devolucion = fechaDeDevolucionTF.getText();
                String numero_tarjeta = numTarjetaTF.getText();
                String fecha_caducidad = fechaCadTF.getText();
                String cvv = cvvTF.getText();
                try {
                    String estado_de_registro = Renta_Service.comprobar_fechas_editar(id_de_renta_a_editar,id_auto, fecha_de_renta, fecha_de_devolucion,fecha_caducidad, numero_tarjeta, cvv);
                    switch (estado_de_registro){
                        case "Permitido":
                            JOptionPane.showMessageDialog(null, "Edición exitosa", "Bien!", JOptionPane.INFORMATION_MESSAGE);
                            Renta_Service.editarRenta(id_de_renta_a_editar,
                                    identificador_cliente, cliente,
                                    id_auto, nombre_auto, fecha_de_renta,
                                    fecha_de_devolucion, numero_tarjeta, fecha_caducidad, cvv
                            );
                            anterior = actual;
                            actual = "rentas";
                            try {
                                limpiarVentana();
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
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
        descripcionLbl.setLocation(400,20);


        String nombres_completos [] = Renta_Service.obtener_columna("SELECT cliente FROM rentas");

        String ids[] = Clientes_Service.obtener_columna("SELECT id_de_renta FROM rentas ORDER BY id_de_renta");

        String id_nombres[] = concatenarArreglos(ids, nombres_completos, " : ");
        JComboBox idRentasCB = new JComboBox(id_nombres);
        System.out.println(ids[idRentasCB.getSelectedIndex()]);
        idRentasCB.setSize(226,40);
        idRentasCB.setLocation(400,80);

        JButton eliminarRentaBtn = new JButton();
        eliminarRentaBtn.setSize(226,31);
        eliminarRentaBtn.setLocation(400, 130);
        ImageIcon eliminarIcon = new ImageIcon("src/img/eliminarRentaBoton.png");
        eliminarRentaBtn.setIcon(eliminarIcon);

        String[] columnasTabla = TablasRamservice.obtener_nombres_columnas("RENTAS");
        JTable tabla_rentas = new JTable();

        DefaultTableModel dtm = new DefaultTableModel();
            dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
            tabla_rentas.setModel(dtm);

        TablasRamservice.crear_tabla(tabla_rentas);

        JScrollPane sp = new JScrollPane(tabla_rentas);
            sp.setSize(900,400);
            sp.setLocation(50,233);
            sp.setVisible(true);

        eliminarRentaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_cl [] = (String.valueOf(idRentasCB.getSelectedItem()).split(":"));
                id_cl[0] = id_cl[0].replace(" ", "");
                Renta_Service.borrar_renta(Integer.parseInt(id_cl[0]));

                DefaultTableModel dtm = new DefaultTableModel();
                    dtm = Renta_Service.crear_dtm_de_rentas(columnasTabla, "SELECT * FROM rentas");
                    tabla_rentas.setModel(dtm);

                TablasRamservice.crear_tabla(tabla_rentas);

                idRentasCB.removeItemAt(idRentasCB.getSelectedIndex());
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

        JButton crearCategorias = new JButton("Crear categoría");
        crearCategorias.setBackground(Color.decode("#38b6ff"));
        crearCategorias.setSize(300,30);
        crearCategorias.setLocation(350,20);
        crearCategorias.setFocusPainted(false);
        crearCategorias.setForeground(Color.white);
        crearCategorias.setBorderPainted(false);
        crearCategorias.setFont(new Font("Tahoma", Font.BOLD, 18));
        categoriasPanel.add(crearCategorias);


        JPanel panelCat = new JPanel();
        panelCat.setOpaque(true);
        panelCat.setLayout(null);
        panelCat.setBackground(Color.LIGHT_GRAY);
        panelCat.setBounds(panelCatX, panelCatY, 826, 232);
        categoriasPanel.add(panelCat);


     //   panelY+=314;



        JTextArea sedanLujo = new JTextArea();
        sedanLujo.setForeground(Color.BLACK);
        sedanLujo.setOpaque(false);
        sedanLujo.setEditable(false);
        sedanLujo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        sedanLujo.setFont(new Font("Tahoma", Font.BOLD, 18));
        sedanLujo.setText("Esta categoría incluye vehículos elegantes y cómodos, ideales para clientes que buscan un viaje sofisticado y de alto nivel");
        sedanLujo.setBounds(268, 54, 291, 115);
        sedanLujo.setLineWrap(true);
        panelCat.add(sedanLujo);



        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Color.decode("#BF0000"));
        btnEliminar.setBounds(585, 131, 226, 53);
        panelCat.add(btnEliminar);

        JButton consultarCat = new JButton("Consultar");

        consultarCat.setFocusPainted(false);
        consultarCat.setForeground(Color.WHITE);
        consultarCat.setFont(new Font("Tahoma", Font.BOLD, 16));
        consultarCat.setBorderPainted(false);
        consultarCat.setBackground(Color.BLACK);
        consultarCat.setBounds(585, 41, 226, 55);
        panelCat.add(consultarCat);

        JLabel nombCat = new JLabel("Sedanes de lujo");
        nombCat.setForeground(Color.BLACK);
        nombCat.setHorizontalAlignment(SwingConstants.CENTER);
        nombCat.setFont(new Font("Tahoma", Font.BOLD, 17));
        nombCat.setBounds(56, 175, 166, 30);
        panelCat.add(nombCat);

        JLabel imgCat = new JLabel("");
        imgCat.setIcon(new ImageIcon("src/img/image 12.png"));
        imgCat.setBounds(32, 29, 226, 140);
        panelCat.add(imgCat);

        consultarCat.addActionListener(new ActionListener() {
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




        return categoriasPanel;
    }

    public JPanel consultarCategorias() {
        anterior = "categorias";

        JPanel consultarCat = new JPanel();
        consultarCat.setForeground(Color.BLACK);
        consultarCat.setSize(1000, 681);
        consultarCat.setLocation(0, 80);
        consultarCat.setLayout(null);
        consultarCat.setBackground(Color.decode("#FFFFFF"));

        JButton imgConsultarCat = new JButton("");
        imgConsultarCat.setBorderPainted(false);
        imgConsultarCat.setContentAreaFilled(false);
        imgConsultarCat.setFocusPainted(false);
        imgConsultarCat.setHorizontalAlignment(SwingConstants.CENTER);
        imgConsultarCat.setIcon(new ImageIcon("src/img/Group 56 (1).png"));
        imgConsultarCat.setBounds(652, 424, 271, 92);
        consultarCat.add(imgConsultarCat);

        JTextArea camDescCat = new JTextArea("");
        camDescCat.setBorder(null);
        camDescCat.setLineWrap(true);
        camDescCat.setBackground(SystemColor.menu);
        camDescCat.setBounds(670, 173, 236, 219);
        consultarCat.add(camDescCat);

        JTextField cambNomCat = new JTextField("");
        cambNomCat.setBorder(null);
        cambNomCat.setBackground(SystemColor.menu);
        cambNomCat.setBounds(670, 84, 236, 29);
        consultarCat.add(cambNomCat);
        cambNomCat.setColumns(10);

        JLabel descCat = new JLabel("Descripción");
        descCat.setFont(new Font("Tahoma", Font.BOLD, 14));
        descCat.setBounds(670, 144, 154, 23);
        consultarCat.add(descCat);

        JLabel nomActCat = new JLabel("Nombre de categoría");
        nomActCat.setFont(new Font("Tahoma", Font.BOLD, 14));
        nomActCat.setBounds(670, 59, 154, 23);
        consultarCat.add(nomActCat);

        JTextArea descActCat = new JTextArea();
        descActCat.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        descActCat.setOpaque(false);
        descActCat.setLineWrap(true);
        descActCat.setForeground(Color.BLACK);
        descActCat.setFont(new Font("Tahoma", Font.PLAIN, 18));
        descActCat.setBounds(47, 469, 497, 152);
        consultarCat.add(descActCat);

        JLabel imgActCat = new JLabel("");
        imgActCat.setHorizontalAlignment(SwingConstants.CENTER);
        imgActCat.setIcon(new ImageIcon("src/img/image 15.png"));
        imgActCat.setBounds(68, 134, 449, 269);
        consultarCat.add(imgActCat);

        JButton editBotCat = new JButton("Editar");
        editBotCat.setForeground(Color.WHITE);
        editBotCat.setFont(new Font("Tahoma", Font.BOLD, 16));
        editBotCat.setFocusPainted(false);
        editBotCat.setBorderPainted(false);
        editBotCat.setBackground(Color.decode("#38B6FF"));
        editBotCat.setBounds(657, 547, 260, 53);
        consultarCat.add(editBotCat);

        JLabel cuadroEditCat = new JLabel();
        cuadroEditCat.setVerticalAlignment(SwingConstants.BOTTOM);
        cuadroEditCat.setOpaque(true);
        cuadroEditCat.setHorizontalAlignment(SwingConstants.CENTER);
        cuadroEditCat.setBackground(SystemColor.controlShadow);
        cuadroEditCat.setBounds(632, 24, 307, 597);
        consultarCat.add(cuadroEditCat);

        JLabel nombreActCat = new JLabel("Sedanes de lujo");
        nombreActCat.setFont(new Font("Tahoma", Font.BOLD, 25));
        nombreActCat.setHorizontalAlignment(SwingConstants.CENTER);
        nombreActCat.setBounds(170, 24, 282, 58);
        consultarCat.add(nombreActCat);

        JLabel fondoDesc = new JLabel("");
        fondoDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        fondoDesc.setOpaque(true);
        fondoDesc.setHorizontalAlignment(SwingConstants.CENTER);
        fondoDesc.setBackground(SystemColor.controlShadow);
        fondoDesc.setBounds(35, 460, 517, 154);
        consultarCat.add(fondoDesc);

        return consultarCat;
    }

    public JPanel marcas() {
        anterior = "home";

        JPanel marcasPanel = new JPanel();
        marcasPanel.setForeground(Color.BLACK);
        marcasPanel.setSize(1000, 800);
        marcasPanel.setLocation(0, 80);
        marcasPanel.setLayout(null);
        marcasPanel.setBackground(Color.decode("#FFFFFF"));

        JButton crearMarcas = new JButton("Crear marca");
        crearMarcas.setBackground(Color.decode("#38b6ff"));
        crearMarcas.setSize(300,30);
        crearMarcas.setLocation(350,20);
        crearMarcas.setFocusPainted(false);
        crearMarcas.setForeground(Color.white);
        crearMarcas.setBorderPainted(false);
        crearMarcas.setFont(new Font("Tahoma", Font.BOLD, 18));
        marcasPanel.add(crearMarcas);


        JPanel panelMarc = new JPanel();
        panelMarc.setOpaque(true);
        panelMarc.setLayout(null);
        panelMarc.setBackground(Color.LIGHT_GRAY);
        panelMarc.setBounds(panelMarcX, panelMarcY, 826, 232);
        marcasPanel.add(panelMarc);


    //    panelY+=314;


        JTextArea BMW = new JTextArea();
        BMW.setForeground(Color.BLACK);
        BMW.setOpaque(false);
        BMW.setEditable(false);
        BMW.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        BMW.setFont(new Font("Tahoma", Font.BOLD, 18));
        BMW.setText("BMW es una marca de automóviles de prestigio que se destaca por su rendimiento y estilo. Sus vehículos combinan la deportividad con el lujo y la elegancia");
        BMW.setBounds(268, 54, 291, 130);
        BMW.setLineWrap(true);
        panelMarc.add(BMW);


        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setBackground(Color.decode("#BF0000"));
        btnEliminar.setBounds(585, 131, 226, 53);
        panelMarc.add(btnEliminar);

        JButton consultarMarc = new JButton("Consultar");
        consultarMarc.setFocusPainted(false);
        consultarMarc.setForeground(Color.WHITE);
        consultarMarc.setFont(new Font("Tahoma", Font.BOLD, 16));
        consultarMarc.setBorderPainted(false);
        consultarMarc.setBackground(Color.BLACK);
        consultarMarc.setBounds(585, 41, 226, 55);
        panelMarc.add(consultarMarc);



        JLabel imgMarc = new JLabel("");
        imgMarc.setHorizontalAlignment(SwingConstants.CENTER);
        imgMarc.setIcon(new ImageIcon("src/img/13.png"));
        imgMarc.setBounds(32, 32, 226, 140);
        panelMarc.add(imgMarc);

        consultarMarc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual ="consultarMarcas";
                try {
                    limpiarVentana();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                revalidate();
                repaint();

            }
        });

        return marcasPanel;
    }

    public JPanel consultarMarcas() {
        anterior = "marcas";

        JPanel consultarMarcas = new JPanel();
        consultarMarcas.setForeground(Color.BLACK);
        consultarMarcas.setSize(1000, 681);
        consultarMarcas.setLocation(0, 80);
        consultarMarcas.setLayout(null);
        consultarMarcas.setBackground(Color.decode("#FFFFFF"));

        JButton imgConsultarMarc = new JButton("");
        imgConsultarMarc.setBorderPainted(false);
        imgConsultarMarc.setContentAreaFilled(false);
        imgConsultarMarc.setFocusPainted(false);
        imgConsultarMarc.setHorizontalAlignment(SwingConstants.CENTER);
        imgConsultarMarc.setIcon(new ImageIcon("src/img/Group 56 (1).png"));
        imgConsultarMarc.setBounds(652, 424, 271, 92);
        consultarMarcas.add(imgConsultarMarc);

        JTextArea camDescMarc = new JTextArea("");
        camDescMarc.setBorder(null);
        camDescMarc.setLineWrap(true);
        camDescMarc.setBackground(SystemColor.menu);
        camDescMarc.setBounds(670, 173, 236, 219);
        consultarMarcas.add(camDescMarc);

        JTextField cambNomMarc = new JTextField("");
        cambNomMarc.setBorder(null);
        cambNomMarc.setBackground(SystemColor.menu);
        cambNomMarc.setBounds(670, 84, 236, 29);
        consultarMarcas.add(cambNomMarc);
        cambNomMarc.setColumns(10);

        JLabel descMarc = new JLabel("Descripción");
        descMarc.setFont(new Font("Tahoma", Font.BOLD, 14));
        descMarc.setBounds(670, 144, 154, 23);
        consultarMarcas.add(descMarc);

        JLabel nomActMarc = new JLabel("Nombre de marca");
        nomActMarc.setFont(new Font("Tahoma", Font.BOLD, 14));
        nomActMarc.setBounds(670, 59, 154, 23);
        consultarMarcas.add(nomActMarc);

        JTextArea descActMarc = new JTextArea();
        descActMarc.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.");
        descActMarc.setOpaque(false);
        descActMarc.setLineWrap(true);
        descActMarc.setForeground(Color.BLACK);
        descActMarc.setFont(new Font("Tahoma", Font.PLAIN, 18));
        descActMarc.setBounds(47, 469, 497, 152);
        consultarMarcas.add(descActMarc);

        JLabel imgActMarc = new JLabel("");
        imgActMarc.setHorizontalAlignment(SwingConstants.CENTER);
        imgActMarc.setIcon(new ImageIcon("src/img/bmwlogo.png"));
        imgActMarc.setBounds(79, 107, 456, 298);
        consultarMarcas.add(imgActMarc);

        JButton editBotMarc = new JButton("Editar");
        editBotMarc.setForeground(Color.WHITE);
        editBotMarc.setFont(new Font("Tahoma", Font.BOLD, 16));
        editBotMarc.setFocusPainted(false);
        editBotMarc.setBorderPainted(false);
        editBotMarc.setBackground(Color.decode("#38B6FF"));
        editBotMarc.setBounds(657, 547, 260, 53);
        consultarMarcas.add(editBotMarc);

        JLabel cuadroEditMarc = new JLabel();
        cuadroEditMarc.setVerticalAlignment(SwingConstants.BOTTOM);
        cuadroEditMarc.setOpaque(true);
        cuadroEditMarc.setHorizontalAlignment(SwingConstants.CENTER);
        cuadroEditMarc.setBackground(SystemColor.controlShadow);
        cuadroEditMarc.setBounds(632, 24, 307, 597);
        consultarMarcas.add(cuadroEditMarc);

        JLabel nombreActMarc = new JLabel("BMW");
        nombreActMarc.setFont(new Font("Tahoma", Font.BOLD, 25));
        nombreActMarc.setHorizontalAlignment(SwingConstants.CENTER);
        nombreActMarc.setBounds(170, 24, 282, 58);
        consultarMarcas.add(nombreActMarc);

        JLabel fondoDesc = new JLabel("");
        fondoDesc.setVerticalAlignment(SwingConstants.BOTTOM);
        fondoDesc.setOpaque(true);
        fondoDesc.setHorizontalAlignment(SwingConstants.CENTER);
        fondoDesc.setBackground(SystemColor.controlShadow);
        fondoDesc.setBounds(35, 460, 517, 154);
        consultarMarcas.add(fondoDesc);


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


        for (Map.Entry<Integer, String> entry : hashMap.entrySet()) {
            Integer clave = entry.getKey();
            String valor = entry.getValue();
            String item = clave + ":" + valor;
            comboBoxModel.addElement(item);
        }
        return comboBoxModel;
    }
    public String[] concatenarArreglos(String[] primer_arreglo, String[] segundo_arreglo, String separador){
        String [] arreglo_concatenado = new String[primer_arreglo.length];
        for (int i = 0; i < primer_arreglo.length; i++){
            arreglo_concatenado[i] = primer_arreglo[i] +separador+ segundo_arreglo[i];
        }
        return arreglo_concatenado;
    };
    public int indexSeleccionado(String arreglo[], String id){
        for (int i = 0; i < arreglo.length; i++){
            if (arreglo[i].equals(id)){
                return i;
            }
        }
        return -1;
    }
    public int indexDeItemSeleccionado(String ids [], String id){
        for (int i = 0; i<ids.length; i++){
            if (ids[i].equals(id)){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws SQLException {
        Ventana screen = new Ventana();
    }
}

