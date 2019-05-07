/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String projectName;

    //private double minBudget;
    //private double maxBudget;
    private int minBudget;

    private int maxBudget;

    private String projectLocation;

    private String projectDescription;

    private Date publishingDate;

    private Date validityPeriod;

    private Collection<ReviewEmp> reviewEmpCollection;

    private FosUser employerId;

    private FreelancersBookmark freelancersBookmark;

    private Collection<Bookmark> bookmarkCollection;

    private Collection<Review> reviewCollection;

    private Collection<Bid> bidCollection;

    public Project() {
    }

    public Project(Integer id) {
        this.id = id;
    }

    public Project(int id, String projectName, int minBudget, int maxBudget, Date validityPeriod) {
        this.projectName = projectName;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.validityPeriod = validityPeriod;
        this.id = id;

    }

    public Project(int id, String projectName) {
        this.projectName = projectName;

        this.id = id;

    }

    public Project(Integer id, String projectName, int minBudget, int maxBudget, String projectLocation, String projectDescription, Date publishingDate, Date validityPeriod) {
        this.id = id;
        this.projectName = projectName;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.projectLocation = projectLocation;
        this.projectDescription = projectDescription;
        this.publishingDate = publishingDate;
        this.validityPeriod = validityPeriod;
    }

    /*public Project(String projectName, double minBudget, double maxBudget) {
        this.projectName = projectName;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;

    }

    public Project(Integer id, String projectName, double minBudget, double maxBudget, String projectLocation, String projectDescription, Date publishingDate, Date validityPeriod) {
        this.id = id;
        this.projectName = projectName;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.projectLocation = projectLocation;
        this.projectDescription = projectDescription;
        this.publishingDate = publishingDate;
        this.validityPeriod = validityPeriod;
    }*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(int minBudget) {
        this.minBudget = minBudget;
    }

    public int getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(int maxBudget) {
        this.maxBudget = maxBudget;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Date getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(Date publishingDate) {
        this.publishingDate = publishingDate;
    }

    public Date getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(Date validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Collection<ReviewEmp> getReviewEmpCollection() {
        return reviewEmpCollection;
    }

    public void setReviewEmpCollection(Collection<ReviewEmp> reviewEmpCollection) {
        this.reviewEmpCollection = reviewEmpCollection;
    }

    public FosUser getEmployerId() {
        return employerId;
    }

    public void setEmployerId(FosUser employerId) {
        this.employerId = employerId;
    }

    public FreelancersBookmark getFreelancersBookmark() {
        return freelancersBookmark;
    }

    public void setFreelancersBookmark(FreelancersBookmark freelancersBookmark) {
        this.freelancersBookmark = freelancersBookmark;
    }

    public Collection<Bookmark> getBookmarkCollection() {
        return bookmarkCollection;
    }

    public void setBookmarkCollection(Collection<Bookmark> bookmarkCollection) {
        this.bookmarkCollection = bookmarkCollection;
    }

    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    public Collection<Bid> getBidCollection() {
        return bidCollection;
    }

    public void setBidCollection(Collection<Bid> bidCollection) {
        this.bidCollection = bidCollection;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", projectName=" + projectName + ", minBudget=" + minBudget + ", maxBudget=" + maxBudget + ", projectLocation=" + projectLocation + ", projectDescription=" + projectDescription + ", publishingDate=" + publishingDate + ", validityPeriod=" + validityPeriod + ", reviewEmpCollection=" + reviewEmpCollection + ", employerId=" + employerId + ", freelancersBookmark=" + freelancersBookmark + ", bookmarkCollection=" + bookmarkCollection + ", reviewCollection=" + reviewCollection + ", bidCollection=" + bidCollection + '}';
    }

}
