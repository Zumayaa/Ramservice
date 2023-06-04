import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class TablasRamservice {
    JTable tabla;
    public static void crear_tabla(String[] columnasTablaClientes, JTable tabla_creada, DefaultTableModel dtm){
        dtm.setColumnIdentifiers(columnasTablaClientes);
        tabla_creada.setModel(dtm);
        modificar_dimensiones_tabla(tabla_creada);
    }
    public static void modificar_dimensiones_tabla(JTable tabla_creada){
        JTableHeader header = tabla_creada.getTableHeader();
        header.setBackground(Color.decode("#38B6FF"));
        header.setPreferredSize(new Dimension(700,40));
        header.setFont(new Font("Arial", Font.PLAIN, 15));

        //Poner los colores de la tabla menos roñosos
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        UIManager.put("TableHeader.cellBorder", BorderFactory.createMatteBorder(0, 1, 0, 1, Color.WHITE));
        headerRenderer.setOpaque(false);

        //Ajustar la amplitud de la tabla
        for (int i = 0; i < tabla_creada.getColumnCount(); i++) {
            tabla_creada.getColumnModel().getColumn(i).setPreferredWidth(75);
            tabla_creada.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
        //Cambiar el tamaño y color de los nombres de las columnas

    }
    static DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
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
}
