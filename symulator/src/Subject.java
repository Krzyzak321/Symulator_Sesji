public class Subject {
    private String name;
    private int difficulty;
    private int predispositions;
    private int requiredTime;
    private int studiedTime;
    private int ects;

    public void study(int hours) {
        // implementacja
    }

    public boolean isReady() {
        // implementacja
        return false;
    }

    public double getProgress() {
        // implementacja
        return 0.0;
    }

    public String getName() {
        return name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getPredispositions() {
        return predispositions;
    }
}