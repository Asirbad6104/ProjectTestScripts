package restassured.models;


public class PatientDashboard {
    private String diseaseType;
    private String content;
    private String severity;

    public String getDiseaseType() { return diseaseType; }
    public void setDiseaseType(String diseaseType) { this.diseaseType = diseaseType; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
}
