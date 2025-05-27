import java.util.List;
import java.util.ArrayList;
import java.util.Random;

//public class CoffeeBreak implements Motivator {
//    private List<Motivator> motivators;
//    private Random random = new Random();
//
//    public CoffeeBreak() {
//        this.motivators = new ArrayList<>();
//        motivators.add("dostane pieniadze od rodzicow");
//        motivators.add("dostane piwo od kolegi");
//        motivators.add("bede mial szansę na stypendium");
//        motivators.add("bede mogl pojechać na wakacje");
//        motivators.add("będe mógł kupić nowy komputer");
//        motivators.add("zły humor");
//        motivators.add("złe wydarzenie w moim życiu");
//        motivators.add("lenistwo");
//        motivators.add("choroba");
//        motivators.add("dziewczyna mnie rzuciła");
//    }
//    @Override
//    public void apply(Student student) {
//        if (motivators.isEmpty()) return;
//        int index = random.nextInt(motivators.size());
//        System.out.println(motivators.get(index));
//        if (index >= 0 && index <= 4) {
//            student.setMotivation(student.getMotivation() + 1);
//        } else if (index >= 5 && index <= 9) {
//            student.setMotivation(student.getMotivation() - 1);
//        }
//    }
//
//    @Override
//    public String getName() {
//        return "Coffee Break";
//    }
//}