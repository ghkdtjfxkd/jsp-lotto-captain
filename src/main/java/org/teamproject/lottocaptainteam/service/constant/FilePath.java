package org.teamproject.lottocaptainteam.service.constant;

public enum FilePath {
    ADMIN_LIST("adminList.md"),
    TOTAL_DRAW("total_draw.md");

    private final String fileName;

    FilePath(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return "src/main/resources/" + fileName;
    }
}
