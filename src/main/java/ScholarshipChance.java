public class ScholarshipChance implements Motivator {
    public void apply(Student student){
        int motivation = student.getMotivation();
        motivation += 20;
        student.setMotivation(motivation);
    }
    public String getName(){
        return "Szansa na stypendium";
    }
}
