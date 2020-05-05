package IITU_shop.client.pages;

import javax.swing.*;

public class ClientFrame extends JFrame {
    public ClientSocket clientSocket;
    public MainMenu mainMenu;
    public RegisterPage registerPage;
    public LoginPage loginPage;
    public AdminPage adminPage;
    public TicketPage ticketPage;
    public UserPage userPage;
    public BuyTicketPage buyTicketPage;
    public HistoryPage historyPage;
    public IncomePage incomePage;
    public ClientFrame(){
        setSize(600, 800);
        setTitle("IITU Shop");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        clientSocket=new ClientSocket();

        mainMenu=new MainMenu(this);
        mainMenu.setVisible(true);
        add(mainMenu);

        registerPage=new RegisterPage(this);
        registerPage.setVisible(false);
        add(registerPage);

        loginPage=new LoginPage(this);
        loginPage.setVisible(false);
        add(loginPage);

        adminPage=new AdminPage(this);
        adminPage.setVisible(false);
        add(adminPage);

        ticketPage =new TicketPage(this);
        ticketPage.setVisible(false);
        add(ticketPage);

        userPage=new UserPage(this);
        userPage.setVisible(false);
        add(userPage);

        buyTicketPage =new BuyTicketPage(this);
        buyTicketPage.setVisible(false);
        add(buyTicketPage);

        historyPage=new HistoryPage(this);
        historyPage.setVisible(false);
        add(historyPage);

        incomePage=new IncomePage(this);
        incomePage.setVisible(false);
        add(incomePage);
    }
}

