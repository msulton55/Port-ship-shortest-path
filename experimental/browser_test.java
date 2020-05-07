package experimental;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.js.JsAccessible;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class browser_test {
    public String originInput;
    public String destinationInput;

    @JsAccessible
    public String getOriginInput() {
        return originInput;
    }

    @JsAccessible
    public String getDestinationInput() {
        return destinationInput;
    }

    public static void main(String[] args)  {
        Engine engine =
                Engine.newInstance(EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED)
                                           .licenseKey("6P830J66YAKYA3V12RFVTC7TX0KBBSJ440HWFG0WKYPI6LQ4584S8KXMI5KKHSID57O4")
                                           .build());

        Browser browser = engine.newBrowser();

        browser.navigation().loadUrl("C:\\Users\\msultont\\Desktop\\Code_Java\\Projek_Fitrah\\src\\view\\map.html");
        BrowserView view = BrowserView.newInstance(browser);

        SwingUtilities.invokeLater(() -> {

            JFrame displayFrame = new JFrame();
            JPanel toolbar = new JPanel();
            JTextField origin = new JTextField("origin", 20);
            JTextField destination = new JTextField("destination", 20);
            JButton submit = new JButton("Submit");

            toolbar.setBorder(new EmptyBorder(10,10,10,10));
            toolbar.add(origin);
            toolbar.add(destination);
            toolbar.add(submit);

            submit.addActionListener(e -> {
                browser_test javaObject = new browser_test();
                javaObject.originInput = origin.getText();
                javaObject.destinationInput = destination.getText();

                JsObject window = browser.mainFrame().get().executeJavaScript("window");
                window.putProperty("java", javaObject);

                browser.mainFrame().ifPresent(frame -> frame.executeJavaScript("window.onload = calculateAndDisplayRoute();"));

            });

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
            displayFrame.add(view, BorderLayout.CENTER);
            displayFrame.add(toolbar, BorderLayout.SOUTH);
            displayFrame.setVisible(true);


        });



    }

}
