/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.io.Serializable;
import java.util.Date;

public class Bookmark implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private Date dateAdded;

    private int projectId;

    private int freelancerId;
        private Project project;

    
        public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Bookmark(int id, int projectId, int freelancerId, Date dateAdded) {
        this.id = id;
        this.dateAdded = dateAdded;
        this.projectId = projectId;
        this.freelancerId = freelancerId;

    }

 public Bookmark() {
    }


    public Bookmark(int projectId, Date dateAdded) {
        this.projectId = projectId;
        this.dateAdded = dateAdded;
    }

    public Bookmark(int freelancerId, int projectId, Date dateAdded) {
        this.freelancerId = freelancerId;

        this.projectId = projectId;
        this.dateAdded = dateAdded;
    }

    public Bookmark(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    @Override
    public String toString() {
        return "Bookmark{" + "id=" + id + ", dateAdded=" + dateAdded + ", projectId=" + projectId + ", freelancerId=" + freelancerId + '}';
    }

}
