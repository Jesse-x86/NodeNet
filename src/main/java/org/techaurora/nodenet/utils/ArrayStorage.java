package org.techaurora.nodenet.utils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ArrayStorage<V extends DataWithIntID>
        implements Collection<V>, Cloneable, Serializable {
    private List<V> list;
    private Deque<Integer> deque;
    private int size;


    public ArrayStorage(){
        this(10);
    }

    public ArrayStorage(int initialCapacity){
        list = new ArrayList<>(initialCapacity);
        deque = new ArrayDeque<>();
        size = 0;
    }

    public ArrayStorage(Collection<? extends V> data){
        this();
        addAll(data);
    }

    public ArrayStorage(Map m){
        this();
        putAll(m);
    }

    public synchronized void compress(){
        if(!deque.isEmpty()){
            deque.clear();
            int slow = 0;
            for(int fast = 0; fast < list.size(); fast++){
                if(null != list.get(fast)){
                    if(slow != fast){
                        list.set(slow, list.get(fast));
                    }
                    slow++;
                }
            }
            list = list.subList(0, slow);
        }
    }


    public int size() {
        return size;
    }

    public int sizeRealList(){
        return list.size();
    }

    public int sizeRealDeque(){
        return deque.size();
    }

    public int sizeReal(){
        return sizeRealList() + sizeRealDeque();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int pointer = 0;
            @Override
            public boolean hasNext() {
                while(pointer < list.size() && null == list.get(pointer)) pointer++;
                return pointer < list.size();
            }

            @Override
            public V next() {
                while(null == list.get(pointer)) pointer++;
                return list.get(pointer++);
            }
        };
    }

    @Override
    public void forEach(Consumer<? super V> action) {
        Collection.super.forEach(action);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public <T> T[] toArray(IntFunction<T[]> generator) {
        return Collection.super.toArray(generator);
    }

    public boolean containsKey(Object key) {
        if(Integer.class.isAssignableFrom(key.getClass())) {
            if (list.size() > (int) key && !deque.contains(key)){
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        if(null == value) return false;
        for(V item : list){
            if(null == item) continue;
            if(value.equals(item)) return true;
        }
        return false;
    }

    public V get(Object key) {
        if(Integer.class.isInstance(key)) {
            return list.get((int) key);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean add(V value){
        if(deque.isEmpty()){
            value.setID(list.size());
            list.add(value);
        } else {
            value.setID(deque.removeFirst());
            list.set((int) value.getID(),value);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends V> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super V> filter) {
        return Collection.super.removeIf(filter);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public V remove(Integer key) {
        V value = list.get(key);
        if(null != value){
            size--;
            deque.addLast(key);
            list.set(key, null);
        }
        return value;
    }

    public void putAll(Map m) {
        addAll(m.values());
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        deque = new ArrayDeque<>();
        size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if(ArrayStorage.class.isInstance(o)) {
            return this.hashCode() == o.hashCode() && this.size == ((ArrayStorage)o).size;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return list.hashCode() ^ deque.hashCode();
    }

    @Override
    public Spliterator<V> spliterator() {
        return Collection.super.spliterator();
    }

    @Override
    public Stream<V> stream() {
        return Collection.super.stream();
    }

    @Override
    public Stream<V> parallelStream() {
        return Collection.super.parallelStream();
    }
}
