import org.apache.tools.ant.Task;
import org.apache.tools.ant.BuildException;
import java.util.List;
import java.util.ArrayList;

public class HelloWorld extends Task {

  private String message;
  private String nestedMessage;
  private List<Message> messages = new ArrayList<>();

  public void execute() {

    /* Print attribute message */
    if (message != null) log("Attribute message: " + message);

    /* Print nested text */
    if (nestedMessage != null && !nestedMessage.isEmpty()) {
      log("Nested message: " + nestedMessage);
    }

    /* Print nested elements */
    for (Message message : messages) {
      log("Nested element: " + message.getMessage());
    }
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Message createMessage() {
    Message message = new Message();
    messages.add(message);
    return message;
  }

  public void addText(String nestedMessage) {
    this.nestedMessage = nestedMessage.replaceAll("\\s+","");
  }

  public class Message {

    private String message;

    public Message() {}

    public void setMessage(String message) {
      this.message = message;
    }

    public String getMessage() {
      return this.message;
    }
  }
}

