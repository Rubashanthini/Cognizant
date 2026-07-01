class VirtualThreadsExample {

    public static void main(String[] args) {

        for (int i = 1; i <= 100000; i++) {

            int number = i;

            Thread.startVirtualThread(() -> {
                System.out.println("Virtual Thread: " + number);
            });
        }

        System.out.println("All Virtual Threads Started");
    }
}