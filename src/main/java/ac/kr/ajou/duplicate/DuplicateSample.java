package ac.kr.ajou.duplicate;

public class DuplicateSample {

    public int calc(EventData ed) {
        if (ed.getPrice() > 10) {
            System.out.println(ed.getPrice());
            System.out.println(ed.getEventName());
            return 1;
        }  else {
            if (ed.getEventName().equals("test")) {
                System.out.println(ed.getPrice());
                System.out.println(ed.getEventName());
                return 2;
            }
        }

        return 0;
    }
}
