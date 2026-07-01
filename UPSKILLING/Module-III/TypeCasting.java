class TypeCasting {
    public static void main(String[] args) {

        double num1 = 45.67;
        int intValue = (int) num1;

        int num2 = 100;
        double doubleValue = (double) num2;

        System.out.println("Original double value: " + num1);
        System.out.println("After casting to int: " + intValue);

        System.out.println("Original int value: " + num2);
        System.out.println("After casting to double: " + doubleValue);
    }
}