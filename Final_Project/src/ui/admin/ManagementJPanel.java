/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.admin;

import java.awt.CardLayout;
import model.EcoSystem;
import javax.swing.JPanel;
import model.UserAccount;
import ui.requests.ReportJPanel;

/**
 *
 * @author sashajohnson
 */
public class ManagementJPanel extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private UserAccount account;
    private String mode;
    private EcoSystem system;
    /**
     * Creates new form ManageUsersJPanel
     */
    public ManagementJPanel(JPanel userProcessContainer, UserAccount account, String mode) {
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.mode = mode;
        this.system = EcoSystem.getInstance();
        initComponents();
        configurePage();
        populateTable();

    }
    
    private void configurePage() {
        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) tblAdminManagement.getModel();

        model.setRowCount(0);

        if (mode.equals("USERS")) {
            lblTitle.setText("Manage Users");
            model.setColumnIdentifiers(new String[]{
                "Username", "Name", "Role", "Organization", "Status"
            });
        } 
        else if (mode.equals("ENTERPRISES")) {
            lblTitle.setText("Manage Enterprises");
            model.setColumnIdentifiers(new String[]{
                "Enterprise Name", "Type", "Organizations Count"
            });
        } 
        else if (mode.equals("ORGANIZATIONS")) {
            lblTitle.setText("Manage Organizations");
            model.setColumnIdentifiers(new String[]{
                "Organization Name", "Enterprise", "Users Count"
            });
        }
    }
    public void refreshTable() {
        populateTable();
    }
    private void populateTable() {
        javax.swing.table.DefaultTableModel model =
                (javax.swing.table.DefaultTableModel) tblAdminManagement.getModel();

        model.setRowCount(0);

        switch (mode) {
            case "USERS":
                populateUsers(model);
                break;

            case "ENTERPRISES":
                populateEnterprises(model);
                break;

            case "ORGANIZATIONS":
                populateOrganizations(model);
                break;

            default:
                break;
        }
    }
    
    private void populateUsers(javax.swing.table.DefaultTableModel model) {
        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                for (model.UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {

                    Object[] row = new Object[5];
                    row[0] = ua.getUsername();
                    row[1] = ua.getPerson() != null ? ua.getPerson().getName() : "";
                    row[2] = ua.getRole() != null ? ua.getRole().getRoleName() : "";
                    row[3] = org.getName();
                    row[4] = "Active";

                    model.addRow(row);
                }
            }
        }
    }
    
    private void populateEnterprises(javax.swing.table.DefaultTableModel model) {
        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            Object[] row = new Object[3];
            row[0] = enterprise.getName();
            row[1] = enterprise.getType();
            row[2] = enterprise.getOrganizationDirectory().getOrganizationList().size();

            model.addRow(row);
        }
    }
    
    private void populateOrganizations(javax.swing.table.DefaultTableModel model) {
        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {

                Object[] row = new Object[3];
                row[0] = org.getName();
                row[1] = enterprise.getName();
                row[2] = org.getUserAccountDirectory().getUserAccountList().size();

                model.addRow(row);
            }
        }
    }
    
    private model.UserAccount getSelectedUser() {
        int selectedRow = tblAdminManagement.getSelectedRow();

        if (selectedRow < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select a user first.");
            return null;
        }

        String username = String.valueOf(tblAdminManagement.getValueAt(selectedRow, 0));

        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                for (model.UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                    if (username.equals(ua.getUsername())) {
                        return ua;
                    }
                }
            }
        }

        return null;
    }
    
    private model.enterprise.Enterprise getSelectedEnterprise() {
        int selectedRow = tblAdminManagement.getSelectedRow();

        if (selectedRow < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select an enterprise first.");
            return null;
        }

        String enterpriseName = String.valueOf(tblAdminManagement.getValueAt(selectedRow, 0));

        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            if (enterpriseName.equals(enterprise.getName())) {
                return enterprise;
            }
        }

        return null;
    }
    
    private model.organization.Organization getSelectedOrganization() {
        int selectedRow = tblAdminManagement.getSelectedRow();

        if (selectedRow < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select an organization first.");
            return null;
        }

        String organizationName = String.valueOf(tblAdminManagement.getValueAt(selectedRow, 0));

        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if (organizationName.equals(org.getName())) {
                    return org;
                }
            }
        }

        return null;
    }
    private void deleteSelectedUser() {
        model.UserAccount selectedUser = getSelectedUser();
        if (selectedUser == null) return;

        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if (org.getUserAccountDirectory().getUserAccountList().remove(selectedUser)) {
                    javax.swing.JOptionPane.showMessageDialog(this, "User deleted.");
                    return;
                }
            }
        }
    }
    private void deleteSelectedEnterprise() {
        model.enterprise.Enterprise selectedEnterprise = getSelectedEnterprise();
        if (selectedEnterprise == null) return;

        if (system.getNetwork().getEnterpriseList().remove(selectedEnterprise)) {            javax.swing.JOptionPane.showMessageDialog(this, "Enterprise deleted.");
        }
    }
    private void deleteSelectedOrganization() {
        model.organization.Organization selectedOrganization = getSelectedOrganization();
        if (selectedOrganization == null) return;

        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            if (enterprise.getOrganizationDirectory().getOrganizationList().remove(selectedOrganization)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Organization deleted.");
                return;
            }
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

        lblTitle = new javax.swing.JLabel();
        lblScreenTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAdminManagement = new javax.swing.JTable();
        btnView = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTitle.setText("Manage Users");

        lblScreenTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        lblScreenTitle.setText("Disaster Response System");

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("< Back");
        btnBack.setBorderPainted(false);
        btnBack.setOpaque(true);
        btnBack.addActionListener(this::btnBackActionPerformed);

        tblAdminManagement.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblAdminManagement);

        btnView.setBackground(new java.awt.Color(102, 153, 255));
        btnView.setForeground(new java.awt.Color(255, 255, 255));
        btnView.setText("View");
        btnView.setBorderPainted(false);
        btnView.setOpaque(true);
        btnView.addActionListener(this::btnViewActionPerformed);

        btnUpdate.setBackground(new java.awt.Color(102, 153, 255));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update");
        btnUpdate.setBorderPainted(false);
        btnUpdate.setOpaque(true);
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setBackground(new java.awt.Color(102, 153, 255));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.setBorderPainted(false);
        btnDelete.setOpaque(true);
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblScreenTitle)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnBack)
                                .addGap(174, 174, 174)
                                .addComponent(lblTitle))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnView)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScreenTitle)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBack)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnView)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(112, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed
    
    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        // TODO add your handling code here:
        ReportJPanel panel = null;

           switch (mode) {
               case "USERS":
                   model.UserAccount selectedUser = getSelectedUser();
                   if (selectedUser == null) return;
                   panel = new ReportJPanel(userProcessContainer, account, selectedUser, ReportJPanel.MODE_VIEW);
                   break;

               case "ENTERPRISES":
                   model.enterprise.Enterprise selectedEnterprise = getSelectedEnterprise();
                   if (selectedEnterprise == null) return;
                   panel = new ReportJPanel(userProcessContainer, account, selectedEnterprise, ReportJPanel.MODE_VIEW);
                   break;

               case "ORGANIZATIONS":
                   model.organization.Organization selectedOrganization = getSelectedOrganization();
                   if (selectedOrganization == null) return;
                   panel = new ReportJPanel(userProcessContainer, account, selectedOrganization, ReportJPanel.MODE_VIEW);
                   break;

               default:
                   return;
           }

           userProcessContainer.add("ReportJPanel_VIEW", panel);
           CardLayout layout = (CardLayout) userProcessContainer.getLayout();
           layout.next(userProcessContainer);
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        ReportJPanel panel = null;

        switch (mode) {
            case "USERS":
                model.UserAccount selectedUser = getSelectedUser();
                if (selectedUser == null) return;
                panel = new ReportJPanel(userProcessContainer, account, selectedUser, ReportJPanel.MODE_UPDATE);
                break;

            case "ENTERPRISES":
                model.enterprise.Enterprise selectedEnterprise = getSelectedEnterprise();
                if (selectedEnterprise == null) return;
                panel = new ReportJPanel(userProcessContainer, account, selectedEnterprise, ReportJPanel.MODE_UPDATE);
                break;

            case "ORGANIZATIONS":
                model.organization.Organization selectedOrganization = getSelectedOrganization();
                if (selectedOrganization == null) return;
                panel = new ReportJPanel(userProcessContainer, account, selectedOrganization, ReportJPanel.MODE_UPDATE);
                break;

            default:
                return;
        }

        userProcessContainer.add("ReportJPanel_UPDATE", panel);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
        
        populateTable();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
            int choice = javax.swing.JOptionPane.showConfirmDialog(
                this,
                "Delete selected item?",
                "Confirm Delete",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (choice != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        switch (mode) {
            case "USERS":
                deleteSelectedUser();
                break;

            case "ENTERPRISES":
                deleteSelectedEnterprise();
                break;

            case "ORGANIZATIONS":
                deleteSelectedOrganization();
                break;

            default:
                return;
        }

        populateTable();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblScreenTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblAdminManagement;
    // End of variables declaration//GEN-END:variables
}
