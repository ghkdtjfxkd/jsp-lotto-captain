package org.teamproject.lottocaptainteam.domain;


public class Member {

    private final String id;
    private final String name;
    private final String password;
    private final String email;
    private final boolean admin;

    private Member(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.admin = false;
    }

    private Member(String id, String name, String password, String email, boolean adminPermission) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.admin = adminPermission;
    }

    public static Member of(String id, String name, String password, String email) {
        return new Member(id, name, password, email);
    }

    public Member withId(String id) {
        return new Member(id, this.name, this.password, this.email, this.admin);
    }

    public Member withName(String name) {
        return new Member(this.id, name, this.password, this.email, this.admin);
    }

    public Member withPassword(String password) {
        return new Member(id, this.name, password, this.email, this.admin);
    }

    public Member withEmail(String email) {
        return new Member(id, this.name, this.password, email, this.admin);
    }

    public Member withAdminPermission(boolean adminPermission) {
        return new Member(this.id, this.name, this.password, this.email, adminPermission);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getAdmin() {
        return this.admin;
    }
}
