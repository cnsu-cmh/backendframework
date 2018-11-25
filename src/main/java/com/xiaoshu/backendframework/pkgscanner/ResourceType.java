package com.xiaoshu.backendframework.pkgscanner;

public enum ResourceType {

    JAR("jar"),

    FILE("file"),

    CLASS_FILE(".class");

    private String typeString;

    private ResourceType(String type) {
        this.typeString = type;
    }

    public String getTypeString() {
        return this.typeString;
    }
}
