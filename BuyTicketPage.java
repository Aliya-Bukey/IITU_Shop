package IITU_shop.client.pages;

import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationField;
import IITU_shop.client.applications.ApplicationLabel;
import IITU_shop.client.applications.ApplicationPanel;
import IITU_shop.data.Ticket;
import IITU_shop.data.UserBuy;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BuyTicketPage extends ApplicationPanel {
    public ClientFrame parent;
    private ApplicationLabel idLabel, nameLabel, priceLabel, countLabel, dateLabel, pleaceLabel, organizationLabel;
    private ApplicationButton buyButton, backButton, refreshButton;
    private ApplicationField idField, nameField, priceField, countField, pleaceField;
    private String[] combo={"choose", "computer", "laptop", "tablet"};
    private String[] date={"choose", "corei3", "corei5", "corei7"};
    private Object[] columns={"ID", "NAME", "PRICE", "COUNT", "DATE", "PLEACE", "ORGANIZATION"};
    private JComboBox jCombodt, jCombodate;
    private JTable table;
    private DefaultTableModel name;
    private JScrollPane pane;
    private ArrayList<Ticket> tickets;

    public BuyTicketPage(ClientFrame parent) {
        this.parent = parent;
        table=new JTable();
        name=new DefaultTableModel();
        name.setColumnIdentifiers(columns);
        table.setModel(name);
        table.setBackground(Color.lightGray);
        table.setForeground(Color.black);
        table.setFont(new Font("Arial", 1, 16));
        table.setRowHeight(30);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i=table.getSelectedRow();
                idField.setText(name.getValueAt(i,0).toString());
                nameField.setText(name.getValueAt(i,1).toString());
                priceField.setText(name.getValueAt(i,2).toString());
                countField.setText("");
                jCombodate.setSelectedItem(name.getValueAt(i,4).toString());
                pleaceField.setText(name.getValueAt(i,5).toString());
                jCombodt.setSelectedItem(name.getValueAt(i,6).toString());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        pane=new JScrollPane(table);
        pane.setBounds(0,0, 600, 400);
        add(pane);
        updateTicket();
        idLabel=new ApplicationLabel("ID");
        idLabel.setLocation(20, 420);
        add(idLabel);
        idField=new ApplicationField();
        idField.setLocation(150, 420);
        add(idField);

        nameLabel=new ApplicationLabel("Name:");
        nameLabel.setLocation(20, 460);
        add(nameLabel);
        nameField=new ApplicationField();
        nameField.setLocation(150, 460);
        add(nameField);

        priceLabel=new ApplicationLabel("Price:");
        priceLabel.setLocation(20, 500);
        add(priceLabel);
        priceField=new ApplicationField();
        priceField.setLocation(150, 500);
        add(priceField);

        countLabel=new ApplicationLabel("Count:");
        countLabel.setLocation(20, 540);
        add(countLabel);
        countField=new ApplicationField();
        countField.setLocation(150, 540);
        add(countField);

        dateLabel=new ApplicationLabel("Date(string):");
        dateLabel.setLocation(20, 580);
        add(  dateLabel);
        jCombodate=new JComboBox(date);
        jCombodate.setBounds(150, 580, 200, 30);
        jCombodate.setBackground(Color.green);
        jCombodate.setForeground(Color.black);
        jCombodate.setFont(new Font("Arial",1, 16));
        jCombodate.setBorder(new EtchedBorder(Color.black,Color.black));
        add(jCombodate);

        pleaceLabel=new ApplicationLabel("Pleace:");
        pleaceLabel.setLocation(20, 620);
        add(pleaceLabel);
        pleaceField=new ApplicationField();
        pleaceField.setLocation(150, 620);
        add(pleaceField);

        organizationLabel=new ApplicationLabel("Organization:");
        organizationLabel.setLocation(20, 660);
        add(organizationLabel);

        jCombodt=new JComboBox(combo);
        jCombodt.setBounds(150, 660, 200, 30);
        jCombodt.setBackground(Color.green);
        jCombodt.setForeground(Color.black);
        jCombodt.setFont(new Font("Arial",1, 16));
        jCombodt.setBorder(new EtchedBorder(Color.black,Color.black));
        add(jCombodt);

        buyButton=new ApplicationButton("BUY");
        buyButton.setLocation(360, 450);
        add(buyButton);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id=Long.valueOf(idField.getText());
                String name=nameField.getText();
                int price=Integer.parseInt(priceField.getText());
                int count=Integer.parseInt(countField.getText());
                String date=jCombodate.getSelectedItem().toString();
                int pleace=Integer.parseInt(pleaceField.getText());
                String organization=jCombodt.getSelectedItem().toString();
                if(name.isEmpty() || date.isEmpty()){
                    JOptionPane.showMessageDialog(parent, "Please, fill all fields!!!");
                }
                else{
                    UserBuy userBuy=new UserBuy(null, ClientApp.currentUser.getId(),organization, count, price*count);
                    parent.clientSocket.buyTicket(userBuy);
                    Ticket ticket =new Ticket(id, null, 0, count, null, 0, null);
                    parent.clientSocket.editTicketBuy(ticket);
                    nameField.setText("");
                    priceField.setText("");
                    countField.setText("");
                    jCombodate.setSelectedIndex(0);
                    pleaceField.setText("");
                    jCombodt.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(parent, "Ticket was bought successfully!!!");
                    clearTicket();
                    updateTicket();
                }
            }
        });

        backButton=new ApplicationButton("BACK");
        backButton.setLocation(360, 500);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.buyTicketPage.setVisible(false);
                parent.userPage.setVisible(true);
            }
        });
        refreshButton=new ApplicationButton("Refresh");
        refreshButton.setLocation(360,550);
        add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearTicket();
                updateTicket();
            }
        });

    }
    public void updateTicket(){
        tickets =parent.clientSocket.getAllTickets();
        Object[] row=new Object[7];
        for(Ticket d: tickets){
            row[0]=d.getId();
            row[1]=d.getName();
            row[2]=d.getPrice();
            row[3]=d.getCount();
            row[4]=d.getDate();
            row[5]=d.getPleace();
            row[6]=d.getOrganization();
            name.addRow(row);
        }
    }
    private void clearTicket(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
}

