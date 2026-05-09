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
}