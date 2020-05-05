package IITU_shop.client.applications;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ApplicationField extends JTextField {
    public ApplicationField(){
        setSize(200, 30);
        setBackground(Color.white);
        setForeground(Color.black);
        setFont(new Font("Arial",1, 16));
        setBorder(new EtchedBorder(Color.black,Color.black));
    }
}
