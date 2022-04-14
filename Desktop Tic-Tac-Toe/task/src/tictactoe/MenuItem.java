package tictactoe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MenuItem extends JMenuItem implements ActionListener {
    private final MenuItemSpec itemSpec;

    MenuItem(final MenuItemSpec itemSpec) {
        this.itemSpec = itemSpec;
        this.setText(itemSpec.getMenuText());
        this.setName(itemSpec.getItemName());
        this.setAccelerator(KeyStroke.getKeyStroke(itemSpec.getKeyEvent(), ActionEvent.ALT_MASK));
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Controller.handleMenuItem(itemSpec);
    }
}

enum MenuItemSpec {
    HUMAN_HUMAN ("Human vs Human", "MenuHumanHuman", KeyEvent.VK_H, new String[]{"Human", "Human"}),
    HUMAN_ROBOT ("Human vs Robot", "MenuHumanRobot", KeyEvent.VK_X, new String[]{"Human", "Robot"}),
    ROBOT_HUMAN ("Robot vs Human", "MenuRobotHuman", KeyEvent.VK_O, new String[]{"Robot", "Human"}),
    ROBOT_ROBOT ("Robot vs Robot", "MenuRobotRobot", KeyEvent.VK_R, new String[]{"Robot", "Robot"}),
    EXIT ("Exit", "MenuExit", KeyEvent.VK_E, new String[]{});

    private final String itemName;
    private final String menuText;
    private final int keyEvent;
    private final String[] buttonText;

    MenuItemSpec(String text, String name, int key, String[] buttonText) {
        this.menuText = text;
        this.itemName = name;
        this.keyEvent = key;
        this.buttonText = buttonText;
    }

    String getItemName() {
        return itemName;
    }

    String getMenuText() {
        return menuText;
    }

    int getKeyEvent() {
        return keyEvent;
    }

    String[] getButtonText() {
        return buttonText;
    }
}
