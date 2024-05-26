package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class consultaAvgXNeoDatis {

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            Objects<punto> puntos = odb.getObjects(punto.class);
            int sumaX = 0;
            int count = 0;

            while (puntos.hasNext()) {
                punto p = puntos.next();
                sumaX += p.getX();
                count++;
            }

            if (count > 0) {
                double promedioX = (double) sumaX / count;
                System.out.println("Valor medio de la coordenada x: " + promedioX);
            } else {
                System.out.println("No se encontraron puntos para calcular el promedio.");
            }

        } catch (Exception e) {
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
