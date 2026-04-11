/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.requests;

import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.EcoSystem;
import model.UserAccount;
import model.WorkRequest;


/**
 *
 * @author sashajohnson
 */
public class ReportJPanel extends javax.swing.JPanel {
    private JPanel userProcessContainer;
    private UserAccount account;
    private String entityType;
    private String mode;

    private WorkRequest request;
    private UserAccount selectedUser;
    private model.enterprise.Enterprise selectedEnterprise;
    private model.organization.Organization selectedOrganization;
    /**
     * Creates new form ReportJPanel
     */

    public static final String ENTITY_REQUEST = "REQUEST";
    public static final String ENTITY_USER = "USER";
    public static final String ENTITY_ENTERPRISE = "ENTERPRISE";
    public static final String ENTITY_ORGANIZATION = "ORGANIZATION";

    public static final String MODE_VIEW = "VIEW";
    public static final String MODE_UPDATE = "UPDATE";

    public ReportJPanel(JPanel userProcessContainer, UserAccount account, WorkRequest request, String mode) {
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.request = request;
        this.mode = mode;
        this.entityType = ENTITY_REQUEST;
        initComponents();
        configureForm();
        populateFields();
    }
    
    public ReportJPanel(JPanel userProcessContainer, UserAccount account, UserAccount selectedUser, String mode) {
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.selectedUser = selectedUser;
        this.mode = mode;
        this.entityType = ENTITY_USER;
        initComponents();
        configureForm();
        populateFields();
    }
    
    public ReportJPanel(JPanel userProcessContainer, UserAccount account, model.enterprise.Enterprise selectedEnterprise, String mode) {
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.selectedEnterprise = selectedEnterprise;
        this.mode = mode;
        this.entityType = ENTITY_ENTERPRISE;
        initComponents();
        configureForm();
        populateFields();
    }
    
