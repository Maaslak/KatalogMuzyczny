package Gui.Change;

import javax.swing.*;
import java.awt.event.*;

public class DialagForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel message;
    private boolean isEndOfDialog = false;
    private boolean returningValue;

    public DialagForm(String message) {
        this.message.setText(message);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        returningValue = true;
        isEndOfDialog = true;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        returningValue = false;
        isEndOfDialog = true;
        dispose();
    }

    public boolean customSetVisible(boolean b) {
        super.setVisible(b);
        while(!isEndOfDialog);
        return returningValue;
    }
}
