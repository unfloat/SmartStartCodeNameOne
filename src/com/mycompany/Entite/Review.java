/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author bhk
 */
public class Review {
   private int id;
   private boolean onBudget;
   private boolean onTime;
   private int rating;
   private String comment;
   private int employerReviewerId;
   private int freelancerReviewedId;
   private int projectId;

    public Review() {
    }

    public Review(int id, boolean onBudget, boolean onTime, int rating, String comment, int projectId, int freelancerReviewedId, int employerReviewerId) {
        this.id = id;
        this.onBudget = onBudget;
        this.onTime = onTime;
        this.rating = rating;
        this.comment = comment;
        this.employerReviewerId = employerReviewerId;
        this.freelancerReviewedId = freelancerReviewedId;
        this.projectId = projectId;
    }

        
    public Review(int id, boolean onBudget, boolean onTime, int rating, String comment) {
        this.id = id;
        this.onBudget = onBudget;
        this.onTime = onTime;
        this.rating = rating;
        this.comment = comment;
    }

    public Review(boolean onBudget, boolean onTime, int rating, String comment) {
        this.onBudget = onBudget;
        this.onTime = onTime;
        this.rating = rating;
        this.comment = comment;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getOnBudget() {
        return onBudget;
    }

    public void setOnBudget(boolean onBudget) {
        this.onBudget = onBudget;
    }

    public boolean getOnTime() {
        return onTime;
    }

    public void setOnTime(boolean onTime) {
        this.onTime = onTime;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getEmployerReviewerId() {
        return employerReviewerId;
    }

    public void setEmployerReviewerId(int employerReviewerId) {
        this.employerReviewerId = employerReviewerId;
    }

    public int getFreelancerReviewedId() {
        return freelancerReviewedId;
    }

    public void setFreelancerReviewedId(int freelancerReviewedId) {
        this.freelancerReviewedId = freelancerReviewedId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    
    @Override
    public String toString() {
        return "Review{" + "Id=" + id + ", \nWas it on Budget=" + onBudget + ", \nWas it on Time=" + onTime + ", \nRating=" + rating + ", \nComment=" + comment + "}\n\n";
    }
   
    
           
}
