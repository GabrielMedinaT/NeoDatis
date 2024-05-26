package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class consultaCountPuntosNeoDatis {

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            Values values = odb.getValues(new ValuesCriteriaQuery(punto.class).count("x"));
            ObjectValues ov = values.nextValues();
            int cuentaPuntos = ((Number) ov.getByAlias("x")).intValue();
            System.out.println("Numero de puntos: " + cuentaPuntos);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
