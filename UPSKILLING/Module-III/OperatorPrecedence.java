class OperatorPrecedence {
    public static void main(String[] args) {

        int result1 = 10 + 5 * 2;
        int result2 = (10 + 5) * 2;
        int result3 = 20 / 5 + 3 * 2;
        int result4 = 8 + 4 - 2 * 3;

        
        System.out.println("10 + 5 * 2 = " + result1);
        System.out.println("(10 + 5) * 2 = " + result2);
        System.out.println("20 / 5 + 3 * 2 = " + result3);
        System.out.println("8 + 4 - 2 * 3 = " + result4);

        System.out.println("\nOrder of Operations:");
        System.out.println("1. Parentheses ()");
        System.out.println("2. Multiplication (*) and Division (/)");
        System.out.println("3. Addition (+) and Subtraction (-)");
    }
}