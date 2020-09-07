import java.lang.*;
import java.util.Scanner;

public class Main {

    int _treeHeightStart = 0, _treeHeightEnd = 0, _dictionarySizeStart = 0, _dictionarySizeEnd = 0;

    public void readInputToAdd(Scanner scanner, Tree myTree) {

        int input;

        // read every input unitl it's negative
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
        if (hasValue == false) {
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
        System.out.println(main._dictionarySizeStart);
        System.out.println(main._treeHeightStart);
        System.out.println(main._dictionarySizeEnd);
        System.out.println(main._treeHeightEnd);
        System.exit(0);
    }
}
