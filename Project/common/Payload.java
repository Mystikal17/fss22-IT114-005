package Project.common;

import java.io.Serializable;
public class Payload implements Serializable {
    //read https://www.baeldung.com/java-serial-version-uid
    private static final long serialVersionUID = 1L;//change this if the class changes
    private boolean startGame;

    private int xCoordinate;
    private int yCoordinate;
    private String color;

    public int getXCoordinate(){
        return xCoordinate;
    }
    public void setXCoordinate(int xCoordinate){
        this.xCoordinate = xCoordinate;
    }
    public int getYCoordinate(){
        return yCoordinate;
    }
    public void setYCoordinate(int yCoordinate){
        this.yCoordinate = yCoordinate;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }

    public boolean isStartGame(){
        return startGame;
    }

    public void setStartGame(boolean startGame){
        this.startGame = startGame;
    }

    /**
     * Determines how to process the data on the receiver's side
     */
    private PayloadType payloadType;
    public PayloadType getPayloadType() {
        return payloadType;
    }
    public void setPayloadType(PayloadType payloadType) {
        this.payloadType = payloadType;
    }

    /**
     * Who the payload is from
     */
    private String clientName;
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Generic text based message
     */
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Generic number for example sake
     */
    private int number;
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
	return String.format("Type[%s], Number[%s], Message[%s]", getPayloadType().toString(), getNumber(),
		getMessage());
    }
}