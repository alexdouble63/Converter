package org.example;

public class Value {
    private String name;
    private boolean isVisited;

    public Value(String name) {
        this.name = name;
        isVisited = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}
