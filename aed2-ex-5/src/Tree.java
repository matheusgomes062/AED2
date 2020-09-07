import java.util.Scanner;

public class Tree {
    private Node rootNode;

    public static boolean isLeaf(Node a){
        if(a.getRightNode()==null && a.getLeftNode()==null) {
            return true;
        }
        return false;
    }

    public static int getMax(int a, int b){
        return (a>b) ? a : b;
    }

    //function to get the height of a tree or node
    public static int getHeight(Node a){
        if(a==null) return 0;
        if(isLeaf(a)) return 1;
        //height of a node will be 1+ greater among height of right subtree and height of left subtree
        return(getMax(getHeight(a.getLeftNode()), getHeight(a.getRightNode())) + 1);
    }

    public void insertValue(int value) {
        insertNode(new Node(value));
    }

    public void insertNode(Node newNode){
        if (rootNode == null) {
            rootNode = new Node(newNode.getValue());
        } else {
            insertNode(rootNode, newNode);
        }
    }

    private void insertNode(Node currentNode, Node newNode) {
        if (newNode.getValue() < currentNode.getValue()) {
            if (currentNode.getLeftNode() == null) {
                currentNode.setLeftNode(newNode);
            }
            else
                insertNode(currentNode.getLeftNode(), newNode);
        }
        if (newNode.getValue() > currentNode.getValue()) {
            if (currentNode.getRightNode() == null)
                currentNode.setRightNode(newNode);
            else
                insertNode(currentNode.getRightNode(), newNode);
        }
    }

    public boolean containsValue(int value){
        if (getNode(rootNode, value) == null) {
            return false;
        }
        return true;
    }

    public Node getNode(Node currentNode, int value){
        if (currentNode == null)
            return null;
        if (currentNode.getValue() == value)
            return currentNode;
        if (value < currentNode.getValue())
            return getNode(currentNode.getLeftNode(), value);
        return getNode(currentNode.getRightNode(), value);
    }

    public Node getFirstNode() {
        return rootNode;
    }

    public Node deleteRecursively(Node node, int value) {

        if (node == null)
            return node;

        if (value < node.getValue()) {
            node.setLeftNode(deleteRecursively(node.getLeftNode(), value));
        } else if (value > node.getValue()) {
            node.setRightNode(deleteRecursively(node.getRightNode(), value));
        } else {

            if (node.getLeftNode() == null) {
                return node.getRightNode();
            } else if (node.getRightNode() == null)
                return node.getLeftNode();

            node.setValue(inOrderSuccessor(rootNode.getRightNode()));
            node.setRightNode(deleteRecursively(rootNode.getRightNode(), (int) rootNode.getValue()));
        }

        return node;

    }

    public static int inOrderSuccessor(Node root) {
        int minimum = (int) root.getValue();
        while (root.getLeftNode() != null) {
            minimum = (int) root.getLeftNode().getValue();
            root = root.getLeftNode();
        }
        return minimum;
    }

    @Override
    public String toString(){
        if (rootNode != null){
            return rootNode.toString();
        }
        return "";
    }
}