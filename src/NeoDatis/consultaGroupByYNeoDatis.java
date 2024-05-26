package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class consultaGroupByYNeoDatis {

    public static void main(String[] args) {
        
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            Values values = odb.getValues(new ValuesCriteriaQuery(punto.class)
                .field("y")
                .count("z")
                .groupBy("y"));
            
            System.out.println("Número de valores de z para cada valor de y:");
            while (values.hasNext()) {
                ObjectValues ov = values.nextValues();
                int y = ((Number) ov.getByAlias("y")).intValue();
                long countZ = ((Number) ov.getByIndex(1)).longValue();
                System.out.println("y: " + y + ", count(z): " + countZ);
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
