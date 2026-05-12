package prog3.tp.model;

public class Vincenty {

    // Parameters WGS-84
    private static final double a = 6378137.0; // semieje
    private static final double b = 6356752.314245; // semieje
    private static final double f = 1 / 298.257223563; // achatamiento

    /**
     * Calculate the distance using Vincenty.
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return distance in km
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {

        double L = Math.toRadians(lon2 - lon1);
        double U1 = Math.atan((1 - f) * Math.tan(Math.toRadians(lat1)));
        double U2 = Math.atan((1 - f) * Math.tan(Math.toRadians(lat2)));

        double sinU1 = Math.sin(U1), cosU1 = Math.cos(U1);
        double sinU2 = Math.sin(U2), cosU2 = Math.cos(U2);

        double lambda = L;
        double lambdaPrev;
        int iterLimit = 100;

        double sinLambda, cosLambda;
        double sinSigma, cosSigma, sigma;
        double sinAlpha, cosSqAlpha;
        double cos2SigmaM;
        double C;

        do {
            sinLambda = Math.sin(lambda);
            cosLambda = Math.cos(lambda);

            sinSigma =
                    Math.sqrt(
                            (cosU2 * sinLambda) * (cosU2 * sinLambda)
                                    + (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda)
                                            * (cosU1 * sinU2 - sinU1 * cosU2 * cosLambda));

            if (sinSigma == 0) return 0;

            cosSigma = sinU1 * sinU2 + cosU1 * cosU2 * cosLambda;
            sigma = Math.atan2(sinSigma, cosSigma);

            sinAlpha = cosU1 * cosU2 * sinLambda / sinSigma;
            cosSqAlpha = 1 - sinAlpha * sinAlpha;

            cos2SigmaM =
                    (cosSqAlpha != 0)
                            ? cosSigma - 2 * sinU1 * sinU2 / cosSqAlpha
                            : 0; // ecuatorial line

            C = f / 16 * cosSqAlpha * (4 + f * (4 - 3 * cosSqAlpha));

            lambdaPrev = lambda;
            lambda =
                    L
                            + (1 - C)
                                    * f
                                    * sinAlpha
                                    * (sigma
                                            + C
                                                    * sinSigma
                                                    * (cos2SigmaM
                                                            + C
                                                                    * cosSigma
                                                                    * (-1
                                                                            + 2
                                                                                    * cos2SigmaM
                                                                                    * cos2SigmaM)));

        } while (Math.abs(lambda - lambdaPrev) > 1e-12 && --iterLimit > 0);

        //  Problem Vincenty
        if (iterLimit == 0) {
            throw new RuntimeException("Vincenty formula failed to converge");
        }

        double uSq = cosSqAlpha * (a * a - b * b) / (b * b);
        double A = 1 + uSq / 16384 * (4096 + uSq * (-768 + uSq * (320 - 175 * uSq)));

        double B = uSq / 1024 * (256 + uSq * (-128 + uSq * (74 - 47 * uSq)));

        double deltaSigma =
                B
                        * sinSigma
                        * (cos2SigmaM
                                + B
                                        / 4
                                        * (cosSigma * (-1 + 2 * cos2SigmaM * cos2SigmaM)
                                                - B
                                                        / 6
                                                        * cos2SigmaM
                                                        * (-3 + 4 * sinSigma * sinSigma)
                                                        * (-3 + 4 * cos2SigmaM * cos2SigmaM)));

        double s = b * A * (sigma - deltaSigma);

        return s / 1000.0;
    }
}
