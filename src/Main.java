/* AUTHORS
 * Muhammad Sulton Tauhid
 * Email	: msulton55@gmail.com
 * Instagram: msultont
 * Whatsapp	: +6282299024212
 * 
 */
package src;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.io.File;       
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;


public class Main {
    static File selectedFile = new File("");
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map");
        JPanel topPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel footerPanel = new JPanel();
        JLabel titleHeader = new JLabel("Welcome\n");
        JLabel mainText1 = new JLabel("Press Start to begin\n");
        JLabel mainText2 = new JLabel("Press Insert Map to put map database\n");
        JLabel mainText3 = new JLabel("Press Exit to close");
        JLabel footer = new JLabel("Created by @msultont");
        JButton button1 = new JButton("Start");
        JButton button2 = new JButton("Insert Map");
        JButton button3 = new JButton("Exit");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(360, 160, 600, 480); 
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setPreferredSize(new Dimension(600, 150));
        titleHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleHeader.setFont(new Font("Serif", Font.PLAIN, 45));
        mainText1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainText1.setFont(new Font("Serif", Font.PLAIN, 20));
        mainText2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainText2.setFont(new Font("Serif", Font.PLAIN, 20));
        mainText3.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainText3.setFont(new Font("Serif", Font.PLAIN, 20));
        topPanel.add(titleHeader);
        topPanel.add(mainText1);
        topPanel.add(mainText2);
        topPanel.add(mainText3); 
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        button1.setAlignmentX(Component.CENTER_ALIGNMENT);
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        button3.setAlignmentX(Component.CENTER_ALIGNMENT);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame startFrame = new JFrame();
                JDialog startBox = new JDialog(startFrame, "System Start");
                JPanel startMainPanel = new JPanel();
                GroupLayout layout = new GroupLayout(startMainPanel);
                JLabel label = new JLabel("Origin");
                JLabel label2 = new JLabel("Destination");
                JTextField textField = new JTextField();
                JTextField textField2 = new JTextField();
                JCheckBox showCheckBox = new JCheckBox("Show map");
                JButton startButton = new JButton("Start");
                JButton displayMapButton = new JButton("Display Map");
                JLabel resultPath = new JLabel();
                JLabel resultDistance = new JLabel();

                startBox.setBounds(402, 248, 515, 325);
                showCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                startMainPanel.setLayout(layout);
                layout.setAutoCreateGaps(true);
                layout.setAutoCreateContainerGaps(true);

                startButton.addActionListener(e1 -> {
                    String file = selectedFile.getAbsolutePath();
                    Graph g = new Graph(file);
                    String origin = textField.getText();
                    JFrame displayFrame = new JFrame();
                    String destination = textField2.getText();
                    List<Node> n = g.shortestPath(origin, destination);
                    Canvas can = new Canvas(g, n);

                    boolean showMap = showCheckBox.isSelected();

                    resultPath.setText("Path: " + n.toString());
                    resultDistance.setText("Distance: " + Node.pathLength(n) + " KM");
                    if (showMap) {
                        displayFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        displayFrame.setBounds(338, 86, 800, 600);
                        displayFrame.setResizable(true);
                        displayFrame.add(can);
                        displayFrame.setVisible(true);
                    }
                });
                displayMapButton.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       Engine engine =
                               Engine.newInstance(EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED)
                                       .licenseKey("6P830J66YAKYA3V12RFVTC7TX0KBBSJ440HWFG0WKYPI6LQ4584S8KXMI5KKHSID57O4")
                                       .build());
                       Browser browser = engine.newBrowser();
                       browser.navigation().loadUrl("https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=11&size=612x612&scale=2&maptype=roadmap");
                       BrowserView view = BrowserView.newInstance(browser);

                       SwingUtilities.invokeLater(() -> {
                           JFrame displayFrame = new JFrame();
                           displayFrame.addWindowListener(new WindowAdapter() {
                               @Override
                               public void windowClosing(WindowEvent e) {
                                   super.windowClosing(e);
                                   engine.close();
                               }
                           });
                           displayFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                           displayFrame.setBounds(338, 86, 1024, 600);
                           displayFrame.setResizable(true);
                           displayFrame.add(view);
                           displayFrame.setVisible(true);
                       });
                   }
               });
                layout.setHorizontalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                        .addComponent(label)
                        .addComponent(label2))
                    .addGroup(layout.createParallelGroup()
                        .addComponent(textField)
                        .addComponent(textField2)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(startButton)
                            .addComponent(displayMapButton))
                        .addComponent(showCheckBox)
                        .addComponent(resultPath)
                        .addComponent(resultDistance))
                );
                layout.linkSize(SwingConstants.HORIZONTAL, startButton, displayMapButton);

                layout.setVerticalGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(label)
                        .addComponent(textField))
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(label2)
                        .addComponent(textField2))
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(startButton)
                        .addComponent(displayMapButton))
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(showCheckBox))
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(resultPath))
                    .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(resultDistance))
                );

                startBox.add(startMainPanel);
                startBox.setVisible(true);

            }

        });
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
        mainPanel.add(button1);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(button2);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(button3);

        footerPanel.add(footer);

        frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);      
        frame.getContentPane().add(footerPanel, BorderLayout.PAGE_END); 
        frame.setVisible(true);
    }
}