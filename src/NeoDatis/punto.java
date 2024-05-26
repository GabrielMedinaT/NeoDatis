package NeoDatis;

public class punto {
    private int x;
    private int y;
    private int z;

    public punto(int a, int b, int c) {
        this.x = a;
        this.y = b;
        this.z = c;
    }

    public void setX(int a) {
        this.x = a;
    }

    public void setY(int b) {
        this.y = b;
    }

    public void setZ(int c) {
        this.z = c;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.z;
    }

    public String mostrar() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }
}
