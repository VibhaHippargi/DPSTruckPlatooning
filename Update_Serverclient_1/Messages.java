public enum Messages {
    START("Server started!"),
    CONNECT("Connecting client"),
    ACCELERATE(""),
    DECELERATE(""),
    DISCONNECT("Disconnecting client");

    private String message;
    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}


//for(Messages message : Messages.values())
//        {
//        System.out.println(message.name() + " :: " + message.getMessage());
//        }