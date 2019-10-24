/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharm;

import java.awt.HeadlessException;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author asimz
 */
public class supplier extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form supplier
     */
    public supplier() {
        conn = MySQLConnection.ConnectDB();
        initComponents();
        fillCombo();
   //     tableView("hello");
    }
        
//call  employer page
    public void callEmpPage(){
        Emploee obj1 = new Emploee();
        obj1.setVisible(true);
        this.dispose();
    }
    //call supplier page
    public void callSupplier(){
        supplier obj1 = new supplier();
        obj1.setVisible(true);
        this.dispose();
    }
    public void callInvoice(){
        InVoice voice = new InVoice();
        voice.setVisible(true);
        this.dispose();
    }
    //call  employer page
    public void callPurchasePage(){
        stock purchase = new stock();
        purchase.setVisible(true);
        this.dispose();
    }
    // Call Splash Screen
    public void callSplash(){
        PharmacySplashScreen splash = new PharmacySplashScreen();
        splash.setVisible(true);
        this.dispose();
    }
    // Call all products
    public void callAllProducts(){
        ProductStatus pro = new ProductStatus();
        pro.setVisible(true);
        this.dispose();
    }
    //view data into table
    public void tableView(String q , int flag){
        try{
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
    //        JOptionPane.showMessageDialog(null, "query executed");
    if(flag == 1){
            supplierTable.setModel(DbUtils.resultSetToTableModel(rs));
    }else{
        compTable.setModel(DbUtils.resultSetToTableModel(rs));
        
    }
    //        JOptionPane.showMessageDialog(null, "Hello");
        }
        catch(Exception e){
        //   JOptionPane.showMessageDialog(this, e, "fech method exception catch block", WIDTH);
     //       JOptionPane.showMessageDialog(null, e);
        }
    }
    //insert into databse
    public void insert(String query) throws SQLException{
        try{
            conn.setAutoCommit(false);
            Statement stmt = null; 
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "data Entered");
            conn.commit();
  //          JOptionPane.showMessageDialog(null, "Hello");
        }
        catch(HeadlessException | SQLException e){
            conn.rollback();
            JOptionPane.showMessageDialog(this, e, "Enter valid data", WIDTH);
        }
    }
    
    // Update supplier record
    public void update(String query) throws SQLException{
        
        try{
            conn.setAutoCommit(false);
            Statement stmt = null; 
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
                conn.commit();
//            JOptionPane.showMessageDialog(null, "query executed");
        }
        catch(HeadlessException | SQLException e){
            conn.rollback();
 //           JOptionPane.showMessageDialog(this, e, "fetch method exception catch block", WIDTH);
    
        }
    }
    //Search  Records
    public ResultSet search(String query) throws SQLException{
        
        try{
         conn.setAutoCommit(false);
         Statement stmt = null; 
         stmt = conn.createStatement();
      /*   String query = "SELECT `ID`, `Name`, `Sur Name`, `CNIC`,"
                 + " `Experience`, `Education`, `Contact`, `Address`,"
                 + "  `Salary` FROM `teacherinfo`.'ID'"
                 + "  = '"+Integer.parseInt(tutID.getText())+"' ";
        */
           rs = stmt.executeQuery(query);
           JOptionPane.showMessageDialog(null, "Recod searched");
           conn.commit();
           
  //         
        }
        catch(Exception e){
         //   JOptionPane.showMessageDialog(this, e );
         conn.rollback();
            JOptionPane.showMessageDialog(null,  "Record not found" );
        }
        return rs;
    }
    // Fill combo box from databse
    public final void fillCombo(){
        try{
            String sql = "SELECT * FROM `suppliercompany`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("compName");
                supplierCompanyComboBox.addItem(name);
            }
            //line of code to get value from combo box
//            String value = supplierCompanyComboBox.getSelectedItem().toString();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e );
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        searchField = new javax.swing.JFormattedTextField();
        supplierSearchbtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        supplierTable = new javax.swing.JTable();
        supplierId = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        expiryDayPolicy = new javax.swing.JFormattedTextField();
        supplierName = new javax.swing.JFormattedTextField();
        supplierContact = new javax.swing.JFormattedTextField();
        addsupplier = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        supplierCompanyComboBox = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        compTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        compName = new javax.swing.JFormattedTextField();
        compContact = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        compAddress = new javax.swing.JTextArea();
        addCompany = new javax.swing.JButton();
        supplierCompanyUpdate = new javax.swing.JButton();
        deleteComp = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        compId = new javax.swing.JFormattedTextField();
        compSearchName = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        supplierSearchbtn1 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        splashMenuItem = new javax.swing.JMenuItem();
        logOutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        saleMenuItem = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        supplierMenuItem = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("supplier");

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Suppliers");
        jLabel1.setDebugGraphicsOptions(javax.swing.DebugGraphics.FLASH_OPTION);

        jLabel2.setText("Search by company name");

        supplierSearchbtn.setText("Search");
        supplierSearchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierSearchbtnActionPerformed(evt);
            }
        });

        supplierTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        supplierTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supplierTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(supplierTable);

        jLabel3.setText("Supplier ID#");

        supplierName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierNameActionPerformed(evt);
            }
        });

        supplierContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierContactActionPerformed(evt);
            }
        });

        addsupplier.setText("Add");
        addsupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsupplierActionPerformed(evt);
            }
        });

        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setText("Supplier Company");

        jLabel5.setText("Expiry Date Policy");

        jLabel6.setText("Supplier Name");

        jLabel7.setText("Supplier Contact Number");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(supplierSearchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(424, 424, 424))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(261, 261, 261))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(supplierCompanyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(120, 120, 120)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(expiryDayPolicy, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(supplierContact, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(addsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(supplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplierSearchbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierId, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(expiryDayPolicy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierCompanyComboBox)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierContact, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addsupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Supplier ", jPanel1);

        compTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(compTable);

        jLabel9.setText("Company Name");

        compName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compNameActionPerformed(evt);
            }
        });

        compContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compContactActionPerformed(evt);
            }
        });

        jLabel10.setText("Contact No.");

        jLabel11.setText("Address");

        compAddress.setColumns(20);
        compAddress.setRows(5);
        jScrollPane1.setViewportView(compAddress);

        addCompany.setText("Add compny");
        addCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCompanyActionPerformed(evt);
            }
        });

        supplierCompanyUpdate.setText("Update company Info");
        supplierCompanyUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierCompanyUpdateActionPerformed(evt);
            }
        });

        deleteComp.setText("delete Company");
        deleteComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCompActionPerformed(evt);
            }
        });

        jLabel8.setText("Company ID #");

        jLabel12.setText("Search by company name");

        supplierSearchbtn1.setText("Search");
        supplierSearchbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierSearchbtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(addCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(43, 43, 43)
                                    .addComponent(supplierCompanyUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                    .addComponent(deleteComp))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(compName, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(compId, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(compContact, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(compSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supplierSearchbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(compSearchName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierSearchbtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(compId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(compContact, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(compName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCompany)
                    .addComponent(supplierCompanyUpdate)
                    .addComponent(deleteComp))
                .addGap(81, 81, 81))
        );

        jTabbedPane1.addTab("Supplier Company", jPanel3);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        splashMenuItem.setText("Splash Screen");
        splashMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                splashMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(splashMenuItem);

        logOutMenuItem.setMnemonic('a');
        logOutMenuItem.setText("LogOut");
        logOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(logOutMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        jMenu3.setText("Sale");

        saleMenuItem.setText("Generate Sale");
        saleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(saleMenuItem);

        jMenuItem5.setText("Sales Invoice");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        menuBar.add(jMenu3);

        jMenu4.setText("Purchase");

        jMenuItem6.setText("Add Purchased products");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem7.setText("Purchase Invoice");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        menuBar.add(jMenu4);

        jMenu1.setText("Product");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem3.setText("View all products");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        menuBar.add(jMenu1);

        jMenu6.setText("Accounts");

        jMenuItem2.setText("Employee");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem2);

        supplierMenuItem.setText("Supplier");
        supplierMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierMenuItemActionPerformed(evt);
            }
        });
        jMenu6.add(supplierMenuItem);

        menuBar.add(jMenu6);

        jMenu5.setText("Report");
        menuBar.add(jMenu5);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        jMenuItem1.setText("User Manual");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        helpMenu.add(jMenuItem1);

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        setSize(new java.awt.Dimension(1152, 697));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try{
            conn.setAutoCommit(false);
            Statement stmt = null;
            stmt = conn.createStatement();
            int row = supplierTable.getSelectedRow();
            String value = (supplierTable.getModel().getValueAt(row, 0).toString());
            
            String query = "DELETE FROM `supplier` WHERE `supplierID` =" +Integer.parseInt(value)+" ";
    //        String query ="delete from student where 'Roll No.'="+Integer.parseInt(deleteRoll.getText())+" ";
        
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, " record deleted");
            conn.commit();
        }
        catch(Exception ex)
        {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            String updateQuerySupplier = "";
            update(updateQuerySupplier);
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void supplierContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierContactActionPerformed

    private void supplierNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierNameActionPerformed

    private void supplierSearchbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierSearchbtnActionPerformed
        // TODO add your handling code here:
        String searchCompany = searchField.getText();
        String searchComp ="Select * from `supplier`";
                //"SELECT `supplier`.`supplierID`, `supplier`.`supplierName`, `supplier`.`supplierContactNo`, `supplier`.`ExpiryDayPolicy`, `suppliercompany`.`compName` FROM `supplier` full join `suppliercompany` on `supplier`.`supplierComp` = `suppliercompany`.`compID` WHERE `suppliercompany`.`compName`LIKE '%"+ searchCompany +"%'";
        tableView(searchComp, 1);
    }//GEN-LAST:event_supplierSearchbtnActionPerformed

    private void compContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compContactActionPerformed

    private void addCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCompanyActionPerformed
        try {
            // TODO add your handling code here:
            String comName = compName.getText();
            String contact = compContact.getText();
            String address = compAddress.getText();
            String insertQuery = "INSERT INTO `suppliercompany`( `compName`, `compContact`, `comAddress`) VALUES ('"+ comName +"','"+Integer.parseInt(contact) +"','"+address+"')";    
            insert(insertQuery);
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addCompanyActionPerformed

    private void deleteCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCompActionPerformed
        // TODO add your handling code here:
       // public void delete(){
        try{
  //          conn.setAutoCommit(false);
            Statement stmt = null;
            stmt = conn.createStatement();
            int row = compTable.getSelectedRow();
            String value = (compTable.getModel().getValueAt(row, 0).toString());
            
            String query =  "DELETE  FROM `suppliercompany` WHERE `compID`  = '" +Integer.parseInt(value)+"' ";
    //        String query ="delete from student where 'Roll No.'="+Integer.parseInt(deleteRoll.getText())+" ";
        
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, " record deleted");
//            conn.commit();
        }
        catch(Exception ex)
        {
        }
    
    }//GEN-LAST:event_deleteCompActionPerformed

    private void supplierCompanyUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierCompanyUpdateActionPerformed
        // TODO add your handling code here:
        try {
            String comName = compName.getText();
            String contact = compContact.getText();
            String address = compAddress.getText();
            String updateQuery = "UPDATE `suppliercompany` SET `compName`='"+ comName +"',`compContact`='"+ contact +"',`comAddress`='"+ address +"' WHERE `compID`='"+compId+"'";
            update(updateQuery);
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_supplierCompanyUpdateActionPerformed

    private void supplierSearchbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierSearchbtn1ActionPerformed
        try {
            // TODO add your handling code here:
            String searchSupComp = compSearchName.getText();
            String searchQuery = "SELECT * FROM `suppliercompany` WHERE `compName` = '"+searchSupComp+"';";
            rs =  search(searchQuery);
            compTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_supplierSearchbtn1ActionPerformed

    private void splashMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_splashMenuItemActionPerformed
        // TODO add your handling code here:
        callSplash();
    }//GEN-LAST:event_splashMenuItemActionPerformed

    private void logOutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutMenuItemActionPerformed
        // TODO add your handling code here:
        LoginPage obj2 = new LoginPage();
        obj2.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logOutMenuItemActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void saleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleMenuItemActionPerformed
        // TODO add your handling code here:
        POS pos = new POS();
        pos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_saleMenuItemActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        callInvoice();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        callPurchasePage();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        callInvoice();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        callAllProducts();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        callEmpPage();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void supplierMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierMenuItemActionPerformed
        // TODO add your handling code here:
        callSupplier();
    }//GEN-LAST:event_supplierMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Contact/Call at: +923320443303");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked
