import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Car[] cars = {
                new Car(1, "Toyota", "Corolla", 2019, "Blue", 20000, "ABC123"),
                new Car(2, "Honda", "Civic", 2018, "Red", 22000, "DEF456"),
                new Car(3, "Toyota", "Camry", 2020, "Black", 25000, "GHI789"),
                new Car(4, "Ford", "Fiesta", 2015, "White", 15000, "JKL012"),
                new Car(5, "Toyota", "Highlander", 2017, "Silver", 30000, "MNO345")
        };

        // Example usage
        saveCarsByMake(cars, "Toyota", "toyota_cars.txt");
        saveCarsByModelAndYears(cars, "Civic", 2, "civic_cars.txt");
        saveCarsByYearAndPrice(cars, 2015, 18000, "expensive_cars.txt");
    }

    public static void saveCarsByMake(Car[] cars, String make, String filename) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getMake().equalsIgnoreCase(make)) {
                filteredCars.add(car);
            }
        }
        writeCarsToFile(filteredCars, filename);
    }

    public static void saveCarsByModelAndYears(Car[] cars, String model, int nYears, String filename) {
        List<Car> filteredCars = new ArrayList<>();
        int currentYear = java.time.Year.now().getValue();
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model) && (currentYear - car.getYear() > nYears)) {
                filteredCars.add(car);
            }
        }
        writeCarsToFile(filteredCars, filename);
    }

    public static void saveCarsByYearAndPrice(Car[] cars, int year, double minPrice, String filename) {
        List<Car> filteredCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.getYear() == year && car.getPrice() > minPrice) {
                filteredCars.add(car);
            }
        }
        writeCarsToFile(filteredCars, filename);
    }

    private static void writeCarsToFile(List<Car> cars, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : cars) {
                writer.write(car.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
