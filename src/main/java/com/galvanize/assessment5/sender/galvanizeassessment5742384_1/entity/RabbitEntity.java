package com.galvanize.assessment5.sender.galvanizeassessment5742384_1.entity;

public class RabbitEntity {



    private String text;
    public RabbitEntity() { }
    public RabbitEntity(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "RabbitEntity{" +
                "text='" + text + '\'' +
                '}';
    }
}
