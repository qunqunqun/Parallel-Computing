package parallel.quick_sort;

public class QRES {
    private int left = 0;
    private int right = 0;
    private int middle = 0;

    public QRES(int left, int right, int middle) {
        this.left = left;
        this.right = right;
        this.middle = middle;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getMiddle() {
        return middle;
    }

    public boolean cmpLeftMiddle()
    {
        return left < middle - 1;
    }

    public boolean cmpMiddleRight()
    {
        return middle + 1 < right;
    }

}
