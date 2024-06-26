/**
 * Class which represents a single recording of a user's health status, including weight, height, step count, heart rate, and hemoglobin A1C.
 */

public class HealthData {
    private int id;
    private int userId;
    private double weight;
    private double height;
    private int steps;
    private int heartRate;
    private String date;
    private double a1c;

    /**
     * Constructor for HealthData
     * @param id
     * @param userId
     * @param weight
     * @param height
     * @param steps
     * @param heartRate
     * @param date
     * @param a1c
     */
    public HealthData(int id, int userId, double weight, double height, int steps, int heartRate, String date, double a1c) {
        this.id = id;
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
        this.a1c = a1c;
    }

    /**
     * Alternate constructor which does not take an ID. Useful if the data does not yet exist in the database.
     * @param userId
     * @param weight
     * @param height
     * @param steps
     * @param heartRate
     * @param date
     * @param a1c
     */
    public HealthData(int userId, double weight, double height, int steps, int heartRate, String date, double a1c) {
        this.id = -1;
        this.userId = userId;
        this.weight = weight;
        this.height = height;
        this.steps = steps;
        this.heartRate = heartRate;
        this.date = date;
        this.a1c = a1c;
    }

    public int getId() {return id;}
    public int getUserId() {return userId;}
    public double getWeight() {return weight;}
    public double getHeight() {return height;}
    public int getSteps() {return steps;}
    public int getHeartRate() {return heartRate;}
    public String getDate() {return date;}
    public double getA1C() {return a1c;}

    public void setId(int id) {this.id = id;}
    public void setUserId(int userId) {this.userId = userId;}
    public void setWeight(double weight) {this.weight = weight;}
    public void setHeight(double height) {this.height = height;}
    public void setSteps(int steps) {this.steps = steps;}
    public void setHeartRate(int heartRate) {this.heartRate = heartRate;}
    public void setDate(String date) {this.date = date;}
    public void setA1C(double a1c) {this.a1c = a1c;}
}