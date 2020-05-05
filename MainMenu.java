package IITU_shop.client.pages;

import IITU_shop.client.applications.ApplicationButton;
import IITU_shop.client.applications.ApplicationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends ApplicationPanel {
    private ClientFrame parent;
    private ApplicationButton loginButton, registerButton, exitButton;
    public MainMenu(ClientFrame parent){
        this.parent=parent;
        loginButton=new ApplicationButton("SIGN IN");
        loginButton.setLocation(180,150);
        add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.mainMenu.setVisible(false);
                parent.loginPage.setVisible(true);
            }
        });

        registerButton=new ApplicationButton("REGISTER");
        registerButton.setLocation(180,220);
        add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.mainMenu.setVisible(false);
                parent.registerPage.setVisible(true);
            }
        });

        exitButton=new ApplicationButton("EXIT");
        exitButton.setLocation(180,290);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
}

