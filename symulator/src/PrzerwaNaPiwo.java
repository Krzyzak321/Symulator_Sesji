public class PrzerwaNaPiwo implements Motivator {
    public void apply(Student student){
        int motivation = student.getMotivation();
        motivation += 10;
        student.setMotivation(motivation);
    }
    public String getName(){
        return "Przerwa na piwo";
    }
}