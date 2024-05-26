package NeoDatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.core.query.criteria.Where;

public class consultaInversaNeoDatis {

    public static void main(String[] args) {
        ODB odb = null;
        try {
            odb = ODBFactory.open("neodatis.test");

            ICriterion criterio = Where.and().add(
                Where.or().add(Where.lt("x", 10)).add(Where.gt("x", 20))
            ).add(Where.gt("z", 5));
            ICriterion criterioInverso = Where.not(criterio);
            CriteriaQuery query = new CriteriaQuery(punto.class, criterioInverso);
            Objects<punto> puntos = odb.getObjects(query);

            System.out.println("Puntos que NO cumplen con las condiciones:");
            int i = 1;
            while (puntos.hasNext()) {
                punto p = puntos.next();
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
