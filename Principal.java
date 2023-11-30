
package handson10;


public class Principal {

    public static void main(String[] args) {

        double data[][] = {
            {108, 95},
            {115, 96},
            {106, 95},
            {97, 97},
            {95, 93},
            {91, 94},
            {97, 95},
            {83, 93},
            {83, 92},
            {78, 86},
            {54, 73},
            {67, 80},
            {56, 65},
            {53, 69},
            {61, 77},
            {115, 96},
            {81, 87},
            {78, 89},
            {30, 60},
            {45, 63},
            {99, 95},
            {32, 61},
            {25, 55},
            {28, 56},
            {90, 94},
            {89, 93}
        };

        printDataSet(data);
        System.out.println("");
        lineal(data);
        System.out.println("");
        cuadratico(data);
        System.out.println("");
        cubico(data);

    }

    public static void printDataSet(double[][] data) {

        int fila = data.length;

        System.out.println("========== Data Set ==========\t");
        System.out.println("==== Column X    Column Y ====");
        for (int i = 0; i < fila; i++) {
            System.out.println("     " + data[i][0] + "          " + data[i][1]);
        }
    }

    public static void lineal(double[][] data) {

        System.out.println("Lineal predictive model");
        System.out.println("");
        
        int fila = data.length;

        double mediaX = 0;
        double mediaY = 0;
        double beta1 = 0;
        double beta0 = 0;
        double[] yPredict = new double[fila];
        double sumSquareDiffX = 0;
        double sumSquareDiffResidual = 0;
        double sumSquareDiffTotal = 0;

        for (int i = 0; i < fila; i++) {
            mediaX += data[i][0];
            mediaY += data[i][1];
        }

        double meanX = mediaX / fila;
        double meanY = mediaY / fila;

        for (int i = 0; i < fila; i++) {
            beta1 += (data[i][0] - meanX) * (data[i][1] - meanY);
            sumSquareDiffX += Math.pow((data[i][0] - meanX), 2);
        }

        beta1 /= sumSquareDiffX;

        beta0 = meanY - (beta1 * meanX);

        System.out.println("Beta1: " + beta1);
        System.out.println("Beta0: " + beta0);
        System.out.println("β0 = " + meanY + " - " + beta1 + " ⋅ " + meanX);
        System.out.println("");
        System.out.println("y = β0 + β1 ⋅ x");
        System.out.println("");

        for (int i = 0; i < fila; i++) {
            yPredict[i] = beta0 + (beta1 * data[i][0]);
            System.out.println((i + 1) + ": " + yPredict[i]);
            sumSquareDiffResidual += Math.pow((data[i][1] - yPredict[i]), 2);
            sumSquareDiffTotal += Math.pow((data[i][1] - meanY), 2);
        }

        double r2 = 1 - (sumSquareDiffResidual / sumSquareDiffTotal);

        System.out.println("");
        System.out.println("R²: " + r2);
    }

