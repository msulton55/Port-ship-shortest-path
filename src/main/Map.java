package src.main;

import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.js.JsAccessible;
import com.teamdev.jxbrowser.js.JsObject;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;

/*
    This map class will display google maps to draw the line of shortest path by Dijkstra Algorithm.
 */

public class Map {

    // Start from here, this object will be used to retrieve Java object to Javascript to be used by google Maps API
    // to draw the maps.
    public List<Node> nodeLatLon;

    public List<Node> getNode() {
        return nodeLatLon;
    }

    @JsAccessible
    public double getNodeLat(int index) {
        double lat = getNode().get(index).lat;
        return lat;
    }

    @JsAccessible
    public double getNodeLon(int index) {
        double lon = getNode().get(index).lon;
        return lon;
    }

    @JsAccessible
    public int getNodeLength() {
        return getNode().size();
    }


    @JsAccessible
    public String getNodeId(int index) {
        return getNode().get(index).id;
    }

    // End of the Java-to-Javascript

    public static void main(List<Node> node)  {

        // Initiate browser engine to display the map.
        Engine engine =
                Engine.newInstance(EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED)
                                                .licenseKey("YOUR_LICENSE_KEY")
                                                .build());
        Browser browser = engine.newBrowser();
        browser.navigation().loadUrl(new File("map.html").getAbsolutePath()); // Locate map.html file to load into the browser.
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

            // Start from here, instantiate this class's object to be used by Javascript to take Java object.

            Map javaObject = new Map();
            javaObject.nodeLatLon = node;
            JsObject window = browser.mainFrame().get().executeJavaScript("window");
            window.putProperty("java", javaObject);

            // End of the line.

            displayFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            displayFrame.setBounds(338, 86, 1024, 600);
            displayFrame.setResizable(true);
            displayFrame.add(view, BorderLayout.CENTER);
            displayFrame.setVisible(true);
        });
    }

}
