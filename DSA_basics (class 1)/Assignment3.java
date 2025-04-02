import java.util.*;

public class Assignment3 {
    // Method to calculate the degree of a polynomial
    public static int calculateDegree(List<int[]> polynomial) {
        int maxDegree = 0;

        for (int[] term : polynomial) {
             int sum = 0;

             for(int i=1 ; i < term.length ; i++){
                sum += i;
             }
 
            maxDegree = Math.max(maxDegree, sum); // Keep track of the maximum degree
        }

        return maxDegree;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<int[]> polynomial = new ArrayList<>();

        System.out.println("Enter the number of terms in the polynomial:");
        int numTerms = scanner.nextInt();

        System.out.println("Enter each term in the format:");
        System.out.println("coefficient power_of_x power_of_y power_of_z");
        System.out.println("Example: 3 2 4 0 for 3x²y⁴");


        for (int i = 0; i < numTerms; i++) {
            System.out.println("Enter term " + (i + 1) + ":");
            int coefficient = scanner.nextInt();
            int powerX = scanner.nextInt();
            int powerY = scanner.nextInt();
            int powerZ = scanner.nextInt();
            polynomial.add(new int[]{coefficient, powerX, powerY, powerZ});
        }


        int degree = calculateDegree(polynomial);
        System.out.println("Degree of the polynomial: " + degree);


        scanner.close();
    }
}