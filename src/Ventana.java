import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class Ventana extends JFrame {
    public JPanel panel = null;
    private String anterior = "cargaPantalla";
    private String actual = "login";
    private JLabel panelActualLbl = new JLabel(actual, JLabel.CENTER);
    private Border roundedBorder = new RoundBorder(16, 1, Color.GRAY);

    JPanel menuSuperiorPanel = new JPanel();

    ImageIcon logoEmpresa = new ImageIcon("company.png");

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

        ImageIcon logoRamsesIcon = new ImageIcon("logoRamservice.png");
        JLabel logoRamsesLbl = new JLabel();
        logoRamsesLbl.setIcon(logoRamsesIcon);
        logoRamsesLbl.setSize(logoRamsesIcon.getIconWidth(),logoRamsesIcon.getIconHeight());
        logoRamsesLbl.setLocation(0,-50);
        panelActualLbl.setSize(300, 80);
        panelActualLbl.setLocation(180, 0);
        panelActualLbl.setForeground(Color.blue);
        panelActualLbl.setFont(new Font("Arial", Font.BOLD, 35));
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

        JButton regresarBtn = new JButton("<--");
        regresarBtn.setLocation(940,panelActualLbl.getY()+20);
        regresarBtn.setSize(50,40);
        regresarBtn.setVisible(true);
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tmp = anterior;
                anterior = actual;
                actual = tmp;
                System.out.println(actual);
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        menuSuperiorPanel.setPreferredSize(new Dimension(1000,80));
        menuSuperiorPanel.setBackground(Color.orange);
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
        panelActualLbl.setText(actual);

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
            panel = home();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("vehiculos")) {
            panel = vehiculos();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("clientes")) {
            panel = clientes();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarCliente")) {
            panel = consultarCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("crearCliente")) {
            panel = crearCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarCliente")) {
            panel = editarCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarClienteSeleccionado")) {
            panel = editarClienteSeleccionado();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("eliminarCliente")) {
            panel = eliminarCliente();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("rentas")) {
            panel = rentas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarRenta")) {
            panel = consultarRentas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("crearRenta")) {
            panel = crearRenta();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarRenta")) {
            panel = editarRenta();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("editarRentaSeleccionada")){
            panel = editarRentaSeleccionada();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("eliminarRenta")){
            panel = eliminarRenta();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("categorias")) {
            panel = categorias();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("marcas")) {
            panel = marcas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarVehiculo")) {
            panel = consultarVehiculo();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarCategorias")) {
            panel = consultarCategorias();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }
        if (actual.equals("consultarMarcas")) {
            panel = consultarMarcas();

            this.add(panel,BorderLayout.CENTER);

            this.repaint();
            this.revalidate();
        }

        if (actual.equals("consultarHistorialClienteSeleccionado")) {
            panel = historialClienteSeleccionado();

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
        ImageIcon imag2 = new ImageIcon("car.png");
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
        ImageIcon imag1 = new ImageIcon("copy.png");
        ImageIcon icono1 = new ImageIcon(imag1.getImage().getScaledInstance(imagen1.getWidth(), imagen1.getHeight(), Image.SCALE_DEFAULT));
        imagen1.setIcon(icono1);
        imagen1.setLocation(33, 430);
        loginPanel.add(imagen1);

        return login;
    }

    public JPanel home() {

        menuSuperiorPanel.setVisible(true);
        panelActualLbl.setText("home");
        anterior = actual;
        actual = "home";
        JPanel homePanel = new JPanel();
        homePanel.setSize(1000, 800);
        homePanel.setLocation(0, 0);
        homePanel.setLayout(null);
        homePanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Home", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        homePanel.add(bienvenido);

        JButton logoutBTN = new JButton("Logout");
        logoutBTN.setSize(100, 20);
        logoutBTN.setLocation(320, 250);
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
        vehiculosBTN.setSize(100, 20);
        vehiculosBTN.setLocation(130, 250);
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
        clientesBTN.setSize(100, 20);
        clientesBTN.setLocation(130, 290);
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
        rentasBTN.setSize(100, 20);
        rentasBTN.setLocation(130, 320);
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
        categoriasBTN.setSize(100, 20);
        categoriasBTN.setLocation(130, 360);
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
        marcasBTN.setSize(100, 20);
        marcasBTN.setLocation(130, 390);
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
        panelActualLbl.setText("vehiculos");

        JPanel vehiculosPanel = new JPanel();
        vehiculosPanel.setSize(1000, 800);
        vehiculosPanel.setLocation(0, 0);
        vehiculosPanel.setLayout(null);
        vehiculosPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("vehiculos", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        vehiculosPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100, 20);
        homeBTN.setLocation(130, 390);
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
        consultaBTN.setSize(100, 20);
        consultaBTN.setLocation(260, 390);
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

        JLabel bienvenido = new JLabel("Clientes", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        clientesPanel.add(bienvenido);

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

        JButton consultarBTN = new JButton("Consultar clientes");
        consultarBTN.setSize(widthBtn, heightBtn);
        consultarBTN.setLocation(xBtn, yBtn);
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
        JButton crearBTN = new JButton("Crear clientes");
        crearBTN.setSize(widthBtn, heightBtn);
        crearBTN.setLocation(xBtn, yBtn);
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
        JButton editarBTN = new JButton("Editar clientes");
        editarBTN.setSize(widthBtn, heightBtn);
        editarBTN.setLocation(xBtn, yBtn);
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

        JButton borrarBTN = new JButton("Eliminar clientes");
        borrarBTN.setSize(widthBtn, heightBtn);
        borrarBTN.setLocation(xBtn, yBtn);
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

        String[] columnasTablaClientes = {"Id cliente", "Nombre", "Apellido", "Correo", "Teléfono", "Tarjeta de crédito", "Estado de cuenta"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(525,500);
        sp.setLocation(250,400);
        sp.setVisible(true);
        clientesPanel.add(sp);
        return clientesPanel;
    }

    public JPanel consultarCliente() {
        anterior = actual;
        actual = "consultarCliente";

        JPanel consultarClientePNL = new JPanel();
        consultarClientePNL.setSize(1000, 800);
        consultarClientePNL.setLocation(0, 0);
        consultarClientePNL.setLayout(null);
        consultarClientePNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Consultar Clientes", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarClientePNL.add(bienvenido);

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
        JComboBox idClientesCB = new JComboBox();
        idClientesCB.setSize(150,40);
        idClientesCB.setLocation(400,100);

        JButton consultarHistorialClienteBtn = new JButton("Consultar historial de cliente: ");
        consultarHistorialClienteBtn.setSize(150,50);
        consultarHistorialClienteBtn.setLocation(400, 200);
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

        String[] columnasTablaClientes = {"Id cliente", "Nombre", "Apellido", "Correo", "Teléfono", "Tarjeta de crédito", "Estado de cuenta"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(525,500);
        sp.setLocation(250,400);
        sp.setVisible(true);

        consultarClientePNL.add(idClientesCB);
        consultarClientePNL.add(sp);
        consultarClientePNL.add(consultarHistorialClienteBtn);
        return consultarClientePNL;
    }

    public JPanel historialClienteSeleccionado(){
        JPanel historialClienteSeleccionadoPanel = new JPanel();
        historialClienteSeleccionadoPanel.setSize(1000, 800);
        historialClienteSeleccionadoPanel.setLocation(0, 0);
        historialClienteSeleccionadoPanel.setLayout(null);
        historialClienteSeleccionadoPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Historial de renta del cliente ", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        historialClienteSeleccionadoPanel.add(bienvenido);
        int xLbl = 125;
        JLabel idClienteLbl = new JLabel("Id cliente: ");
        idClienteLbl.setSize(300,50);
        idClienteLbl.setLocation(xLbl,100);
        xLbl += 150;

        JLabel nombreLbl = new JLabel("Nombre: ");
        nombreLbl.setSize(300,50);
        nombreLbl.setLocation(xLbl,100);
        xLbl += 150;

        JLabel apellidosLbl = new JLabel("Apellidos: ");
        apellidosLbl.setSize(300,50);
        apellidosLbl.setLocation(xLbl,100);
        xLbl += 150;

        JLabel correoLbl = new JLabel("Correo:");
        correoLbl.setSize(300,50);
        correoLbl.setLocation(xLbl,100);
        xLbl += 150;

        JLabel telefonoLbl = new JLabel("Teléfono: ");
        telefonoLbl.setSize(300,50);
        telefonoLbl.setLocation(xLbl,100);

        String[] columnasTablaClientes = {"Id renta", "Cliente", "Automóvil", "Fecha de renta", "Fecha de devolución", "Costo", "Método de pago", "Observaciones"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(600,500));
        sp.setSize(600,500);
        sp.setLocation(200,200);
        sp.setVisible(true);
        historialClienteSeleccionadoPanel.add(idClienteLbl);
        historialClienteSeleccionadoPanel.add(nombreLbl);
        historialClienteSeleccionadoPanel.add(apellidosLbl);
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

        JLabel bienvenido = new JLabel("Crear Cliente", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        crearClientesPNL.add(bienvenido);

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
        int y = 150;
        JLabel nombresLbl = new JLabel("Nombres");
        nombresLbl.setLocation(x,y);
        nombresLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        ApellidosLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        telefonoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        correoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        correoLbl.setSize(200,40);
        crearClientesPNL.add(correoLbl);
        y += 50;
        JTextField correoTF = new JTextField();
        correoTF.setBorder(roundedBorder);
        correoTF.setLocation(x,y);
        correoTF.setSize(200,30);
        crearClientesPNL.add(correoTF);

        x = x*3;
        y = 150;

        JLabel passwordLbl = new JLabel("Contraseña");
        passwordLbl.setLocation(x,y);
        passwordLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        passwordConfLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordConfLbl.setSize(200,40);
        crearClientesPNL.add(passwordConfLbl);
        y += 50;
        JPasswordField passwordConfPF = new JPasswordField();
        passwordConfPF.setBorder(roundedBorder);
        passwordConfPF.setLocation(x,y);
        passwordConfPF.setSize(200,30);
        crearClientesPNL.add(passwordConfPF);


        y += 50;
        JLabel numTarjetaLbl = new JLabel("Num tarj");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        numTarjetaLbl.setSize(200,40);
        crearClientesPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        crearClientesPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha cad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        fechaCadLbl.setSize(200,40);
        crearClientesPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField();
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        crearClientesPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CCV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        ccvLbl.setSize(200,40);
        crearClientesPNL.add(ccvLbl);

        x += 100;

        JTextField ccvTF = new JTextField();
        ccvTF.setBorder(roundedBorder);
        ccvTF.setLocation(x,y);
        ccvTF.setSize(100,30);
        crearClientesPNL.add(ccvTF);

        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setOpaque(true);
        cancelarBtn.setBackground(Color.red);
        cancelarBtn.setSize(200,30);
        cancelarBtn.setLocation(200,y+75);
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

        JButton guardarBtn = new JButton("Dar de alta cliente");
        guardarBtn.setOpaque(true);
        guardarBtn.setBackground(Color.blue);
        guardarBtn.setSize(200,30);
        guardarBtn.setLocation(500,y+75);
        crearClientesPNL.add(guardarBtn);

        return crearClientesPNL;
    }

    public JPanel editarCliente() {
        anterior = actual;
        actual = "editarCliente";

        JPanel editarClientesPNL = new JPanel();
        editarClientesPNL.setSize(1000, 800);
        editarClientesPNL.setLocation(0, 0);
        editarClientesPNL.setLayout(null);
        editarClientesPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Editar Cliente", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        editarClientesPNL.add(bienvenido);

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

        JComboBox idClientesCB = new JComboBox();
        idClientesCB.setSize(150,40);
        idClientesCB.setLocation(400,100);

        JButton consultarHistorialClienteBtn = new JButton("Editar cliente: ");
        consultarHistorialClienteBtn.setSize(150,50);
        consultarHistorialClienteBtn.setLocation(400, 200);
        consultarHistorialClienteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "editarClienteSeleccionado";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });

        String[] columnasTablaClientes = {"Id cliente", "Nombre", "Apellido", "Correo", "Teléfono", "Tarjeta de crédito", "Estado de cuenta"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(525,500);
        sp.setLocation(250,400);
        sp.setVisible(true);

        editarClientesPNL.add(idClientesCB);
        editarClientesPNL.add(sp);
        editarClientesPNL.add(consultarHistorialClienteBtn);

        return editarClientesPNL;
    }
    public JPanel editarClienteSeleccionado(){ //paneles llegados por medio de otro boton no deben de tener ele cambio nac recuerda!!!!!!

        JPanel editarClienteSeleccionadoPNL = new JPanel();
        editarClienteSeleccionadoPNL.setSize(1000, 800);
        editarClienteSeleccionadoPNL.setLocation(0, 0);
        editarClienteSeleccionadoPNL.setLayout(null);
        editarClienteSeleccionadoPNL.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Editar Cliente seleccionado", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        editarClienteSeleccionadoPNL.add(bienvenido);

        int x = 200;
        int y = 150;
        JLabel nombresLbl = new JLabel("Nombres");
        nombresLbl.setLocation(x,y);
        nombresLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        ApellidosLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        telefonoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        telefonoLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(telefonoLbl);
        y += 50;
        JTextField telefonoTF = new JTextField();
        telefonoTF.setBorder(roundedBorder);
        telefonoTF.setLocation(x,y);
        telefonoTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(telefonoTF);

        y += 50;
        JLabel correoLbl = new JLabel("Correo electronico");
        correoLbl.setLocation(x,y);
        correoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        correoLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(correoLbl);
        y += 50;
        JTextField correoTF = new JTextField();
        correoTF.setBorder(roundedBorder);
        correoTF.setLocation(x,y);
        correoTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(correoTF);

        x = x*3;
        y = 150;

        JLabel passwordLbl = new JLabel("Contraseña");
        passwordLbl.setLocation(x,y);
        passwordLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        passwordConfLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordConfLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(passwordConfLbl);
        y += 50;
        JPasswordField passwordConfPF = new JPasswordField();
        passwordConfPF.setBorder(roundedBorder);
        passwordConfPF.setLocation(x,y);
        passwordConfPF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(passwordConfPF);


        y += 50;
        JLabel numTarjetaLbl = new JLabel("Num tarj");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        numTarjetaLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha cad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        fechaCadLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField();
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        editarClienteSeleccionadoPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CCV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        ccvLbl.setSize(200,40);
        editarClienteSeleccionadoPNL.add(ccvLbl);

        x += 100;

        JTextField ccvTF = new JTextField();
        ccvTF.setBorder(roundedBorder);
        ccvTF.setLocation(x,y);
        ccvTF.setSize(100,30);
        editarClienteSeleccionadoPNL.add(ccvTF);

        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setOpaque(true);
        cancelarBtn.setBackground(Color.red);
        cancelarBtn.setSize(200,30);
        cancelarBtn.setLocation(200,y+75);
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

        JButton guardarBtn = new JButton("Guardar cambios");
        guardarBtn.setOpaque(true);
        guardarBtn.setBackground(Color.blue);
        guardarBtn.setSize(200,30);
        guardarBtn.setLocation(500,y+75);
        editarClienteSeleccionadoPNL.add(guardarBtn);



        return editarClienteSeleccionadoPNL;
    }

    public JPanel eliminarCliente(){

        JPanel eliminarPanel = new JPanel();
        eliminarPanel.setSize(1000, 800);
        eliminarPanel.setLocation(0, 0);
        eliminarPanel.setLayout(null);
        eliminarPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Rentas", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        eliminarPanel.add(bienvenido);

        JComboBox idClientesCB = new JComboBox();
        idClientesCB.setSize(150,40);
        idClientesCB.setLocation(400,100);

        JButton eliminarClienteBtn = new JButton("Eliminar cliente: ");
        eliminarClienteBtn.setOpaque(true);
        eliminarClienteBtn.setBackground(Color.red);
        eliminarClienteBtn.setSize(150,50);
        eliminarClienteBtn.setLocation(400, 200);

        String[] columnasTablaClientes = {"Id cliente", "Nombre", "Apellido", "Correo", "Teléfono", "Tarjeta de crédito", "Estado de cuenta"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(525,500);
        sp.setLocation(250,400);
        sp.setVisible(true);

        eliminarPanel.add(idClientesCB);
        eliminarPanel.add(sp);
        eliminarPanel.add(eliminarClienteBtn);


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

        JLabel bienvenido = new JLabel("Rentas", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        rentasPanel.add(bienvenido);

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
        JButton consultarBTN = new JButton("Consultar rentas");
        consultarBTN.setSize(widthBtn, heightBtn);
        consultarBTN.setLocation(xBtn, yBtn);
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

        JButton crearBTN = new JButton("Crear renta");
        crearBTN.setSize(widthBtn, heightBtn);
        crearBTN.setLocation(xBtn, yBtn);
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

        JButton editarBTN = new JButton("Editar renta");
        editarBTN.setSize(widthBtn, heightBtn);
        editarBTN.setLocation(xBtn, yBtn);
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

        JButton eliminarBTN = new JButton("Eliminar renta");
        eliminarBTN.setSize(widthBtn, heightBtn);
        eliminarBTN.setLocation(xBtn, yBtn);
        rentasPanel.add(eliminarBTN);
        eliminarBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anterior = actual;
                actual = "eliminarRenta";
                limpiarVentana();

                repaint();
                revalidate();
            }
        });
        String[] columnasTablaClientes = {"Id renta", "Cliente", "Automóvil", "Fecha de renta", "Fecha de devolución", "Costo", "Método de pago", "Observaciones"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(600,500));
        sp.setSize(600,500);
        sp.setLocation(200,500);
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

        JLabel bienvenido = new JLabel("Consultar Rentas", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        consultarCarPNL.add(bienvenido);


        JComboBox idClientesCB = new JComboBox();
        idClientesCB.setSize(150,40);
        idClientesCB.setLocation(400,100);
        consultarCarPNL.add(idClientesCB);

        JButton consultarHistorialClienteBtn = new JButton("Consultar historial de cliente: ");
        consultarHistorialClienteBtn.setSize(150,50);
        consultarHistorialClienteBtn.setLocation(400, 200);
        consultarCarPNL.add(consultarHistorialClienteBtn);
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

        String[] columnasTablaClientes = {"Id renta", "Cliente", "Automóvil", "Fecha de renta", "Fecha de devolución", "Costo", "Método de pago", "Observaciones"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(600,500));
        sp.setSize(600,500);
        sp.setLocation(200,500);
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

        JLabel bienvenido = new JLabel("Crear Renta", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        crearRentaPNL.add(bienvenido);

        int x = 100;
        int y = 100;
        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        fechaInicioLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        fechaDeDevolucionLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        idClienteLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        nombresLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        apellidosLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        telefonoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        telefonoLbl.setSize(200,40);
        crearRentaPNL.add(telefonoLbl);
        y += 50;
        JTextField telefonoTF = new JTextField();
        telefonoTF.setBorder(roundedBorder);
        telefonoTF.setLocation(x,y);
        telefonoTF.setSize(200,30);
        crearRentaPNL.add(telefonoTF);

        y += 50;
        JLabel numTarjetaLbl = new JLabel("Número tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        numTarjetaLbl.setSize(200,40);
        crearRentaPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        crearRentaPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha cad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        fechaCadLbl.setSize(200,40);
        crearRentaPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField();
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        crearRentaPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CCV");
        ccvLbl.setLocation(x,y);
        ccvLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        JLabel costoEstimadoLbl = new JLabel("Costo estimado");
        costoEstimadoLbl.setLocation(x,y);
        costoEstimadoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        costoEstimadoLbl.setSize(200,40);
        crearRentaPNL.add(costoEstimadoLbl);

        y += 50;

        JTextArea costoEstimadoTA = new JTextArea();
        costoEstimadoTA.setBackground(Color.pink);
        costoEstimadoTA.setLocation(x,y);
        costoEstimadoTA.setFont(new Font("Arial", Font.PLAIN, 16));
        costoEstimadoTA.setSize(200,200);
        crearRentaPNL.add(costoEstimadoTA);

        y += costoEstimadoTA.getHeight();

        JButton crearRentaBtn = new JButton("Crear renta");
        crearRentaBtn.setOpaque(true);
        crearRentaBtn.setBackground(Color.blue);
        crearRentaBtn.setSize(200,30);
        crearRentaBtn.setLocation(x,y);
        crearRentaPNL.add(crearRentaBtn);

        y += 50;
        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setOpaque(true);
        cancelarBtn.setBackground(Color.red);
        cancelarBtn.setSize(200,30);
        cancelarBtn.setLocation(x,y);
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

        JLabel bienvenido = new JLabel("Editar Renta", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        editarRentaPNL.add(bienvenido);

        JComboBox idRentasCB = new JComboBox();
        idRentasCB.setSize(150,40);
        idRentasCB.setLocation(400,100);

        JButton editarRentaBtn = new JButton("Editar renta: ");
        editarRentaBtn.setSize(150,50);
        editarRentaBtn.setLocation(400, 200);
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

        String[] columnasTablaClientes = {"Id cliente", "Nombre", "Apellido", "Correo", "Teléfono", "Tarjeta de crédito", "Estado de cuenta"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(525,500);
        sp.setLocation(250,400);
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

        JLabel bienvenido = new JLabel("Editar Renta", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        editarRentaSeleccionadaPNL.add(bienvenido);

        int x = 100;
        int y = 100;
        JLabel carroLbl = new JLabel("Carro a rentar");
        carroLbl.setLocation(x,y);
        carroLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        fechaInicioLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        fechaDeDevolucionLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        idClienteLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        nombresLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        apellidosLbl.setFont(new Font("Arial", Font.PLAIN, 16));
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
        telefonoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        telefonoLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(telefonoLbl);
        y += 50;
        JTextField telefonoTF = new JTextField();
        telefonoTF.setBorder(roundedBorder);
        telefonoTF.setLocation(x,y);
        telefonoTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(telefonoTF);

        y += 50;
        JLabel numTarjetaLbl = new JLabel("Número tarjeta");
        numTarjetaLbl.setLocation(x,y);
        numTarjetaLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        numTarjetaLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(numTarjetaLbl);
        y += 50;
        JTextField numTarjetaTF = new JTextField();
        numTarjetaTF.setBorder(roundedBorder);
        numTarjetaTF.setLocation(x,y);
        numTarjetaTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(numTarjetaTF);

        y += 50;
        JLabel fechaCadLbl = new JLabel("Fecha cad");
        fechaCadLbl.setLocation(x,y);
        fechaCadLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        fechaCadLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(fechaCadLbl);
        y += 50;
        JTextField fechaCadTF = new JTextField();
        fechaCadTF.setBorder(roundedBorder);
        fechaCadTF.setLocation(x,y);
        fechaCadTF.setSize(200,30);
        editarRentaSeleccionadaPNL.add(fechaCadTF);

        y += 50;

        JLabel ccvLbl = new JLabel("CCV");
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
        JLabel costoEstimadoLbl = new JLabel("Costo estimado");
        costoEstimadoLbl.setLocation(x,y);
        costoEstimadoLbl.setFont(new Font("Arial", Font.PLAIN, 16));
        costoEstimadoLbl.setSize(200,40);
        editarRentaSeleccionadaPNL.add(costoEstimadoLbl);

        y += 50;

        JTextArea costoEstimadoTA = new JTextArea();
        costoEstimadoTA.setBackground(Color.pink);
        costoEstimadoTA.setLocation(x,y);
        costoEstimadoTA.setFont(new Font("Arial", Font.PLAIN, 16));
        costoEstimadoTA.setSize(200,200);
        editarRentaSeleccionadaPNL.add(costoEstimadoTA);

        y += costoEstimadoTA.getHeight();

        JButton crearRentaBtn = new JButton("Guardar cambios");
        crearRentaBtn.setOpaque(true);
        crearRentaBtn.setBackground(Color.blue);
        crearRentaBtn.setSize(200,30);
        crearRentaBtn.setLocation(x,y);
        editarRentaSeleccionadaPNL.add(crearRentaBtn);

        y += 50;
        JButton cancelarBtn = new JButton("Cancelar");
        cancelarBtn.setOpaque(true);
        cancelarBtn.setBackground(Color.red);
        cancelarBtn.setSize(200,30);
        cancelarBtn.setLocation(x,y);
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

        JComboBox idRentasCB = new JComboBox();
        idRentasCB.setSize(150,40);
        idRentasCB.setLocation(400,100);

        JButton eliminarRentaBtn = new JButton("Eliminar renta: ");
        eliminarRentaBtn.setSize(150,50);
        eliminarRentaBtn.setLocation(400, 200);
        eliminarRentaBtn.setOpaque(true);
        eliminarRentaBtn.setBackground(Color.red);

        String[] columnasTablaClientes = {"Id cliente", "Nombre", "Apellido", "Correo", "Teléfono", "Tarjeta de crédito", "Estado de cuenta"};
        Object [][]datos = new Object[9][columnasTablaClientes.length];
        DefaultTableModel dtm = new DefaultTableModel(datos,columnasTablaClientes);
        JTable tablaClientes = new JTable(dtm);
        JScrollPane sp = new JScrollPane(tablaClientes);
        sp.setPreferredSize(new Dimension(500,500));
        sp.setSize(525,500);
        sp.setLocation(250,400);
        sp.setVisible(true);

        eliminarRentaPNL.add(idRentasCB);
        eliminarRentaPNL.add(sp);
        eliminarRentaPNL.add(eliminarRentaBtn);

        return eliminarRentaPNL;
    }
    public JPanel categorias() {
        anterior = actual;
        actual = "categorias";

        JPanel categoriasPanel = new JPanel();
        categoriasPanel.setSize(1000, 800);
        categoriasPanel.setLocation(0, 0);
        categoriasPanel.setLayout(null);
        categoriasPanel.setBackground(Color.decode("#FFFFFF"));

        JLabel bienvenido = new JLabel("Categorias", JLabel.CENTER);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 35));
        bienvenido.setSize(300, 80);
        bienvenido.setLocation(130, 10);
        bienvenido.setForeground(Color.decode("#005F04"));
        categoriasPanel.add(bienvenido);

        JButton homeBTN = new JButton("Regresar");
        homeBTN.setSize(100, 20);
        homeBTN.setLocation(130, 390);
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
        consultaBTN.setSize(100, 20);
        consultaBTN.setLocation(260, 390);
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
}

