/**
 * A class for medicine reminders, which include the prescription and the time period during which the prescription should be taken.
 */

public class MedicineReminder {
    private int id;
    private int userId;
    private String medicineName;
    private String dosage;
    private String schedule;
    private String startDate;
    private String endDate;

    // Constructor

    /**
     * Constructor for a MedicineReminder.
     * @param id
     * @param userId
     * @param medicineName
     * @param dosage
     * @param schedule
     * @param startDate
     * @param endDate
     */
    public MedicineReminder(int id, int userId, String medicineName, String dosage, String schedule, String startDate, String endDate) {
        this.id = id;
        this.userId = userId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for a MedicineReminder which does not yet have an ID.
     * @param userId
     * @param medicineName
     * @param dosage
     * @param schedule
     * @param startDate
     * @param endDate
     */
    public MedicineReminder(int userId, String medicineName, String dosage, String schedule, String startDate, String endDate) {
        this.id = -1;
        this.userId = userId;
        this.medicineName = medicineName;
        this.dosage = dosage;
        this.schedule = schedule;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public int getUserId() {return userId;}
    public void setUserId(int userId) {this.userId = userId;}

    public String getMedicineName() {return medicineName;}
    public void setMedicineName(String medicineName) {this.medicineName = medicineName;}

    public String getDosage() {return dosage;}
    public void setDosage(String dosage) {this.dosage = dosage;}

    public String getSchedule() {return schedule;}
    public void setSchedule(String schedule) {this.schedule = schedule;}

    public String getStartDate() {return startDate;}
    public void setStartDate(String startDate) {this.startDate = startDate;}

    public String getEndDate() {return endDate;}
    public void setEndDate(String endDate) {this.endDate = endDate;}
}
