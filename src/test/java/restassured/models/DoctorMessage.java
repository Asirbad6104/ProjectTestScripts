package restassured.models;


public class DoctorMessage {
    private int id;
    private String patientName;
    private String diseaseType;
    private String severity;
    private UserInfo sender;
    private UserInfo receiver;
    private String content;
    private String timestamp;
    private boolean resolved;

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public boolean isResolved() { return resolved; }
    public void setResolved(boolean resolved) { this.resolved = resolved; }

    public UserInfo getSender() { return sender; }
    public void setSender(UserInfo sender) { this.sender = sender; }

    public UserInfo getReceiver() { return receiver; }
    public void setReceiver(UserInfo receiver) { this.receiver = receiver; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public String getDiseaseType() { return diseaseType; }
    public void setDiseaseType(String diseaseType) { this.diseaseType = diseaseType; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
}

