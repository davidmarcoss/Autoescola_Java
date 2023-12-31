/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.autoescola.GUI;

import info.infomila.autoescola.interficie.persistencia.IGestorBD;
import info.infomila.autoescola.models.Alumne;
import info.infomila.autoescola.persistencia.GestorBD;
import info.infomila.autoescola.models.GestorBDException;
import info.infomila.autoescola.models.Professor;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author David
 */
public class LlistaAlumnes extends javax.swing.JFrame
{
    private List<Alumne> llAlumnes;
    private List<Professor> llProfessors;
    private IGestorBD bd = null;
    private static String classNameArg = null;

    /**
     * Creates new form Alumnes
     */
    public LlistaAlumnes() throws GestorBDException
    {
        initComponents();
        
        try
        {
            //bd = new GestorBD();
            bd = (IGestorBD) Class.forName(classNameArg).newInstance();
            
            omplirTaula();

            llProfessors = bd.selectProfessors();
            for (Professor professor : llProfessors)
            {
                input_professor.addItem(professor);
            }
            


            this.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    int i = JOptionPane.showConfirmDialog(null, "Estás segur que vols sortir?");
                    if(i == 0)
                    {
                        try
                        {
                            bd.close();
                            System.exit(0);  
                        }
                        catch (GestorBDException ex)
                        {
                            Logger.getLogger(LlistaAlumnes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            
        } catch(GestorBDException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(0);
        }
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(LlistaAlumnes.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(LlistaAlumnes.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(LlistaAlumnes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void omplirTaula() throws GestorBDException
    {
        llAlumnes = bd.selectAlumnes();
        
        DefaultTableModel model = (DefaultTableModel) tableAlumnes.getModel();
        model.setRowCount(0);
        for(Alumne al : llAlumnes)
        {
            model.addRow(new Object[]{
                al.getNif(),
                al.getNom(),
                al.getCognoms(),
                al.getCorreu(),
                al.getPoblacio(),
                al.getAdreca(),
                al.getTelefon(),
                al.getNomProfessor()
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButton1 = new javax.swing.JButton();
        scrollTable = new javax.swing.JScrollPane();
        tableAlumnes = new javax.swing.JTable();
        Titol_lbl = new javax.swing.JLabel();
        btnGestionar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCrear = new javax.swing.JButton();
        input_nif = new javax.swing.JTextField();
        label_nif = new javax.swing.JLabel();
        input_nom = new javax.swing.JTextField();
        label_nom = new javax.swing.JLabel();
        input_cognoms = new javax.swing.JTextField();
        label_cognoms = new javax.swing.JLabel();
        input_correu = new javax.swing.JTextField();
        label_correu = new javax.swing.JLabel();
        label_password = new javax.swing.JLabel();
        input_poblacio = new javax.swing.JTextField();
        label_poblacio = new javax.swing.JLabel();
        input_adreca = new javax.swing.JTextField();
        label_adreca = new javax.swing.JLabel();
        input_telefon = new javax.swing.JTextField();
        label_telefon = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        label_professor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        input_professor = new javax.swing.JComboBox<>();
        input_password = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        label_titolForm = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Autoboxy");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        tableAlumnes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String []
            {
                "NIF", "Nom", "Cognoms", "Correu", "Poblacio", "Adrecal", "Telefon", "Professor"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                true, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        scrollTable.setViewportView(tableAlumnes);

        Titol_lbl.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        Titol_lbl.setText("Gestió dels Alumnes");

        btnGestionar.setText("Gestionar Pràctiques");
        btnGestionar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGestionarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Desactivar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCrearActionPerformed(evt);
            }
        });

        label_nif.setText("NIF");

        label_nom.setText("Nom");

        label_cognoms.setText("Cognoms");

        label_correu.setText("Correu");

        label_password.setText("Password");

        label_poblacio.setText("Població");

        label_adreca.setText("Adreça");

        label_telefon.setText("Telèfon de contacte");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnModificarActionPerformed(evt);
            }
        });

        label_professor.setText("Professor");

        input_password.setText("jPasswordField1");

        label_titolForm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_titolForm.setText("Creació d'un alumne");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGestionar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 854, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(label_nif)
                                                    .addComponent(input_nif, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(label_nom)
                                                    .addComponent(input_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(input_correu, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(input_telefon)
                                            .addComponent(label_cognoms)
                                            .addComponent(input_cognoms, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label_telefon)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_titolForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(169, 169, 169))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(label_correu)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(label_poblacio)
                                                    .addComponent(input_poblacio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(label_adreca)
                                                    .addComponent(input_adreca, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(label_professor)
                                                    .addComponent(input_professor, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(input_password)
                                                        .addGap(2, 2, 2))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(label_password)
                                                        .addGap(0, 0, Short.MAX_VALUE)))))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jSeparator2))
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Titol_lbl)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Titol_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_titolForm)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_nif)
                            .addComponent(label_nom)
                            .addComponent(label_cognoms))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(input_nif, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(input_nom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(input_cognoms, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_correu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(input_correu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_telefon)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(input_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_poblacio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(input_poblacio, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(label_adreca)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(20, 20, 20)
                                    .addComponent(input_adreca, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label_professor)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(input_professor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(input_password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(label_password))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGestionar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGestionarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGestionarActionPerformed
    {//GEN-HEADEREND:event_btnGestionarActionPerformed
        int rowIndex = tableAlumnes.getSelectedRow();
        if(rowIndex >= 0)
        {
            Alumne alumneSeleccionat = llAlumnes.get(rowIndex);
            
            new LlistaPractiques(this, alumneSeleccionat, bd).setVisible(true);
        }
    }//GEN-LAST:event_btnGestionarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEliminarActionPerformed
    {//GEN-HEADEREND:event_btnEliminarActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Estas segur que vols desactivar aquest alumne?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION)
        {
            try
            {
                int rowIndex = tableAlumnes.getSelectedRow(); 
                Alumne alumneSeleccionat = null;
                int size = llAlumnes.size();
                for(int i = 0; i < size; i++)
                {
                    if(i == rowIndex)
                    {
                        alumneSeleccionat = llAlumnes.get(i);
                    }
                }
                
                bd.deleteAlumne(alumneSeleccionat.getNif());
                omplirTaula();
            }
            catch (GestorBDException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCrearActionPerformed
    {//GEN-HEADEREND:event_btnCrearActionPerformed
        MessageDigest dg;
        
        // Encriptem el password
        String passwordmd5 = input_password.getText();
        try
        {
            dg = MessageDigest.getInstance("MD5");
            dg.update(passwordmd5.getBytes());
            
            BigInteger hash = new BigInteger(1, dg.digest());
            
            passwordmd5 = hash.toString(16);
            while (passwordmd5.length() < 32) {
                passwordmd5 = "0" + passwordmd5;
            }
        }
        catch (NoSuchAlgorithmException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        // Recollim tots els valors entrats
        String nif = input_nif.getText();
        String nom = input_nom.getText();
        String cognoms = input_cognoms.getText();
        String correu = input_correu.getText();
        String password = passwordmd5;
        String telefon = input_telefon.getText();
        String poblacio = input_poblacio.getText();
        String adreca = input_adreca.getText();
        Professor professor = llProfessors.get(input_professor.getSelectedIndex());
        
        // Inserim a la base de dades
        try
        {
            bd.insertAlumne(new Alumne(nif, nom, cognoms, correu, passwordmd5, telefon, poblacio, adreca, professor.getNif()));
            
            omplirTaula();
        }
        catch (GestorBDException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
  
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnModificarActionPerformed
    {//GEN-HEADEREND:event_btnModificarActionPerformed
        try
        {
            int rowIndex = tableAlumnes.getSelectedRow();
            if(rowIndex >= 0)
            {
                Alumne alumneSeleccionat = llAlumnes.get(rowIndex);
                
                new GestioAlumne(this, alumneSeleccionat, llProfessors, bd).setVisible(true);
            }
            
            omplirTaula();
        }
        catch (GestorBDException ex)
        {
            Logger.getLogger(LlistaAlumnes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    public static void main(String args[])
    {
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(LlistaAlumnes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        try
        {
            classNameArg = args[0];

            java.awt.EventQueue.invokeLater(new Runnable()
            {
                public void run()
                { 
                    try 
                    {
                        new LlistaAlumnes().setVisible(true);
                    } 
                    catch (GestorBDException ex)
                    {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            });
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(null, "S'ha d'indicar el nom de la classe Gestora de la Base de Dades");
        }
        
        

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titol_lbl;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGestionar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JTextField input_adreca;
    private javax.swing.JTextField input_cognoms;
    private javax.swing.JTextField input_correu;
    private javax.swing.JTextField input_nif;
    private javax.swing.JTextField input_nom;
    private javax.swing.JPasswordField input_password;
    private javax.swing.JTextField input_poblacio;
    private javax.swing.JComboBox<Professor> input_professor;
    private javax.swing.JTextField input_telefon;
    private javax.swing.JButton jButton1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel label_adreca;
    private javax.swing.JLabel label_cognoms;
    private javax.swing.JLabel label_correu;
    private javax.swing.JLabel label_nif;
    private javax.swing.JLabel label_nom;
    private javax.swing.JLabel label_password;
    private javax.swing.JLabel label_poblacio;
    private javax.swing.JLabel label_professor;
    private javax.swing.JLabel label_telefon;
    private javax.swing.JLabel label_titolForm;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tableAlumnes;
    // End of variables declaration//GEN-END:variables
}
