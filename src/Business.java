import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Business {

    private String number = "", created_at, complited_at, name, description;

    public Business (String name) {
        this.number = this.generateNumber();
        this.name = name;
        this.created_at = new SimpleDateFormat("d.M.y HH:mm:ss").format(new Date());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void complite() {
        this.complited_at = new SimpleDateFormat("d.M.y HH:mm:ss").format(new Date());
    }

    public String detail() {
        return "Название :" + this.name +"\n"
                + "Номер :" + this.number + "\n"
                + "Дата создание" + this.created_at + "\n"
                + (this.complited_at != null ? ("Дата выполнения :" + this.complited_at) : "Не выполнено") + "\n"
                + "Описание :" + (this.description == null ? "Отсутсвует" : this.description);
    }

    public String getNumber() {
        return this.number;
    }

    public String getName() {
        return this.name;
    }

    public String getInfo() {
        return "Номер - " + this.getNumber() + " Название - " + this.getName();
    }

    private String generateNumber() {
        String numbers = "";
        for (int i = 0; i < 10; i++) {
            number += Character.toString((char) (new Random().nextInt(26) + 'A'));
        }
        return number;
    }

    public List<String> getFields() {
        List<String> fields =  new ArrayList<>();
        fields.add(complited_at == null ? "Выполнить" : "Обновить время выполнения " + this.complited_at);
        fields.add("Название -  " + this.name);
        fields.add("Описание -  " + (this.description == null ? "Отсутсвует" : this.description));
        return fields;
    }

    public boolean is_compliated() {
        return !(this.complited_at == null);
    }

    public boolean checkCompliatedDate(String date) {
        if (complited_at == null) {
            return false;
        }
        String [] Arr;
        String [] D = this.complited_at.split(" ");
        return D[0].equals(date);
    }

    public boolean checkCreatedDate(String date) {
        String [] D = this.created_at.split(" ");
        return D[0].equals(date);
    }
}
