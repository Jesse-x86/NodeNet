package org.techaurora.nodenet.utils;

import java.io.Serializable;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ArrayStorage<K extends Integer, V extends DataWithID>  extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable {
    private List<V> list;
    private Deque<Integer> deque;
    private int size;


    public ArrayStorage(){
        list = new ArrayList<>();
        deque = new ArrayDeque<>();
        size = 0;
    }

    public ArrayStorage(int initialCapacity){
        list = new ArrayList<>(initialCapacity);
        deque = new ArrayDeque<>();
        size = 0;
    }

    public ArrayStorage(Map<? extends K, ? extends V> m){
        this();
        putAll(m);
    }


    @Override
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if(Integer.class.isAssignableFrom(key.getClass())) {
            if (list.size() > (int) key && !deque.contains(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if(null == value) return false;
        for(V item : list){
            if(null == item) continue;
            if(value.equals(item)) return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if(Integer.class.isInstance(key)) {
            return list.get((int) key);
        }
        throw new IllegalArgumentException();
    }

    public V add(V value){
        if(deque.isEmpty()){
            value.setID(list.size());
            list.add(value);
        } else {
            value.setID(deque.removeFirst());
            list.set((int) value.getID(),value);
        }
        size++;
        return value;
    }

    @Override
    @Deprecated
    public V put(K key, V value) {
        if(Integer.class.isInstance(key)) {
            value.setID(key);
            if((int) key < list.size()){
                // get value to return
                V val = list.get((int) key);
                // re-calculate size
                if(null == val && null != value){
                    // null to not null, +1
                    size++;
                    deque.remove((int) key);
                } else if (null == value && null != val){
                    // not null to null, -1
                    size--;
                    deque.add((int) key);
                }
                // set value
                list.set((int) key, value);
                return val;
            } else{
                // expand list
                for(int i = list.size(); i < (int) key; i++){
                    list.add(null);
                    deque.add(i);
                }
                // insert value and expand size
                list.add(value);
                size++;
                return null;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public V remove(Object key) {
        if(Integer.class.isInstance(key)) {
            V value = list.get((int) key);
            if(null != value){
                size--;
                deque.addLast((int) key);
                list.set((int) key, null);
            }
            return value;
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(Entry<? extends K, ? extends V> entry : m.entrySet()){
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        deque = new ArrayDeque<>();
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        return null;
    }

    @Override
    public Collection<V> values() {
        return list;
    }

    @Override
    public Set<Entry<K,V>> entrySet() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return Map.super.getOrDefault(key, defaultValue);
    }

    @Override
    public void forEach(BiConsumer action) {
        Map.super.forEach(action);
    }

    @Override
    public void replaceAll(BiFunction function) {
        Map.super.replaceAll(function);
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        return Map.super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return Map.super.remove(key, value);
    }
}
