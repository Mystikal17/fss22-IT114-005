package Project2.client.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.swing.JPanel;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;

import Project2.client.Card;
import Project2.client.Client;
import Project2.client.ICardControls;
import Project2.client.IGameEvents;
import Project2.common.Phase;
import Project2.server.GameRoom;

public class GamePanel extends JPanel implements IGameEvents {
    private CardLayout cardLayout;
    private JButton loadButton;


    public GamePanel(ICardControls controls) {
        super(new CardLayout());
        cardLayout = (CardLayout) this.getLayout();
        this.setName(Card.GAME_SCREEN.name());
        Client.INSTANCE.addCallback(this);
        // this is purely for debugging
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("GamePanel Resized to " + e.getComponent().getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // System.out.println("Moved to " + e.getComponent().getLocation());
            }
        });
        createReadyPanel();
        createGameView();
        createChoicePanel();
        setVisible(false);
        // don't need to add this to ClientUI as this isn't a primary panel(it's nested
        // in ChatGamePanel)
        // controls.addPanel(Card.GAME_SCREEN.name(), this);
    }

    private void createReadyPanel() {
        JPanel readyPanel = new JPanel();
        JButton readyButton = new JButton();
        readyButton.setText("Ready");
        readyButton.addActionListener(l -> {
            try {
                Client.INSTANCE.sendReadyStatus();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        readyPanel.add(readyButton);
        this.add(readyPanel);

         JButton startButton = new JButton();
    startButton.setText("Start");
    startButton.addActionListener(e -> {
        // Perform actions related to starting the game here
    });
    readyPanel.add(startButton);

    this.add(readyPanel);
}
    

    private void createGameView() {
        JPanel container = new JPanel(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, getFocusTraversalKeysEnabled());
        splitPane.setResizeWeight(.6);
        splitPane.setOneTouchExpandable(false); // This disables the one-touch expandable buttons
        splitPane.setEnabled(false); // This makes the divider non-movable
        container.add(splitPane, BorderLayout.CENTER);
        add(container);
    }

       @Override
    public void onReceivePhase(Phase phase) {
        // I'll temporarily do next(), but there may be scenarios where the screen can
        // be inaccurate
        System.out.println("Received phase: " + phase.name());
        if (phase == Phase.READY) {
            if (!isVisible()) {
                setVisible(true);
                this.getParent().revalidate();
                this.getParent().repaint();
                System.out.println("GamePanel visible");
            } else {
                cardLayout.next(this);
            }
        } else if (phase == Phase.SELECTION) {
            cardLayout.next(this);
        }
    }

    private void createChoicePanel(){
        JButton rockButton = new JButton("Rock");
        rockButton.addActionListener(e -> {
            try {
                Client.INSTANCE.sendChoice("Rock");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JButton paperButton = new JButton("Paper");
        paperButton.addActionListener(e -> {
            try {
                Client.INSTANCE.sendChoice("Paper");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JButton scissorsButton = new JButton("Scissors");
        scissorsButton.addActionListener(e -> {
            try {
                Client.INSTANCE.sendChoice("Scissors");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        add(rockButton);
        add(paperButton);
        add(scissorsButton);
    }


    @Override
    public void onClientConnect(long id, String clientName, String message) {
    }

    @Override
    public void onClientDisconnect(long id, String clientName, String message) {
    }

    @Override
    public void onMessageReceive(long id, String message) {
    }

    @Override
    public void onReceiveClientId(long id) {
    }

    @Override
    public void onSyncClient(long id, String clientName) {
    }

    @Override
    public void onResetUserList() {
    }

    @Override
    public void onReceiveRoomList(String[] rooms, String message) {
    }

    @Override
    public void onRoomJoin(String roomName) {
    }

    @Override
    public void onReceiveReady(long clientId) {
    }



}
