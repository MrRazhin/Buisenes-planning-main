import java.util.*;

public class Main {
    public static List<Business> businesses = new ArrayList<>();
    public static BusinessController controller = new BusinessController();
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        controller.menu();
        int command = scanner.nextInt();
        while (true) {
            System.out.println(listener(command));
            controller.menu();
            command = scanner.nextInt();
        }
    }

    private static String listener(int command) {
        switch (command) {
            case 1 : {
                return controller.create(businesses);
            }
            case 2 : {
                return controller.delete(businesses);
            }
            case 3 : {
                return controller.edit(businesses);
            }
            case 4 : {
                System.out.println("Список всех заданий");
                return controller.index(businesses);
            }
            case 5 : {
                return controller.compliteIndex(businesses, true);
            }
            case 6 : {
                return controller.compliteIndex(businesses, false);
            }
            case 7 : {
                System.out.println("Введите дату создания в формате dd.mm.yyyy");
                return controller.dateAction(businesses, true);
            }
            case 8 : {
                System.out.println("Введите дату выполнения в формате dd.mm.yyyy");
                return controller.dateAction(businesses, false);
            }
            case 9 : {
                return controller.show(businesses);
            }
            default: return "Такой команды нет";
        }
    }
}