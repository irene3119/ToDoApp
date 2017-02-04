package com.simpleapp.irene.simpletodo;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Irene on 2017/2/4.
 */

@Table(database = TodoItemDatabase.class)
public class TodoItem extends BaseModel implements Serializable {

    @PrimaryKey (autoincrement=true)
    @Column
    private int id;

    @Column
    private String title;

    @Column
    private String priority;

    @Column
    private String status;

    @Column
    private Date dueDate;

    // DBFlow 4.0 is generating the wrong foreign key relation (should be getUid() instead of getUser())
    // if you use private fields.
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return this.priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return this.dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}
