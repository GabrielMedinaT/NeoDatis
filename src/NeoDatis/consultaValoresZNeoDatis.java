package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class consultaValoresZNeoDatis {

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            Values values = odb.getValues(new ValuesCriteriaQuery(punto.class).field("z"));
            System.out.println("Valores del atributo z de los puntos:");

            while (values.hasNext()) {
                ObjectValues ov = values.nextValues();
                System.out.println("z: " + ov.getByAlias("z"));
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
