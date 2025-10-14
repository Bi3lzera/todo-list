package com.todolist.VO;

import java.util.Date;

public class TarefaVO {
    private String description;
    private String title;
    private Date creationDate;
    private Date plannedDate;

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
}
