package IITU_shop.client.pages;

import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationLabel;
import IITU_shop.client.applications.ApplicationPanel;
import IITU_shop.data.Ticket;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IncomePage extends ApplicationPanel {
    public ClientFrame parent;
    private ApplicationButton backButton, refreshButton;
    private ApplicationLabel label, textLabel;
    private Object[] columns={"ID", "NAME", "PRICE", "COUNT", "DATE", "PLEACE", "ORGANIZATION", "SOLD"};
    private JTable table;
    private DefaultTableModel name;
    private JScrollPane pane;
    private ArrayList<Ticket> tickets;

    public IncomePage(ClientFrame parent) {
        this.parent = parent;
        table=new JTable();
        name=new DefaultTableModel();
        name.setColumnIdentifiers(columns);
        table.setModel(name);
        table.setBackground(Color.lightGray);
        table.setForeground(Color.black);
        table.setFont(new Font("Arial", 1, 16));
        table.setRowHeight(30);
        pane=new JScrollPane(table);
        pane.setBounds(0,0, 600, 400);
        add(pane);
        updateTicket();


        label=new ApplicationLabel("Total income: "+Integer.toString(getTotalPrice()));
        label.setBounds(400, 450, 300, 30);
        add(label);

        refreshButton=new ApplicationButton("REFRESH");
        refreshButton.setLocation(200, 500);
        add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Total income: "+Integer.toString(getTotalPrice()));
                clearTicket();
                updateTicket();
            }
        });

        backButton=new ApplicationButton("BACK");
        backButton.setLocation(200, 550);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.incomePage.setVisible(false);
                parent.adminPage.setVisible(true);
            }
        });

    }
    public void updateTicket(){
        tickets =parent.clientSocket.getAllTickets();
        Object[] row=new Object[8];
        int total=0;
        for(Ticket d: tickets){
            if(d.getSold()>0){
                row[0]=d.getId();
                row[1]=d.getName();
                row[2]=d.getPrice();
                row[3]=d.getCount();
                row[4]=d.getDate();
                row[5]=d.getPleace();
                row[6]=d.getOrganization();
                row[7]=d.getSold();
                name.addRow(row);

            }

        }
    }
    public int getTotalPrice(){
        tickets =parent.clientSocket.getAllTickets();

        int total=0;
        for(Ticket d: tickets){
            if(d.getSold()>0){
                total+=d.getSold()*d.getPrice();
            }
        }
        return total;
    }
    public void clearTicket(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }
}

