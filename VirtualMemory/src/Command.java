class Command implements Runnable{
    private string CommandId;
    private int firstParameter;
    private int secondParameter;

    public Command(string commandId, int firstParameter, int secondParameter) {
        CommandId = commandId;
        this.firstParameter = firstParameter;
        this.secondParameter = secondParameter;
    }
    @Override
    public void run() {
    }
    public string getCommandId() {
        return CommandId;
    }

    public void setCommandId(string commandId) {
        CommandId = commandId;
    }

    public int getFirstParameter() {
        return firstParameter;
    }

    public void setFirstParameter(int firstParameter) {
        this.firstParameter = firstParameter;
    }

    public int getSecondParameter() {
        return secondParameter;
    }

    public void setSecondParameter(int secondParameter) {
        this.secondParameter = secondParameter;
    }
}