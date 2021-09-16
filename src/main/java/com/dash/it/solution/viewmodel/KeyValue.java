package com.dash.it.solution.viewmodel;
public class KeyValue<T,V>
{
    private T key;
    private V value;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

 
}