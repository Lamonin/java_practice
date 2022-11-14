package LogMessage;

public class LogMessage {
    private String machineId;
    private String description;

    public LogMessage(String message) {
        String[] splitMessage = message.split(":");
        machineId = splitMessage[0];
        description = splitMessage[1];
    }

    public boolean containsWord(String wordToFind) {
        for (String str : description.split(" "))
        {
            if (str.equals(wordToFind)){
                return true;
            }
        }

        return false;
    }

    public String getMessage() {
        return machineId + ":" + description;
    }

    public String getMachineId() {
        return machineId;
    }

    public String getDescription() {
        return description;
    }
}