    public static void cuadratico(double[][] data) {

        System.out.println("Quadratic predictive model");
        System.out.println("");

        int n = data.length;

        double sumax = 0, sumay = 0, sumax2 = 0, sumax3 = 0, sumax4 = 0, sumaxy = 0, sumax2y = 0;

        for (int i = 0; i < n; i++) {
            double x = data[i][0];
            double y = data[i][1];
            double x2 = x * x;
            double x3 = x2 * x;
            double x4 = x2 * x2;

            sumax += x;
            sumay += y;
            sumax2 += x2;
            sumax3 += x3;
            sumax4 += x4;
            sumaxy += x * y;
            sumax2y += x2 * y;
        }

        double det = (sumax2 * sumax2 * sumax2) + (sumax * sumax * sumax4) + (n * sumax3 * sumax3) - (sumax4 * sumax2 * n) - (sumax3 * sumax * sumax2) - (sumax2 * sumax3 * sumax);
        double beta0 = (sumay * sumax2 * sumax2) + (sumaxy * sumax3 * n) + (sumax2y * sumax * sumax) - (sumaxy * sumax * sumax2) - (sumay * sumax3 * sumax) - (sumax2y * sumax2 * n);
        double beta1 = (sumax2 * sumaxy * sumax2) + (sumay * sumax * sumax4) + (n * sumax3 * sumax2y) - (sumax4 * sumaxy * n) - (sumax2y * sumax * sumax2) - (sumax2 * sumax3 * sumay);
        double beta2 = (sumax2 * sumax2 * sumax2y) + (sumax * sumaxy * sumax4) + (sumay * sumax3 * sumax3) - (sumax4 * sumax2 * sumay) - (sumax3 * sumaxy * sumax2) - (sumax2y * sumax3 * sumax);

        beta0 = beta0 / det;
        beta1 = beta1 / det;
        beta2 = beta2 / det;

        System.out.println("B0: " + beta0);
        System.out.println("B1: " + beta1);
        System.out.println("B2: " + beta2);
        System.out.println("Y = β0 " + beta0 + " + β1 " + beta1 + " x + β2 " + beta2 + " x²");
        System.out.println("");

        double[] yPredict = new double[n];

        // Predicción para un nuevo valor de x
        for (int i = 0; i < n; i++) {
            yPredict[i] = beta0 * (Math.pow(data[i][0], 2)) + beta1 * (data[i][0]) + beta2;
            System.out.println((i + 1) + ": " + yPredict[i]);
        }

        double meany = 0;

        for (int i = 0; i < n; i++) {
            meany += data[i][1];
        }

        meany = meany / n;
        
        double totalSumOfSquares = 0;

        for (int i = 0; i < n; i++) {
            totalSumOfSquares += Math.pow((data[i][1] - meany), 2);
        }

        // Calcular la suma de errores cuadráticos residuales
        double sumOfSquaredResiduals = 0;
        for (int i = 0; i < n; i++) {
            sumOfSquaredResiduals += Math.pow((data[i][1] - yPredict[i]), 2);
        }

        // Calcular R^2
        double rSquared = 1 - (sumOfSquaredResiduals / totalSumOfSquares);

        System.out.println("");

        System.out.println("R²: " + rSquared);

    }

