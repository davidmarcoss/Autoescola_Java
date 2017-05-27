/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.autoescola.interficie.persistencia;

import info.infomila.autoescola.models.Alumne;
import info.infomila.autoescola.models.GestorBDException;
import info.infomila.autoescola.models.Practica;
import info.infomila.autoescola.models.Professor;
import java.util.List;

/**
 *
 * @author David
 */
public interface IGestorBD
{
    void close() throws GestorBDException;
    
    // Select Alumnes
    List<Alumne> selectAlumnes() throws GestorBDException;
    
    // Insert Alumne
    void insertAlumne(Alumne alumne) throws GestorBDException;
    
    // Update Alumne
    void updateAlumne(Alumne alumne) throws GestorBDException;
    
    // Delete Alumne
    void deleteAlumne(String nif) throws GestorBDException;
    
    
    // Select Practiques
    List<Practica> selectPractiques(Alumne alumne) throws GestorBDException;
    
    // Insert Practiques
    void insertPractica(Practica practica) throws GestorBDException;
    
    // Update Practiques
    void updatePractica(Practica practica) throws GestorBDException;
    
    // Delete Practica
    void deletePractica(Integer id) throws GestorBDException;
    
    // Select professors
    List<Professor> selectProfessors()throws GestorBDException;
}
