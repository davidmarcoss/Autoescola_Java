/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.autoescola.models;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author David
 */
public final class Alumne
{

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_NIF_REGEX = Pattern.compile("^[0-9]{8}[A-Z]$", Pattern.CASE_INSENSITIVE);
    
    private String nif;
    private String nom;
    private String cognoms;
    private String correu;
    private String password;
    private String poblacio;
    private String adreca;
    private String telefon;
    private String professor;
    private String nomProfessor;
    private Integer desactivat;

    // CONSTRUCTORS
    public Alumne(String nif, String nom, String cognoms, String correu, String poblacio, String adreca, String telefon, String professor, String nomProfessor, Integer desactivat) throws GestorBDException
    {
        setNif(nif);
        setNom(nom);
        setCognoms(cognoms);
        setCorreu(correu);
        setPoblacio(poblacio);
        setAdreca(adreca);
        setTelefon(telefon);
        setProfessor(professor);
        setNomProfessor(nomProfessor);
        setDesactivat(desactivat);
    }

    public Alumne(String nif, String nom, String cognoms, String correu, String password, String telefon, String poblacio, String adreca, String professor) throws GestorBDException
    {
        setNif(nif);
        setNom(nom);
        setCognoms(cognoms);
        setCorreu(correu);
        setPassword(password);
        setPoblacio(poblacio);
        setAdreca(adreca);
        setTelefon(telefon);
        setProfessor(professor);
    }

    // GETTERS
    public String getNif()
    {
        return nif;
    }

    public String getNom()
    {
        return nom;
    }

    public String getCognoms()
    {
        return cognoms;
    }

    public String getCorreu()
    {
        return correu;
    }

    public String getPassword()
    {
        return password;
    }

    public String getPoblacio()
    {
        return poblacio;
    }

    public String getAdreca()
    {
        return adreca;
    }

    public String getTelefon()
    {
        return telefon;
    }

    public String getProfessor()
    {
        return professor;
    }

    public String getNomProfessor()
    {
        return nomProfessor;
    }

    public Integer getDesactivat()
    {
        return desactivat;
    }

    // SETTERS
    public void setNif(String nif) throws GestorBDException
    {
        if (nif == null || nif.isEmpty() || nif.length() < 9 || nif.length() > 9)
        {
            throw new GestorBDException("El NIF es obligatori i ha de ser de 8 caràcters.");
        }

        this.nif = nif;
    }

    public void setNom(String nom) throws GestorBDException
    {
        if (nom == null || nom.isEmpty() || nom.length() > 20)
        {
            throw new GestorBDException("El nom es obligatori i no pot estar buit.");
        }
        this.nom = nom;
    }

    public void setCognoms(String cognoms) throws GestorBDException
    {
        if (cognoms == null || cognoms.isEmpty() || cognoms.length() > 20)
        {
            throw new GestorBDException("Els cognoms són obligatoris.");
        }
        this.cognoms = cognoms;
    }

    public void setCorreu(String correu) throws GestorBDException
    {
        if (correu == null || correu.isEmpty() || !validate(correu) || correu.length() > 30)
        {
            throw new GestorBDException("El correu és obligatori.");
        }
        this.correu = correu;
    }

    public void setPassword(String password) throws GestorBDException
    {
        if (password == null || password.isEmpty() || password.length() < 32 || password.length() > 32)
        {
            throw new GestorBDException("La contrassenya és obligatoria.");
        }
        this.password = password;
    }

    public void setPoblacio(String poblacio) throws GestorBDException
    {
        if (poblacio == null || poblacio.isEmpty() || poblacio.length() > 20)
        {
            throw new GestorBDException("La població és obligatoria.");
        }
        this.poblacio = poblacio;

    }

    public void setAdreca(String adreca) throws GestorBDException
    {
        if (adreca == null || adreca.isEmpty() || adreca.length() > 20)
        {
            throw new GestorBDException("L'adreça és obligatoria.");
        }
        this.adreca = adreca;
    }

    public void setTelefon(String telefon) throws GestorBDException
    {
        if (telefon == null || telefon.isEmpty() || telefon.length() < 9 ||telefon.length() > 9)
        {
            throw new GestorBDException("El telèfon és obligatori.");
        }
        this.telefon = telefon;
    }

    public void setProfessor(String professor) throws GestorBDException
    {
        if (professor == null || professor.isEmpty() || professor.length() < 9 || professor.length() > 9)
        {
            throw new GestorBDException("El professor és obligatori.");
        }
        this.professor = professor;
    }

    public void setDesactivat(Integer desactivat) throws GestorBDException
    {
        if (desactivat == null || desactivat < 0 || desactivat > 1)
        {
            throw new GestorBDException("S'ha d'indicar l'estat de l'usuari (0 - Activat, 1 - Desactivat)");
        }
        this.desactivat = desactivat;
    }

    public void setNomProfessor(String nomProfessor) throws GestorBDException
    {
        if(nomProfessor == null || nomProfessor.isEmpty() || nomProfessor.length() > 40)
        {
            throw new GestorBDException("El nom i cognoms del professor no pot superar els 40 caràcters.");
        }
        this.nomProfessor = nomProfessor;
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.nif);
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
        final Alumne other = (Alumne) obj;
        if (!Objects.equals(this.nif, other.nif))
        {
            return false;
        }
        return true;
    }

    public static boolean validate(String emailStr)
    {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        
        return matcher.find();
    }

}
