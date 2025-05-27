public class PrzerwaNaPiwo implements Motivator {
    void apply(Student student){
        int motivation = student.getMotivation();
        motivation += 10;
        if (motivation > 100) {
            motivation = 100;
        }
        student.setMotivation(motivation);
    }
    String getName(){
        return "PrzerwaNaPiwo";
    }
}