    public ReportJPanel(JPanel userProcessContainer, UserAccount account, model.organization.Organization selectedOrganization, String mode) {
        this.userProcessContainer = userProcessContainer;
        this.account = account;
        this.selectedOrganization = selectedOrganization;
        this.mode = mode;
        this.entityType = ENTITY_ORGANIZATION;
        initComponents();
        configureForm();
        populateFields();
    }
    private String findOrganizationNameForUser(UserAccount user) {
        EcoSystem system = EcoSystem.getInstance();

        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                for (model.UserAccount ua : org.getUserAccountDirectory().getUserAccountList()) {
                    if (ua == user) {
                        return org.getName();
                    }
                }
            }
        }
        return "";
    }
    private String findEnterpriseNameForOrganization(model.organization.Organization organization) {
        EcoSystem system = EcoSystem.getInstance();

        for (model.enterprise.Enterprise enterprise : system.getNetwork().getEnterpriseList()) {            for (model.organization.Organization org : enterprise.getOrganizationDirectory().getOrganizationList()) {
                if (org == organization) {
                    return enterprise.getName();
                }
            }
        }
        return "";
    }
    private void configureForm() {
    boolean editable = MODE_UPDATE.equals(mode);
    btnSave.setVisible(editable);
    
    txtField1.setEditable(false);
    txtField2.setEditable(false);
    txtField3.setEditable(false);
    txtField4.setEditable(false);

    switch (entityType) {

        case ENTITY_REQUEST:

            lblTitle.setText(
                    (editable ? "Update Request - " : "View Request - ")
                    + request.getRequestId()
            );

            lblField1.setText("Request ID");
            lblField2.setText("Type");
            lblField3.setText("Status");
            lblField4.setText("Priority");

            // ID and Type are always locked
            txtField1.setEditable(false);
            txtField2.setEditable(false);

            // Only these can change
            if (editable) {
                txtField3.setEditable(true);
                txtField4.setEditable(true);
            }

            break;


        case ENTITY_USER:

            lblTitle.setText(
                    editable ? "Update User" : "View User"
            );

            lblField1.setText("Username");
            lblField2.setText("Name");
            lblField3.setText("Role");
            lblField4.setText("Organization");

            // Username locked (acts like ID)
            txtField1.setEditable(false);

            if (editable) {
                txtField2.setEditable(true);
            }

            break;


        case ENTITY_ENTERPRISE:

            lblTitle.setText(
                    editable ? "Update Enterprise"
                             : "View Enterprise"
            );

            lblField1.setText("Enterprise Name");
            lblField2.setText("Type");
            lblField3.setText("Organizations Count");
            lblField4.setText("Status");

            // Only name editable
            txtField1.setEditable(false);

            if (editable) {
                txtField1.setEditable(true);
            }

            break;


        case ENTITY_ORGANIZATION:

            lblTitle.setText(
                    editable ? "Update Organization"
                             : "View Organization"
            );

            lblField1.setText("Organization Name");
            lblField2.setText("Enterprise");
            lblField3.setText("Users Count");
            lblField4.setText("Status");

            // Only name editable
            txtField1.setEditable(false);

            if (editable) {
                txtField1.setEditable(true);
            }

            break;
    }
    }
    private void populateFields() {
        switch (entityType) {
            case ENTITY_REQUEST:
                txtField1.setText(request.getRequestId());
                txtField2.setText(request.getClass().getSimpleName());
                txtField3.setText(request.getStatus());
                txtField4.setText(request.getPriority());
                break;

            case ENTITY_USER:
                txtField1.setText(selectedUser.getUsername());
                txtField2.setText(selectedUser.getPerson() != null ? selectedUser.getPerson().getName() : "");
                txtField3.setText(selectedUser.getRole() != null ? selectedUser.getRole().getRoleName() : "");
                txtField4.setText(findOrganizationNameForUser(selectedUser));
                break;

            case ENTITY_ENTERPRISE:
                txtField1.setText(selectedEnterprise.getName());
                txtField2.setText(String.valueOf(selectedEnterprise.getType()));
                txtField3.setText(String.valueOf(selectedEnterprise.getOrganizationDirectory().getOrganizationList().size()));
                txtField4.setText("Active");
                break;

            case ENTITY_ORGANIZATION:
                txtField1.setText(selectedOrganization.getName());
                txtField2.setText(findEnterpriseNameForOrganization(selectedOrganization));
                txtField3.setText(String.valueOf(selectedOrganization.getUserAccountDirectory().getUserAccountList().size()));
                txtField4.setText("Active");
                break;
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
        lblField1 = new javax.swing.JLabel();
        lblField3 = new javax.swing.JLabel();
        lblField4 = new javax.swing.JLabel();
        lblField2 = new javax.swing.JLabel();
        txtField1 = new javax.swing.JTextField();
        txtField2 = new javax.swing.JTextField();
        txtField3 = new javax.swing.JTextField();
        txtField4 = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();

        lblTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTitle.setText("Report");

        lblScreenTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
        lblScreenTitle.setText("Disaster Response System");

        btnBack.setBackground(new java.awt.Color(153, 153, 153));
        btnBack.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("< Back");
        btnBack.setBorderPainted(false);
        btnBack.setOpaque(true);
        btnBack.addActionListener(this::btnBackActionPerformed);

        lblField1.setText("input");

        lblField3.setText("input");

        lblField4.setText("input");

        lblField2.setText("input");

        txtField1.setText("output");

        txtField2.setText("output");

        txtField3.setText("output");

        txtField4.setText("output");

        btnSave.setBackground(new java.awt.Color(102, 153, 255));
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Save");
        btnSave.setBorderPainted(false);
        btnSave.setOpaque(true);
        btnSave.addActionListener(this::btnSaveActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnBack))
                            .addComponent(lblScreenTitle)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblField4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblField3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblField2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblField1)
                                    .addGap(59, 59, 59)
                                    .addComponent(txtField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnSave))
                            .addComponent(lblTitle))))
                .addContainerGap(285, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblScreenTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBack)
                .addGap(4, 4, 4)
                .addComponent(lblTitle)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblField1)
                    .addComponent(txtField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblField2)
                    .addComponent(txtField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblField3)
                    .addComponent(txtField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblField4)
                    .addComponent(txtField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(btnSave)
                .addContainerGap(75, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
            switch (entityType) {
            case ENTITY_REQUEST:
                request.setStatus(txtField3.getText().trim());
                request.setPriority(txtField4.getText().trim());
                break;

            case ENTITY_USER:
                selectedUser.setUsername(txtField1.getText().trim());
                if (selectedUser.getPerson() != null) {
                    selectedUser.getPerson().setName(txtField2.getText().trim());
                }
                break;

            case ENTITY_ENTERPRISE:
                selectedEnterprise.setName(txtField1.getText().trim());
                break;

            case ENTITY_ORGANIZATION:
                selectedOrganization.setName(txtField1.getText().trim());
                break;
        }

        JOptionPane.showMessageDialog(this, "Updated successfully.");

        java.awt.Component[] components = userProcessContainer.getComponents();

        for (java.awt.Component comp : components) {

            if (comp instanceof ui.admin.ReportingJPanel) {
                ui.admin.ReportingJPanel panel =
                        (ui.admin.ReportingJPanel) comp;
                panel.refreshTable();
            }

            if (comp instanceof ui.admin.ManagementJPanel) {
                ui.admin.ManagementJPanel panel =
                        (ui.admin.ManagementJPanel) comp;
                panel.refreshTable();
            }
        }

        userProcessContainer.remove(this);

        CardLayout layout =
                (CardLayout) userProcessContainer.getLayout();

        layout.previous(userProcessContainer);
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel lblField1;
    private javax.swing.JLabel lblField2;
    private javax.swing.JLabel lblField3;
    private javax.swing.JLabel lblField4;
    private javax.swing.JLabel lblScreenTitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField txtField1;
    private javax.swing.JTextField txtField2;
    private javax.swing.JTextField txtField3;
    private javax.swing.JTextField txtField4;
    // End of variables declaration//GEN-END:variables
}
