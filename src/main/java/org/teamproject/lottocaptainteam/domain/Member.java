package org.teamproject.lottocaptainteam.domain;


public class Member {

    private final long id;
    private final String name;

    public Member(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Member idChangesTo(long id) {
        return new Member(id, this.name);
    }

    public Member nameChangesTo(String name) {
        return new Member(this.id, name);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
