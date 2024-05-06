package org.techaurora.nodenet.utils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Consumer;

public class StringIndexedArray<T> implements Iterable, Serializable {
    private final List<String> indexArray;
    private final List<T> data;

    public StringIndexedArray(List<String> indexArray){
        this(indexArray, (T) null);
    }

    public StringIndexedArray(List<String> indexArray, T initValue){
        this.indexArray = indexArray;
        data = new ArrayList<>();
        for(int i = 0; i < indexArray.size(); i++){
            data.add(initValue);
        }
    }

    public StringIndexedArray(List<String> indexArray, List<T> data){
        this.indexArray = indexArray;
        this.data = data;
    }

    public StringIndexedArray(Map<String, ? extends T> data){
        this.indexArray = new ArrayList<>();
        this.data = new ArrayList<>();
        for(Map.Entry<String, ? extends T> entry : data.entrySet()){
            this.indexArray.add(entry.getKey());
            this.data.add(entry.getValue());
        }
    }

    public Map<String, T> toMap(){
        return toMap(new HashMap<>());
    }

    public Map<String, T> toMap(Map<String, T> map){
        for(int i = 0; i < this.indexArray.size(); i++){
            map.put(this.indexArray.get(i), this.data.get(i));
        }
        return map;
    }

    public void put(String index, T value){
        int _index = indexArray.indexOf(index);
        if(_index > -1) {
            data.set(_index, value);
        }
        throw new IllegalArgumentException();
    }

    public void put(int index, T value){
        data.set(index, value);
    }

    public T get(String index){
        int _index = indexArray.indexOf(index);
        if(_index > -1) {
            return data.get(_index);
        }
        throw new IllegalArgumentException();
    }

    public T get(int index){
        return data.get(index);
    }

    public T remove(String index){
        int _index = indexArray.indexOf(index);
        if(_index > -1) {
            return data.set(_index, null);
        }
        throw new IllegalArgumentException();
    }

    public T remove(int index){
        return data.set(index, null);
    }

    public String getStringIndex(int index){
        return indexArray.get(index);
    }

    public int getIndex(String index){
        return indexArray.indexOf(index);
    }

    public void clear(){
        for(int i = 0; i < data.size(); i++){
            data.set(i, null);
        }
    }

    public List<String> getIndexArray(){
        return Collections.unmodifiableList(indexArray);
    }

    public List<T> toArray(){
        return Collections.unmodifiableList(data);
    }

    public <T> StringIndexedArray<T> unmodifiableCopy(){
        return new unmodifiableStringIndexedArray<>(indexArray, data);
    }

    @Override
    public Iterator iterator() {
        return data.iterator();
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }

    private class unmodifiableStringIndexedArray<T> extends StringIndexedArray<T>{
        public unmodifiableStringIndexedArray(List indexArray, List data) {
            super(indexArray, data);
        }

        @Override
        public void put(String index, T value){
            throw new UnsupportedOperationException();
        }

        @Override
        public void put(int index, T value){
            throw new UnsupportedOperationException();
        }

        @Override
        public T remove(String index){
            throw new UnsupportedOperationException();
        }

        @Override
        public T remove(int index){
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear(){
            throw new UnsupportedOperationException();
        }
    }
}
