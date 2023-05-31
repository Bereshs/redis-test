package org.example.redistest;

import org.redisson.api.RList;

public class UsersQueue {
    private RList<String> usersList;
    private int pointer;

    public UsersQueue(RList usersList) {
        this.usersList = usersList;
        pointer = -1;
    }

    public String getNext() {
        pointer++;
        if (pointer >= usersList.size()) {
            pointer = 0;
        }
        return usersList.get(pointer);
    }

    public String get(int index) {
        if (index > usersList.size()) {
            return null;
        }
        return usersList.get(index);
    }

    public void add(String element) {
        usersList.add(element);
    }

    public int size() {
        return usersList.size();
    }


}
