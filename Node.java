package RedBlackTree;

public class Node {
    private Integer value;

    private NodeColor color;

    private Node parent;

    private Node left;

    private Node right;

    public Node() { //нода по умолчанию
        value = null;
        color = NodeColor.NONE;
        parent = null;
        left = null;
        right = null;
    }

    public Node(int value, NodeColor color) { //инициализируем
        this.value = value;
        this.color = color;
        parent = null;
        left = null;
        right = null;
    }

    public Node(Node node) { //конструктор копий, еще один экз с такими же данными
        value = node.value;
        color = node.color;
        parent = node.parent;
        left = node.left;
        right = node.right;
    }

    public boolean isFree() { // отвечает за значение в листьях = null
        return value == null;
    }

    public boolean isLeftFree() { //несуществование ребенка в принципе
        return left == null;
    }

    public boolean isRightFree() {
        return right == null;
    }

    public boolean isParentFree() {
        return parent == null;
    }

    public Integer getValue() { //получение значения
        return value;
    }

    public void setValue(int value) { //установка значения
        this.value = value;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node node) {
        parent = node;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node node) {
        left = node;
        if(left != null) left.parent = this;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node node) {
        right = node;
        if(right != null) right.parent = this;
    }

    public boolean isBlack() {
        return color == NodeColor.BLACK;
    }

    public void makeBlack() {
        color = NodeColor.BLACK;
    }

    public boolean isRed() {
        return color == NodeColor.RED;
    }

    public void makeRed() {
        color = NodeColor.RED;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    public Node getGrandfather() {
        if(parent != null)
            return parent.parent;
        return null; //если родитель налл,если у родителя нет родителя
    }

    public Node getUncle() {
        Node grand = getGrandfather();
        if(grand != null) {
            if(grand.left == parent)
                return grand.right;
            else if(grand.right == parent)
                return grand.left;
        }
        return null;
    }

    public Node getNextValue() {
        Node temp = null;
        Node node = this;

        if(!node.isRightFree()) {
            temp = node.getRight();
            while(!temp.isLeftFree())
                temp = temp.getLeft();
            return temp;
        }
        temp = node.getParent();
        while(temp != null && node == temp.getRight()) {
            node = temp;
            temp = temp.getParent();
        }
        return temp;
    }

    public String getColorName() {
        return ((isBlack()) ? "B" : "R");
    }
}