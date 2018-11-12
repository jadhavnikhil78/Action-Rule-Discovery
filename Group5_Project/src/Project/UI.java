package Project;

/* Created by
 * Group 5
 * Bala Guna Teja Karlapudi
 * Nikhil Jadhav
 * Phyllis Jones
 * Saketh Kumar Kappala
 */

import java.awt.List;
import java.io.*;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;


public class UI extends javax.swing.JPanel {

  InputVerifier verifier = new MyNumericVerifier();
  JFrame f = new JFrame();

  private javax.swing.JButton openFile1;
  private javax.swing.JButton openFile2;
  private javax.swing.JButton loadData;
  private javax.swing.JLabel filePathLabel1;
  private javax.swing.JLabel filePathLabel2;
  private javax.swing.ButtonGroup YGroup;
  private javax.swing.JLabel chooseStable;    
  private javax.swing.JLabel columnNumberLabel;
  private javax.swing.JComboBox decisionColumn;
  private javax.swing.JPanel decisionPanel;
  private javax.swing.JTextField indices;
  private javax.swing.JTextField support;
  private javax.swing.JTextField confidence;
  private javax.swing.JTextField decisionValueFrom;
  private javax.swing.JTextField decisionValueTo;
  private javax.swing.JLabel decisionValueFromLabel;
  private javax.swing.JLabel decisionValueToLabel;
  private javax.swing.JFileChooser fileChooser;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel supportLabel;
  private javax.swing.JLabel confidenceLabel;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.ButtonGroup ruleButtonGroup;
  private javax.swing.JList stableColumns;
  private javax.swing.JPanel stablePanel;
  private javax.swing.JButton submit;
  private String[] allAttributes1;
  private String[] allAttributes2;
  private String[] allAttributesExceptDecision;
  private LinkedList<String> totalData;
	
	
	
  public UI() {        
      initComponents();
      
  }

  @SuppressWarnings("unchecked")
  
