import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    String expected = "Rental Record for C. U. Stomer\n\tYou've Got Mail\t3.5\n\tMatrix\t2.0\nAmount owed is 5.5\nYou earned 2 frequent points\n";

    //se agrego rentalinfo //
    //para seguir el princicio de responsabilidad unica
    RentalInfo rentalInfo = new RentalInfo();
    
    Customer customer = new Customer("C. U. Stomer", Arrays.asList(
            new MovieRental("F001", 3),
            new MovieRental("F002", 1)
    ));
    
    String result = rentalInfo.statement(customer);

    //se acorto el if
    if (!result.equals(expected)) {
        throw new AssertionError("Expected: \n" + expected + "\nGot: \n" + result);
    }

    System.out.println("Success");
  }
}
