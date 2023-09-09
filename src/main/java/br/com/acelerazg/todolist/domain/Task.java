package br.com.acelerazg.todolist.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Task extends Base {

    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private LocalDateTime endDate;
    private Status status;
    private Category category;
    private Priority priority;
    private List<LocalDateTime> alarms;

    public Task(int id, String title, String description, Status status, Category category, Priority priority,
                LocalDateTime endDate, List<LocalDateTime> alarms) {
        super(id, title);
        this.description = description;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.endDate = endDate;
        this.creationDate = LocalDateTime.now();
        this.lastModificationDate = LocalDateTime.now();
        this.alarms = alarms;
    }

    public Task(int id, String title, String description, LocalDateTime creationDate,
                LocalDateTime lastModificationDate, Status status, Category category, Priority priority,
                LocalDateTime endDate, List<LocalDateTime> alarms) {
        super(id, title);
        this.description = description;
        this.creationDate = creationDate;
        this.lastModificationDate = lastModificationDate;
        this.status = status;
        this.category = category;
        this.priority = priority;
        this.endDate = endDate;
        this.alarms = alarms;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(LocalDateTime lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<LocalDateTime> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<LocalDateTime> alarms) {
        this.alarms = alarms;
    }

    @Override
    public String toString() {
        StringBuilder alarmsList = new StringBuilder();
        if (!alarms.isEmpty()) {
            int count = 0;
            for (LocalDateTime alarm : alarms) {
                alarmsList.append(alarm.toString());
                count++;
                if (count < alarms.size()) {
                    alarmsList.append("/");
                }
            }
        }

        return super.toString()  + "," +
                description + "," +
                creationDate + "," +
                lastModificationDate + "," +
                status + "," +
                category.getTitle() + "," +
                priority + "," +
                endDate + "," +
                alarmsList;
    }

    public int compareToCategory(Task otherTask) {
        return category.compareTo(otherTask.category);
    }

    public int compareToStatus(Task otherTask) {
        return status.compareTo(otherTask.status);
    }

    public int compareToPriority(Task otherTask) {
        return priority.compareTo(otherTask.priority);
    }

}