  private void initComponents() {

      chooseStable = new javax.swing.JLabel();    
      indices = new javax.swing.JTextField();
      decisionPanel = new javax.swing.JPanel();
      decisionValueToLabel = new javax.swing.JLabel();
      decisionValueFromLabel = new javax.swing.JLabel();
      columnNumberLabel = new javax.swing.JLabel();
      decisionValueTo = new javax.swing.JTextField();
      decisionValueFrom = new javax.swing.JTextField();
      support = new javax.swing.JTextField();
      confidence = new javax.swing.JTextField();
      decisionColumn = new javax.swing.JComboBox();
      stablePanel = new javax.swing.JPanel();
      YGroup = new javax.swing.ButtonGroup();
      jLabel2 = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      stableColumns = new javax.swing.JList();
      stableColumns.setEnabled(false);
      submit = new javax.swing.JButton();
      loadData = new javax.swing.JButton();
      openFile1 = new javax.swing.JButton();
      openFile2 = new javax.swing.JButton();
      fileChooser = new javax.swing.JFileChooser();
      filePathLabel1 = new javax.swing.JLabel();
      filePathLabel2 = new javax.swing.JLabel();
      jLabel3 = new javax.swing.JLabel();
      jLabel4 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      supportLabel = new javax.swing.JLabel();
      confidenceLabel = new javax.swing.JLabel();
      totalData = new LinkedList<String>();


      decisionPanel.setBackground(Color.WHITE);
      //decisionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Decision Attribute Information", 0, 0, new java.awt.Font("Arial", 1, 20)));
      decisionValueFromLabel.setText("Decision Value From:");
      decisionValueToLabel.setText("Decision Value To:");

      columnNumberLabel.setText("Decision Attribute:");

      decisionValueFrom.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              decisionValueFromActionPerformed(evt);
          }
      });

      decisionValueTo.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              decisionValueToActionPerformed(evt);
          }
      });
      


      javax.swing.GroupLayout decisionPanelLayout = new javax.swing.GroupLayout(decisionPanel);
      decisionPanel.setLayout(decisionPanelLayout);
      
      decisionPanelLayout.setHorizontalGroup(
          decisionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(decisionPanelLayout.createSequentialGroup()
          	.addComponent(decisionValueFromLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
          	.addComponent(decisionValueFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
          	.addGap(60, 60, 60)
          	.addComponent(decisionValueToLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
          	.addComponent(decisionValueTo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
          	.addGroup(decisionPanelLayout.createSequentialGroup()
              .addComponent(columnNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(decisionColumn, javax.swing.GroupLayout.DEFAULT_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
      );
      decisionPanelLayout.setVerticalGroup(
          decisionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(decisionPanelLayout.createSequentialGroup()
              .addContainerGap()
              .addGroup(decisionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addComponent(decisionColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addComponent(columnNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(18, 18, 18)
              .addGroup(decisionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              		.addComponent(decisionValueFromLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addComponent(decisionValueFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(decisionValueToLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addComponent(decisionValueTo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
      ));
      stablePanel.setBackground(Color.WHITE);
      //stablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "Stable Attribute Information", 0, 0, new java.awt.Font("Ariel", 1, 20)));

      jLabel2.setText("Stable Attributes:");
      chooseStable.setText("Enter Indices:");

      javax.swing.GroupLayout stablePanelLayout = new javax.swing.GroupLayout(stablePanel);
      stablePanel.setLayout(stablePanelLayout);
      stablePanelLayout.setHorizontalGroup(
          stablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(stablePanelLayout.createSequentialGroup()
              .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(60, 60, 60)
              .addComponent(chooseStable, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(indices, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
      );
      stablePanelLayout.setVerticalGroup(
          stablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(stablePanelLayout.createSequentialGroup()
              .addGroup(stablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                  
                  .addComponent(chooseStable, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(indices, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
      );

      submit.setText("Submit");
      submit.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              try {
					submitActionPerformed(evt);
					System.exit(0);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
          }
      });

      openFile1.setText("Open");
      openFile1.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              filePathActionPerformed(evt);  
          }
      });
      
      openFile2.setText("Open");
      openFile2.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              filePathActionPerformed1(evt);  
          }
      });

      loadData.setText("Load Data");
      loadData.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              try {
         				dataLoadActionPerformed(evt);
         				} catch (IOException e) {
         					
         					e.printStackTrace();
         				}  
          }
      });
      
      
      
      filePathLabel1.setText("Attribute File: ");
      filePathLabel2.setText("Data File: ");
      supportLabel.setText("Support: ");
      confidenceLabel.setText("Confidence: ");
      

      
      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                      .addGap(45, 45, 45)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addComponent(decisionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                          .addComponent(stablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                  .addGroup(layout.createSequentialGroup()
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                              .addGap(365, 365, 365)
                              .addComponent(submit))
                          .addGroup(layout.createSequentialGroup()
                              .addGap(15, 15, 15)
                              .addComponent(filePathLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(1)
                              .addComponent(openFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addGap(10)	
                          	.addComponent(filePathLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                              .addGap(1)
                              .addComponent(openFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addGap(30)	
                          	.addComponent(supportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addGap(1)
                          	.addComponent(support, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addGap(10)
                          	.addComponent(confidenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addGap(1)
                          	.addComponent(confidence, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addGap(30)
                              .addComponent(loadData, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addGap(10)
                          	.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                          	.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                          .addGroup(layout.createSequentialGroup()
                              .addGap(210, 210, 210)
                              .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                      .addGap(0, 66, Short.MAX_VALUE)))
              .addContainerGap())
      );
      layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
              .addGap(30, 30, 30)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(filePathLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(openFile1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(filePathLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(openFile2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              	.addComponent(supportLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              	.addComponent(support, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              	.addComponent(confidenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              	.addComponent(confidence, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              	.addComponent(loadData, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              	.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
              	.addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGap(35, 35, 35)
              .addComponent(decisionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addGap(32, 32, 32)
              .addComponent(stablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(submit)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
  }

  private void decisionValueFromActionPerformed(java.awt.event.ActionEvent evt) {
      // TODO add your handling code here:
  }

  private void decisionValueToActionPerformed(java.awt.event.ActionEvent evt) {
      // TODO add your handling code here:
  }
  

  private void dataLoadActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
      // TODO add your handling code here:
      InputVerifier verifier = new MyNumericVerifier();
      support.setInputVerifier(verifier);
      InputVerifier verifier1 = new MyNumericVerifier();
      confidence.setInputVerifier(verifier1);

      
  	String fileName = jLabel4.getText();
      String line = null;
          FileReader fileReader = new FileReader(fileName);
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          while((line = bufferedReader.readLine()) != null) {
              allAttributes1 = line.split("\\s+");
              allAttributes2 = line.split("\\s+");
          }   
          for(int i =0; i<allAttributes1.length;i++){
          	allAttributes1[i] = Integer.toString(i).concat("-").concat(allAttributes1[i]);
          }
          bufferedReader.close();         
          stableColumns.setModel(new javax.swing.AbstractListModel() {
              public int getSize() { return allAttributes1.length; }
              public Object getElementAt(int i) { return allAttributes1[i]; }
          });
          jScrollPane1.setViewportView(stableColumns);
          decisionColumn.setModel(new javax.swing.DefaultComboBoxModel(allAttributes1));

  }
  
  private void submitActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
      // Indices of Stable Attributes
      int i = 0;
      
      int[] stableAttributes;
      if(indices.getText().toString().length()!=0){
      	String[] stableAttributeIndices = indices.getText().toString().split(",");
      	stableAttributes = new int[stableAttributeIndices.length];
          for (String attributeIndex:stableAttributeIndices) {
          	stableAttributes[i] = Integer.parseInt(attributeIndex);
          	i++;
          }
      }
      else{
      	stableAttributes = new int[0];
      }
      
      ActionRules.SetAttributeNames(allAttributes2);
      ActionRules.SetDataFilePath(jLabel5.getText().toString());
      ActionRules.SetSupportThreshold(Integer.parseInt(support.getText().toString()));
      ActionRules.SetConfidenceThreshold(Integer.parseInt(confidence.getText().toString()));
      ActionRules.SetDecisionAttribute(decisionColumn.getSelectedItem().toString().split("-")[1]);
      ActionRules.SetDecisionFromValue(decisionValueFrom.getText().toString());
      ActionRules.SetDecisionToValue(decisionValueTo.getText().toString());
      ActionRules.SetStableAttribute(stableAttributes);
      
      ActionRules.GenerateActionRules();
      
  }
  
//Attribute File
  private void filePathActionPerformed(java.awt.event.ActionEvent evt) {
      // TODO add your handling code here:
  	int returnVal = fileChooser.showOpenDialog(this);
  	if(returnVal == JFileChooser.APPROVE_OPTION){
  		jLabel4.setText(fileChooser.getSelectedFile().getPath());
  		jLabel4.setVisible(false);
  	}
  }
//Data File    	
  private void filePathActionPerformed1(java.awt.event.ActionEvent evt) {
          // TODO add your handling code here:
  	int returnVal = fileChooser.showOpenDialog(this);
  	if(returnVal == JFileChooser.APPROVE_OPTION){
  		jLabel5.setText(fileChooser.getSelectedFile().getPath());
  		jLabel5.setVisible(false);
  	}
  }
  
  // Trial
  public class MyNumericVerifier extends InputVerifier {
      @Override
      public boolean verify(JComponent input) {
         String text = null;

         if (input instanceof JTextField) {
           text = ((JTextField) input).getText();
         }

         try {
            Integer.parseInt(text);
         } catch (NumberFormatException e) {
             JOptionPane.showMessageDialog(null, "Invalid data");
      	   return false;
         }

         return true;
      }

      @Override
      public boolean shouldYieldFocus(JComponent input) {
         boolean valid = verify(input);
         if (!valid) {
            JOptionPane.showMessageDialog(null, "Invalid data");
         }

         return valid;
     }
  }


  
}



