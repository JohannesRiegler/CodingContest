package School;

public class Trade {
    public int targetId;
    public int mySweetType;
    public int targetSweetType;
    public int gain;

    public Trade(int targetId, int mySweetType, int targetSweetType, int gain) {
        this.targetId = targetId;
        this.mySweetType = mySweetType;
        this.targetSweetType = targetSweetType;
        this.gain = gain;
    }
}
