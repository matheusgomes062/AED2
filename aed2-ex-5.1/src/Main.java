import java.lang.*;
import java.util.Scanner;

    //MAIN START -------------------------------------------------------------------
    public class Main {

        //TREE START -------------------------------------------------------------------
        public static class Tree {
            private Node rootNode;

            public boolean isLeaf(Node a){
                if(a.getRightNode()==null && a.getLeftNode()==null) {
                    return true;
                }
                return false;
            }

            public int getMax(int a, int b){
                return (a>b) ? a : b;
            }

            //function to get the height of a tree or node
            public int getHeight(Node a){
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
                    node.setRightNode(deleteRecursively(rootNode.getRightNode(), rootNode.getValue()));
                }

                return node;

            }

            public int inOrderSuccessor(Node root) {
                int minimum = root.getValue();
                while (root.getLeftNode() != null) {
                    minimum = root.getLeftNode().getValue();
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
        //TREE END -------------------------------------------------------------------
        //NODE START -------------------------------------------------------------------

        public static class Node {
            private Node leftNode;
            private Node rightNode;
            private int value;

            public Node(int value) {
                leftNode = null;
                rightNode = null;
                this.value = value;
            }

            public void setLeftNode(Node node){
                this.leftNode = node;
            }
            public void setRightNode(Node node){
                this.rightNode = node;
            }

            public void setValue(int value){
                this.value = value;
            }
            public int getValue() {
                return value;
            }

            public Node getLeftNode() {
                return leftNode;
            }
            public Node getRightNode() {
                return rightNode;
            }

            @Override
            public String toString() {
                String result = value + "";
                if (leftNode  != null) result = leftNode.toString() + "-" + result;
                if (rightNode != null) result = result + "-" + rightNode.toString();
                return result;
            }
        }

        //NODE END -------------------------------------------------------------------

        int _treeHeightStart = 0, _treeHeightEnd = 0, _dictionarySizeStart = 0, _dictionarySizeEnd = 0;

        public void readInputToAdd(Scanner scanner, Tree myTree) {

            int input;

            // read every input until it's negative
            while ((input = scanner.nextInt()) > 0) {
                _dictionarySizeStart++;
                myTree.insertValue(input);
            }
            _treeHeightStart = myTree.getHeight(myTree.getFirstNode());
        }

        public void readInputToSearch(Scanner scanner, Tree myTree) {

            int input;

            // read the number to search
            input = scanner.nextInt();
            boolean hasValue = myTree.containsValue(input);
            if (!hasValue) {
                myTree.insertValue(input);
                _treeHeightEnd ++;
                _dictionarySizeEnd ++;
            } else {
                myTree.deleteRecursively(myTree.getFirstNode(), input);
                _treeHeightEnd --;
                _dictionarySizeEnd --;
            }
            _dictionarySizeEnd += _dictionarySizeStart;
            _treeHeightEnd += _treeHeightStart;
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Main main = new Main();

            Tree myTree = new Tree();
            main.readInputToAdd(scanner, myTree);
            main.readInputToSearch(scanner, myTree);
            System.out.println(main._dictionarySizeStart + " " + main._treeHeightStart);
//            System.out.println(main._treeHeightStart);
            System.out.println(main._dictionarySizeEnd + " " + main._treeHeightEnd);
//            System.out.println(main._treeHeightEnd);
            System.exit(0);
        }
    }

    //MAIN END -------------------------------------------------------------------
