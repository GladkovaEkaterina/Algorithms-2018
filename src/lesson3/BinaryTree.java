package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        this.root = delete(root, (T) o);
        size--;
        return contains(o);
    }

    private Node<T> delete(Node<T> root, T value) {
        if (root != null) {
            int c = value.compareTo(root.value);
            if (c < 0)
                root.left = delete(root.left, value);
            else if (c > 0)
                root.right = delete(root.right, value);
            else if (root.left != null && root.right != null) {
                Node<T> min = root.right;
                while (min.left != null)
                    min = min.left;
                Node<T> newRoot = new Node<>(min.value);
                newRoot.right = delete(root.right, min.value);
                newRoot.left = root.left;
                return newRoot;
            } else if (root.right == null)
                root = root.left;
            else
                root = root.right;
        }
        return root;
    }
    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private Node<T> current = null;
        private ArrayList<Node<T>> content = new ArrayList<>();

        private BinaryTreeIterator() {
            Node<T> node = root;
            Stack<Node<T>> nodes = new Stack<>();
            int mode = 1;
            do {
                switch (mode) {
                    case 1:
                        mode = 2;
                        break;
                    case 2:
                        if (node.left != null) {
                            nodes.push(node);
                            node = node.left;
                        } else
                            mode = 3;
                        break;
                    case 3:
                        content.add(node);
                        if (node.right != null) {
                            nodes.push(node);
                            node = node.right;
                            mode = 2;
                        } else
                            mode = 4;
                        break;
                    case 4:
                        if (nodes.empty()) mode = 1;
                        else if (nodes.peek().left == node) {
                            node = nodes.pop();
                            mode = 3;
                        } else if (nodes.peek().right == node)
                            node = nodes.pop();
                        break;
                }
            } while (mode != 1);
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        private Node<T> findNext() {
            try {
                current = content.remove(0);
            } catch (IndexOutOfBoundsException ex) {
                return null;
            }
            return current;
        }

        @Override
        public boolean hasNext() {
            return findNext() != null;
        }

        @Override
        public T next() {
            if (current == null) throw new NoSuchElementException();
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            if (root == current)
                root = BinaryTree.this.delete(root, next());
            else {
                Node<T> p = getParent();
                p = BinaryTree.this.delete(getParent(), current.value);
            }
            size--;
        }

        private Node<T> getParent() {
            Node<T> tNode = root;
            while (tNode.left != current && tNode.right != current) {
                if (tNode.value.compareTo(current.value) > 0)
                    tNode = tNode.left;
                else
                    tNode = tNode.right;
            }
            return tNode;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
