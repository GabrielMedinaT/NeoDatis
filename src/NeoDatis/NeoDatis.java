package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.OID;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.neodatis.odb.core.query.criteria.Where;

public class NeoDatis {

    public static int azar() {
        return (int) (Math.random() * 100);
    }

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            Objects<punto> objetos = odb.getObjects(punto.class);
            while (objetos.hasNext()) {
                punto p = objetos.next();
                odb.delete(p);
            }

            OID oid3 = null;
            for (int i = 0; i < 10; i++) {
                int x = azar();
                int y = azar();
                int z = azar();
                punto p = new punto(x, y, z);
                OID oid = odb.store(p);
                if (i == 2) {
                    oid3 = oid;
                }
            }

            punto pEspecial = new punto(azar(), azar(), 5);
            punto pEspecial2 = new punto(azar(), azar(), 5);
            punto pEspecial3 = new punto(azar(), azar(), 5);

            odb.store(pEspecial);
            odb.store(pEspecial2);
            odb.store(pEspecial3);

            CriteriaQuery query = new CriteriaQuery(punto.class, Where.equal("z", 5));
            Objects<punto> puntosZ5 = odb.getObjects(query);

            List<punto> listaPuntosZ5 = new ArrayList<>();
            while (puntosZ5.hasNext()) {
                listaPuntosZ5.add(puntosZ5.next());
            }
            listaPuntosZ5.sort(Comparator.comparingInt(punto::getX).reversed().thenComparingInt(punto::getY).reversed());

            System.out.println("Puntos con coordenada z = 5: " + listaPuntosZ5.size());
            for (punto p : listaPuntosZ5) {
                System.out.println(p.mostrar());
            }

            if (oid3 != null) {
                punto p = (punto) odb.getObjectFromId(oid3);
                p.setZ(5);
                odb.store(p);
            } else {
                System.out.println("OID 3 no encontrado.");
            }
            objetos = odb.getObjects(punto.class);
            System.out.println("Se almacenaron " + objetos.size() + " puntos y se leyeron " + objetos.size() + " puntos");
            int i = 1;
            while (objetos.hasNext()) {
                punto p = objetos.next();
                System.out.println((i++) + p.mostrar());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
