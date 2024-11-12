package com.example.project;

public class BT<T> {
    BTNode<T> root, current;

    /** Creates a new instance of BT */
    public BT() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public T retrieve() {
        return current.data;
    }

    public void update(T val) {
        current.data = val;
    }

    public boolean insert(Relative rel, T val) {
        switch(rel) {
            case Root:
                if (!empty()) return false;
                current = root = new BTNode<T>(val);
                return true;
            case LeftChild:
                if (current.left != null) return false;
                current.left = new BTNode<T>(val);
                current = current.left;
                return true;
            case RightChild:
                if (current.right != null) return false;
                current.right = new BTNode<T>(val);
                current = current.right;
                return true;
            default:
                return false;
        }
    }

    public void deleteSubtree() {
        if (current == root) {
            current = root = null;
        } else {
            BTNode<T> p = current;
            find(Relative.Parent);
            if (current.left == p)
                current.left = null;
            else 
                current.right = null;
            current = root;
        }
    }

    public boolean find(Relative rel) {
        switch (rel) {
            case Root:
                current = root;
                return true;
            case Parent:
                if (current == root) return false;
                current = findparent(current, root);
                return true;
            case LeftChild:
                if (current.left == null) return false;
                current = current.left;
                return true;
            case RightChild:
                if (current.right == null) return false;
                current = current.right;
                return true;
            default:
                return false;
        }
    }

    private BTNode<T> findparent(BTNode<T> p, BTNode<T> t) {
        if (t == null) return null;
        if (t.right == p || t.left == p) return t;
        BTNode<T> q = findparent(p, t.left);
        if (q != null) return q;
        return findparent(p, t.right);
    }

    public boolean isLeaf() {
        return current.left == null && current.right == null;
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(BTNode<T> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }
}
