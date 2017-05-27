/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.autoescola.models;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static info.infomila.autoescola.models.Alumne.VALID_EMAIL_ADDRESS_REGEX;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author David
 */
public class Practica
{
    
    private Integer id;
    private Date dataInici;
    private Integer duracio;
    private Alumne alumne;
    private String observacions;

    public Practica(Date dataInici, Integer duracio, Alumne alumne, String observacions)
    {
        this.dataInici = dataInici;
        this.duracio = duracio;
        this.alumne = alumne;
        this.observacions = observacions;
    }
        
    public Practica(Integer id, Date dataInici, Integer duracio, Alumne alumne, String observacions)
    {
        this.id = id;
        this.dataInici = dataInici;
        this.duracio = duracio;
        this.alumne = alumne;
        this.observacions = observacions;
    }
    
    public Integer getId()
    {
        return id;
    }


    public Date getDataInici()
    {
        return dataInici;
    }


    public Integer getDuracio()
    {
        return duracio;
    }
    

    public Alumne getAlumne()
    {
        return alumne;
    }

    public String getObservacions()
    {
        return observacions;
    }

    
    public void setId(Integer id) throws GestorBDException
    {
        if(id == null || id < 0)
        {
            throw new GestorBDException("El id de pràctica és obligatori i positiu.");
        }
        this.id = id;
    }

    public void setDataInici(Date dataInici) throws GestorBDException
    {
        if(dataInici == null)
        {
            throw new GestorBDException("La data d'inici es obligatoria.");
        }
        this.dataInici = dataInici;
    }
    
    public void setDuracio(Integer duracio) throws GestorBDException
    {
        if(duracio == null || duracio <= 0)
        {
            throw new GestorBDException("La duració es obligatoria i estrictament positiva.");
        }
        this.duracio = duracio;
    }
    
    public void setAlumne(Alumne alumne)
    {
        this.alumne = alumne;
    }

    public void setObservacions(String observacions) throws GestorBDException
    {
        if(observacions.length() > 255)
        {
            throw new GestorBDException("Les observacions no poden tindre mes de 255 caràcters de llargada.");
        }
        this.observacions = observacions;
    }
    

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Practica other = (Practica) obj;
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        return true;
    }
    

}
