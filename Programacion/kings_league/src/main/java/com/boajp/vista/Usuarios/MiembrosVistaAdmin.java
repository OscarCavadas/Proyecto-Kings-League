package com.boajp.vista.Usuarios;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MiembrosVistaAdmin {
    private JTextField textField1;
    private JTextField textField2;
    private JButton eliminarButton;
    private JButton aceptarButton;
    private JTextField textField3;
    private JTextField textField4;


    public MiembrosVistaAdmin() {


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }
        });
    }
}
