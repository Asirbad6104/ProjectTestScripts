package restassured.models;
import java.util.List;

public class DoctorResponse {

    private String diagnosis;
    private List<Medication> medications;

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public List<Medication> getMedications() { return medications; }
    public void setMedications(List<Medication> medications) { this.medications = medications; }

    public static class Medication {
        private String medicationName;
        private String dosageTiming;
        private int durationDays;
        private String startDate;

        public String getMedicationName() { return medicationName; }
        public void setMedicationName(String medicationName) { this.medicationName = medicationName; }

        public String getDosageTiming() { return dosageTiming; }
        public void setDosageTiming(String dosageTiming) { this.dosageTiming = dosageTiming; }

        public int getDurationDays() { return durationDays; }
        public void setDurationDays(int durationDays) { this.durationDays = durationDays; }

        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }
    }
}


