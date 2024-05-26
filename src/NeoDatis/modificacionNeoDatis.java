package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;


public class modificacionNeoDatis {

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");
            punto pInicial = new punto(33, 33, 33);
            OID oid = odb.store(pInicial);
            odb.commit();
            punto pCreado = (punto) odb.getObjectFromId(oid);
            System.out.println("Punto creado: " + pCreado.mostrar());
            pCreado.setX(44);
            pCreado.setY(44);
            pCreado.setZ(44);
            odb.store(pCreado);
            odb.commit();
            punto pModificado = (punto) odb.getObjectFromId(oid);
            System.out.println("Punto modificado: " + pModificado.mostrar());
            ICriterion criterio = Where.and().add(Where.equal("x", 44)).add(Where.equal("y", 44)).add(Where.equal("z", 44));
            CriteriaQuery query = new CriteriaQuery(punto.class, criterio);
            Objects<punto> puntosAEliminar = odb.getObjects(query);

            if (puntosAEliminar.hasNext()) {
                punto pEliminar = puntosAEliminar.next();
                odb.delete(pEliminar);
                odb.commit();
                System.out.println("Punto eliminado: " + pEliminar.mostrar());
            } else {
                System.out.println("No se encontró el punto con coordenadas (44,44,44) para eliminar.");
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
