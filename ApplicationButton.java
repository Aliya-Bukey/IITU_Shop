package IITU_shop.client.applications;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ApplicationButton extends JButton {
    public ApplicationButton(String text){
        setText(text);
        setSize(200,40);
        setBackground(Color.green);
        setForeground(Color.black);
        setFont(new Font("Arial",1, 16));
        setBorder(new EtchedBorder(Color.black,Color.black));
    }
}

