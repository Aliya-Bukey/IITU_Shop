package IITU_shop.server;

import IITU_shop.data.Ticket;
import IITU_shop.data.User;
import IITU_shop.data.UserBuy;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class ServerApp {
    public static Connection connection;
    public static void main(String args[]){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:8080/our_p?useUnicode=true&serverTimezone=UTC","root", "");
            ServerSocket serverSocket=new ServerSocket(2222);
            System.out.println("Waiting for a client...");
            while(true){
                Socket socket=serverSocket.accept();
                System.out.println("Client connected");
                ServerThread serverThread=new ServerThread(socket);
                serverThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void addUser(User user){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO users(id, login, password, fullname, role)"+
                    " VALUES(null, ?, ?, ?, ?) ");
            statement.setString(1,user.getLogin());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFullname());
            statement.setInt(4,user.getRole());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUserBuy(UserBuy userBuy){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO users_buy(id, user_id, organization, count1, totalsum)"+
                    " VALUES(null, ?, ?, ?, ?) ");
            statement.setLong(1,userBuy.getUser_id());
            statement.setString(2,userBuy.getOrganization());
            statement.setInt(3,userBuy.getCount());
            statement.setInt(4,userBuy.getTotalsum());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static User getUser(String login, String password){
        User user=null;
        try {
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM users where login=? and password=? ");
            statement.setString(1,login);
            statement.setString(2,password);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String login1=resultSet.getString("login");
                String password1=resultSet.getString("password");
                String fullname=resultSet.getString("fullname");
                int role=resultSet.getInt("role");
                user=new User(id, login1, password1, fullname, role);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public static void addTicket(Ticket ticket){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "INSERT INTO ticketss(id, name, price, count1, date, pleace, organization, sold )"+
                    " VALUES(null, ?,?,?,?,?,?,?)");
            statement.setString(1, ticket.getName());
            statement.setInt(2, ticket.getPrice());
            statement.setInt(3, ticket.getCount());
            statement.setString(4, ticket.getDate());
            statement.setInt(5, ticket.getPleace());
            statement.setString(6, ticket.getOrganization());
            statement.setInt(7,0);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Ticket> getAllTickets(){
        ArrayList<Ticket> tickets =new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM ticketss");
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                String name=resultSet.getString("name");
                int price=resultSet.getInt("price");
                int count=resultSet.getInt("count1");
                String date=resultSet.getString("date");
                int pleace=resultSet.getInt("pleace");
                String organization=resultSet.getString("organization");
                int sold=resultSet.getInt("sold");
                tickets.add(new Ticket(id, name, price, count,sold,date, pleace,organization ));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public static Ticket getTicket(Long id){
        Ticket ticket =new Ticket();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM ticketss where id=?");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id1=resultSet.getLong("id");
                String name=resultSet.getString("name");
                int price=resultSet.getInt("price");
                int count=resultSet.getInt("count1");
                String date=resultSet.getString("date");
                int pleace=resultSet.getInt("pleace");
                String organization=resultSet.getString("organization");
                ticket =new Ticket(id1, name, price, count, date, pleace, organization);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }
    public static void updateTicketBuy(Long id, int newcount, int sold){
        try {
            PreparedStatement statement=connection.prepareStatement("UPDATE ticketss set count1=?, sold=? WHERE id=?");
            statement.setInt(1, newcount);
            statement.setInt(2,sold);
            statement.setLong(3, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void editTicket(Ticket ticket){
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    " UPDATE ticketss set name=?,price=?,count1=?,date=?,pleace=?,organization=?"+
                    " WHERE id=?");
            statement.setString(1, ticket.getName());
            statement.setInt(2, ticket.getPrice());
            statement.setInt(3, ticket.getCount());
            statement.setString(4, ticket.getDate());
            statement.setInt(5, ticket.getPleace());
            statement.setString(6, ticket.getOrganization());
            statement.setLong(7, ticket.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteTicket(Long id){
        try {
            PreparedStatement statement=connection.prepareStatement("DELETE FROM ticketss WHERE id=? ");
            statement.setLong(1,id);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<UserBuy> getAllGoods(Long user_id){
        ArrayList<UserBuy> userBuys=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "SELECT * FROM users_buy WHERE user_id=?");
            statement.setLong(1, user_id);
            ResultSet resultSet=statement.executeQuery();
            while(resultSet.next()){
                Long id=resultSet.getLong("id");
                Long user_id1=resultSet.getLong("user_id");
                String organization
                        =resultSet.getString("organization");
                int count=resultSet.getInt("count1");
                int totalsum=resultSet.getInt("totalsum");

                UserBuy userBuy=new UserBuy(id, user_id1, organization, count, totalsum);
                userBuys.add(userBuy);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBuys;
    }
}


