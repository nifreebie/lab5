package org.example.utils;

import lombok.NoArgsConstructor;
import org.example.model.Product;

import java.util.*;
@NoArgsConstructor
public class MyLinkedHashSet<T> implements Set<T>, Iterable<T> {
    private HashMap<T, Node<T>> map = new HashMap<>();
    private Node<T> head;
    private Node<T> tail;
    private int size;
    public MyLinkedHashSet(List<T> arrayList) {
        for (T item : arrayList) {
            add(item);
        }
    }

    private static class Node<T>{
        T value;
        Node<T> next;
        Node<T> prev;

        Node(T value) {
            this.value = value;
        }

    }
    @Override
     public boolean add(T value){
        if(map.containsValue(value)){
            return false;
        }
        else{
            Node<T> newNode = new Node<>(value);
            map.put(value, newNode);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }

            size++;
            return true;
        }
     }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        map.clear();
        head = null;
        tail = null;
        size = 0;

    }
    @Override
    public boolean remove(Object value){
         Node<T> nodeToRemove = map.get((T) value);
         if(nodeToRemove == null){
             return false;
         }else{
             map.remove(value);
             if (nodeToRemove.prev != null) {
                 nodeToRemove.prev.next = nodeToRemove.next;
             } else {
                 head = nodeToRemove.next;
             }
             if (nodeToRemove.next != null) {
                 nodeToRemove.next.prev = nodeToRemove.prev;
             } else {
                 tail = nodeToRemove.prev;
             }
             size--;
             return true;
         }
     }
     public int size(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedHashSetIterator();
    }

    private class MyLinkedHashSetIterator implements Iterator<T> {
        private Node<T> currentNode;

        MyLinkedHashSetIterator() {
            currentNode = map.isEmpty() ? null : head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T value = currentNode.value;
            currentNode = currentNode.next;
            return value;
        }
    }

}
