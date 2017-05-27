/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.autoescola.models;

import java.util.Objects;

/**
 *
 * @author David
 */
public class Professor
{
    private String nif;
    private String nom;
    private String cognoms;

    public Professor(String nif, String nom, String cognoms)
    {
        this.nif = nif;
        this.nom = nom;
        this.cognoms = cognoms;
    }
    
    public String getNif()
    {
        return nif;
    }

    public void setNif(String nif)
    {
        this.nif = nif;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getCognoms()
    {
        return cognoms;
    }

    public void setCognoms(String cognoms)
    {
        this.cognoms = cognoms;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.nif);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Professor other = (Professor) obj;
        if (!Objects.equals(this.nif, other.nif))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return  nom + "," + cognoms;
    }

    
    
}
