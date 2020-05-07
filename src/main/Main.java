/* AUTHORS
 * Muhammad Sulton Tauhid
 * Email	: msulton55@gmail.com
 * Instagram: msultont
 */
package src.main;

import java.awt.*;
import java.util.List;
import java.io.File;       
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

public class Main {

    // Global variable to save lists of port coordinate files.
    static File selectedFile = new File("");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            // Main component of front page of the program.
            // Initiate all frame component.
            JFrame frame = new JFrame("Sea-port Shortest Path Calculation");
            JPanel topPanel = new JPanel();
            JPanel mainPanel = new JPanel();
            JPanel footerPanel = new JPanel();

            // Initiate all label component to be put inside the frame.
            JLabel titleHeader = new JLabel("Welcome\n");
            JLabel mainText1 = new JLabel("Press Start to begin\n");
            JLabel mainText2 = new JLabel("Press Insert Map to put map database\n");
            JLabel mainText3 = new JLabel("Press Exit to close");
            JLabel footer = new JLabel("Created by @msultont");

            // Initiate the button component to listen the event.
            JButton button1 = new JButton("Start");
            JButton button2 = new JButton("Insert Map");
            JButton button3 = new JButton("Exit");

            // Define the sub-component settings.
            titleHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleHeader.setFont(new Font("Serif", Font.PLAIN, 45));
            mainText1.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainText1.setFont(new Font("Serif", Font.PLAIN, 20));
            mainText2.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainText2.setFont(new Font("Serif", Font.PLAIN, 20));
            mainText3.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainText3.setFont(new Font("Serif", Font.PLAIN, 20));
            button1.setAlignmentX(Component.CENTER_ALIGNMENT);
            button2.setAlignmentX(Component.CENTER_ALIGNMENT);
            button3.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Put sub-component to the panel component.
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
            topPanel.setPreferredSize(new Dimension(600, 150));
            topPanel.add(titleHeader);
            topPanel.add(mainText1);
            topPanel.add(mainText2);
            topPanel.add(mainText3);
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(button1);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(button2);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(button3);
            footerPanel.add(footer);

            // Start button action listener.
            SwingUtilities.invokeLater(() -> {
                button1.addActionListener(e -> {
                    JFrame startFrame = new JFrame();
                    JDialog startBox = new JDialog(startFrame, "System Start");
                    JPanel startMainPanel = new JPanel();
                    GroupLayout layout = new GroupLayout(startMainPanel);
                    JLabel label = new JLabel("Origin");
                    JLabel label2 = new JLabel("Destination");
                    JTextField textField = new JTextField();
                    JTextField textField2 = new JTextField();
                    JButton startButton = new JButton("Start");
                    JCheckBox showMap = new JCheckBox("Show Map");
                    JLabel resultPath = new JLabel();
                    JLabel resultDistance = new JLabel();

                    startBox.setBounds(402, 248, 515, 325);

                    startMainPanel.setLayout(layout);
                    layout.setAutoCreateGaps(true);
                    layout.setAutoCreateContainerGaps(true);

                    startButton.addActionListener(e1 -> {
                        String file = selectedFile.getAbsolutePath();
                        Graph g = new Graph(file);
                        String origin = textField.getText();
                        String destination = textField2.getText();
                        List<Node> n = g.shortestPath(origin, destination);

                        resultPath.setText("Path: " + n.toString());
                        resultDistance.setText("Distance: " + Node.pathLength(n) + " KM");

                        if (showMap.isSelected()) {
                            Map.main(n);
                        }

                    });

                    layout.setHorizontalGroup(layout.createSequentialGroup()
                                                      .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                                                        .addComponent(label)
                                                                        .addComponent(label2))
                                                      .addGroup(layout.createParallelGroup()
                                                                        .addComponent(textField)
                                                                        .addComponent(textField2)
                                                                        .addComponent(startButton)
                                                                        .addComponent(showMap)
                                                                        .addComponent(resultPath)
                                                                        .addComponent(resultDistance))
                    );
                    layout.linkSize(SwingConstants.HORIZONTAL, startButton);

                    layout.setVerticalGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                                                      .addComponent(label)
                                                                      .addComponent(textField))
                                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                                                      .addComponent(label2)
                                                                      .addComponent(textField2))
                                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                                                      .addComponent(startButton))
                                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                                                      .addComponent(showMap))
                                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                                                      .addComponent(resultPath))
                                                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                                                      .addComponent(resultDistance))
                    );

                    startBox.add(startMainPanel);
                    startBox.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    startBox.setVisible(true);

                });
            });

            // Insert map action listener.
            SwingUtilities.invokeLater(() -> {
                button2.addActionListener(e -> {
                    JFrame openFileFrame = new JFrame();
                    JDialog openFileWindow = new JDialog(openFileFrame, "Open File");
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(openFileWindow);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        System.out.println(selectedFile.getAbsolutePath());
                    }

                });
            });

            // Exit action listener.
            SwingUtilities.invokeLater(() -> {
                button3.addActionListener(e -> {
                    JFrame boxFrame = new JFrame();
                    JDialog box = new JDialog(boxFrame, "System");
                    JPanel headPanel = new JPanel();
                    JPanel mainButtonPanel = new JPanel();
                    JLabel label = new JLabel("Are you sure?");
                    JButton yesButton = new JButton("Yes");
                    JButton noButton = new JButton("No");

                    box.setBounds(564, 300, 300, 150);

                    yesButton.addActionListener(e13 -> System.exit(1));
                    noButton.addActionListener(e12 -> boxFrame.dispose());


                    label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                    label.setFont(new Font("Serif", Font.PLAIN, 20));
                    headPanel.add(label);

                    mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.X_AXIS));
                    mainButtonPanel.add(Box.createRigidArea(new Dimension(80, 0)));
                    mainButtonPanel.add(yesButton);
                    mainButtonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
                    mainButtonPanel.add(noButton);

                    box.add(headPanel, BorderLayout.PAGE_START);
                    box.add(mainButtonPanel, BorderLayout.CENTER);
                    box.setVisible(true);

                });
            });

            // Put all sub-components into main frame.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(359, 160, 600, 480);
            frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
            frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
            frame.getContentPane().add(footerPanel, BorderLayout.PAGE_END);
            frame.setVisible(true);
        });
    }
}