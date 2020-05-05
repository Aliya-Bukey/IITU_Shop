package IITU_shop.client.pages;

import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationLabel;
import IITU_shop.client.applications.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage extends ApplicationPanel {
    private ClientFrame parent;
    private ApplicationLabel userLabel;
    private ApplicationButton buyTicket,buyClothe,historyButton,logoutButton;
    public UserPage(ClientFrame parent) {
        this.parent = parent;
        userLabel=new ApplicationLabel("");
        userLabel.setBounds(120, 120, 200, 50 );
        add(userLabel);

        buyTicket=new ApplicationButton("BUY TICKETS");
        buyTicket.setLocation(180, 200);
        add(buyTicket);
        buyTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.userPage.setVisible(false);
                parent.buyTicketPage.setVisible(true);
            }
        });

        buyClothe=new ApplicationButton("BUY CLOTHES");
        buyClothe.setLocation(180, 260);
        add(buyClothe);

        historyButton=new ApplicationButton("HISTORY");
        historyButton.setLocation(180, 320);
        add(historyButton);
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.userPage.setVisible(false);
                parent.historyPage.setVisible(true);
            }
        });


        logoutButton=new ApplicationButton("LOGOUT");
        logoutButton.setLocation(180, 380);
        add(logoutButton);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientApp.currentUser=null;
                parent.userPage.setVisible(false);
                parent.loginPage.setVisible(true);
            }
        });
    }
    public void updateData(){
        userLabel.setText("Welcome "+ClientApp.currentUser.getFullname());
    }

}
