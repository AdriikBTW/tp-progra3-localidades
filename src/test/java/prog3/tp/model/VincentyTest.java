package prog3.tp.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class VincentyTest {

    // Buenos Aires → Córdoba aprox
    @Test
    public void testDistanceBuenosAiresCordoba() {

        double lat1 = -34.6037; // Buenos Aires
        double lon1 = -58.3816;

        double lat2 = -31.4201; // Córdoba
        double lon2 = -64.1888;

        double distance = Vincenty.distance(lat1, lon1, lat2, lon2);

        // distancia real ~ 647 km
        assertEquals(647, distance, 0.500); // tolerancia de 5 km
    }

    // Mismo punto → distancia 0
    @Test
    public void testSamePoint() {

        double distance = Vincenty.distance(10, 10, 10, 10);

        assertEquals(0, distance, 0.0001);
    }

    // Distancia corta (más sensible a errores numéricos)
    @Test
    public void testShortDistance() {

        double lat1 = -34.6037;
        double lon1 = -58.3816;

        double lat2 = -34.6040;
        double lon2 = -58.3820;

        double distance = Vincenty.distance(lat1, lon1, lat2, lon2);

        assertTrue(distance > 0);
        assertTrue(distance < 1); // debería ser menos de 1 km
    }

    // Caso complicado: casi antipodal
    @Test
   public void testAntipodalCase() {

        double lat1 = 0;
        double lon1 = 0;

        double lat2 = 0;
        double lon2 = 179.999;

        assertThrows(RuntimeException.class, () -> {
            Vincenty.distance(lat1, lon1, lat2, lon2);
        });
    }
    
    @Test
    public void testNorthPoleToSouthPole() {
       
    	 // A lo largo de un meridiano, del polo norte al sur
        double lat1 = 90;
        double lon1 = 0;

        double lat2 = -90;
        double lon2 = 0;
        
        double distance = Vincenty.distance(lat1, lon1, lat2, lon2);
        
        // Aproximadamente 20,003.93 km
        assertEquals(20003.93, distance, 0.1);
    }
    
    @Test
    public void testEquatorialDistance() {
        // Dos puntos en el ecuador separados por 1 grado
        double lat1 = 0;
        double lon1 = 0;

        double lat2 = 0;
        double lon2 = 1;
        
        double distance = Vincenty.distance(lat1, lon1, lat2, lon2);

        // 111.319 km aprox
        assertEquals(111.319, distance, 0.001);
    }
    
    @Test
    public void testLongitudeNormalization() {
        // Debería ser la misma distancia que de 0 a 10
        double dist1 = Vincenty.distance(0, 0, 0, -10);
        double dist2 = Vincenty.distance(0, 350, 0, 0); // 350 es lo mismo que -10
        
        assertEquals(dist1, dist2, 0.001);
    }
}