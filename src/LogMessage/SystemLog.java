package LogMessage;

import java.util.ArrayList;

public class SystemLog {
    private ArrayList<LogMessage> messageList;

    public SystemLog() {
        messageList = new ArrayList<>();
    }

    public void addMessage(LogMessage message)
    {
        messageList.add(message);
    }

    public ArrayList<LogMessage> getMessageList() {
        return messageList;
    }

    public ArrayList<LogMessage> removeMessages(String keyword) {
        ArrayList<LogMessage> removedMessages = new ArrayList<>();

        for (LogMessage m : messageList)
        {
            if (m.containsWord(keyword))
            {
                removedMessages.add(m);
            }
        }

        for (LogMessage rm : removedMessages)
        {
            messageList.remove(rm);
        }

        return removedMessages;
    }
}
