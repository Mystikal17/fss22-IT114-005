package Project2.common;

import Project2.server.GameRoom;

public class Player {
    private boolean isReady = false;
    private GameRoom.Choice choice;
    private boolean hasChosen = false;
    private boolean isSpectator = false;
   

    public GameRoom.Choice getChoice(){
        return choice;
    }

    public void setChoice(GameRoom.Choice choice){
        if(!hasChosen){
            this.choice = choice;
            hasChosen = true;
        }
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }

    public boolean isReady() {
        return this.isReady;
    }

    public void setSpectator(boolean isSpectator){
        this.isSpectator = isSpectator;
    }

    public boolean isSpectator(){
        return this.isSpectator;
    }
}