package org.teamproject.lottocaptainteam.domain;


public class Member {

    private String id;
    private String name;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member nameChaneTo(String name) {
        return new Member(this.id, name);
    }

    public Member idChangeTo(String id) {
        return new Member(id, this.name);
    }
}
