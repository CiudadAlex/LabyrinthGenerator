package org.leviatanplatform.labyrinth.model;

public class Tuple<O> {

    private O obj1;
    private O obj2;

    public Tuple(O obj1, O obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public O getObj1() {
        return obj1;
    }

    public O getObj2() {
        return obj2;
    }


}
