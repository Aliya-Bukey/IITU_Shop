package IITU_shop.client.pages;

import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationField;
import IITU_shop.client.applications.ApplicationLabel;
import IITU_shop.client.applications.ApplicationPanel;
import IITU_shop.data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends ApplicationPanel {
    private ClientFrame parent;
    private ApplicationLabel loginLabel, passwordLabel;
    private ApplicationField loginField, passwordField;
    private ApplicationButton addButton, backButton;
    public LoginPage(ClientFrame parent){
        this.parent=parent;
        loginLabel=new ApplicationLabel("Login");
        loginLabel.setLocation(100, 200);
        add(loginLabel);
        loginField=new ApplicationField();
        loginField.setLocation(250, 200);
        add(loginField);

        passwordLabel=new ApplicationLabel("Password");
        passwordLabel.setLocation(100, 250);
        add(passwordLabel);
        passwordField=new ApplicationField();
        passwordField.setLocation(250, 250);
        add(passwordField);

        addButton=new ApplicationButton("SIGN IN");
        addButton.setLocation(50, 300);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login=loginField.getText();
                String password=passwordField.getText();
                User user=parent.clientSocket.toLogin(new User(null, login, password, null, 0));
                if(user!=null){

                    ClientApp.currentUser=user;
                    if(user.getRole()==1){
                        parent.loginPage.setVisible(false);
                        parent.adminPage.updateData();
                        parent.adminPage.setVisible(true);
                    }
                    else if(user.getRole()==2){
                        parent.loginPage.setVisible(false);
                        parent.userPage.updateData();
                        parent.userPage.setVisible(true);
                    }
                }
                else {
                    loginField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(parent, "Login or Password is incorrect!!!");
                }
            }
        });

        backButton=new ApplicationButton("BACK");
        backButton.setLocation(300, 300);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.loginPage.setVisible(false);
                parent.mainMenu.setVisible(true);
            }
        });
    }
}

