import java.lang.reflect.Method;

class TestClass {

    public void display() {
        System.out.println("Method Invoked");
    }
}

class ReflectionExample {
    public static void main(String[] args) {

        try {

            Class<?> cls = Class.forName("TestClass");

            Method[] methods = cls.getDeclaredMethods();

            for (Method m : methods) {
                System.out.println("Method Name: " + m.getName());
            }

            Object obj = cls.getDeclaredConstructor().newInstance();

            Method method = cls.getMethod("display");

            method.invoke(obj);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}