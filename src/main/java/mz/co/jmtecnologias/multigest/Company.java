/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mz.co.jmtecnologias.multigest;

import java.util.Date;

/**
 *
 * @author JM-Tecnologias
 */
public class Company {
    private int id;
    private String name;
    private String reson;
    private int nuit;
    private String description;
    private boolean deleted_status;
    private Date created_at;
    private Date updated_at;

    public Company() {
    }

    public Company(String name, String reson, int nuit, String description) {
        this.name = name;
        this.reson = reson;
        this.nuit = nuit;
        this.description = description;
        this.deleted_status = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public int getNuit() {
        return nuit;
    }

    public void setNuit(int nuit) {
        this.nuit = nuit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted_status() {
        return deleted_status;
    }

    public void setDeleted_status(boolean deleted_status) {
        this.deleted_status = deleted_status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
    
}
