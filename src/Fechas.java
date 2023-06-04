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
}
