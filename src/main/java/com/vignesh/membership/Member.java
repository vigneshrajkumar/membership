package com.vignesh.membership;

/**
 * Member
 */
public class Member {

    private String name;
    private int id;

    public Member(int id, String name){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" + Integer.toString(this.id) + ":" + this.name + "}";
    }

}