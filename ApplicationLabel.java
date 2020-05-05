package IITU_shop.client.applications;

import javax.swing.*;
import java.awt.*;

public class ApplicationLabel extends JLabel {
    public ApplicationLabel(String text){
        setText(text);
        setSize(100, 30);
        setForeground(Color.black);
        setFont(new Font("Arial",1, 16));
    }
}

