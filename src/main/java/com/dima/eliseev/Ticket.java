package com.dima.eliseev;

class Ticket {
    private String name;
    private String status;
    private String code;
    private String assignedTo;
    private String openTime;
    private int urgency;
    private String group;

    public Ticket(String name, String status, String code, String assignedTo, String openTime, int urgency, String group) {
        this.name = name;
        this.status = status;
        this.code = code;
        this.assignedTo = assignedTo;
        this.openTime = openTime;
        this.urgency = urgency;
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

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getOpenTime() {
        return openTime;
    }

    public int getUrgency() {
        return urgency;
    }

    public String getGroup() {
        return group;
    }
}


