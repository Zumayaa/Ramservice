import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Fechas {

    public static boolean compararFechas(String fecha_inicio_1, String fecha_final_1,
                                                     String fecha_inicio_2, String fecha_final_2) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate1 = dateFormat.parse(fecha_inicio_1);
            Date endDate1 = dateFormat.parse(fecha_final_1);
            Date startDate2 = dateFormat.parse(fecha_inicio_2);
            Date endDate2 = dateFormat.parse(fecha_final_2);

            return startDate1.compareTo(endDate2) <= 0 && endDate1.compareTo(startDate2) >= 0;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean verificarLegalidadDeFechas(String fecha_inicio, String fecha_final, String comparacion) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate1 = dateFormat.parse(fecha_inicio);
            Date endDate1 = dateFormat.parse(fecha_final);
            return startDate1.before(endDate1) || (startDate1.equals(endDate1) && comparacion.equals("RENTAR"));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    public int dias_de_diferencia(String fecha_inicio, String fecha_final){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate1 = dateFormat.parse(fecha_inicio);
            Date endDate1 = dateFormat.parse(fecha_final);

            return startDate1.compareTo(endDate1);
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }


    public static String obtenerFechaActual() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fechaActual.format(formatter);
    }


    public static int getDias_De_Renta(String fecha_inicial, String fecha_final) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = dateFormat.parse(fecha_inicial);
            Date endDate = dateFormat.parse(fecha_final);

            long diffInMilliseconds = Math.abs(endDate.getTime() - startDate.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);
            if (diffInDays == 0){
                diffInDays = 1;
            }
            return (int) diffInDays;
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }
    // funciones especifocas
    static class NumericDocument extends PlainDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            // Validar que solo se ingresen caracteres numéricos
            if (str.matches("[0-9-]*")) {
                super.insertString(offs, str, a);
            }
        }
    }

    static class FechaDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
            // Insertar guiones después del año y el mes
            super.insertString(fb, offset, text, attrs);
            if (offset == 3 || offset == 6) {
                insertText(fb, offset + 1, "-", attrs);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            // Reemplazar guiones después del año y el mes
            super.replace(fb, offset, length, text, attrs);
            if (offset == 3 || offset == 6) {
                insertText(fb, offset + 1, "-", attrs);
            }
        }

        private void insertText(FilterBypass fb, int offset, String text, AttributeSet attrs) throws BadLocationException {
            Document doc = fb.getDocument();
            doc.insertString(offset, text, attrs);
        }
    }
}
