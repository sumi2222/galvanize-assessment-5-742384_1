package com.galvanize.assessment5.sender.galvanizeassessment5742384_1.entity;

import java.util.Arrays;

public class FrontEndEntity {

    private String[] path;
    public FrontEndEntity(){}
    public FrontEndEntity(String[] path) {
        this.path = path;
    }

    public String[] getPath() {
        return path;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FrontEndEntity{" +
                "path=" + Arrays.toString(path) +
                '}';
    }

}
