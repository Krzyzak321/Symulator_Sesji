import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Procrastination implements Motivator {
    public void apply(Student student){
        int motivation = student.getMotivation();
        motivation -= 10;

        student.setMotivation(motivation);
    }
    public String getName(){
        return "Brak chęci do nauki";
    }
    //    private List<Motivator> motivators;
//    private Random random = new Random();
//    public CoffeeBreak() {
//        this.motivators = new ArrayList<>();
//        motivators.add("będe mial lepsze samopoczucie");
//        motivators.add("Kasia się ze mną umowi");
//        motivators.add("bede mial szanse na lepsza prace ");
//        motivators.add("tragedia w rodzinie");
//        motivators.add("zmęczenie");
//        motivators.add("brak czasu");
//
//    }
//    @Override
//    public void apply(Student student) {
//        if (motivators.isEmpty()) return;
//        int index = random.nextInt(motivators.size());
//        System.out.println(motivators.get(index));
//        if (index >= 0 && index <= 2) {
//            student.setMotivation(student.getMotivation() + 1);
//        } else if (index >= 3 && index <= 5) {
//            student.setMotivation(student.getMotivation() - 1);
//        }
//    }
//
//    @Override
//    public String getName() {
//        return "Procrastination";
//    }
}