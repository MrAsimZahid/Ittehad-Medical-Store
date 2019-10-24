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
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author asimz
 */
public class stock extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form stock
     */
    public stock() {
        initComponents();
        conn = MySQLConnection.ConnectDB();
        fillCombo();
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
    public void tableView(String q ){
        try{
            pst = conn.prepareStatement(q);
            rs = pst.executeQuery();
    //        JOptionPane.showMessageDialog(null, "query executed");
            stockTable.setModel(DbUtils.resultSetToTableModel(rs));
    
    //        JOptionPane.showMessageDialog(null, "Hello");
        }
        catch(Exception e){
        //   JOptionPane.showMessageDialog(this, e, "fech method exception catch block", WIDTH);
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //insert into databse
    public void insert(String query) throws SQLException{
        try{
            conn.setAutoCommit(false);
            Statement stmt = null; 
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Teacher data Entered");
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
    public void fillCombo(){
        try{
            String sql = "SELECT * FROM `supplier`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("supplierName");
                supplierName.addItem(name);
            }
            //line of code to get value from combo box
//            String value = supplierCompanyComboBox.getSelectedItem().toString();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,  e );
        }
    }
    //Populate List with medicine name
    public void fillProductList(String product){
        DefaultListModel model = new DefaultListModel();
        try{
            String sql = "SELECT `productName` FROM `product` WHERE `productName` LIKE '%"+product+"%'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("productName");
                model.addElement(name);
            }
            productList.setModel(model);
            
        }
        catch(Exception e){
            //line of code to select item from product list
        //     String tmp = (String)productList.getSelectedValue();
        }
    }
 /*   String buyPrice             = buyingPrice.getText();
            String marginPercentage     = margin.getText();
            String retail               = retailPrice.getText();
            String packsGot             = packsGet.getText();
            String totalQty             = totQty.getText();
            String minimumLevel         = minLevel.getText();
            String totalAmt             = net.getText();
   */         
    ////////////////////////////////////////////////////////////////////////////
    // Numerical functions
    //Sellling Price Calculator
    public void sellingPrice(){
        String buyPrice             = buyingPrice.getText();
        String marginPercentage     = margin.getText();
        float buy = Float.parseFloat(buyPrice);
        float marginPercent = Float.parseFloat(marginPercentage);
        float sell = buy + (marginPercent/100) * buy;
        retailPrice.setText(Float.toString(sell));
    }
    // Margin Callculator
    public void marginCalculator(){
        String buyPrice             = buyingPrice.getText();
        String retail               = retailPrice.getText();
        float buy = Float.parseFloat(buyPrice);
        float sell = Float.parseFloat(retail);
        float marginPercent = (sell/buy)* 100;
        margin.setText(Float.toString(marginPercent));
    }
    public void totalQty(){
       int pack = 0;
       int bonus = 0;
       pack = Integer.parseInt(packsGet.getText());
       bonus = Integer.parseInt(bonusQty.getText());
       int total = pack + bonus;
       totQty.setText(Integer.toString(total));
    }
    //Net Amount
    public float netAmt(){
       String buyPrice             = buyingPrice.getText();
       float buy = (float) 0.0;
       int pack = 0;
       buy = Float.parseFloat(buyPrice);
       pack = Integer.parseInt(packsGet.getText());
       float netAmount = buy * pack;
       net.setText( Float.toString(netAmount));
       return netAmount;
    }
    ////////////////////////////////////////////////////////////////////////////
    public void queries() throws SQLException{
        try{
            String pName = productName.getText();
       //     conn.setAutoCommit(false);
            String query = "SELECT `productID`, `pBuyingPrice`, `pSellingPrice` FROM `product` WHERE `productName` = '"+pName+"'";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            String id = rs.getString("productID");
            serialNo.setText(id);
            float byPrice = rs.getFloat("pBuyingPrice");
            buyingPrice.setText(Float.toString(byPrice));
            float sellPrice = rs.getFloat("pSellingPrice");
            buyingPrice.setText(Float.toString(sellPrice));
            
       //     conn.commit();
  //          JOptionPane.showMjessageDialog(null, "Hello");
        }
        catch(HeadlessException | SQLException e){
         //   conn.rollback();
            JOptionPane.showMessageDialog(this, e, "Error Arose", WIDTH);
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

        jMenu4 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        stockTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        InVoiceNo = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        invoiceDate = new com.toedter.calendar.JDateChooser();
        cash = new javax.swing.JRadioButton();
        credit = new javax.swing.JRadioButton();
        supplierName = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        totAmount = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        serialNo = new javax.swing.JTextField();
        productName = new javax.swing.JTextField();
        packs = new javax.swing.JTextField();
        batchNo = new javax.swing.JTextField();
        expiryDate = new com.toedter.calendar.JDateChooser();
        buyingPrice = new javax.swing.JFormattedTextField();
        margin = new javax.swing.JFormattedTextField();
        retailPrice = new javax.swing.JFormattedTextField();
        packsGet = new javax.swing.JFormattedTextField();
        bonusQty = new javax.swing.JFormattedTextField();
        totQty = new javax.swing.JFormattedTextField();
        minLevel = new javax.swing.JFormattedTextField();
        net = new javax.swing.JFormattedTextField();
        creditDate = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        productList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        splashMenuItem = new javax.swing.JMenuItem();
        logOutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        saleMenuItem = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
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

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Purchase");
        setResizable(false);

        stockTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(stockTable);

        jLabel2.setText("InVoice #");

        jLabel3.setText("Supplier");

        invoiceDate.setDateFormatString("yyyy-MM-dd");

        buttonGroup1.add(cash);
        cash.setText("Cash");
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });

        buttonGroup1.add(credit);
        credit.setText("Credit");
        credit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditActionPerformed(evt);
            }
        });

        jLabel4.setText("InVoice Date");

        jLabel5.setText("Amount");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Sr#");

        jLabel7.setText("Product Name");

        jLabel8.setText("Pack");

        jLabel9.setText("Batch No.");

        jLabel10.setText("Expiry Date");

        jLabel11.setText("Buying Price");

        jLabel12.setText("Margin %");

        jLabel13.setText("Retail Price");

        jLabel14.setText("Pack");

        jLabel15.setText("Bonus");

        jLabel16.setText("Quantity");

        jLabel17.setText("Total Qty.");

        jLabel18.setText("Min Level");

        jLabel19.setText("Net Amount");

        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });
        productName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                productNameKeyTyped(evt);
            }
        });

        packs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packsActionPerformed(evt);
            }
        });

        expiryDate.setDateFormatString("yyyy-MM-dd");

        buyingPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyingPriceActionPerformed(evt);
            }
        });

        margin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marginActionPerformed(evt);
            }
        });

        retailPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retailPriceActionPerformed(evt);
            }
        });

        packsGet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packsGetActionPerformed(evt);
            }
        });

        bonusQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bonusQtyActionPerformed(evt);
            }
        });

        totQty.setEditable(false);

        net.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                netActionPerformed(evt);
            }
        });

        creditDate.setDateFormatString("yyyy-MM-dd");

        productList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productListMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(productList);

        jLabel1.setText("Products List");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Purchase");

        jButton4.setText("Add");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InVoiceNo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceDate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(394, 394, 394)
                        .addComponent(totAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(credit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(creditDate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cash)
                                .addGap(140, 140, 140)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supplierName, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(72, 72, 72)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(370, 370, 370))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(serialNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(productName)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(packs, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(batchNo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(expiryDate, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(buyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                            .addComponent(margin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                            .addComponent(retailPrice))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel15)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel17))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(packsGet, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bonusQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totQty, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                                    .addComponent(minLevel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(net, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 940, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InVoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(invoiceDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cash, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(supplierName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel3)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(credit)
                                            .addComponent(creditDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(serialNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(packs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(batchNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(buyingPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(margin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(retailPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(packsGet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bonusQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(totQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(minLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(net, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(expiryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(83, 83, 83))
        );

        jScrollPane1.setViewportView(jPanel1);

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

        jMenu7.setText("Purchase");

        jMenuItem6.setText("Add Purchased products");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem6);

        jMenuItem7.setText("Purchase Invoice");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem7);

        menuBar.add(jMenu7);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1286, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1302, 584));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void creditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditActionPerformed
        // TODO add your handling code here:
        credit.setActionCommand("Credit");
        creditDate.setVisible(true);
    }//GEN-LAST:event_creditActionPerformed

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        // TODO add your handling code here:
        cash.setActionCommand("Cash");
        cash.setSelected(true);
        creditDate.setVisible(false);
    }//GEN-LAST:event_cashActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void packsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_packsActionPerformed

    private void marginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marginActionPerformed
        // TODO add your handling code here:
        sellingPrice();
    }//GEN-LAST:event_marginActionPerformed

    private void bonusQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bonusQtyActionPerformed
        // TODO add your handling code here:
        totalQty();
        netAmt();
    }//GEN-LAST:event_bonusQtyActionPerformed

    private void netActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_netActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_netActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            String inVoice              = InVoiceNo.getText();
            String totalCost            = totAmount.getText();
            String suplierNameCombo     = supplierName.getSelectedItem().toString();
            String inVoiceDate          = ((JTextField)invoiceDate.getDateEditor().getUiComponent()).getText();
            String cashCredit           = buttonGroup1.getSelection().getActionCommand();
            String crditDate            = ((JTextField)creditDate.getDateEditor().getUiComponent()).getText();
                
                        
            //  String inVoice              = InVoiceNo.getText();
            String pName                = productName.getText();
            String pack                 = packs.getText();
            String BatchNo              = batchNo.getText();
            String buyPrice             = buyingPrice.getText();
            String marginPercentage     = margin.getText();
            String retail               = retailPrice.getText();
            String packsGot             = packsGet.getText();
            String totalQty             = totQty.getText();
            String minimumLevel         = minLevel.getText();
            String totalAmt             = net.getText();
            if( cashCredit == "Cash"){
                crditDate = null;
            }
            //to get supplier 
            String quer = "SELECT `supplierID` FROM `supplier` WHERE `supplierName` = '"+suplierNameCombo+"'";
            pst = conn.prepareStatement(quer);
            rs = pst.executeQuery();
            int supliid = rs.getInt("supplierID");
            
            String insertQuery = "INSERT INTO `orders`(`order_id`, `InVoiceDate`, `order_type`, `creditReturnDate`, `order_total_amount`, `batch`, `supID`) "
                    + "VALUES ('"+inVoice+"','"+inVoiceDate+"','"+cashCredit+"','"+crditDate+"','"+Float.parseFloat(totalCost)+"','"+BatchNo+"','"+supliid+"')";
            insert(insertQuery);
            String insertQ = "";
            insert(insertQ);
            
        } catch (SQLException ex) {
            Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView("select * from `product`");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void productListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productListMouseClicked
        try {
            // TODO add your handling code here:
            String tmp = (String)productList.getSelectedValue();
            productName.setText(tmp);
            queries();
            marginCalculator();
       //     sellingPrice();
        } catch (SQLException ex) {
            Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_productListMouseClicked

    private void productNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productNameKeyReleased
        
        
    }//GEN-LAST:event_productNameKeyReleased

    private void packsGetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packsGetActionPerformed
        // TODO add your handling code here:
        netAmt();
        totalQty();
        
    }//GEN-LAST:event_packsGetActionPerformed

    private void retailPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retailPriceActionPerformed
        // TODO add your handling code here:
        marginCalculator();
        
    }//GEN-LAST:event_retailPriceActionPerformed

    private void buyingPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyingPriceActionPerformed
        // TODO add your handling code here:
        sellingPrice();
        netAmt();
    }//GEN-LAST:event_buyingPriceActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try{
  //        conn.setAutoCommit(false);
            Statement stmt = null;
            stmt = conn.createStatement();
            int row = stockTable.getSelectedRow();
            String value = (stockTable.getModel().getValueAt(row, 0).toString());
            
            String query =  "SELECT * FROM `product` WHERE `productID` = '" +Integer.parseInt(value)+"' ";
    //        String query ="delete from student where 'Roll No.'="+Integer.parseInt(deleteRoll.getText())+" ";
        
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, " record deleted");
//            conn.commit();
        }
        catch(Exception e)
        {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void productNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productNameKeyTyped
        // TODO add your handling code here:
      
    }//GEN-LAST:event_productNameKeyTyped

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO add your handling code here:
        fillProductList(productName.getText());
    }//GEN-LAST:event_productNameActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            String pName                = productName.getText();
            String pack                 = packs.getText();
            String BatchNo              = batchNo.getText();
            String buyPrice             = buyingPrice.getText();
            String marginPercentage     = margin.getText();
            String retail               = retailPrice.getText();
            String packsGot             = packsGet.getText();
            String totalQty             = totQty.getText();
            String minimumLevel         = minLevel.getText();
            String totalAmt             = net.getText();
            String xpDate            = ((JTextField)expiryDate.getDateEditor().getUiComponent()).getText();
            
            String insertQuery = "INSERT INTO `purchaseinvoice`(`productNam`, `BatchN`, `expiryD`, `buying`, `retailP`, `totalQty`, `minLev`, `nAmount`, `Margine`) "
                    + "VALUES ('"+pName+"','"+BatchNo+"','"+xpDate+"','"+buyPrice+"','"+retail+"','"+totalQty+"','"+minimumLevel+"','"+totalAmt+"','"+marginPercentage+"')";
            //"INSERT INTO `orders`(`order_id`, `InVoiceDate`, `order_type`, `creditReturnDate`, `order_total_amount`, `batch`, `supID`) "
            //+ "VALUES ('"+inVoice+"','"+inVoiceDate+"','"+cashCredit+"','"+crditDate+"','"+Float.parseFloat(totalCost)+"','"+BatchNo+"','"+supliid+"')";
            insert(insertQuery);
            
        } catch (SQLException ex) {
            Logger.getLogger(stock.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView("SELECT * FROM `purchaseinvoice`");
            
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new stock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField InVoiceNo;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JTextField batchNo;
    private javax.swing.JFormattedTextField bonusQty;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JFormattedTextField buyingPrice;
    private javax.swing.JRadioButton cash;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JRadioButton credit;
    private com.toedter.calendar.JDateChooser creditDate;
    private javax.swing.JMenuItem exitMenuItem;
    private com.toedter.calendar.JDateChooser expiryDate;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private com.toedter.calendar.JDateChooser invoiceDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuItem logOutMenuItem;
    private javax.swing.JFormattedTextField margin;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JFormattedTextField minLevel;
    private javax.swing.JFormattedTextField net;
    private javax.swing.JTextField packs;
    private javax.swing.JFormattedTextField packsGet;
    private javax.swing.JList<String> productList;
    private javax.swing.JTextField productName;
    private javax.swing.JFormattedTextField retailPrice;
    private javax.swing.JMenuItem saleMenuItem;
    private javax.swing.JTextField serialNo;
    private javax.swing.JMenuItem splashMenuItem;
    private javax.swing.JTable stockTable;
    private javax.swing.JMenuItem supplierMenuItem;
    private javax.swing.JComboBox<String> supplierName;
    private javax.swing.JFormattedTextField totAmount;
    private javax.swing.JFormattedTextField totQty;
    // End of variables declaration//GEN-END:variables
}
