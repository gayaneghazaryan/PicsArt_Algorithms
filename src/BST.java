public class BST {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    Node root;

    public BST() {
        root = null;
    }


    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if(node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    public void preorder() {
        preorder(root);
    }

    public void preorder(Node node) {
        if(node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder() {
        postorder(root);
    }

    public void postorder(Node node) {
        if(node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.value + " ");
    }

    public void levelOrder() {
        levelOrder(root);
    }

    public void levelOrder(Node node) {
        int height = height(node);
        for(int i = 1; i <= height; i++ ) {
            currentLevel(node,i);
        }
    }

    private int height(Node node) {
        int totalHeight;
        if(node == null) {
            return 0;
        }
        else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);

            totalHeight = Math.max(leftHeight, rightHeight) +1;

        }
        return totalHeight;
    }

    public void currentLevel(Node node, int level) {
        if(node == null) {
            return;
        }
        if(level == 1) {
            System.out.print(node.value + " ");
        }
        else if( level > 1) {
            currentLevel(node.left, level-1);
            currentLevel(node.right, level-1);
        }
    }

    public void insert(int value) {
        root = insert(root,value);
    }

    private Node insert(Node node,int value) {
        if(node == null) {
            node = new Node(value);
            return node;
        }

        if(value < node.value) {
            node.left = insert(node.left,value);
        } else if(value > node.value) {
            node.right = insert(node.right,value);
        }
        return node;
    }

    public void delete(int value) {
        delete(root,value);
    }
    private Node delete(Node node, int value) {
        if(node == null) {
            return null;
        }
        //the deleted value is smaller than the root's value
        if(value < node.value) {
            node.left = delete(node.left,value);
            //the deleted value is bigger than the root's value
        } else if ( value > node.value) {
            node.right = delete(node.right,value);
        } else {
            //node with one child or no child
            if(node.left == null) {
                return node.right;
            } else if( node.right == null) {
                return node.left;
            }
            //node with two children

            node.value = minValue(node.right).value;
            node.right = delete(node.right,node.value);
        }
        return node;
    }

    private Node minValue(Node node) {
        if(node.left == null) {
            return node;
        }
        return minValue(node.left);
    }


    public static void main(String[] args) {
        int n = 10;

        BST bst = new BST();
        bst.insert(50);
        bst.insert(20);
        bst.insert(10);
        bst.insert(15);
        bst.insert(80);
        bst.insert(55);
        bst.insert(100);

        bst.levelOrder();
        bst.delete(55);
        System.out.println();
        System.out.println("After deletion");
        bst.levelOrder();

    }
}
