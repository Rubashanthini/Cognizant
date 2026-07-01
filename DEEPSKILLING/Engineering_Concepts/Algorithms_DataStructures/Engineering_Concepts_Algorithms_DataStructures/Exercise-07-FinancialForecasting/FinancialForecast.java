public class FinancialForecast {
    public static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static double calculateFutureValueMemoized(double presentValue, double growthRate, int years, double[] memo) {
        if (years == 0) {
            return presentValue;
        }
        if (memo[years] != 0.0) {
            return memo[years];
        }
        double result = calculateFutureValueMemoized(presentValue * (1 + growthRate), growthRate, years - 1, memo);
        memo[years] = result;
        return result;
    }
}
