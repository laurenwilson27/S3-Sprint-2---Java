
public class Doctor extends User{
    private String medicalLicenseNumber;
    private String specialization;

    public Doctor(int id, String firstName, String lastName, String email, String password, boolean isDoctor, String medicalLicenseNumber, String specialization) {
        super(id, firstName, lastName, email, password, isDoctor);
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

