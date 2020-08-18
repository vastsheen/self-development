package com.xh.structrue.tree;

import lombok.Data;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Data
public class RedBlackTreeMap<K, V> implements Map {

    private Entry<K, V> root;

    public RedBlackTreeMap(){

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Map.Entry> entrySet() {
        return null;
    }

    @Data

    class Entry<K, V> implements Map.Entry{

        private static final boolean BLACK = false;

        private static final boolean RED = true;

        Entry<K, V> parentNode;
        Entry<K, V> leftNode;
        Entry<K, V> rightNode;
        boolean color = BLACK;

        @Override
        public Object getKey() {
            return null;
        }

        @Override
        public Object getValue() {
            return null;
        }

        @Override
        public Object setValue(Object value) {
            return null;
        }
    }
}
