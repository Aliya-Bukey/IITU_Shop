package IITU_shop.client.pages;
import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationField;
import IITU_shop.client.applications.ApplicationPanel;
import IITU_shop.data.User;
import IITU_shop.client.applications.ApplicationLabel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends ApplicationPanel {
    private ClientFrame parent;
    private ApplicationLabel loginLabel, passwordLabel,fioLabel;
    private ApplicationField loginField, passwordField, fioField;
    private ApplicationButton addButton, backButton;

    public RegisterPage(ClientFrame parent) {
        this.parent = parent;
        loginLabel=new ApplicationLabel("Login:");
        loginLabel.setLocation(100, 150);
        add(loginLabel);
        loginField=new ApplicationField();
        loginField.setLocation(250,150);
        add(loginField);

        passwordLabel=new ApplicationLabel("Password:");
        passwordLabel.setLocation(100, 210);
        add(passwordLabel);
        passwordField=new ApplicationField();
        passwordField.setLocation(250,210);
        add(passwordField);

        fioLabel=new ApplicationLabel("Full name:");
        fioLabel.setLocation(100, 270);
        add(fioLabel);
        fioField=new ApplicationField();
        fioField.setLocation(250,270);
        add(fioField);

        addButton=new ApplicationButton("ADD");
        addButton.setLocation(100, 330);
        add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login=loginField.getText();
                String password=passwordField.getText();
                String fullname=fioField.getText();
                if(login.isEmpty() || password.isEmpty() || fullname.isEmpty()){
                    JOptionPane.showMessageDialog(parent,"Please, fill all fields!!!");

                }
                else{
                    User user=new User(null, login, password,fullname,2);
                    parent.clientSocket.addUser(user);
                    loginField.setText("");
                    passwordField.setText("");
                    fioField.setText("");
                    JOptionPane.showMessageDialog(parent,"You are registered successfully!!!");
                }
            }
        });

        backButton=new ApplicationButton("BACK");
        backButton.setLocation(320, 330);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.registerPage.setVisible(false);
                parent.mainMenu.setVisible(true);
            }
        });


    }
}

