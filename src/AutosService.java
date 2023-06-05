import javax.swing.*;
import java.sql.SQLException;

public class AutosService {
    public static ComboBoxModel descargar_id_autos() throws SQLException {
        return (ComboBoxModel) AutosDAO.seleccionar_autos();
    }
}
