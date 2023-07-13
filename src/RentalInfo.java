//refactorizado por Pastor Moreno Wiston

// DESCRIPCION DE CAMBIOS EN LOS CODE SMELLS
//En la clase RentalInfo, se realizó una serie de cambios para seguir las buenas prácticas:

//Se agregó un constructor para inicializar el mapa movies con los datos correspondientes.
//Se utilizó un StringBuilder en lugar de concatenar cadenas directamente para mejorar el rendimiento y eficiencia.
//Se extrajeron los cálculos de monto y puntos de bonificación a métodos separados para mejorar la legibilidad y facilitar el mantenimiento.
//Se utilizaron if-else if en lugar de varios if independientes para simplificar la lógica y evitar evaluaciones repetidas de condiciones.


import java.util.HashMap;

public class RentalInfo {
    private HashMap<String, Movie> movies;

    public RentalInfo() {
        movies = new HashMap<>();
        movies.put("F001", new Movie("You've Got Mail", "regular"));
        movies.put("F002", new Movie("Matrix", "regular"));
        movies.put("F003", new Movie("Cars", "childrens"));
        movies.put("F004", new Movie("Fast & Furious X", "new"));
    }

    public String statement(Customer customer) {
        double totalAmount = 0;
        int frequentEnterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + customer.getName() + "\n");

        for (MovieRental rental : customer.getRentals()) {
            Movie movie = movies.get(rental.getMovieId());
            double thisAmount = calculateAmount(movie, rental);

            frequentEnterPoints += calculateFrequentEnterPoints(movie, rental);

            result.append("\t").append(movie.getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentEnterPoints).append(" frequent points\n");

        return result.toString();
    }

    private double calculateAmount(Movie movie, MovieRental rental) {
        double thisAmount = 0;

        if (movie.getCode().equals("regular")) {
            thisAmount = 2;
            if (rental.getDays() > 2) {
                thisAmount += (rental.getDays() - 2) * 1.5;
            }
        } else if (movie.getCode().equals("new")) {
            thisAmount = rental.getDays() * 3;
        } else if (movie.getCode().equals("childrens")) {
            thisAmount = 1.5;
            if (rental.getDays() > 3) {
                thisAmount += (rental.getDays() - 3) * 1.5;
            }
        }

        return thisAmount;
    }

    private int calculateFrequentEnterPoints(Movie movie, MovieRental rental) {
        int frequentEnterPoints = 1;

        if (movie.getCode().equals("new") && rental.getDays() > 2) {
            frequentEnterPoints++;
        }

        return frequentEnterPoints;
    }
}
