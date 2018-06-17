import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;


public class Layout extends JPanel implements IView {
    Model model;
    GridPanelDisplay gridPane;
    static String myapiKey;
    static ArrayList<String> mytitle;
    static ArrayList<String> myvideoUrl;
    URL myUrl;
    JScrollPane scrollPane;
    JButton gridButton;
    JButton listButton;
    RatingFilter ratingFilter;
    JTextField t;

    public Layout(Model model, String apiKey) {
        super();
        this.myapiKey = apiKey;
        t = new JTextField(10);
        this.model = model;
        this.gridPane = new GridPanelDisplay(this.model);
        this.scrollPane = new JScrollPane(this.gridPane);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        this.setLayout(new BorderLayout());
        this.mytitle = new ArrayList<String>(26);
        this.myvideoUrl = new ArrayList<String>(26);
        toolBar();
        this.add(this.scrollPane, BorderLayout.CENTER);

    }

    public void toolBar() {
        this.setLayout(new BorderLayout());
        JPanel toolBar = new JPanel();
        this.add(toolBar, BorderLayout.NORTH);
        this.model = model;
       /* try {
            URL weburl = new URL("https://i.ytimg.com/vi/mRf3-JkwqfU/default.jpg");
            BufferedImage img = ImageIO.read(weburl);
            ImageIcon image = new ImageIcon(img);
            JLabel label2 = new JLabel(image);
            this.add(label2,BorderLayout.CENTER);
        } catch (MalformedURLException e1) {
            System.out.println("BAD LINK");
            e1.printStackTrace();
        } catch (Exception e1) {
            System.out.println("BAD IMAGE");
        }*/
        this.model.addObserver(this);
        ImageIcon gridImage = new ImageIcon("grid.png");
        gridButton = new JButton(gridImage);
        gridButton.setPreferredSize(new Dimension(35, 35));
        ImageIcon listImage = new ImageIcon("list.png");
        listButton = new JButton(listImage);
        listButton.setPreferredSize(new Dimension(35, 35));
        ImageIcon loadImage = new ImageIcon("search.png");
        JButton loadButton = new JButton(loadImage);
        loadButton.setPreferredSize(new Dimension(35, 35));
        toolBar.add(gridButton);  ///
        toolBar.add(listButton); /////

        gridButton.setBorder( BorderFactory.createMatteBorder(2,2,2,2,Color.black));
        gridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setListView(false);
            }
        });
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setListView(true);
            }
        });
        JLabel title = new JLabel("Search");
        title.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        toolBar.add(title);         /////////
        Font bigFont = t.getFont().deriveFont(Font.PLAIN, 25f);
        this.t.setFont(bigFont );
        toolBar.add(t); //////////
        toolBar.add(loadButton);
        JPanel loadSave = new JPanel();
        JButton loadButton1 = new JButton("Load");
        JButton saveButton = new JButton("Save");
        Box b = Box.createVerticalBox();
        b.add(Box.createVerticalGlue());
        b.add(loadButton1);
        b.add(saveButton);
        loadSave.add(b);
        toolBar.add(loadSave);
        this.ratingFilter = new RatingFilter(model);
        toolBar.add(ratingFilter);
        JPanel filter = new JPanel();
        JButton filterButton = new JButton("filter?");
        JButton resetButton = new JButton("Reset");
        resetButton.setEnabled(false);
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalGlue());
        box.add(filterButton);
        box.add(resetButton);
        filter.add(box);
        toolBar.add(filter);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButton.setEnabled(true);
                filterButton.setEnabled(false);
                model.filterVideos();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButton.setEnabled(false);
                filterButton.setEnabled(true);
                model.filterVideos();
            }
        });
       // mySearch= t.getText();

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchVal = t.getText();
                //System.out.println("GOT HERE");
                Main.clear();
                Main.search(searchVal);
            }
        });
    }


    public void updateView(EventEnum a) {
        switch(a) {
            case UpdateLayout:
                if (this.model.listView) {
                    this.listButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.black));
                    this.gridButton.setBorder(UIManager.getBorder("Button.border"));
                } else {
                    this.listButton.setBorder(UIManager.getBorder("Button.border"));
                    this.gridButton.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.black));
                }
                break;
        }
        this.revalidate();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
