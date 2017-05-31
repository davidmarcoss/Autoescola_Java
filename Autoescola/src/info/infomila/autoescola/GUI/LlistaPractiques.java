/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.infomila.autoescola.GUI;

import info.infomila.autoescola.interficie.persistencia.IGestorBD;
import info.infomila.autoescola.models.Alumne;
import info.infomila.autoescola.models.GestorBDException;
import info.infomila.autoescola.models.Practica;
import info.infomila.autoescola.persistencia.GestorBD;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 *
 * @author David
 */
public class LlistaPractiques extends javax.swing.JDialog
{
    public static final Pattern VALID_DATE_REGEX = Pattern.compile("([0-9]{2})\\/([0-9]{2})\\/([0-9]{4}) ([0-9]{2}):([0-9]{2}):([0-9]{2})", Pattern.CASE_INSENSITIVE);

    private List<Practica> llPractiques;
    private IGestorBD bd;
    private Alumne alumne;
    
    /**
     * Creates new form Practiques
     */
    public LlistaPractiques(java.awt.Frame parent, Alumne alumne, IGestorBD bd)
    {
        super(parent,true);
        
        initComponents();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        if(alumne != null)
        {
            this.alumne = alumne;
            
            label_dadesAlumne.setText("Pràctiques de l'alumne: " + this.alumne.getNom() + " , " + this.alumne.getCognoms());
            
            omplirTaulaPractiques(this.alumne);
        }
    }
    
    private void omplirTaulaPractiques(Alumne alumne)
    {
        try
        {
            bd = new GestorBD();
            llPractiques = bd.selectPractiques(alumne);
            DefaultTableModel model = (DefaultTableModel) tablePractiques.getModel();
            model.setRowCount(0);
            for(Practica prac : llPractiques)
            {
                model.addRow(new Object[]{
                    prac.getDataInici(),
                    prac.getDuracio(),
                    prac.getObservacions(),
                });
            }
        }
        catch (GestorBDException ex)
        {
            Logger.getLogger(LlistaPractiques.class.getName()).log(Level.SEVERE, null, ex);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablePractiques = new javax.swing.JTable();
        label_titol = new javax.swing.JLabel();
        label_dadesAlumne = new javax.swing.JLabel();
        label_dataInici = new javax.swing.JLabel();
        input_dataInici = new javax.swing.JFormattedTextField();
        label_duracio = new javax.swing.JLabel();
        label_observacions = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        input_observacions = new javax.swing.JTextArea();
        btnCrear = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        label_titolForm = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        input_duracio = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablePractiques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String []
            {
                "Data Inici", "Duració", "Observacions"
            }
        ));
        jScrollPane1.setViewportView(tablePractiques);

        label_titol.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        label_titol.setText("Gestio de les pràctiques");

        label_dataInici.setLabelFor(input_dataInici);
        label_dataInici.setText("Data i Hora (dd/mm/yyyy hh:mm:ss)");

        input_dataInici.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))));

        label_duracio.setLabelFor(input_duracio);
        label_duracio.setText("Duració");

        label_observacions.setLabelFor(input_observacions);
        label_observacions.setText("Observacions");

        input_observacions.setColumns(20);
        input_observacions.setRows(5);
        jScrollPane2.setViewportView(input_observacions);

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCrearActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnModificarActionPerformed(evt);
            }
        });

        label_titolForm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label_titolForm.setText("Creació d'una classe pràctica");

        input_duracio.setModel(new javax.swing.SpinnerNumberModel(0, 0, 4, 1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2)
                                    .addComponent(btnCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label_titolForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(label_dataInici)
                                                .addGap(0, 25, Short.MAX_VALUE))
                                            .addComponent(input_dataInici))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label_duracio)
                                            .addComponent(input_duracio, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(34, 34, 34))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(label_observacions)
                                        .addGap(6, 6, 6))))
                            .addComponent(jSeparator2))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_titol)
                            .addComponent(label_dadesAlumne, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(label_titol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_dadesAlumne, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_titolForm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_dataInici)
                            .addComponent(label_duracio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(input_dataInici, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(input_duracio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(label_observacions)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCrearActionPerformed
    {//GEN-HEADEREND:event_btnCrearActionPerformed
        try {                                             
            String strDate = input_dataInici.getText();
            
            validateDate(strDate);
                
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date dataInici = null;
            try {
                dataInici = df.parse(strDate);
                String newDateString = df.format(dataInici);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer duracio = (Integer) input_duracio.getValue();
            String observacions = input_observacions.getText();
            
            // Inserim a la base de dades
            GestorBD bd;
            try
            {
                bd = new GestorBD();
                
                bd.insertPractica(new Practica(dataInici, duracio, alumne, observacions));
                
                omplirTaulaPractiques(alumne);
            }
            catch (GestorBDException ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } catch (GestorBDException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnCrearActionPerformed
    
    public static boolean validateDate(String strDate) throws GestorBDException
    {
        Matcher matcher = VALID_DATE_REGEX.matcher(strDate);
        
        if(!matcher.find()) throw new GestorBDException("La data ha de ser correcte i amb el format indicat.");
        return matcher.find();
    }
    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEliminarActionPerformed
    {//GEN-HEADEREND:event_btnEliminarActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "Estas segur que vols eliminar aquesta pràctica?", "Warning", dialogButton);
        if (dialogResult == JOptionPane.YES_OPTION)
        {
            try
            {
                int rowIndex = tablePractiques.getSelectedRow(); 
                Practica practicaSeleccionada = llPractiques.get(rowIndex);

                GestorBD bd;
                bd = new GestorBD();
                bd.deletePractica(practicaSeleccionada.getId());
                omplirTaulaPractiques(alumne);
            }
            catch (GestorBDException ex)
            {
                Logger.getLogger(LlistaAlumnes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnModificarActionPerformed
    {//GEN-HEADEREND:event_btnModificarActionPerformed
        int rowIndex = tablePractiques.getSelectedRow();
        if(rowIndex >= 0)
        {
            Practica practicaSeleccionada = llPractiques.get(rowIndex);
            
            new GestioPractica(this, practicaSeleccionada, this.alumne, bd).setVisible(true);
        }
        omplirTaulaPractiques(this.alumne);
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JFormattedTextField input_dataInici;
    private javax.swing.JSpinner input_duracio;
    private javax.swing.JTextArea input_observacions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel label_dadesAlumne;
    private javax.swing.JLabel label_dataInici;
    private javax.swing.JLabel label_duracio;
    private javax.swing.JLabel label_observacions;
    private javax.swing.JLabel label_titol;
    private javax.swing.JLabel label_titolForm;
    private javax.swing.JTable tablePractiques;
    // End of variables declaration//GEN-END:variables
}
