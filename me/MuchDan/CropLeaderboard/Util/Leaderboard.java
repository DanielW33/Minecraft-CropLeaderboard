package me.MuchDan.CropLeaderboard.Util;

public class Leaderboard {
    private String UUID;
    private int Value;

    public String getUUID(){
        return UUID;
    }
    public int getValue(){
        return Value;
    }
    public void setUUID(String UUID){
        this.UUID = UUID;
    }
    public void setValue(int Value){
        this.Value = Value;
    }
}
