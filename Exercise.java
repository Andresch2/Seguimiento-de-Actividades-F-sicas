public class Exercise {
    private String type;
    private String day;
    private String time;

    public Exercise(String type, String day, String time) {
        this.type = type;
        this.day = day;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }
}
