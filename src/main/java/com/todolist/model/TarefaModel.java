package com.todolist;

import java.util.Date;

public class TarefaModel {
    private String description;
    private String title;
    private String creationDate;
    private String plannedDate;

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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String CreationDate) {
        creationDate = CreationDate;
    }

    public String getPlannedDate() {
        return creationDate;
    }

    public void setPlannedDate(String PlannedDate) {
        plannedDate = PlannedDate;
    }
}
