package com.xh.structrue.trie;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;

import static cn.hutool.core.util.StrUtil.EMPTY;

//@ToString
public class TrieNode {
    private TrieNode[] subArr = new TrieNode[4];
    private int availableCount = 0;
    private String value;
    private TrieNode parent;
    private int level = 0;

    public void add(String value) {
        final String saveValue = value.substring(this.value.length());
        if(saveValue.isEmpty()) {
            return;
        }
        if (availableCount == 0) {
            addNewNode(saveValue);
        } else {
            final int samePrefixNodeIndex = findSavePrefixNodeIndex(saveValue);
            if (-1 == samePrefixNodeIndex) {
                this.addNewNode(saveValue);
                return;
            }
            final TrieNode sameNode = subArr[samePrefixNodeIndex];
            final int sameIndex = findSameIndex(sameNode.value, saveValue);
            if (sameIndex == sameNode.value.length()) {
                if (saveValue.length() > sameIndex) {
                    sameNode.add(saveValue);
                }
            }
            if (sameIndex < sameNode.value.length()) {
                refresh(sameIndex, samePrefixNodeIndex, saveValue);
            }
        }
    }

    private void refresh(int sameIndex, int samePrefixNodeIndex, String value) {
        final TrieNode samePrefixNode = this.subArr[samePrefixNodeIndex];
        final String originValue = samePrefixNode.value;
        final TrieNode[] tempArr = samePrefixNode.subArr;
        this.subArr[samePrefixNodeIndex] = new TrieNode(this, originValue.substring(0, sameIndex));
        final TrieNode newNode = this.subArr[samePrefixNodeIndex].addNewNode(originValue.substring(sameIndex));
        newNode.subArr = tempArr;

        refreshLevel(newNode);

        this.subArr[samePrefixNodeIndex].add(value);
    }

    private void refreshLevel(TrieNode newParentNode) {
        final TrieNode[] subArr = newParentNode.subArr;
        int count = 0;
        for (TrieNode trieNode : subArr) {
            if (null == trieNode) {
                break;
            }
            count ++;
            trieNode.parent = newParentNode;
            trieNode.level = newParentNode.level + 1;
            refreshLevel(trieNode);
        }
        newParentNode.availableCount = count;
    }

    private TrieNode addNewNode(String value) {
        final TrieNode trieNode = new TrieNode(this, value);

        if (subArr.length == availableCount) {
            TrieNode[] newArr = new TrieNode[subArr.length * 2];
            System.arraycopy(this.subArr, 0, newArr, 0, subArr.length);
            subArr = newArr;
        }
        subArr[availableCount] = trieNode;
        availableCount++;
        return trieNode;
    }

    private int findSameIndex(String nodeValue, String newValue) {
        int result = 1;
        for (int j = 2; j <= nodeValue.length() && j <= newValue.length(); j++) {
            if (newValue.indexOf(nodeValue.substring(0, j)) == 0) {
                result = j;
            }
        }
        return result;
    }

    private int findSavePrefixNodeIndex(String value) {
        for (int i = 0; i < availableCount; i++) {
            final TrieNode trieNode = subArr[i];
            final String tagValue = trieNode.value;
            if (value.indexOf(tagValue.charAt(0)) == 0) {
                return i;
            }
        }
        return -1;
    }

    public TrieNode() {
        this.value = EMPTY;
    }

    public TrieNode(TrieNode parent, String value) {
        this.value = value;
        this.parent = parent;
        this.level = parent.level + 1;
    }

    public static void main(String[] args) {
//        final TrieNode head = new TrieNode();
//        head.add("hea");
//        head.add("heb!");
//        head.add("hec!");
//        head.add("hed!");
//        head.add("hee!");
//        head.add("hef!");
//        head.add("heg!");
//        head.add("heh!");
//        head.add("hei!");
//        head.add("hej!");
//        head.add("hek!");
//        head.add("hel!");
//        System.out.println(head);
//        head.add("hello!world");
//        head.add("he");
//        head.add("t");
//        head.add("hello1");
//        head.add("thello1");
//        System.out.println(head);

        final String[] split = "//".split("/");
        final String[] strings = Arrays.stream(split).filter(StrUtil::isNotEmpty).toArray(String[]::new);
        System.out.println(Arrays.toString(strings));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append("\t");
        }
        builder.append("value:").append(this.value).append("\n");
        for (TrieNode trieNode : subArr) {
            if (null != trieNode) {
                builder.append(trieNode.toString());
            }
        }
        return builder.toString();
    }
}
