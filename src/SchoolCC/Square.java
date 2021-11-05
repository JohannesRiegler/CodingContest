package SchoolCC;

public class Square {
    public int posX;
    public int posY;
    public int size;

    Square(int x, int y, int s) {
        posX = x;
        posY = y;
        size = s;
    }

    @Override
    public String toString() {
        return posY + " " + posX;
    }
}