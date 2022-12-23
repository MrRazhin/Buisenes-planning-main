import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BusinessController {
    Scanner scanner = new Scanner(System.in);

    public String create(List<Business> businesses) {
        System.out.println("Введите название задания");
        String name = scanner.next();
        businesses.add(new Business(name));
        return "Задача создана";
    }

    public String edit (List<Business> businesses) {
        System.out.println("Введите номер задания которое хотите отредактировать или 0 для выхода в меню");
        System.out.println(this.index(businesses));
        String number = scanner.next();
        Business current_business = new Business(null);
        for(Business business: businesses) {
            if (business.getNumber().equals(number)) {
                current_business = business;
                break;
            }
        }
        if (current_business.getName()!= null) {
            System.out.println("Введите номер поля которое хотите отредактировать или 0 для выхода меню");
            int key = 1;
            for (String field: current_business.getFields()) {
                System.out.println(key + " " + field);
                key++;
            }
            int field = scanner.nextInt();
            switch (field) {
                case 0 -> {
                    return "";
                }
                case 1 -> {
                    current_business.complite();
                    return "Задание выполнено";
                }
                case 2 -> {
                    String newName = scanner.next();
                    current_business.setName(newName);
                    return "Название изменено";
                }
                case 3 -> {
                    String newDescription = scanner.next();
                    current_business.setDescription(newDescription);
                    return "Описание изменено";
                }
                default -> {
                    System.out.println("Не верная команда");
                    return this.edit(businesses);
                }
            }
        } else if (number.equals("0")) {
            return "";
        } else {
            System.out.println("Не верный номер");
            return this.edit(businesses);
        }
    }

    public String delete(List<Business> businesses) {
        System.out.println("Введите номер задания которое нужно удалить или 0 для выхода в меню");
        System.out.println(this.index(businesses));
        String number = scanner.next();
        for(Business business: businesses) {
            if (business.getNumber().equals(number)) {
                businesses.remove(business);
                return "Задача удалена";
            }
        }
        if (number.equals("0")) {
            return "";
        } else {
            System.out.println("Нет такого номера");
            return this.delete(businesses);
        }
    }

    public String index (List<Business> businesses) {
        String list = "";
        for (Business business: businesses) {
             list += business.getInfo() + "\n";
        }
        return list.length() > 1 ? list : "Список пуст";
    }

    public String compliteIndex(List<Business> businesses, boolean complite) {
        System.out.println(complite ? "Список выполненных заданий" : "Список не выполненных заданий");
        String list = "";
        for (Business business: businesses) {
            if ((complite && business.is_compliated()) || (!complite && !business.is_compliated())) {
                list += business.getInfo() + "\n";
            }
        }
        return list.length() > 1 ? list : "Список пуст";
    }

    public String show (List<Business> businesses) {
        System.out.println("Введите номер задачи чтоб посмотреть ее информацию или 0 для выхода в меню");
        System.out.println(this.index(businesses));
        String number = scanner.next();
        if (number.equals("0")) {
            return "";
        }
        for(Business business: businesses) {
            if (business.getNumber().equals(number)) {
                return business.detail();
            }
        }
        System.out.println("Нет такого номера");
        return this.show(businesses);
    }


    public String dateAction (List<Business> businesses , boolean created) {
        String date = scanner.next();
        if (date.equals("0")) {
            return "";
        }
        if (this.checkDate(date)) {
            List<Business> date_businesses = getDateBusinesses(businesses, date, created);
            if (date_businesses.isEmpty()) {
                return "Нет задач в на эту дату выполнения";
            }
            System.out.println(this.dateMenu());
            int time_command = scanner.nextInt();
            switch (time_command) {
                case 0 : {
                    return "";
                }
                case 1 : {
                    return this.index(date_businesses);
                }
                case 2 : {
                    return this.compliteIndex(date_businesses, true);
                }
                case 3 : {
                    return this.compliteIndex(date_businesses, false);
                }
                default: return this.dateAction(businesses, created);
            }
        }
        System.out.println("Не верно введена дата, попробуйте еще раз или введите 0 для выхода в меню");
        return this.dateAction(businesses , created);
    }

    private boolean checkDate(String date) {
        String [] D = date.split("\\.");
        return D.length == 3
                && D[0].length() == 2 && Integer.parseInt(D[0]) <=31
                && D[1].length() == 2 && Integer.parseInt(D[1]) <= 12
                && D[2].length() == 4;
    }

    private static  List<Business> getDateBusinesses(List<Business> businesses, String date, boolean created) {
        List<Business> businessList = new ArrayList<>();
        for (Business business: businesses) {
            if ((!created && business.checkCompliatedDate(date)) || (created && business.checkCreatedDate(date))) {
                businessList.add(business);
            }
        }
        return businessList;
    }

    private String dateMenu() {
        return "1. Посмотреть все задания\n2. Посмотреть выполненные задания\n3. Посмотреть не выполненные задания\n или 0 для выхода в меню";
    }

    public void menu() {
        String [] actions = new String[] {
                "Создать задание",
                "Удалить задание",
                "Отредактировть задание",
                "Просмотреть список ВСЕХ заданий",
                "Просмотреть список ВЫПОЛНЕННЫХ заданий",
                "Просмотреть список НЕВЫПОЛНЕННЫХ заданий",
                "Пункты 4-6 на конкертную дату ВЫПОЛНЕНИЯ",
                "Пункты 4-6 на конкертную дату СОЗДАНИЯ",
                "Посмотреть детальную инормацию о задании"
        };
        for (int i = 0; i < actions.length; i++) {
            System.out.println("Введите " + (i + 1) + " чтобы " + actions[i]);
        }
    }
}
