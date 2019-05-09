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
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String noteName;
    private String noteText;
    private String priority;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Note() {
    }

    public Note(Integer id) {
        this.id = id;
    }

    public Note(Integer id, String noteName, String noteText, String priority) {
        this.id = id;
        this.noteName = noteName;
        this.noteText = noteText;
        this.priority = priority;
    }
    
    
    public Note(String priority, String noteText, String noteName) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", noteName=" + noteName + ", noteText=" + noteText + ", priority=" + priority + '}';
    }

    
    
    
    
}
