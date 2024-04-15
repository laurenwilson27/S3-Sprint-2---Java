/**
 * A child class of User which also contains a medical license number, and a medical specialization.
 */

public class Doctor extends User{
    private String medicalLicenseNumber;
    private String specialization;

    /**
     * Constructor for a Doctor
     *
     * @param id
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param isDoctor
     * @param medicalLicenseNumber
     * @param specialization
     */
    public Doctor(int id, String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
        super(id, firstName, lastName, email, password, isDoctor);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }

    /**
     * Alternate constructor for a Doctor, without an id field. This is useful for creating a Doctor that does not yet exist in the database.
     *
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @param isDoctor
     * @param medicalLicenseNumber
     * @param specialization
     */
    public Doctor(String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
        super(firstName, lastName, email, password, isDoctor);
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.specialization = specialization;
    }

    // Getters and setters for the new properties
    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setMedicalLicenseNumber(String number) {
        medicalLicenseNumber = number;
    }

    public void setSpecialization(String specialty) {
        specialization = specialty;
    }
}

