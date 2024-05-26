package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;


public class consultaSumaXYNeoDatis {

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            // Imprimir los puntos y sus coordenadas
            Objects<punto> puntos = odb.getObjects(punto.class);
            int sumaX = 0;
            int sumaY = 0;

            System.out.println("Puntos y sus coordenadas:");
            while (puntos.hasNext()) {
                punto p = puntos.next();
                System.out.println("x=" + p.getX() + " y=" + p.getY());
                sumaX += p.getX();
                sumaY += p.getY();
            }

            System.out.println("Suma de las coordenadas x: " + sumaX);
            System.out.println("Suma de las coordenadas y: " + sumaY);

        } catch (Exception e) {
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
