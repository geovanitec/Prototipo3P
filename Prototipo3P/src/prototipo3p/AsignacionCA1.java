package prototipo3p;







/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author  geovani
 */
public class AsignacionCA1 extends javax.swing.JInternalFrame {
    String Semestre = "Semestre" ;
    String Trimestre = "Trimestre" ;
    String Unidad = "Unidad" ;
    String Bimestre = "Bimestre";
    String Extraordinario = "Extraordinario";
    String Privado = "Privado";
    String Zona = "Zona";
    String Item = "";
    String Item2 = "";
    String Parcial_1 = "";
    String Parcial_2 = "";
    String Parcial_3 = "";
    String Parcial_1T = "";
    String Parcial_2T = "";
    String Parcial_3T = "";
    String Parcial_1B = "";
    String Unidad_1 = "";
    String Unidad_2 = "";
    String Unidad_3 = "";
    String Unidad_4 = "";
    String ExtraordinarioS = "";
    String PrivadoS = "";
    String ZonaS = "";
    String NotaS = "";
    String SumaS = "";
    
    float Parcial_Flotante = 0;
    float Parcial2_Flotante = 0;
    float Parcial3_Flotante = 0;
    float Parcial_FlotanteT = 0;
    float Parcial2_FlotanteT = 0;
    float Parcial3_FlotanteT = 0;
    float Parcial_FlotanteB = 0;
    float Unidad1_Flotante = 0;
    float Unidad2_Flotante = 0;
    float Unidad3_Flotante = 0;
    float Unidad4_Flotante = 0;
    float Extraordinario_Flotante = 0;
    float Privado_Flotante = 0;
    float Zona_Flotante = 0;
    float Nota_Flotante = 0;
    float Suma = 0;
    
    boolean Encontrado;
    boolean EncontradoMayorCero = false;
   
    String[] NombresColumnasAsignacionA = {"id_Alumno", "codigo_sede", "codigo_jornada", "codigo_seccion", "codigo_aula", "codigo_curso", "carnet_alumno","Tipo_Nota","Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos"};

    public void MostrarDB(String Tabla) {
        String[] columnas = new String[24];
        String query;
        try {

            Connection c = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);

            query = "SELECT * FROM " + Tabla;


            PreparedStatement consulta = c.prepareStatement(query);
            ResultSet resultado = consulta.executeQuery();
            DefaultTableModel md = new DefaultTableModel(null, NombresColumnasAsignacionA);

            while (resultado.next()) {
                for (int i = 0; i < 23; i++) {
                    columnas[i] = resultado.getString(NombresColumnasAsignacionA[i]);
                }
                md.addRow(columnas);

            }
            tblAsignacionA.setModel(md);

        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    public AsignacionCA1() {
        initComponents();
        jLabel_Tipo.setVisible(false);
        jLabel_Extra.setVisible(false);
        jLabel_Extra.setEnabled(false);
        txt_Nuevo.setVisible(false);
        Busqueda();

            

        jComboBox_Parcial.addItem("Ingrese Una Opcion");
        jComboBox_Parcial.addItem(Semestre);
        jComboBox_Parcial.addItem(Trimestre);
        jComboBox_Parcial.addItem(Unidad);
        jComboBox_Parcial.addItem(Bimestre);
        jComboBox_Parcial.addItem(Extraordinario);
        jComboBox_Parcial.addItem(Privado);
        jComboBox_Parcial.addItem(Zona);        
            
        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select nombre_carrera from carreras");
            ResultSet rs = pst.executeQuery();

            PreparedStatement pst2 = cn.prepareStatement("select nombre_sede from sedes");
            ResultSet rs2 = pst2.executeQuery();

            PreparedStatement pst3 = cn.prepareStatement("select nombre_jornada from jornadas");
            ResultSet rs3 = pst3.executeQuery();

            PreparedStatement pst4 = cn.prepareStatement("select nombre_seccion from secciones");
            ResultSet rs4 = pst4.executeQuery();

            PreparedStatement pst5 = cn.prepareStatement("select nombre_aula from aulas");
            ResultSet rs5 = pst5.executeQuery();

            PreparedStatement pst6 = cn.prepareStatement("select nombre_curso from cursos");
            ResultSet rs6 = pst6.executeQuery();

            PreparedStatement pst7 = cn.prepareStatement("select nombre_alumno from alumnos");
            ResultSet rs7 = pst7.executeQuery();

            cbox_carrera.addItem("Seleccione una opción");
            while (rs.next()) {
                cbox_carrera.addItem(rs.getString("nombre_carrera"));
            }

            cbox_sede.addItem("Seleccione una opción");
            while (rs2.next()) {
                cbox_sede.addItem(rs2.getString("nombre_sede"));
            }

            cbox_j.addItem("Seleccione una opción");
            while (rs3.next()) {
                cbox_j.addItem(rs3.getString("nombre_jornada"));
            }

            cbox_sec.addItem("Seleccione una opción");
            while (rs4.next()) {
                cbox_sec.addItem(rs4.getString("nombre_seccion"));
            }

            cbox_aula.addItem("Seleccione una opción");
            while (rs5.next()) {
                cbox_aula.addItem(rs5.getString("nombre_aula"));
            }

            cbox_curso.addItem("Seleccione una opción");
            while (rs6.next()) {
                cbox_curso.addItem(rs6.getString("nombre_curso"));
            }

            cbox_alum.addItem("Seleccione una opción");
            while (rs7.next()) {
                cbox_alum.addItem(rs7.getString("nombre_alumno"));
            }
            

            

        } catch (Exception e) 
        {

        }
        MostrarDB("asignacioncursosalumnos");
    }
    
