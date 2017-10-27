public class BlackRedTree<K extends Comparable<K>, V> {

    Node<K, V> root;
    private class Node<k, V>
    {
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;
        public boolean isRed;
        public int N;
        public K key;

        public Node ()
        {
            isRed = false;
        }

        public Node(V value, K key, boolean color)
        {
            isRed = color;
            this.value = value;
            this.key = key;
            isRed = color;
            left = new Node<K, V>();
            right = new Node<K, V>();
        }
    }

    public BlackRedTree()
    {
        root = new Node<>();
    }

    public Node get(K key)
    {
        Node node = get(key, root);
        return node;
    }
    private Node get(K key, Node<K, V> node)
    {
        if (node == null)
        {
            return null;
        }

        if (key.compareTo(node.key) > 0)
        {
            get(key, node.right);
        }

        else if (key.compareTo(node.key) < 0)
        {
            get(key, node.left);
        }

        else
        {
            return node;
        }
        return null;
    }

    public void put(Node<K, V> node)
    {

    }

    private void put(Node<K, V> node, Node<K, V> toPut)
    {
        if (node.key.compareTo(toPut.key) > 0 && toPut.right != null)
        {
            put(node, toPut.right);
        }
        else if (node.key.compareTo(toPut.key) < 0 && node.left != null)
        {
            put(node, toPut.left);
        }
        else
        {
            toPut.isRed = true;
            toPut = node;
        }

        if (!toPut.left.isRed && toPut.right.isRed)
        {
            toPut = rotateLeft(toPut);
        }
        else if (toPut.left.isRed && toPut.right.isRed)
        {
            toPut = flipColor(toPut);
        }
        else  if (toPut.left.isRed && toPut.left.left.isRed)
        {
            toPut = rotateRight(toPut);
        }
    }

    private Node rotateLeft(Node<K, V> node)
    {
        Node temp = node.right;
        node.right = temp.left;
        node.left.isRed = true;
        temp.left = node;
        temp.isRed = node.isRed;
        return temp;
    }

    private Node rotateRight(Node<K, V> node)
    {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.isRed = node.isRed;
        node.isRed = true;
        return  temp;
    }

    private Node flipColor(Node node)
    {
        node.isRed = true;
        node.left.isRed = false;
        node.right.isRed = false;
    }
}
