import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {

    static Model model;
    static Layout layout;         //////
    static String apiKey;
    static JFrame myframe;

    public static void main(String[] args) {
        JFrame window = new JFrame("YouTube Video Gallery ");
        myframe = window;
        apiKey = "AIzaSyY_sBQmAwZxi80CV1EPHZU_MHFRdWpUbg1tOE"; // TEMP KEY(security) PLease contact me for correct key
        model = new Model();
        layout = new Layout(model, apiKey);
        window.setSize(1200, 700);
        window.setResizable(false);
        window.setMinimumSize(new Dimension(1200, 400));
        window.setMaximumSize(new Dimension(1200, 400));
       // window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model.resizeFrame(layout.scrollPane.getViewport().getWidth());
        window.setResizable(true);
        window.setLocationByPlatform(true);
        //addContent
        window.setContentPane(layout);
        window.setVisible(true);
        window.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                model.resizeFrame(layout.scrollPane.getViewport().getWidth());
            }
        });


    }
    //  System.out.println(youtubeModel.title.size());
    // Collections.copy(rootView.mytitle,youtubeModel.title);
    // youtubeModel.title = rootView.mytitle;
      /*      int index1 =0;
            while (index1 < youtubeModel.title.size()) {
                rootView.mytitle.add(youtubeModel.title.get(index1));
                index1++;
            } */
    // youtubeModel.videoUrl = rootView.myvideoUrl;
    // System.out.println(rootView.mytitle.size());

     public static void search (String searchyt) {
         ModelYoutube youtubeModel = new ModelYoutube("AIzaSyY_sBQmAwZxi80CV1EPHZU_MHFRdWpUbg1tOE",model); //, window); // (TEMP KEY) SECURITY
         youtubeModel.searchVideos(searchyt);
     }

     public static void clear() {
         model = new Model();
         model.resizeFrame(layout.scrollPane.getViewport().getWidth());
         layout = new Layout(model, apiKey);
         myframe.setContentPane(layout);
         myframe.setVisible(true);
         myframe.addComponentListener(new ComponentAdapter() {
             @Override
             public void componentResized(ComponentEvent e) {
                 super.componentResized(e);
                 model.resizeFrame(layout.scrollPane.getViewport().getWidth());
             }
         });
     }

}
