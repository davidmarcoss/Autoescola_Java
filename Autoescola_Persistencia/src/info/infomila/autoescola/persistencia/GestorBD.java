/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.autoescola.persistencia;

import info.infomila.autoescola.models.GestorBDException;
import info.infomila.autoescola.interficie.persistencia.IGestorBD;
import info.infomila.autoescola.models.Alumne;
import info.infomila.autoescola.models.Practica;
import info.infomila.autoescola.models.Professor;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class GestorBD implements IGestorBD
{

    private final String xmlPropietats = "autoescola.xml";
    private Connection conn;

    public GestorBD() throws GestorBDException
    {
        String driver, url, user, pass;

        try
        {
            Properties props = new Properties();
            props.loadFromXML(new FileInputStream(xmlPropietats));
            if (null == (driver = props.getProperty("driver")))
            {
                if (System.getProperty("java.version").compareTo("1.6") < 0)
                {
                    throw new GestorBDException("Error: No està definit el driver JDBC a utilitzar");
                }
            }
            if (null == (url = props.getProperty("url")))
            {
                throw new GestorBDException("Error: No està definit la url de connexió");
            }
            user = props.getProperty("user");   // Hi ha SGBD que no exigeixen usuari (MsAccess)
            pass = props.getProperty("password");
        }
        catch (IOException ioe)
        {
            throw new GestorBDException("Problemes en recuperar l'arxiu de configuració " + xmlPropietats);
        }
        if (driver != null)
        {
            try
            {
                Class.forName(driver);
            }
            catch (ClassNotFoundException cnfe)
            {
                throw new RuntimeException("No es troba la classe " + driver);
            }
        }

        try
        {
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);
        }
        catch (SQLException sqle)
        {
            throw new GestorBDException("Problema en establir la connexió, no es pot obrir el programa.");
        }
    }

    @Override
    public void close() throws GestorBDException
    {
        if (conn != null)
        {
            try
            {
                conn.rollback();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en fer rollback final.\n" + ex.getMessage());
            }
            try
            {
                conn.close();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en tancar la connexió.\n" + ex.getMessage());
            }
        }
    }

    /**
     * Funció per a obtenir tots els alumnes amb totes les seves pràctiques per
     * a poder fer el mestre detall (Alumne 1 - N Pràctiques).
     *
     * @return
     */
    @Override
    public List<Alumne> selectAlumnes() throws GestorBDException
    {
        List<Alumne> llAlumnes = new ArrayList<>();
        Statement st = null;

        boolean add = false;
        try
        {
            st = conn.createStatement();
            try (ResultSet rs = st.executeQuery("SELECT alu_nif, alu_nom, alu_cognoms, alu_correu, alu_poblacio, alu_adreca, alu_telefon, alu_professor_nif, ad.admin_nom, ad.admin_cognoms FROM alumnes al INNER JOIN administradors ad on ad.admin_nif = al.alu_professor_nif WHERE alu_desactivat = 0"))
            {
                while (rs.next())
                {
                    llAlumnes.add(new Alumne(rs.getString("alu_nif"), rs.getString("alu_nom"), rs.getString("alu_cognoms"), rs.getString("alu_correu"), rs.getString("alu_poblacio"), rs.getString("alu_adreca"), rs.getString("alu_telefon"), rs.getString("alu_professor_nif"), rs.getString("ad.admin_nom")+", "+rs.getString("ad.admin_cognoms"), 0));
                }
            }  
        }
        catch (SQLException ex)
        {
            throw new GestorBDException("Error en intentar recuperar la llista d'Alumnes.\n" + ex.getMessage());
        }
        finally
        {
            if (st != null)
            {
                try
                {
                    st.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return llAlumnes;
    }

    @Override
    public void insertAlumne(Alumne alumne) throws GestorBDException
    {
        try
        {
            conn.setAutoCommit(false);

            PreparedStatement ps = null;
            try
            {
                String query = "INSERT INTO alumnes VALUES(?, ?, ?, ?, ? ,?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);

                ps.setString(1, alumne.getNif());
                ps.setString(2, alumne.getNom());
                ps.setString(3, alumne.getCognoms());
                ps.setString(4, alumne.getCorreu());
                ps.setString(5, alumne.getPassword());
                ps.setString(6, alumne.getPoblacio());
                ps.setString(7, alumne.getAdreca());
                ps.setString(8, alumne.getTelefon());
                ps.setString(9, alumne.getProfessor());
                ps.setInt(10, 0);
                ps.executeUpdate();

                conn.commit();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en intentar inserir l'Alumne " + alumne.getNif() + "\n" + ex.getMessage());
            }
            finally
            {
                if (ps != null)
                {
                    try
                    {
                        ps.close();
                    }
                    catch (SQLException ex)
                    {
                        throw new GestorBDException("Error en intentar tancar la sentència que ha inserit l'Alumne.\n" + ex.getMessage());
                    }
                }
                try
                {
                    conn.rollback();
                }
                catch (SQLException ex)
                {
                    throw new GestorBDException("Error en intentar fer rollback en insertAlumne().\n" + ex.getMessage());
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAlumne(Alumne alumne) throws GestorBDException
    {
        try
        {
            conn.setAutoCommit(false);

            PreparedStatement ps = null;
            try
            {
                String query = "UPDATE alumnes SET alu_nif = ?, alu_nom = ?, alu_cognoms = ?, alu_correu = ?, alu_poblacio = ?, alu_adreca = ?, alu_telefon = ?, alu_professor_nif = ?";
                ps = conn.prepareStatement(query);

                ps.setString(1, alumne.getNif());
                ps.setString(2, alumne.getNom());
                ps.setString(3, alumne.getCognoms());
                ps.setString(4, alumne.getCorreu());
                ps.setString(5, alumne.getPoblacio());
                ps.setString(6, alumne.getAdreca());
                ps.setString(7, alumne.getTelefon());
                ps.setString(8, alumne.getProfessor());
                ps.executeUpdate();

                conn.commit();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en modificar l'alumne " + alumne.getNif() + "\n" + ex.getMessage());
            }
            finally
            {
                if (ps != null)
                {
                    try
                    {
                        ps.close();
                    }
                    catch (SQLException ex)
                    {
                        throw new GestorBDException("Error en intentar tancar la sentència que ha modificat l'Alumne.\n" + ex.getMessage());
                    }
                }
                try
                {
                    conn.rollback();
                }
                catch (SQLException ex)
                {
                    throw new GestorBDException("Error en intentar fer rollback en updateAlumne().\n" + ex.getMessage());
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAlumne(String nif) throws GestorBDException
    {
        PreparedStatement ps = null;

        try
        {
            conn.setAutoCommit(false);

            String query = "UPDATE alumnes SET alu_desactivat = ? WHERE alu_nif = ?";

            ps = conn.prepareStatement(query);

            ps.setInt(1, 1);
            ps.setString(2, nif);
            ps.executeUpdate();

            conn.commit();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException ex)
                {
                    throw new GestorBDException("Error en intentar tancar la sentència que ha desactivat l'Alumne.\n" + ex.getMessage());
                }
            }
            try
            {
                conn.rollback();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en intentar fer rollback en deleteAlumne().\n" + ex.getMessage());
            }
        }
    }

    @Override
    public List<Practica> selectPractiques(Alumne alumne) throws GestorBDException
    {
        List<Practica> llPractiques = new ArrayList<>();

        PreparedStatement ps = null;

        try
        {
            String query = "SELECT * FROM alumne_practiques WHERE alu_prac_alumne_nif = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, alumne.getNif());
            try (ResultSet rs = ps.executeQuery())
            {
                while (rs.next())
                {
                    Integer id = rs.getInt("alu_prac_id");
                    Date dataInici = rs.getDate("alu_prac_data_inici");
                    Integer duracio = rs.getInt("alu_prac_duracio");
                    String observacions = rs.getString("alu_prac_observacions");
                    
                    llPractiques.add(new Practica(id, dataInici, duracio, alumne, observacions));
                }
            }
        }
        catch (SQLException ex)
        {
            throw new GestorBDException("Error en intentar recuperar la llista de practiques de practiques.\n" + ex.getMessage());
        }
        finally
        {
            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return llPractiques;
    }

    @Override
    public void insertPractica(Practica practica) throws GestorBDException
    {
        try
        {
            conn.setAutoCommit(false);

            PreparedStatement ps = null;
            try
            {
                int queryId = this.getLastId("alumne_practiques");
                
                String query = "INSERT INTO alumne_practiques VALUES(?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);

                ps.setInt(1, queryId);
                Timestamp stamp =  new Timestamp(practica.getDataInici().getTime());
                ps.setTimestamp(2, stamp);
                ps.setInt(3, practica.getDuracio());
                ps.setString(4, practica.getAlumne().getNif());
                ps.setString(5, practica.getObservacions());
                ps.executeUpdate();
                
                this.setLastId("alumne_practiques", queryId);

                conn.commit();
            }
            catch (SQLException ex)
            {
                Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                throw new GestorBDException("Error en intentar inserir la pràctica\n" + ex.getMessage());
            }
            finally
            {
                if (ps != null)
                {
                    try
                    {
                        ps.close();
                    }
                    catch (SQLException ex)
                    {
                        throw new GestorBDException("Error en intentar tancar la sentència que ha inserit la pràctica.\n" + ex.getMessage());
                    }
                }
                try
                {
                    conn.rollback();
                }
                catch (SQLException ex)
                {
                    throw new GestorBDException("Error en intentar fer rollback en insertPractica().\n" + ex.getMessage());
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updatePractica(Practica practica) throws GestorBDException
    {
        try
        {
            conn.setAutoCommit(false);

            PreparedStatement ps = null;
            try
            {
                String query = "UPDATE alumne_practiques SET alu_prac_observacions = ? WHERE alu_prac_id = ?";
                ps = conn.prepareStatement(query);

                ps.setString(1, practica.getObservacions());
                ps.setInt(2, practica.getId());
                ps.executeUpdate();

                conn.commit();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en modificar la pràctica " + practica.getId() + ex.getMessage());
            }
            finally
            {
                if (ps != null)
                {
                    try
                    {
                        ps.close();
                    }
                    catch (SQLException ex)
                    {
                        throw new GestorBDException("Error en intentar tancar la sentència que ha modificat la pràctica." + practica.getId() + "\n" + ex.getMessage());
                    }
                }
                try
                {
                    conn.rollback();
                }
                catch (SQLException ex)
                {
                    throw new GestorBDException("Error en intentar fer rollback en updateAlumne().\n" + ex.getMessage());
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deletePractica(Integer id) throws GestorBDException
    {
        PreparedStatement ps = null;

        try
        {
            conn.setAutoCommit(false);

            String query = "DELETE FROM alumne_practiques WHERE alu_prac_id = ?";

            ps = conn.prepareStatement(query);

            ps.setInt(1, id);
            ps.executeUpdate();

            conn.commit();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (SQLException ex)
                {
                    throw new GestorBDException("Error en intentar tancar la sentència que elimina la pràctica.\n" + ex.getMessage());
                }
            }
            try
            {
                conn.rollback();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en intentar fer rollback en deletePractica().\n" + ex.getMessage());
            }
        }
    }

    @Override
    public List<Professor> selectProfessors() throws GestorBDException
    {
        List<Professor> llProfessors = new ArrayList<>();
        Statement st = null;

        try
        {
            st = conn.createStatement();
            try (ResultSet rs = st.executeQuery("SELECT admin_nif, admin_nom, admin_cognoms FROM administradors"))
            {
                while (rs.next())
                {
                    llProfessors.add(new Professor(rs.getString("admin_nif"), rs.getString("admin_nom"), rs.getString("admin_cognoms")));
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestorBDException("Error en intentar recuperar la llista de Professors.\n" + ex.getMessage());
        }
        finally
        {
            if (st != null)
            {
                try
                {
                    st.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return llProfessors;
    }
    
    private int getLastId(String nomTaula) throws GestorBDException
    {
        int id = 0;  

        Statement st = null;
        try
        {
            st = conn.createStatement();
            try (ResultSet rs = st.executeQuery("SELECT last_id FROM index_taules WHERE taula_nom = '" + nomTaula + "'"))
            {
                rs.next();
                id = rs.getInt("last_id");
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestorBDException("Error en intentar recuperar l'últim Id.\n" + ex.getMessage());
        }
        finally
        {
            if (st != null)
            {
                try
                {
                    st.close();
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return id;
    }
    
    private void setLastId(String nomTaula, Integer id) throws GestorBDException
    {
        id = id + 1;
        try
        {
            conn.setAutoCommit(false);

            PreparedStatement ps = null;
            try
            {
                String query = "UPDATE index_taules SET last_id = ? WHERE taula_nom = ?";
                ps = conn.prepareStatement(query);

                ps.setInt(1, id);
                ps.setString(2, nomTaula);
                ps.executeUpdate();

                conn.commit();
            }
            catch (SQLException ex)
            {
                throw new GestorBDException("Error en modificar el index de la taula" + ex.getMessage());
            }
            finally
            {
                if (ps != null)
                {
                    try
                    {
                        ps.close();
                    }
                    catch (SQLException ex)
                    {
                        throw new GestorBDException("Error en intentar tancar la sentència que modificat l'índex de la taula. \n" + ex.getMessage());
                    }
                }
                try
                {
                    conn.rollback();
                }
                catch (SQLException ex)
                {
                    throw new GestorBDException("Error en intentar fer rollback en setLastId().\n" + ex.getMessage());
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
