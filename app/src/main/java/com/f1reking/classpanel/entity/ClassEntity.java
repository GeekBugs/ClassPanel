package com.f1reking.classpanel.entity;

/**
 * Created by F1ReKing on 2016/2/24.
 */
public class ClassEntity {

    public String className;
    public int classIcon;

    public ClassEntity(String className, int classIcon) {
        this.className = className;
        this.classIcon = classIcon;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getClassIcon() {
        return classIcon;
    }

    public void setClassIcon(int classIcon) {
        this.classIcon = classIcon;
    }
}
