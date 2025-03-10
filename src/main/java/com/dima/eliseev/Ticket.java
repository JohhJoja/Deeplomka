package com.dima.eliseev;

public class Ticket {
    private String name;
    private String status;
    private String code;
    private String assignedTo;
    private String openTime;
    private int urgency;
    private String group;

    // Конструктор
    public Ticket(String name, String status, String code, String assignedTo, String openTime, int urgency, String group) {
        this.name = name;
        this.status = status;
        this.code = code;
        this.assignedTo = assignedTo;
        this.openTime = openTime;
        this.urgency = urgency;
        this.group = group;
    }

    // Геттеры и сеттеры для каждого поля
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", openTime='" + openTime + '\'' +
                ", urgency=" + urgency +
                ", group='" + group + '\'' +
                '}';
    }
}

