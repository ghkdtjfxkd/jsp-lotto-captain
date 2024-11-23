package org.teamproject.lottocaptainteam.domain;


public class Member {

    private final String id;
    private final String name;
    private final String password;
    private final String email;

    private Member(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public static Member of(String id, String name, String password, String email) {
        return new Member(id, name, password, email);
    }

    public Member withId(String id) {
        return new Member(id, name, password, email);
    }

    public Member withName(String name) {
        return new Member(id, name, password, email);
    }

    public Member withPassword(String password) {
        return new Member(id, name, password, email);
    }

    public Member withEmail(String email) {
        return new Member(id, name, password, email);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