    public String[]datos(String datos)
    {
        Item = jComboBox_Parcial.getSelectedItem().toString();
        int i = 0, j = 0, suma = j+1;
        
        if(Item == "Semestre")
        {
            i = 3;
        }
        else if(Item == "Trimestre")
        {
            i = 3;
        }
        else if(Item == "Bimestre")
        {
            i = 1;
        }
        else if(Item == "Unidad")
        {
            i=4;
        }
        else if(Item == "Extraordinario")
        {
            i=1;
        }
        else if(Item == "Privado")
        {
            i=1;
        }      
        else if(Item == "Zona")
        {
            i=1;
        }        
      
        String[] informacion = new String[i];
        
    
        if(datos.equalsIgnoreCase("Semestre"))
        {
            /*while(j<i)
            {
                informacion[j] = "Parcial " + suma;
                j++;
                suma++;
            }*/
            
            informacion[0] = "Parcial 1";
            informacion[1] = "Parcial 2";
            informacion[2] = "Parcial 3";
        }
        
        if(datos.equalsIgnoreCase("Trimestre"))
        {
           /* while(j<i)
            {
                informacion[j] = "Parcial " + suma;
                j++;
                suma++;
            }*/
            informacion[0] = "Parcial 1";
            informacion[1] = "Parcial 2";
            informacion[2] = "Parcial 3"; 
        }
        
        if(datos.equalsIgnoreCase("Unidad"))
        {
            
           /* while(j<i)
            {
                informacion[j] = "Unidad " + suma;
                j++;
                suma++;
            }*/
            
            informacion[0] = "Unidad 1";
            informacion[1] = "Unidad 2";
            informacion[2] = "Unidad 3"; 
            informacion[3] = "Unidad 4"; 
        }
            
        if(datos.equalsIgnoreCase("Bimestre"))
        {
           /* while(j<i)
            {
                informacion[j] = "Parcial " + suma;
                j++;
                suma++;
            }*/
            informacion[0] = "Parcial 1";
        }
        
        if(datos.equalsIgnoreCase("Extraordinario"))
        {
           /* while(j<i)
            {
                informacion[j] = "Parcial " + suma;
                j++;
                suma++;
            }*/
            informacion[0] = "Extraordinario";
        }       
        
        if(datos.equalsIgnoreCase("Privado"))
        {
           /* while(j<i)
            {
                informacion[j] = "Parcial " + suma;
                j++;
                suma++;
            }*/
            informacion[0] = "Privado";
        }        
        
        if(datos.equalsIgnoreCase("Zona"))
        {
           /* while(j<i)
            {
                informacion[j] = "Parcial " + suma;
                j++;
                suma++;
            }*/
            informacion[0] = "Zona";
        }             
         
        
        return informacion;
   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txt_Nuevo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtbuscado = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_Codigo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbox_carrera = new javax.swing.JComboBox<String>();
        lb1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbox_sede = new javax.swing.JComboBox<String>();
        lb2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbox_j = new javax.swing.JComboBox<String>();
        lb3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbox_sec = new javax.swing.JComboBox<String>();
        lb4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbox_aula = new javax.swing.JComboBox<String>();
        lb5 = new javax.swing.JLabel();
        lb6 = new javax.swing.JLabel();
        cbox_curso = new javax.swing.JComboBox<String>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbox_alum = new javax.swing.JComboBox<String>();
        lb7 = new javax.swing.JLabel();
        jLabel_Extra = new javax.swing.JLabel();
        jLabel_Tipo = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAsignacionA = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox_Parcial = new javax.swing.JComboBox<String>();
        jComboBox_Parcial2 = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        txt_Nota = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Asignacion cursos alumnos");
        setVisible(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEliminar.setText("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, 80, 20));

        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 95, -1));
        jPanel2.add(txt_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 500, 88, -1));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 95, -1));
        jPanel2.add(txtbuscado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 450, 102, -1));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 95, -1));

        jLabel4.setText("Codigo");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        txt_Codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_CodigoActionPerformed(evt);
            }
        });
        jPanel2.add(txt_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 219, -1));

        jLabel5.setText("Carrera");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        cbox_carrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_carreraActionPerformed(evt);
            }
        });
        jPanel2.add(cbox_carrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 70, 219, 20));

        lb1.setText("...");
        jPanel2.add(lb1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, -1, -1));

        jLabel6.setText("Sede");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 30, -1));

        cbox_sede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_sedeActionPerformed(evt);
            }
        });
        jPanel2.add(cbox_sede, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 219, 20));

        lb2.setText("...");
        jPanel2.add(lb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        jLabel8.setText("Jornada");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        cbox_j.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_jActionPerformed(evt);
            }
        });
        jPanel2.add(cbox_j, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 219, -1));

        lb3.setText("...");
        jPanel2.add(lb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        jLabel7.setText("Seccion");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        cbox_sec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_secActionPerformed(evt);
            }
        });
        jPanel2.add(cbox_sec, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 219, -1));

        lb4.setText("...");
        jPanel2.add(lb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, -1, -1));

        jLabel10.setText("Aula");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        cbox_aula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_aulaActionPerformed(evt);
            }
        });
        jPanel2.add(cbox_aula, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 219, -1));

        lb5.setText("...");
        jPanel2.add(lb5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, -1, -1));

        lb6.setText("...");
        jPanel2.add(lb6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        cbox_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_cursoActionPerformed(evt);
            }
        });
        jPanel2.add(cbox_curso, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 219, -1));

        jLabel9.setText("Curso");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, -1));

        jLabel11.setText("Alumno");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        cbox_alum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_alumActionPerformed(evt);
            }
        });
        jPanel2.add(cbox_alum, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 220, -1));

        lb7.setText("...");
        jPanel2.add(lb7, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, -1, -1));

        jLabel_Extra.setText("Usted Asistio a Sus Parciales ,No tiene permiso de Extraordinario.");
        jPanel2.add(jLabel_Extra, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 340, 50));

        jLabel_Tipo.setText("Alumno ya ingresado en Semestre o Trimestre o Bimestre o Unidad,ingrese en correcto.");
        jPanel2.add(jLabel_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 470, 30));

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblAsignacionA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblAsignacionA.setGridColor(new java.awt.Color(255, 255, 255));
        tblAsignacionA.setSelectionBackground(new java.awt.Color(102, 204, 255));
        jScrollPane3.setViewportView(tblAsignacionA);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 980, 310));

        jTabbedPane3.addTab("Datos", jPanel3);

        jPanel2.add(jTabbedPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 1000, 390));

        jLabel1.setText("Tipo de Nota");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jComboBox_Parcial.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_ParcialItemStateChanged(evt);
            }
        });
        jPanel2.add(jComboBox_Parcial, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 221, -1));

        jComboBox_Parcial2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        jPanel2.add(jComboBox_Parcial2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 220, -1));

        jLabel3.setText("Nota");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, -1, -1));

        txt_Nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NotaActionPerformed(evt);
            }
        });
        jPanel2.add(txt_Nota, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 220, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbox_carreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_carreraActionPerformed
        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select codigo_carrera from carreras where nombre_carrera= ?");
            pst.setString(1, cbox_carrera.getSelectedItem().toString());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lb1.setText(rs.getString("codigo_carrera"));

            } else {

            }

        } catch (Exception e) {

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_carreraActionPerformed

    private void cbox_sedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_sedeActionPerformed

        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select codigo_sede from sedes where nombre_sede= ?");
            pst.setString(1, cbox_sede.getSelectedItem().toString());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lb2.setText(rs.getString("codigo_sede"));

            } else {

            }

        } catch (Exception e) {

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_sedeActionPerformed

    private void cbox_jActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_jActionPerformed
        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select codigo_jornada from jornadas where nombre_jornada= ?");
            pst.setString(1, cbox_j.getSelectedItem().toString());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lb3.setText(rs.getString("codigo_jornada"));

            } else {

            }

        } catch (Exception e) {

        }        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_jActionPerformed

    private void cbox_secActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_secActionPerformed
        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select codigo_seccion from secciones where nombre_seccion= ?");
            pst.setString(1, cbox_sec.getSelectedItem().toString());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lb4.setText(rs.getString("codigo_seccion"));

            } else {

            }

        } catch (Exception e) {

        }  // TODO add your handling code here:
    }//GEN-LAST:event_cbox_secActionPerformed

    private void cbox_aulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_aulaActionPerformed
        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select codigo_aula from aulas where nombre_aula= ?");
            pst.setString(1, cbox_aula.getSelectedItem().toString());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lb5.setText(rs.getString("codigo_aula"));

            } else {

            }

        } catch (Exception e) {

        }

