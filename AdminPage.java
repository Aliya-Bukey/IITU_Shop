package IITU_shop.client.pages;

import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationLabel;
import IITU_shop.client.applications.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends ApplicationPanel {
    private ClientFrame parent;
    private ApplicationLabel label;
    private ApplicationButton ticketButton, clotheButton, incomeButton, logoutButton;
    public AdminPage(ClientFrame parent) {
        this.parent = parent;

        label=new ApplicationLabel("");
        label.setBounds(120, 120, 200, 50 );
        add(label);

        ticketButton=new ApplicationButton("TICKETS");
        ticketButton.setLocation(180, 200);
        add(ticketButton);
        ticketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.adminPage.setVisible(false);
                parent.ticketPage.setVisible(true);
            }
        });

        clotheButton=new ApplicationButton("CLOTHES");
        clotheButton.setLocation(180, 260);
        add(clotheButton);

        incomeButton=new ApplicationButton("INCOME");
        incomeButton.setLocation(180, 320);
        add(incomeButton);
        incomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.adminPage.setVisible(false);
                parent.incomePage.setVisible(true);
            }
        });

        logoutButton=new ApplicationButton("LOGOUT");
        logoutButton.setLocation(180, 380);
        add(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientApp.currentUser=null;
                parent.adminPage.setVisible(false);
                parent.loginPage.setVisible(true);
            }
        });
    }
    public void updateData(){
        label.setText("Welcome "+ClientApp.currentUser.getFullname());
    }
}

