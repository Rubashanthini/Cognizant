public class FinancialForecastTest {
    public static void main(String[] args) {
        double presentValue = 10000.00;
        double growthRate = 0.08;
        int years = 5;

        System.out.println("Present Value: " + presentValue);
        System.out.println("Annual Growth Rate: " + (growthRate * 100) + "%");
        System.out.println("Number of Years: " + years);

        double futureValue = FinancialForecast.calculateFutureValue(presentValue, growthRate, years);
        System.out.println("\nFuture Value After " + years + " Years (Recursive): " + futureValue);

        int[] yearsToForecast = {1, 2, 3, 4, 5, 10};
        System.out.println("\nForecast for Multiple Year Ranges:");
        for (int y : yearsToForecast) {
            double value = FinancialForecast.calculateFutureValue(presentValue, growthRate, y);
            System.out.println("Years: " + y + " -> Future Value: " + value);
        }
    }
}