// TODO add your handling code here:
    }//GEN-LAST:event_cbox_aulaActionPerformed

    private void cbox_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_cursoActionPerformed
        try {
           Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select codigo_curso from cursos where nombre_curso= ?");
            pst.setString(1, cbox_curso.getSelectedItem().toString());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lb6.setText(rs.getString("codigo_curso"));

            } else {

            }

        } catch (Exception e) {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_cursoActionPerformed

    private void cbox_alumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_alumActionPerformed
        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select carnet_alumno from alumnos where nombre_alumno= ?");
            pst.setString(1, cbox_alum.getSelectedItem().toString());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                lb7.setText(rs.getString("carnet_alumno"));

            } else {

            }

        } catch (Exception e) {

        }    // TODO add your handling code here:
    }//GEN-LAST:event_cbox_alumActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

            jLabel_Extra.setVisible(false);
            Busqueda();
            Item = jComboBox_Parcial.getSelectedItem().toString();
            Item2 = jComboBox_Parcial2.getSelectedItem().toString();        
            if(Encontrado == true)
            {
                
                
                if(Item == "Semestre" && Item2 == "Parcial 1")
                {
                    IngresarMAC(Item,Item2,10,"Parcial_1",Parcial_Flotante);
                    MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Semestre" && Item2 == "Parcial 2")
                {
                    IngresarMAC(Item,Item2,11,"Parcial_2",Parcial2_Flotante);
                    MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Semestre" && Item2 == "Parcial 3")
                {
                    IngresarMAC(Item,Item2,12,"Parcial_3",Parcial3_Flotante);
                    MostrarDB("asignacioncursosalumnos");
                }     
                else if(Item == "Trimestre" && Item2 == "Parcial 1")
                {
                    IngresarMAC(Item,Item2,13,"Parcial_1T",Parcial_FlotanteT);
                            MostrarDB("asignacioncursosalumnos");
                }            
                else if(Item == "Trimestre" && Item2 == "Parcial 2")
                {
                    IngresarMAC(Item,Item2,14,"Parcial_2T",Parcial2_FlotanteT);
                            MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Trimestre" && Item2 == "Parcial 3")
                {
                    IngresarMAC(Item,Item2,15,"Parcial_3T",Parcial3_FlotanteT);
                            MostrarDB("asignacioncursosalumnos");
                }    
                else if(Item == "Bimestre" && Item2 == "Parcial 1")
                {
                    IngresarMAC(Item,Item2,16,"Parcial_1B",Parcial_FlotanteB);
                            MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Unidad" && Item2 == "Unidad 1")
                {
                    IngresarMAC(Item,Item2,17,"Unidad_1",Unidad1_Flotante);
                            MostrarDB("asignacioncursosalumnos");
                }      
                else if(Item == "Unidad" && Item2 == "Unidad 2")
                {
                    IngresarMAC(Item,Item2,18,"Unidad_2",Unidad2_Flotante);
                            MostrarDB("asignacioncursosalumnos");
                }    
                else if(Item == "Unidad" && Item2 == "Unidad 3")
                {
                    IngresarMAC(Item,Item2,19,"Unidad_3",Unidad3_Flotante);
                            MostrarDB("asignacioncursosalumnos");
                }             
                else if(Item == "Unidad" && Item2 == "Unidad 4")
                {
                    IngresarMAC(Item,Item2,20,"Unidad_4",Unidad4_Flotante);
                            MostrarDB("asignacioncursosalumnos");
                }           
                else if(Item == "Extraordinario" && Item2 == "Extraordinario")
                {
                    IngresarMAC(Item,Item2,21,"Extraordinario",Extraordinario_Flotante);
                            MostrarDB("asignacioncursosalumnos");
                }      
                else if(Item == "Privado" && Item2 == "Privado")
                {
                    IngresarMAC(Item,Item2,22,"Privado",Privado_Flotante);
                            MostrarDB("asignacioncursosalumnos");
                }  
                else if(Item == "Zona" && Item2 == "Zona")
                {
                    IngresarMAC(Item,Item2,23,"Zona",Zona_Flotante);
                            MostrarDB("asignacioncursosalumnos");
                }        
                
                /*try
                {
         
                Item = jComboBox_Parcial.getSelectedItem().toString();
                Item2 = jComboBox_Parcial2.getSelectedItem().toString();
                //System.out.println(Item2);
            
                String ID = txt_Codigo.getText().trim();
            
                Connection cn = DriverManager.getConnection(Principal.BD, Principal.Usuario, Principal.Contraseña);
                PreparedStatement pst = cn.prepareStatement("update asignacioncursosalumnos set  id_Alumno = ?, codigo_carrera = ?,codigo_sede = ?,codigo_jornada = ?, codigo_seccion = ?,codigo_aula=?,codigo_curso=?,carnet_alumno=?,Tipo_Nota=?,Parcial_1=?,Parcial_2=?,Parcial_3=?,Parcial_4=?,Parcial_5=?,Extraordinario=?,Privado=?,Zona=?,nota_asignacioncursoalumnos=? where id_Alumno = " + ID);
            
                pst.setString(1, txt_Codigo.getText().trim());
                pst.setString(2, lb1.getText().trim());
                pst.setString(3, lb2.getText().trim());
                pst.setString(4, lb3.getText().trim());
                pst.setString(5, lb4.getText().trim());
                pst.setString(6, lb5.getText().trim());
                pst.setString(7, lb6.getText().trim());
                pst.setString(8, lb7.getText().trim());
                
            
                if(Item == "Semestre")
                {
                    pst.setString(9,Item);
                    if(Item2 == "Parcial 1")
                    {
                    BusquedaMayor("Parcial_1");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(10,txt_Nota.getText().trim());
                    }
                    else
                    {
                    System.out.println("Ya ingresado");
                    }
                    BusquedaMayor("Parcial_2");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(11,txt_Nuevo.getText().trim());
                    }
                    BusquedaMayor("Parcial_3");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(12,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Parcial_4");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(13,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Parcial_5");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(14,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Extraordinario");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(15,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Privado");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(16,txt_Nuevo.getText().trim());
                    }
                    BusquedaMayor("Zona");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(17,txt_Nuevo.getText().trim());
                    }
     
                    pst.setString(18, txt_Nota.getText().trim());
                    pst.executeUpdate();
            
                    System.out.println("Registrado");
                    }
                }

                if(Item == "Semestre")
                {
                    pst.setString(9,Item);
                    if(Item2 == "Parcial 2")
                    {
                    BusquedaMayor("Parcial_1");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(10,txt_Nuevo.getText().trim());
                    }
                    else
                    {
                    System.out.println("Ya ingresado");
                    }
                    BusquedaMayor("Parcial_2");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(11,txt_Nota.getText().trim());
                    }
                    BusquedaMayor("Parcial_3");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(12,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Parcial_4");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(13,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Parcial_5");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(14,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Extraordinario");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(15,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Privado");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(16,txt_Nuevo.getText().trim());
                    }
                    BusquedaMayor("Zona");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(17,txt_Nuevo.getText().trim());
                    }
     
                    pst.setString(18, txt_Nota.getText().trim());
                    pst.executeUpdate();
            
                    System.out.println("Registrado");
                    }
                }

                if(Item == "Semestre")
                {
                    pst.setString(9,Item);
                    if(Item2 == "Parcial 3")
                    {
                    BusquedaMayor("Parcial_1");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(10,txt_Nuevo.getText().trim());
                    }
                    else
                    {
                    System.out.println("Ya ingresado");
                    }
                    BusquedaMayor("Parcial_2");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(11,txt_Nuevo.getText().trim());
                    }
                    BusquedaMayor("Parcial_3");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(12,txt_Nota.getText().trim());
                    }
                    
                    BusquedaMayor("Parcial_4");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(13,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Parcial_5");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(14,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Extraordinario");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(15,txt_Nuevo.getText().trim());
                    }
                    
                    BusquedaMayor("Privado");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(16,txt_Nuevo.getText().trim());
                    }
                    BusquedaMayor("Zona");
                    txt_Nuevo.setText(Parcial_1);
                    if(Parcial_Flotante >= 0)
                    {
                    pst.setString(17,txt_Nuevo.getText().trim());
                    }
     
                    pst.setString(18, txt_Nota.getText().trim());
                    pst.executeUpdate();
            
                    System.out.println("Registrado");
                    }
                }


                }catch(Exception e)
                {
                System.out.println(e);
                }*/
            }     
            else if(Encontrado == false)
        {
            Item = jComboBox_Parcial.getSelectedItem().toString();
            Item2 = jComboBox_Parcial2.getSelectedItem().toString();
            
              
            try
            {

                System.out.println(Item);
                System.out.println(Item2);
            
            
                if(Item == "Semestre" && Item2 == "Parcial 1")
                {
                   IngresarMA(Item,Item2,10);
                           MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Semestre" && Item2 == "Parcial 2")
                {
                    IngresarMA(Item,Item2,11);
                            MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Semestre" && Item2 == "Parcial 3")
                {
                   IngresarMA(Item,Item2,12);
                           MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Trimestre" && Item2 == "Parcial 1")
                {
                    IngresarMA(Item,Item2,13);
                            MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Trimestre" && Item2 == "Parcial 2")
                {
                    IngresarMA(Item,Item2,14);
                            MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Trimestre" && Item2 == "Parcial 3")
                {
                    IngresarMA(Item,Item2,15);
                            MostrarDB("asignacioncursosalumnos");
                }  
                else if(Item == "Bimestre" && Item2 == "Bimestre")
                {
                    IngresarMA(Item,Item2,16);
                    MostrarDB("asignacioncursosalumnos");
                }                     
                else if(Item == "Unidad" && Item2 == "Unidad 1")
                {
                    IngresarMA(Item,Item2,17);
                            MostrarDB("asignacioncursosalumnos");
                }                
                else if(Item == "Unidad" && Item2 == "Unidad 2")
                {
                    IngresarMA(Item,Item2,18);
                            MostrarDB("asignacioncursosalumnos");
                }
                else if(Item == "Unidad" && Item2 == "Unidad 3")
                {
                    IngresarMA(Item,Item2,19);
                    MostrarDB("asignacioncursosalumnos");
                }                  
                else if(Item == "Unidad" && Item2 == "Unidad 4")
                {
                    IngresarMA(Item,Item2,20);
                            MostrarDB("asignacioncursosalumnos");
                }     
                else if(Item == "Extraordinario" && Item2 == "Extraordinario")
                {                     
                    IngresarMA(Item,Item2,21);
                    MostrarDB("asignacioncursosalumnos");
                }               
                else if(Item == "Privado" && Item2 == "Privado")
                {
                    IngresarMA(Item,Item2,22);
                            MostrarDB("asignacioncursosalumnos");
                }  
                else if(Item == "Zona" && Item2 == "Zona")
                {
                    IngresarMA(Item,Item2,23);
                    MostrarDB("asignacioncursosalumnos");
                }                    
            }catch(Exception e)
            {
            System.out.println(e);
            }  
        }
       /* try {
            Connection cn = DriverManager.getConnection(Principal.BD, Principal.Usuario, Principal.Contraseña);
            PreparedStatement pst = cn.prepareStatement("insert into asignacioncursosalumnos values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, txt_id.getText().trim());
            pst.setString(2, lb1.getText().trim());
            pst.setString(3, lb2.getText().trim());
            pst.setString(4, lb3.getText().trim());
            pst.setString(5, lb4.getText().trim());
            pst.setString(6, lb5.getText().trim());
            pst.setString(7, lb6.getText().trim());
            pst.setString(8, lb7.getText().trim());
            pst.setString(9, txt_n.getText().trim());

            pst.executeUpdate();
            MostrarDB("asignacioncursosalumnos");
            JOptionPane.showMessageDialog(this, "¡REGISTRO EXITOSO!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);

            txt_n.setText("");
            txt_id.setText("");
            lb1.setText("");
            lb2.setText("");
            lb3.setText("");
            lb4.setText("");
            lb5.setText("");
            lb6.setText("");
            lb7.setText("");
            cbox_j.setSelectedIndex(0);
            cbox_curso.setSelectedIndex(0);
            cbox_sede.setSelectedIndex(0);
            cbox_carrera.setSelectedIndex(0);
            cbox_alum.setSelectedIndex(0);
            cbox_sec.setSelectedIndex(0);
            cbox_aula.setSelectedIndex(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "¡REGITRO FALLIDO!", "Error", JOptionPane.ERROR_MESSAGE);

        }*/

        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public void IngresarMA(String ItemMA,String Item2MA,int Lugar)
    {
         try
            {
                String cero = "0";
                if(Item == ItemMA && Item2 == Item2MA)
                {
                    boolean prueba = true;
                    System.out.println(prueba);
                    if(prueba == true)
                    {
                        Connection cn4 = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
                        PreparedStatement pst4 = cn4.prepareStatement("insert into asignacioncursosalumnos values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    
                        pst4.setString(1, txt_Codigo.getText().trim());
                        pst4.setString(2, lb1.getText().trim());
                        pst4.setString(3, lb2.getText().trim());
                        pst4.setString(4, lb3.getText().trim());
                        pst4.setString(5, lb4.getText().trim());
                        pst4.setString(6, lb5.getText().trim());
                        pst4.setString(7, lb6.getText().trim());
                        pst4.setString(8, lb7.getText().trim());
                        pst4.setString(9,Item);
                        for(int i = 10;i<=24;i++)
                        {
                            if(i == Lugar)
                            {
                                pst4.setString(i,txt_Nota.getText().trim());
                             
                            }
                            else
                            {
                                pst4.setString(i,"0");
                            }   

                        }

                        pst4.executeUpdate();
                        txt_Codigo.setText("");

                        System.out.println("Registrado");
                    }
                }
            }catch(Exception e)
            {
            System.out.println(e);
            }  
    }
    
    public void IngresarMAC(String ItemMAC,String Item2MAC,int LugarC,String ParcialMAC,Float ParcialFloat)
    {
        try
                {
         
                Item = jComboBox_Parcial.getSelectedItem().toString();
                Item2 = jComboBox_Parcial2.getSelectedItem().toString();
                //System.out.println(Item2);
            
                String ID = txt_Codigo.getText().trim();
            
                Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
                PreparedStatement pst = cn.prepareStatement("update asignacioncursosalumnos set  id_Alumno = ?, codigo_carrera = ?,codigo_sede = ?,codigo_jornada = ?, codigo_seccion = ?,codigo_aula=?,codigo_curso=?,carnet_alumno=?,Tipo_Nota=?,Parcial_1=?,Parcial_2=?,Parcial_3=?,Parcial_1T=?,Parcial_2T=?,Parcial_3T=?,Parcial_1B=?,Unidad_1=?,Unidad_2=?,Unidad_3=?,Unidad_4=?,Extraordinario=?,Privado=?,Zona=?,nota_asignacioncursoalumnos=? where id_Alumno = " + ID);
            
                pst.setString(1, txt_Codigo.getText().trim());
                pst.setString(2, lb1.getText().trim());
                pst.setString(3, lb2.getText().trim());
                pst.setString(4, lb3.getText().trim());
                pst.setString(5, lb4.getText().trim());
                pst.setString(6, lb5.getText().trim());
                pst.setString(7, lb6.getText().trim());
                pst.setString(8, lb7.getText().trim());
                
            
                if(Item == ItemMAC)
                {
                     
                    pst.setString(9,Item);
                    System.out.println(Item);

                    if(Item2 == Item2MAC)
                    {
                        System.out.println(Item2);
                        BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                        for(int i = 10;i<=24;i++)
                        {
                            if(ParcialFloat == 0)
                            {
                                if(i == LugarC)
                                {
                                    System.out.println(i);
                                    pst.setString(i,txt_Nota.getText().trim());
                               
                                }
                                
                                if(Item == ItemMAC && i == 21)
                                {
                                    if(Parcial_Flotante > 0  && Parcial2_Flotante >0 && Parcial3_Flotante>0)
                                    {
                                        jLabel_Extra.setVisible(true);
                                        pst.setString(i,"0");
                                        
                                    }
                                }
                                
                                if(Item == ItemMAC && i == 21)
                                {
                                    if(Parcial_FlotanteT > 0  && Parcial2_FlotanteT >0 && Parcial3_FlotanteT>0)
                                    {
                                        jLabel_Extra.setVisible(true);
                                        pst.setString(i,"0");
                                                                              
                                        
                                       // JOptionPane.showMessageDialog(null,"El Alumno No puede Tener Extraordinario, Asistio a todos sus cursos,Ingrese de Nuevo.");
                            
                                    }                                       
                                }
                                
                                if(Item == ItemMAC && i == 21)
                                {
                                    if(Parcial_FlotanteB > 0)
                                    {
                                        jLabel_Extra.setVisible(true);
                                        pst.setString(i,"0");
                                                                            
                                        
                                       // JOptionPane.showMessageDialog(null,"El Alumno No puede Tener Extraordinario, Asistio a todos sus cursos,Ingrese de Nuevo.");
                            
                                    }                                       
                                }                                   
                                
                                if(Item == ItemMAC && i == 21)
                                {
                                    if(Unidad1_Flotante > 0  && Unidad2_Flotante >0 && Unidad3_Flotante>0 && Unidad4_Flotante ==0)
                                    {
                                        jLabel_Extra.setVisible(true);
                                        pst.setString(i,"0");
                                                                                
                                        
                                       // JOptionPane.showMessageDialog(null,"El Alumno No puede Tener Extraordinario, Asistio a todos sus cursos,Ingrese de Nuevo.");
                            
                                    }                                       
                                } 
                                
                                
                                if(Item == ItemMAC && i == 21)
                                {
                                    if(Parcial_FlotanteB >0)
                                    {
                                        jLabel_Extra.setVisible(true);
                                        pst.setString(i,"0");
                                         
                                    }                                      
                                }           
                            }
                            
                            if(ParcialFloat >=0)
                            {
                                System.out.println("Hola2");
                                if(Parcial_FlotanteT == 0 && Parcial2_FlotanteT == 0 && Parcial3_FlotanteT ==0 && Parcial_FlotanteB==0 && Unidad1_Flotante ==0 && Unidad2_Flotante ==0 && Unidad3_Flotante ==0 && Unidad4_Flotante == 0 )
                                {
                               if(i == 10 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Parcial_1);
                                    pst.setString(i,txt_Nuevo.getText().trim());
                               }

                               else if(i== 11 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Parcial_2);
                                    pst.setString(i,txt_Nuevo.getText().trim());
                               }
                               else if( i == 12 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Parcial_3);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }                                    
                                }
                                else if(i==10 || i==11 || i==12)
                                {
                                    pst.setString(i,"0");  
                                    jLabel_Tipo.setVisible(true);
                                }

                               if(Parcial_Flotante == 0 && Parcial2_Flotante == 0 && Parcial3_Flotante ==0 && Parcial_FlotanteB==0 && Unidad1_Flotante ==0 && Unidad2_Flotante ==0 && Unidad3_Flotante ==0 && Unidad4_Flotante == 0 )    
                               {
                               if( i == 13 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Parcial_1T);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               } 
                             
                               else if( i == 14 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Parcial_2T);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }        
                               
                               else if( i == 15 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Parcial_3T);
                                    pst.setString(i,txt_Nuevo.getText().trim());     
                               }    

                               }
                               else if(i==13 || i==14 || i==15)
                               {
                                    pst.setString(i,"0");  
                                    jLabel_Tipo.setVisible(true);    
                                    
                               }     
                                    
                                if(Parcial_Flotante == 0 && Parcial2_Flotante == 0 && Parcial3_Flotante ==0 &&Parcial_FlotanteT == 0 && Parcial2_FlotanteT == 0 && Parcial3_FlotanteT ==0  && Unidad1_Flotante ==0 && Unidad2_Flotante ==0 && Unidad3_Flotante ==0 && Unidad4_Flotante == 0 )   
                                {
                                    if( i == 16 && i != LugarC)
                                    {
                                    txt_Nuevo.setText(Parcial_1B);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                                    }                                        
                                }
                                else if(i == 16)
                                {
                                    pst.setString(i,"0");  
                                    jLabel_Tipo.setVisible(true);                                     
                                }
                                if(Parcial_Flotante == 0 && Parcial2_Flotante == 0 && Parcial3_Flotante ==0 && Parcial_FlotanteT == 0 && Parcial2_FlotanteT == 0 && Parcial3_FlotanteT ==0 && Parcial_FlotanteB==0)
                                {
                               if( i == 17 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Unidad_1);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }   
                               else if( i == 18 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Unidad_2);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }          
                               else if( i == 19 && i != LugarC)
                               {
                                    txt_Nuevo.setText(Unidad_3);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }    
                               else if( i == 20 && i != LugarC)
                               {
                                   System.out.println("Entre");
                                    txt_Nuevo.setText(Unidad_4);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }                                      
                                }
                                else if(i==17 || i==18 || i==19 || i == 20)
                                {
                                    pst.setString(i,"0");  
                                    jLabel_Tipo.setVisible(true); 
                                }

    
                               if( i == 21 && i != LugarC)
                               {
                                    txt_Nuevo.setText(ExtraordinarioS);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }    
                               else if( i == 22 && i != LugarC)
                               {
                                    txt_Nuevo.setText(PrivadoS);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }          
                               else if( i == 23 && i != LugarC)
                               {
                                    txt_Nuevo.setText(ZonaS);
                                    pst.setString(i,txt_Nuevo.getText().trim());                                   
                               }    
                               else if( i == 24 && i != LugarC)
                               {
                                   
                                    txt_Nuevo.setText(SumaS);
                                    pst.setString(i,txt_Nuevo.getText().trim());  
                                    
                               }                                

                               
                            }
                        }
           
                    
                    pst.executeUpdate();
                    txt_Codigo.setText("");
                    System.out.println("Registrado");
                    }
                }
                }catch(Exception e)
                {
                System.out.println(e);
                
                }
        
        
    }
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        jLabel_Extra.setVisible(false);
        jLabel_Tipo.setVisible(false);
        String buscar = txtbuscado.getText().trim();
        if (buscar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "¡No se ingreso el campo de busqueda!", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
           Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("select * from asignacioncursosalumnos where id_Alumno = ?");
            pst.setString(1, txtbuscado.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_Codigo.setText(rs.getString("id_Alumno"));
                lb1.setText(rs.getString("codigo_carrera"));
                lb2.setText(rs.getString("codigo_sede"));
                lb3.setText(rs.getString("codigo_jornada"));
                lb4.setText(rs.getString("codigo_seccion"));
                lb5.setText(rs.getString("codigo_aula"));
                lb6.setText(rs.getString("codigo_curso"));
                lb7.setText(rs.getString("carnet_alumno"));
                txt_Nota.setText(rs.getString("nota_asignacioncursoalumnos"));

                btnEliminar.setEnabled(true);
                btnModificar.setEnabled(true);

            } else {
                JOptionPane.showMessageDialog(null, " no registrado.");
            }

        } catch (Exception err) {
            err.printStackTrace();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("delete from asignacioncursosalumnos where id_Alumno = ?");

            pst.setString(1, txtbuscado.getText().trim());
            pst.executeUpdate();
            MostrarDB("asignacioncursosalumnos");
            txtbuscado.setText("");

            JOptionPane.showMessageDialog(this, "REGISTRO ELIMINADO.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);

            txt_Codigo.setText("");
            txt_Nota.setText("");
            lb1.setText("");
            lb2.setText("");
            lb3.setText("");
            lb4.setText("");
            lb5.setText("");
            lb6.setText("");
            lb7.setText("");
            cbox_carrera.setSelectedIndex(0);
            cbox_j.setSelectedIndex(0);
            cbox_aula.setSelectedIndex(0);
            cbox_alum.setSelectedIndex(0);
            cbox_sec.setSelectedIndex(0);
            cbox_sede.setSelectedIndex(0);
            cbox_curso.setSelectedIndex(0);
            txtbuscado.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en la eliminación de registros.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        
        Item = jComboBox_Parcial.getSelectedItem().toString();
        Item2 = jComboBox_Parcial2.getSelectedItem().toString();  
        if(Item == "Semestre" && Item2== "Parcial 1")
        {
            ModificarT(Item,Item2,10);
        }
        else if(Item == "Semestre" && Item2== "Parcial 2")
        {
            ModificarT(Item,Item2,11);
        }
        else if(Item == "Semestre" && Item2== "Parcial 3")
        {
            ModificarT(Item,Item2,12);
        }    
        else if(Item == "Trimestre" && Item2== "Parcial 1")
        {
            ModificarT(Item,Item2,13);
        }           
        else if(Item == "Trimestre" && Item2== "Parcial 2")
        {
            ModificarT(Item,Item2,14);
        }     
        else if(Item == "Trimestre" && Item2== "Parcial 3")
        {
            ModificarT(Item,Item2,15);
        }       
        else if(Item == "Bimestre" && Item2== "Parcial 1")
        {
            ModificarT(Item,Item2,16);
        }     
        else if(Item == "Unidad" && Item2== "Unidad 1")
        {
            ModificarT(Item,Item2,17);
        }  
        else if(Item == "Unidad" && Item2== "Unidad 2")
        {
            ModificarT(Item,Item2,18);
        }          
        else if(Item == "Unidad" && Item2== "Unidad 3")
        {
            ModificarT(Item,Item2,19);
        }     
        else if(Item == "Unidad" && Item2== "Unidad 4")
        {
            ModificarT(Item,Item2,20);
        }     
        else if(Item == "Extraordinario" && Item2== "Extraordinario")
        {
            ModificarT(Item,Item2,21);
        }   
        else if(Item == "Privado" && Item2== "Privado")
        {
            ModificarT(Item,Item2,22);
        }             
        else if(Item == "Zona" && Item2== "Zona")
        {
            ModificarT(Item,Item2,23);
        }             

        /*try {
            String codigo = txtbuscado.getText().trim();

            Connection cn = DriverManager.getConnection(Principal.BD, Principal.Usuario, Principal.Contraseña);
            PreparedStatement pst = cn.prepareStatement("update asignacioncursosalumnos set  id_Alumno = ?, codigo_carrera = ?,codigo_sede = ?,codigo_jornada = ?, codigo_seccion = ?,codigo_aula=?,codigo_curso=?,carnet_alumno=?,Tipo_Nota=?,Parcial_1=?,Parcial_2=?,Parcial_3=?,Parcial_1T=?,Parcial_2T=?,Parcial_3T=?,Parcial_1B=?,Unidad_1=?,Unidad_2=?,Unidad_3=?,Unidad_4=?,Extraordinario=?,Privado=?,Zona=?,nota_asignacioncursoalumnos=? where id_Alumno = " + codigo);

            pst.setString(1, txt_Codigo.getText().trim());
            pst.setString(2, lb1.getText().trim());
            pst.setString(3, lb2.getText().trim());
            pst.setString(4, lb3.getText().trim());
            pst.setString(5, lb4.getText().trim());
            pst.setString(6, lb5.getText().trim());
            pst.setString(7, lb6.getText().trim());
            pst.setString(8, lb7.getText().trim());
            pst.setString(9, txt_Nota.getText().trim());
            pst.executeUpdate();
            MostrarDB("asignacioncursosalumnos");
            JOptionPane.showMessageDialog(this, "MODIFICACION EXITOSA.", "Exito", JOptionPane.INFORMATION_MESSAGE);

            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);
            txt_Codigo.setText("");
            txt_Nota.setText("");
            lb1.setText("");
            lb2.setText("");
            lb3.setText("");
            lb4.setText("");
            lb5.setText("");
            lb6.setText("");
            lb7.setText("");
            cbox_carrera.setSelectedIndex(0);
            cbox_j.setSelectedIndex(0);
            cbox_aula.setSelectedIndex(0);
            cbox_alum.setSelectedIndex(0);
            cbox_sec.setSelectedIndex(0);
            cbox_sede.setSelectedIndex(0);
            cbox_curso.setSelectedIndex(0);
            txtbuscado.setText("");

        } catch (Exception e) {
            System.out.println(e);
        }*/

        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    public void ModificarT(String ItemT,String Item2T,int Lugar)
    {
        Item = jComboBox_Parcial.getSelectedItem().toString();
        Item2 = jComboBox_Parcial2.getSelectedItem().toString();        
        
            try {
            String codigo = txtbuscado.getText().trim();

           Connection cn = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pst = cn.prepareStatement("update asignacioncursosalumnos set  id_Alumno = ?, codigo_carrera = ?,codigo_sede = ?,codigo_jornada = ?, codigo_seccion = ?,codigo_aula=?,codigo_curso=?,carnet_alumno=?,Tipo_Nota=?,Parcial_1=?,Parcial_2=?,Parcial_3=?,Parcial_1T=?,Parcial_2T=?,Parcial_3T=?,Parcial_1B=?,Unidad_1=?,Unidad_2=?,Unidad_3=?,Unidad_4=?,Extraordinario=?,Privado=?,Zona=?,nota_asignacioncursoalumnos=? where id_Alumno = " + codigo);

            pst.setString(1, txt_Codigo.getText().trim());
            pst.setString(2, lb1.getText().trim());
            pst.setString(3, lb2.getText().trim());
            pst.setString(4, lb3.getText().trim());
            pst.setString(5, lb4.getText().trim());
            pst.setString(6, lb5.getText().trim());
            pst.setString(7, lb6.getText().trim());
            pst.setString(8, lb7.getText().trim());

            
            if(Item == ItemT)
            {
                pst.setString(9,ItemT);
                
                if(Item2 == Item2T)
                {
                    BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                    for(int i = 10;i<=24;i++)
                    {
                        if(i == Lugar)
                        {
                            pst.setString(i, txt_Nota.getText().trim());
                        }
                        else if(i ==10 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Parcial_1);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }
                        else if(i ==11 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Parcial_2);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }              
                        else if(i ==12 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Parcial_3);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }
                        else if(i ==13 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Parcial_1T);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }    
                        else if(i ==14 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Parcial_2T);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }           
                        else if(i ==15 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Parcial_3T);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }           
                        else if(i ==16 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Parcial_1B);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }          
                        else if(i ==17 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Unidad_1);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }          
                        else if(i ==18 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Unidad_2);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }   
                        else if(i ==19 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Unidad_3);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }  
                        else if(i ==20 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(Unidad_4);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }       
                        else if(i ==21 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(ExtraordinarioS);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }         
                        else if(i ==22 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(PrivadoS);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }             
                        else if(i ==23 && i != Lugar)
                        {
                           // BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(ZonaS);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }           
                        else if(i ==24 && i != Lugar)
                        {
                            BusquedaMayor("Parcial_1","Parcial_2","Parcial_3","Parcial_1T","Parcial_2T","Parcial_3T","Parcial_1B","Unidad_1","Unidad_2","Unidad_3","Unidad_4","Extraordinario","Privado","Zona","nota_asignacioncursoalumnos");
                            txt_Nuevo.setText(SumaS);
                            pst.setString(i,txt_Nuevo.getText().trim());
                        }                        

                    }
                }
            }
            
            
            pst.executeUpdate();
            MostrarDB("asignacioncursosalumnos");
            JOptionPane.showMessageDialog(this, "MODIFICACION EXITOSA.", "Exito", JOptionPane.INFORMATION_MESSAGE);

            btnEliminar.setEnabled(false);
            btnModificar.setEnabled(false);
            txt_Codigo.setText("");
            txt_Nota.setText("");
            lb1.setText("");
            lb2.setText("");
            lb3.setText("");
            lb4.setText("");
            lb5.setText("");
            lb6.setText("");
            lb7.setText("");
            cbox_carrera.setSelectedIndex(0);
            cbox_j.setSelectedIndex(0);
            cbox_aula.setSelectedIndex(0);
            cbox_alum.setSelectedIndex(0);
            cbox_sec.setSelectedIndex(0);
            cbox_sede.setSelectedIndex(0);
            cbox_curso.setSelectedIndex(0);
            txtbuscado.setText("");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
        public boolean Busqueda()
    {
        try
        {
            Connection cnB = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pstB = cnB.prepareStatement("select * from asignacioncursosalumnos where id_Alumno = ?");
            
            pstB.setString(1,txt_Codigo.getText().trim());
            ResultSet rsB = pstB.executeQuery();
           
            
            if(rsB.next())
            {
                Encontrado = true;
                
            }
            else
            {
                Encontrado = false;
               
            }
            
            
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        return Encontrado;
    }
        
        
    public void BusquedaMayor(String Parcial,String Parcial2,String Parcial3,String Parcial_T, String Parcial2_T,String Parcial3_T,String Parcial_B,String Unidad1,String Unidad2,String Unidad3,String Unidad4,String Extraordinario,String Privado,String Zona,String Nota)
    {
        try
        {
           Connection cnB = DriverManager.getConnection(CONTENEDOR.BD,CONTENEDOR.Usuario,CONTENEDOR.Contraseña);
            PreparedStatement pstB = cnB.prepareStatement("select * from asignacioncursosalumnos where id_Alumno = ?");
            
            pstB.setString(1,txt_Codigo.getText().trim());
            ResultSet rsB = pstB.executeQuery();
           
            
            if(rsB.next())
            {
                Parcial_1 = rsB.getString(Parcial);
                Parcial_Flotante = Float.parseFloat(Parcial_1);
                
                Parcial_2 = rsB.getString(Parcial2);
                Parcial2_Flotante = Float.parseFloat(Parcial_2);
                
                Parcial_3 = rsB.getString(Parcial3);
                Parcial3_Flotante = Float.parseFloat(Parcial_3);
                
                Parcial_1T = rsB.getString(Parcial_T);
                Parcial_FlotanteT = Float.parseFloat(Parcial_1T);        
                
                Parcial_2T = rsB.getString(Parcial2_T);
                Parcial2_FlotanteT = Float.parseFloat(Parcial_2T);
                
                Parcial_3T = rsB.getString(Parcial3_T);
                Parcial3_FlotanteT = Float.parseFloat(Parcial_3T);       
                
                Parcial_1B = rsB.getString(Parcial_B);
                Parcial_FlotanteB = Float.parseFloat(Parcial_1B);                   
                
                Unidad_1 = rsB.getString(Unidad1);
                Unidad1_Flotante = Float.parseFloat(Unidad_1);               
                
                Unidad_2 = rsB.getString(Unidad2);
                Unidad2_Flotante = Float.parseFloat(Unidad_2);
                
                Unidad_3 = rsB.getString(Unidad3);
                Unidad3_Flotante = Float.parseFloat(Unidad_3);

                Unidad_4 = rsB.getString(Unidad4);
                Unidad4_Flotante = Float.parseFloat(Unidad_4);                
                
                ExtraordinarioS = rsB.getString(Extraordinario);
                Extraordinario_Flotante = Float.parseFloat(ExtraordinarioS);        
                
                PrivadoS = rsB.getString(Privado);
                Privado_Flotante = Float.parseFloat(PrivadoS);
                
                ZonaS = rsB.getString(Zona);
                Zona_Flotante = Float.parseFloat(ZonaS);

                NotaS = rsB.getString(Nota);
                Nota_Flotante = Float.parseFloat(NotaS);        
                
                Suma =  Parcial_Flotante + Parcial2_Flotante + Parcial3_Flotante +Parcial_FlotanteT + Parcial2_FlotanteT+Parcial3_FlotanteT
                +Parcial_FlotanteB+Unidad1_Flotante+Unidad2_Flotante+Unidad3_Flotante+Unidad4_Flotante+Extraordinario_Flotante+Privado_Flotante
                +Zona_Flotante;     
                
       
                SumaS = Float.toString(Suma);
                
                if(Parcial_Flotante > 0  && Parcial_Flotante !=0)
                {
                    EncontradoMayorCero = true;
                    System.out.println("Este "+ Parcial_1T);
                  // Lugar(cnB,pstB,rsB,Parcial,Lugar);
                }
                else
                {
                EncontradoMayorCero = false;
                System.out.println(EncontradoMayorCero);
                }
                
            }

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
        
    private void txt_CodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_CodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_CodigoActionPerformed

    private void txt_NotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NotaActionPerformed

    private void jComboBox_ParcialItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_ParcialItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED)
        {
            if(this.jComboBox_Parcial.getSelectedIndex() >0)
            {
                this.jComboBox_Parcial2.setModel(new DefaultComboBoxModel(this.datos(this.jComboBox_Parcial.getSelectedItem().toString())));
            }
        }
    }//GEN-LAST:event_jComboBox_ParcialItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cbox_alum;
    private javax.swing.JComboBox<String> cbox_aula;
    private javax.swing.JComboBox<String> cbox_carrera;
    private javax.swing.JComboBox<String> cbox_curso;
    private javax.swing.JComboBox<String> cbox_j;
    private javax.swing.JComboBox<String> cbox_sec;
    private javax.swing.JComboBox<String> cbox_sede;
    private javax.swing.JComboBox<String> jComboBox_Parcial;
    private javax.swing.JComboBox<String> jComboBox_Parcial2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Extra;
    private javax.swing.JLabel jLabel_Tipo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JTable tblAsignacionA;
    private javax.swing.JTextField txt_Codigo;
    private javax.swing.JTextField txt_Nota;
    private javax.swing.JTextField txt_Nuevo;
    private javax.swing.JTextField txtbuscado;
    // End of variables declaration//GEN-END:variables
}
