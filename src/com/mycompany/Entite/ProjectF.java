/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.io.Serializable;


/**
 *
 * @author ASUS
 */
public class ProjectF implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String projectName;

    private double maxBudget;

    private String projectDescription;

    private double minBudget;

    private String projectLocation;

    private String projectStartDay;

    private String projectEndDay;

    private String address;

    private int idSkill;

    private int idCategory;
    
    

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

    public double getMaxBudget() {
        return maxBudget;
    }

    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public double getMinBudget() {
        return minBudget;
    }

    public void setMinBudget(double minBudget) {
        this.minBudget = minBudget;
    }

    public String getProjectLocation() {
        return projectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        this.projectLocation = projectLocation;
    }

    public String getProjectStartDay() {
        return projectStartDay;
    }

    public void setProjectStartDay(String projectStartDay) {
        this.projectStartDay = projectStartDay;
    }

    public String getProjectEndDay() {
        return projectEndDay;
    }

    public void setProjectEndDay(String projectEndDay) {
        this.projectEndDay = projectEndDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public ProjectF() {
    }
    
    public ProjectF(Integer id, String projectName, double minBudget, String projectDescription, double maxBudget, String projectLocation, String projectStartDay, String projectEndDay, String address, int idSkill, int idCategory) {
        this.id = id;
        this.projectName = projectName;
        this.maxBudget = maxBudget;
        this.projectDescription = projectDescription;
        this.minBudget = minBudget;
        this.projectLocation = projectLocation;
        this.projectStartDay = projectStartDay;
        this.projectEndDay = projectEndDay;
        this.address = address;
        this.idSkill = idSkill;
        this.idCategory = idCategory;
    }

    public ProjectF(String projectName, double minBudget, String projectDescription, double maxBudget, String projectLocation, String projectStartDay, String projectEndDay, String address, int idSkill, int idCategory) {
        this.projectName = projectName;
        this.maxBudget = maxBudget;
        this.projectDescription = projectDescription;
        this.minBudget = minBudget;
        this.projectLocation = projectLocation;
        this.projectStartDay = projectStartDay;
        this.projectEndDay = projectEndDay;
        this.address = address;
        this.idSkill = idSkill;
        this.idCategory = idCategory;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", projectName=" + projectName + ", maxBudget=" + maxBudget + ", projectDescription=" + projectDescription + ", minBudget=" + minBudget + ", projectLocation=" + projectLocation + ", projectStartDay=" + projectStartDay + ", projectEndDay=" + projectEndDay + ", address=" + address + ", idSkill=" + idSkill + ", idCategory=" + idCategory + '}';
    }
    
    
    
}
