package com.javacodegeeks.enterprise.rest.database;

public enum Database {
	
    USER("users"),
    NOTE("notes");

    private final String table;

    Database(String table) {
        this.table = table;
    }

    public String getTableName() {
        return this.table;
    }

    public static void main(String[] args) {
    }
}
