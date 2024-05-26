package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class consultaMaxMinYNeoDatis {

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            // Obtener el valor máximo de la coordenada y
            Values valuesMax = odb.getValues(new ValuesCriteriaQuery(punto.class).max("y"));
            ObjectValues ovMax = valuesMax.nextValues();
            int maxY = ((Number) ovMax.getByAlias("y")).intValue();

            // Obtener el valor mínimo de la coordenada y
            Values valuesMin = odb.getValues(new ValuesCriteriaQuery(punto.class).min("y"));
            ObjectValues ovMin = valuesMin.nextValues();
            int minY = ((Number) ovMin.getByAlias("y")).intValue();

            System.out.println("Valor máximo de la coordenada y: " + maxY);
            System.out.println("Valor mínimo de la coordenada y: " + minY);

        } catch (Exception e) {
        } finally {
            if (odb != null) {
                odb.close();
            }
        }
    }
}
