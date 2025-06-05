public class Disease implements Motivator{
        public void apply(Student student){
            int motivation = student.getMotivation();
            motivation -= 30;
            student.setMotivation(motivation);
        }
        public String getName(){
            return "Choroba";
        }
}
