package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;

public class crearPuntosNeoDatis {

    public static int azar() {
        return (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            // Crear y almacenar 10 puntos con y = 50
            for (int i = 0; i < 10; i++) {
                int x = azar();
                int y = 50;
                int z = azar();
                punto p = new punto(x, y, z);
                odb.store(p);
            }

            odb.commit();
            System.out.println("Se crearon 10 puntos con y = 50.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