// insert supplier data into database
    private void addsupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsupplierActionPerformed
        try {
            // TODO add your handling code here:
            String suplierName = supplierName.getText();
            String exDayPolicy = expiryDayPolicy.getText();
            String supliercontact = supplierContact.getText();
            String value = supplierCompanyComboBox.getSelectedItem().toString();
          //  String suplierName = supplierName.getText();
          /////////////////////////////////////////////////////////
          //to get supplier 
          /////////////////////////////////////////////////////////
            String insertQuerySupplier = "INSERT INTO `supplier`(`supplierName`, `supplierContactNo`, `ExpiryDayPolicy`, `supplierComp`) "
                    + "VALUES ('"+suplierName+"','"+Integer.parseInt(supliercontact)+"','"+exDayPolicy+"','"+1+"')";
//"";//"DECLARE @id INT = SELECT  `compID` FROM `suppliercompany` WHERE `compName` = '";";
            
        /*            + "INSERT INTO `supplier`(`supplierName`, `supplierContactNo`, `ExpiryDayPolicy`, `supplierComp`) "
                    + "VALUES ('"+suplierName+"','"+Integer.parseInt(supliercontact)+"','"+exDayPolicy+"','"+1+"')"
          */  insert(insertQuerySupplier);
        } catch (SQLException ex) {
            Logger.getLogger(supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_addsupplierActionPerformed
      // get DATA FROM table and insert into form
    private void supplierTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supplierTableMouseClicked
      
        int rowIndex = supplierTable.getSelectedRow();
        TableModel model = supplierTable.getModel();
        supplierId.setText(model.getValueAt(rowIndex, 0).toString());
        supplierName.setText(model.getValueAt(rowIndex, 1).toString());
        supplierContact.setText(model.getValueAt(rowIndex, 2).toString());
        expiryDayPolicy.setText(model.getValueAt(rowIndex, 3).toString());
    }//GEN-LAST:event_supplierTableMouseClicked

    private void compNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compNameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supplier().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton addCompany;
    private javax.swing.JButton addsupplier;
    private javax.swing.JTextArea compAddress;
    private javax.swing.JFormattedTextField compContact;
    private javax.swing.JFormattedTextField compId;
    private javax.swing.JFormattedTextField compName;
    private javax.swing.JFormattedTextField compSearchName;
    private javax.swing.JTable compTable;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JButton deleteComp;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JFormattedTextField expiryDayPolicy;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem logOutMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem saleMenuItem;
    private javax.swing.JFormattedTextField searchField;
    private javax.swing.JMenuItem splashMenuItem;
    private javax.swing.JComboBox<String> supplierCompanyComboBox;
    private javax.swing.JButton supplierCompanyUpdate;
    private javax.swing.JFormattedTextField supplierContact;
    private javax.swing.JFormattedTextField supplierId;
    private javax.swing.JMenuItem supplierMenuItem;
    private javax.swing.JFormattedTextField supplierName;
    private javax.swing.JButton supplierSearchbtn;
    private javax.swing.JButton supplierSearchbtn1;
    private javax.swing.JTable supplierTable;
    // End of variables declaration//GEN-END:variables

}
