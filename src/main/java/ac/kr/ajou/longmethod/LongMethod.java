package ac.kr.ajou.longmethod;

public class LongMethod {

    public boolean doSomething(String social, String name, String password) {

        if (social.length() != 6) {
            System.out.println("Social invalid");
            throw new RuntimeException();
        }

        for (int i = 0; i < social.length(); i++) {

            if (!Character.isDigit(social.charAt(i))) {
                System.out.println("Social invalid");
                throw new RuntimeException();
            }
        }

        if (name.length() > 5) {
            System.out.println("name invalid");
            throw new RuntimeException();
        }

        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))) {
                System.out.println("name invalid");
                throw new RuntimeException();
            }
        }

        if (password.length() < 9) {
            System.out.println("password invalid");
            throw new RuntimeException();
        }


        int temp = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                temp++;
            }
        }

        if (temp < 2) {
            System.out.println("password invalid");
            throw new RuntimeException();
        }

        return true;
    }
}
