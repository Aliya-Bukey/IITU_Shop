package IITU_shop.server;


import IITU_shop.data.Packet;
import IITU_shop.data.Ticket;
import IITU_shop.data.User;
import IITU_shop.data.UserBuy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            while(true){
                Packet packet=(Packet)ois.readObject();
                if(packet.getCode().equals("ADD_USER")){
                    User user=(User)packet.getData();
                    ServerApp.addUser(user);
                }
                else if(packet.getCode().equals("CHEK USER")){
                    User user=(User)packet.getData();
                    User response=ServerApp.getUser(user.getLogin(), user.getPassword());
                    Packet packet1=new Packet("CHEK User", response);
                    oos.writeObject(packet1);
                }
                else if(packet.getCode().equals("ADD_TICKET")){
                    Ticket ticket =(Ticket)packet.getData();
                    ServerApp.addTicket(ticket);
                }
                else if(packet.getCode().equals("LIST_TICKET")){
                    ArrayList<Ticket> tickets =ServerApp.getAllTickets();
                    Packet packet1=new Packet("LIST_TICKET", tickets);
                    oos.writeObject(packet1);
                }
                else if(packet.getCode().equals("EDIT_TICKET")){
                    Ticket ticket =(Ticket)packet.getData();
                    ServerApp.editTicket(ticket);
                }
                else if(packet.getCode().equals("DELETE_TICKET")){
                    Ticket ticket =(Ticket)packet.getData();
                    ServerApp.deleteTicket(ticket.getId());
                }
                else if(packet.getCode().equals("BUY_TICKET")){
                    UserBuy userBuy=(UserBuy)packet.getData();
                    ServerApp.addUserBuy(userBuy);
                }
                else if(packet.getCode().equals("EDIT_TICKET_BUY")){
                    Ticket ticket =(Ticket)packet.getData();
                    Ticket frDB=ServerApp.getTicket(ticket.getId());
                    int newcount=frDB.getCount()- ticket.getCount();
                    ServerApp.updateTicketBuy(ticket.getId(), newcount, ticket.getCount());
                }
                else if(packet.getCode().equals("LIST_USER_GOODS")){
                    UserBuy userBuy=(UserBuy)packet.getData();
                    ArrayList<UserBuy> userBuys=ServerApp.getAllGoods(userBuy.getUser_id());
                    Packet packet1=new Packet("LIST_USER_GOODS", userBuys);
                    oos.writeObject(packet1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

