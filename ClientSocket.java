package IITU_shop.client.pages;
import IITU_shop.data.Ticket;
import IITU_shop.data.Packet;
import IITU_shop.data.User;
import IITU_shop.data.UserBuy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientSocket {
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ClientSocket() {
        try {
            socket=new Socket("localhost:8080",2222);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            oos.close();
            ois.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addUser(User user){
        Packet packet=new Packet("ADD_USER",user);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public User toLogin(User user){
        User result=null;
        try {
            Packet packet=new Packet("AUTH", user);
            oos.writeObject(packet);

            Packet packet1=(Packet)ois.readObject();
            result=(User) packet1.getData();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public void addTicket(Ticket ticket){
        Packet packet=new Packet("ADD_TICKET", ticket);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Ticket> getAllTickets(){
        ArrayList<Ticket> tickets =new ArrayList<>();
        Packet packet=new Packet("LIST_TICKET", null);
        try {
            oos.writeObject(packet);
            Packet response=(Packet)ois.readObject();
            tickets =(ArrayList<Ticket>)response.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }
    public void editTicket(Ticket ticket){
        Packet packet=new Packet("EDIT_TICKET", ticket);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void editTicketBuy(Ticket ticket){
        Packet packet=new Packet("EDIT_TICKET_BUY", ticket);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteTicket(Ticket ticket){
        Packet packet=new Packet("DELETE_TICKET", ticket);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void buyTicket(UserBuy userBuy){
        Packet packet=new Packet("BUY_TICKET", userBuy);
        try {
            oos.writeObject(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<UserBuy> getAllGoods(UserBuy userBuy){
        ArrayList<UserBuy> userBuys=new ArrayList<>();
        Packet packet=new Packet("LIST_USER_GOODS", userBuy);
        try {
            oos.writeObject(packet);
            Packet response=(Packet)ois.readObject();
            userBuys=(ArrayList<UserBuy>)response.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userBuys;
    }

}