    public static void cubico(double[][] data) {

        System.out.println("Cubic predictive model");
        System.out.println("");

        int n = data.length;

        double sumX = 0, sumY = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumX5 = 0, sumX6 = 0, sumXY = 0, sumX2Y = 0, sumX3Y = 0;

        for (int i = 0; i < n; i++) {

            sumX += data[i][0];
            sumY += data[i][1];
            sumX2 += Math.pow(data[i][0], 2);
            sumX3 += Math.pow(data[i][0], 3);
            sumX4 += Math.pow(data[i][0], 4);
            sumX5 += Math.pow(data[i][0], 5);
            sumX6 += Math.pow(data[i][0], 6);
            sumXY += data[i][0] * data[i][1];
            sumX2Y += Math.pow(data[i][0], 2) * data[i][1];
            sumX3Y += Math.pow(data[i][0], 3) * data[i][1];

        }

        double det = (sumX3 * sumX3 * sumX3 * sumX3)
                - (sumX3 * sumX3 * sumX2 * sumX4)
                - (sumX3 * sumX2 * sumX4 * sumX3)
                + (sumX3 * sumX2 * sumX2 * sumX5)
                + (sumX3 * sumX * sumX4 * sumX4)
                - (sumX3 * sumX * sumX3 * sumX5)
                - (sumX2 * sumX4 * sumX3 * sumX3)
                + (sumX2 * sumX4 * sumX2 * sumX4)
                + (sumX2 * sumX2 * sumX5 * sumX3)
                - (sumX2 * sumX2 * sumX2 * sumX6)
                - (sumX2 * sumX * sumX5 * sumX4)
                + (sumX2 * sumX * sumX3 * sumX6)
                + (sumX * sumX4 * sumX4 * sumX3)
                - (sumX * sumX4 * sumX2 * sumX5)
                - (sumX * sumX3 * sumX5 * sumX3)
                + (sumX * sumX3 * sumX2 * sumX6)
                + (sumX * sumX * sumX5 * sumX5)
                - (sumX * sumX * sumX4 * sumX6)
                - (n * sumX4 * sumX4 * sumX4)
                + (n * sumX4 * sumX3 * sumX5)
                + (n * sumX3 * sumX5 * sumX4)
                - (n * sumX3 * sumX3 * sumX6)
                - (n * sumX2 * sumX5 * sumX5)
                + (n * sumX2 * sumX4 * sumX6);

        double beta3 = (sumY * sumX3 * sumX3 * sumX3)
                - (sumY * sumX3 * sumX2 * sumX4)
                - (sumY * sumX2 * sumX4 * sumX3)
                + (sumY * sumX2 * sumX2 * sumX5)
                + (sumY * sumX * sumX4 * sumX4)
                - (sumY * sumX * sumX3 * sumX5)
                - (sumX2 * sumXY * sumX3 * sumX3)
                + (sumX2 * sumXY * sumX2 * sumX4)
                + (sumX2 * sumX2 * sumX2Y * sumX3)
                - (sumX2 * sumX2 * sumX2 * sumX3Y)
                - (sumX2 * sumX * sumX2Y * sumX4)
                + (sumX2 * sumX * sumX3 * sumX3Y)
                + (sumX * sumXY * sumX4 * sumX3)
                - (sumX * sumXY * sumX2 * sumX5)
                - (sumX * sumX3 * sumX2Y * sumX3)
                + (sumX * sumX3 * sumX2 * sumX3Y)
                + (sumX * sumX * sumX2Y * sumX5)
                - (sumX * sumX * sumX4 * sumX3Y)
                - (n * sumXY * sumX4 * sumX4)
                + (n * sumXY * sumX3 * sumX5)
                + (n * sumX3 * sumX2Y * sumX4)
                - (n * sumX3 * sumX3 * sumX3Y)
                - (n * sumX2 * sumX2Y * sumX5)
                + (n * sumX2 * sumX4 * sumX3Y);

        double beta2 = (sumX3 * sumXY * sumX3 * sumX3)
                - (sumX3 * sumXY * sumX4 * sumX2)
                - (sumX3 * sumX2 * sumX2Y * sumX3)
                + (sumX3 * sumX2 * sumX2 * sumX3Y)
                + (sumX3 * sumX * sumX2Y * sumX4)
                - (sumX3 * sumX * sumX3 * sumX3Y)
                - (sumY * sumX4 * sumX3 * sumX3)
                + (sumY * sumX4 * sumX2 * sumX4)
                + (sumY * sumX2 * sumX5 * sumX3)
                - (sumY * sumX2 * sumX2 * sumX6)
                - (sumY * sumX * sumX5 * sumX4)
                + (sumY * sumX * sumX3 * sumX6)
                + (sumX * sumX4 * sumX2Y * sumX3)
                - (sumX * sumX4 * sumX2 * sumX3Y)
                - (sumX * sumXY * sumX5 * sumX3)
                + (sumX * sumXY * sumX6 * sumX2)
                + (sumX * sumX * sumX5 * sumX3Y)
                - (sumX * sumX * sumX2Y * sumX6)
                - (n * sumX4 * sumX2Y * sumX4)
                + (n * sumX4 * sumX3 * sumX3Y)
                + (n * sumXY * sumX5 * sumX4)
                - (n * sumXY * sumX3 * sumX6)
                - (n * sumX2 * sumX5 * sumX3Y)
                + (n * sumX2 * sumX2Y * sumX6);

        double beta1 = (sumX3 * sumX3 * sumX2Y * sumX3)
                - (sumX3 * sumX3 * sumX3Y * sumX2)
                - (sumX3 * sumXY * sumX4 * sumX3)
                + (sumX3 * sumXY * sumX2 * sumX5)
                + (sumX3 * sumX * sumX4 * sumX3Y)
                - (sumX3 * sumX * sumX2Y * sumX5)
                - (sumX2 * sumX4 * sumX2Y * sumX3)
                + (sumX2 * sumX4 * sumX2 * sumX3Y)
                + (sumX2 * sumXY * sumX5 * sumX3)
                - (sumX2 * sumXY * sumX2 * sumX6)
                - (sumX2 * sumX * sumX5 * sumX3Y)
                + (sumX2 * sumX * sumX2Y * sumX6)
                + (sumY * sumX4 * sumX4 * sumX3)
                - (sumY * sumX4 * sumX2 * sumX5)
                - (sumY * sumX3 * sumX5 * sumX3)
                + (sumY * sumX3 * sumX2 * sumX6)
                + (sumY * sumX * sumX5 * sumX5)
                - (sumY * sumX * sumX4 * sumX6)
                - (n * sumX4 * sumX4 * sumX3Y)
                + (n * sumX4 * sumX2Y * sumX5)
                + (n * sumX3 * sumX5 * sumX3Y)
                - (n * sumX3 * sumX2Y * sumX6)
                - (n * sumXY * sumX5 * sumX5)
                + (n * sumXY * sumX4 * sumX6);

        double beta0 = (sumX3 * sumX3 * sumX3 * sumX3Y)
                - (sumX3 * sumX3 * sumX2Y * sumX4)
                - (sumX3 * sumX2 * sumX4 * sumX3Y)
                + (sumX3 * sumX2 * sumX2Y * sumX5)
                + (sumX3 * sumXY * sumX4 * sumX4)
                - (sumX3 * sumXY * sumX3 * sumX5)
                - (sumX2 * sumX4 * sumX3 * sumX3Y)
                + (sumX2 * sumX4 * sumX2Y * sumX4)
                + (sumX2 * sumX2 * sumX5 * sumX3Y)
                - (sumX2 * sumX2 * sumX2Y * sumX6)
                - (sumX2 * sumXY * sumX5 * sumX4)
                + (sumX2 * sumXY * sumX3 * sumX6)
                + (sumX * sumX4 * sumX4 * sumX3Y)
                - (sumX * sumX4 * sumX2Y * sumX5)
                - (sumX * sumX3 * sumX5 * sumX3Y)
                + (sumX * sumX3 * sumX2Y * sumX6)
                + (sumX * sumXY * sumX5 * sumX5)
                - (sumX * sumXY * sumX4 * sumX6)
                - (sumY * sumX4 * sumX4 * sumX4)
                + (sumY * sumX4 * sumX3 * sumX5)
                + (sumY * sumX3 * sumX5 * sumX4)
                - (sumY * sumX3 * sumX3 * sumX6)
                - (sumY * sumX2 * sumX5 * sumX5)
                + (sumY * sumX2 * sumX4 * sumX6);

        beta3 = beta3 / det;
        beta2 = beta2 / det;
        beta1 = beta1 / det;
        beta0 = beta0 / det;

        System.out.println("Beta0: " + beta0);
        System.out.println("Beta1: " + beta1);
        System.out.println("Beta2: " + beta2);
        System.out.println("Beta3: " + beta3);

        System.out.println("");

        System.out.println("Y = β0 " + beta0 + " + β1 " + beta1 + " x + β2 " + beta2 + " x² + β3 " + beta3 + " x³");

        System.out.println("");

        double[] yPredict = new double[n];

        // Predicción para un nuevo valor de x
        for (int i = 0; i < n; i++) {
            yPredict[i] = beta0 + beta1 * (data[i][0]) + beta2 * (Math.pow(data[i][0], 2)) + beta3 * (Math.pow(data[i][0], 3));
            System.out.println((i + 1) + ": " + yPredict[i]);
        }

        double meany = 0;

        for (int i = 0; i < n; i++) {
            meany += data[i][1];
        }
        
        meany = meany / n;

        double totalSumOfSquares = 0;

        for (int i = 0; i < n; i++) {
            totalSumOfSquares += Math.pow((data[i][1] - meany), 2);
        }

        // Calcular la suma de errores cuadráticos residuales
        double sumOfSquaredResiduals = 0;
        for (int i = 0; i < n; i++) {
            sumOfSquaredResiduals += Math.pow((data[i][1] - yPredict[i]), 2);
        }

        // Calcular R^2
        double rSquared = 1 - (sumOfSquaredResiduals / totalSumOfSquares);

        System.out.println("");

        System.out.println("R²: " + rSquared);

    }

}

