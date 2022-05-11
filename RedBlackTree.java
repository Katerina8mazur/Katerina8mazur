package RedBlackTree;
import java.util.ArrayList;
import java.util.HashSet;

public class RedBlackTree implements SearchTree {
    private Node root;

    private Node NULL;
    private HashSet<Integer> set;

    public void clean() {
        set.clear();
    }

    public RedBlackTree() {
        root = new Node();
        NULL = new Node();
        NULL.setColor(NodeColor.BLACK);
        root.setParent(NULL);
        root.setRight(NULL);
        root.setLeft(NULL);
        set = new HashSet<>();
    }

    private static void leftRotate(RedBlackTree tree, Node node) {
        Node nodeParent = node.getParent();
        Node nodeRight = node.getRight();
        if(nodeParent != tree.NULL) {
            if(nodeParent.getLeft() == node)
                nodeParent.setLeft(nodeRight);
            else
                nodeParent.setRight(nodeRight);
        }
        else {
            tree.root = nodeRight;
            tree.root.setParent(tree.NULL);
        }
        node.setRight(nodeRight.getLeft());
        nodeRight.setLeft(node);
    }

    private static void rightRotate(RedBlackTree tree, Node node) {
        Node nodeParent = node.getParent();
        Node nodeLeft = node.getLeft();
        if(nodeParent != tree.NULL) {
            if(nodeParent.getLeft() == node)
                nodeParent.setLeft(nodeLeft);
            else
                nodeParent.setRight(nodeLeft);
        }
        else {
            tree.root = nodeLeft;
            tree.root.setParent(tree.NULL);
        }
        node.setLeft(nodeLeft.getRight());
        nodeLeft.setRight(node);
    }

    @Override
    public void add(int o) {
        Node node = root, temp = NULL;
        Node newNode = new Node(o, NodeColor.RED);
        while(node != null && node != NULL && !node.isFree()) {
            temp = node;
            if(newNode.getValue() < node.getValue())
                node = node.getLeft();
            else
                node = node.getRight();
        }
        newNode.setParent(temp);
        if(temp == NULL)
            root.setValue(newNode.getValue());
        else {
            if(newNode.getValue() < temp.getValue())
                temp.setLeft(newNode);
            else
                temp.setRight(newNode);
        }
        newNode.setLeft(NULL);
        newNode.setRight(NULL);
        fixInsert(newNode);
    }

    private void fixInsert(Node node) {
        Node temp;
        while(!node.isParentFree() && node.getParent().isRed()) {
            if(node.getParent() == node.getGrandfather().getLeft()) {
                temp = node.getGrandfather().getRight();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    node = node.getGrandfather();
                }
                else {
                    if(node == node.getParent().getRight()) {
                        node = node.getParent();
                        leftRotate(this, node);
                    }
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    rightRotate(this, node.getGrandfather());
                }
            }
            else {
                temp = node.getGrandfather().getLeft();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    node = node.getGrandfather();
                }
                else {
                    if(node == node.getParent().getLeft()) {
                        node = node.getParent();
                        rightRotate(this, node);
                    }
                    node.getParent().makeBlack();
                    node.getGrandfather().makeRed();
                    leftRotate(this, node.getGrandfather());
                }
            }
        }
        root.makeBlack();
    }

    @Override
    public boolean remove(int o) {
        return remove(findNode(o));
    }

    private boolean remove(Node node)
    {
        Node temp = NULL, nextVal = NULL;

        if(node == null || node == NULL) return false;

        if(node.isLeftFree() || node.isRightFree()) {
            nextVal = node;
        } else {
            nextVal = node.getNextValue();
        }

        if(!nextVal.isLeftFree())
            temp = nextVal.getLeft();
        else
            temp = nextVal.getRight();
        temp.setParent(nextVal.getParent());

        if(nextVal.isParentFree())
            root = temp;
        else if(nextVal == nextVal.getParent().getLeft())
            nextVal.getParent().setLeft(temp);
        else
            nextVal.getParent().setRight(temp);

        if(nextVal != node) {
            node.setValue(nextVal.getValue());
        }
        if(nextVal.isBlack())
            fixRemove(temp);
        return true;
    }

    private void fixRemove(Node node)
    {
        Node temp;
        while(node != root && node.isBlack()) {
            if(node == node.getParent().getLeft()) {
                temp = node.getParent().getRight();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeRed();
                    leftRotate(this, node.getParent());
                    temp = node.getParent().getRight();
                }
                if(temp.getLeft().isBlack() && temp.getRight().isBlack()) {
                    temp.makeRed();
                    node = node.getParent();
                }
                else {
                    if(temp.getRight().isBlack()) {
                        temp.getLeft().makeBlack();
                        temp.makeRed();
                        rightRotate(this, temp);
                        temp = node.getParent().getRight();
                    }
                    temp.setColor(node.getParent().getColor());
                    node.getParent().makeBlack();
                    temp.getRight().makeBlack();
                    leftRotate(this, node.getParent());
                    node = root;
                }
            }
            else {
                temp = node.getParent().getLeft();
                if(temp.isRed()) {
                    temp.makeBlack();
                    node.getParent().makeRed();
                    rightRotate(this, node.getParent());
                    temp = node.getParent().getLeft();
                }
                if(temp.getLeft().isBlack() && temp.getRight().isBlack()) {
                    temp.makeRed();
                    node = node.getParent();
                }
                else {
                    if(temp.getLeft().isBlack()) {
                        temp.getRight().makeBlack();
                        temp.makeRed();
                        leftRotate(this, temp);
                        temp = node.getParent().getLeft();
                    }
                    temp.setColor(node.getParent().getColor());
                    node.getParent().makeBlack();
                    temp.getLeft().makeBlack();
                    rightRotate(this, node.getParent());
                    node = root;
                }
            }
        }
        node.makeBlack();
    }

    @Override
    public boolean contains(int o) {
        return (findNode(o) != NULL);
    }

    private Node findNode(int o) {
        Node node = root;
        while(node != null && node != NULL && node.getValue() != o) {
            if(node.getValue() > o)
                node = node.getLeft();
            else
                node = node.getRight();
        }
        return node;
    }

    public static void printTree(RedBlackTree tree) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        nodes.add(0, tree.root);
        printNodes(tree, nodes);
    }

    private static void printNodes(RedBlackTree tree, ArrayList<Node> nodes) {
        int childsCounter = 0;
        int nodesAmount = nodes.size();
        ArrayList<Node> childs = new ArrayList<Node>();

        for(int i = 0; i < nodesAmount; i++) {
            if(nodes.get(i) != null && nodes.get(i) != tree.NULL) {
                System.out.print("(" + nodes.get(i).getValue().toString() + "," + nodes.get(i).getColorName() + ")");
                if(!nodes.get(i).isLeftFree()) {
                    childs.add(nodes.get(i).getLeft());
                    childsCounter++;
                }
                else
                    childs.add(null);
                if(!nodes.get(i).isRightFree()) {
                    childs.add(nodes.get(i).getRight());
                    childsCounter++;
                }
                else
                    childs.add(null);
            }
            else {
                System.out.print("(nil)");
            }
        }
        System.out.print("\n");

        if(childsCounter != 0)
            printNodes(tree, childs);
    }
}
