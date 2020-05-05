package IITU_shop.client.pages;
import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationPanel;
import IITU_shop.data.UserBuy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HistoryPage extends ApplicationPanel {
    private ClientFrame parent;
    private JTable table;
    private DefaultTableModel name;
    private JScrollPane pane;
    private ApplicationButton backButton, refreshButton;
    private Object[] columns={"#","Organization", "COUNT", "PRICE"};
    private ArrayList<UserBuy> userBuys;
    private UserBuy userBuy;
    public HistoryPage(ClientFrame parent) {
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
        if(ClientApp.currentUser!=null){
            updateGood();
        }

        refreshButton=new ApplicationButton("REFRESH");
        refreshButton.setLocation(200, 450);
        add(refreshButton);
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGood();
                updateGood();
            }
        });

        backButton=new ApplicationButton("BACK");
        backButton.setLocation(200, 500);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.historyPage.setVisible(false);
                parent.userPage.setVisible(true);
            }
        });
    }
    private void updateGood(){
        userBuy=new UserBuy(null, ClientApp.currentUser.getId(), null, 0, 0);
        userBuys=parent.clientSocket.getAllGoods(userBuy);
        Object[] row=new Object[4];
        for(UserBuy u:userBuys){
            row[0]=u.getId();
            row[1]=u.getOrganization();
            row[2]=u.getCount();
            row[3]=u.getTotalsum();
            name.addRow(row);
        }
    }
    private void clearGood(){
        DefaultTableModel dm = (DefaultTableModel)table.getModel();
        while(dm.getRowCount() > 0)
        {
            dm.removeRow(0);
        }
    }

}

