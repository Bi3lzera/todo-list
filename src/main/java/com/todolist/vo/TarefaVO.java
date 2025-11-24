package com.todolist.vo;

import java.util.Date;

public class TarefaVO {

    private int id;
    private String description;
    private String title;
    private Date creationDate;
    private Date plannedDate;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        id = Id;
    }

    public TarefaVO() {
        creationDate = new Date();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        description = Description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        title = Title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date PlannedDate) {
        plannedDate = PlannedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String Status) {
        status = Status;
    }
}
