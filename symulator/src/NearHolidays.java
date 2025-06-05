public class NearHolidays implements Motivator {
    public void apply(Student student){
        int motivation = student.getMotivation();
        motivation += 15;
        student.setMotivation(motivation);
    }
    public String getName(){
        return "Niedługo przerwa od nauki";
    }
}
