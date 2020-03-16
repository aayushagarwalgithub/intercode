/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interviewee;

import static interviewee.firstscreen.device;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ESCAPE;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author Sambhav Kanishka
 */
public class Screen extends javax.swing.JFrame implements KeyListener {

    /**
     * Creates new form Screen
     */
    int flag=1;
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    ServerSocket ss;
    Socket s;
    DataInputStream dis;
    String str;
    DataOutputStream dout;
    Thread mt = new Thread();
    Thread lthread;
    Find find;
    Replace replace;
    String s_name;

    public Screen() throws IOException {
        this.setUndecorated(true);
//        this.setAlwaysOnTop(true);
        this.setVisible(true);

        initComponents();
        addKeyListener(this);
        setFocusable(true);
        device.setFullScreenWindow(this);

        s = new Socket("192.168.43.148", 6668); //172.29.44.250
        Mthread t1 = new Mthread();
        t1.start();
        dout = new DataOutputStream(s.getOutputStream());
        
        lthread=new Thread(new Runnable(){

            @Override
            public void run() {
               try
               {
                   //Thread.sleep(2000);
                   //while(true)
                    {
                        lines = new JTextArea("1");
                        lines.setBackground(Color.RED);
                        lines.setEditable(false);
                        code_editor.getDocument().addDocumentListener(new DocumentListener()
                        {
                            public String getText()
                            {
                                    int caretPosition = code_editor.getDocument().getLength();
                                    System.out.println("caretPosition="+caretPosition);
                                    Element root = code_editor.getDocument().getDefaultRootElement();
                                    System.out.println("Element root"+root);
                                    String text = "1" + System.getProperty("line.separator");
                                    System.out.println("text="+text);
                                    for(int i = 2; i < root.getElementIndex(caretPosition) + 2; i++)
                                    {
                                            text += i + System.getProperty("line.separator");
                                            System.out.println(text);
                                    }
                                    return text;
                            }
                            @Override
                            public void changedUpdate(DocumentEvent de) {
                                    lines.setText(getText());
                            }

                            @Override
                            public void insertUpdate(DocumentEvent de) {
                                    lines.setText(getText());
                            }
                            @Override
                            public void removeUpdate(DocumentEvent de) {
                                    lines.setText(getText());
                            }

                        });
                        jScrollPane1.getViewport().add(code_editor);
                        jScrollPane1.setRowHeaderView(lines);
                        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         //               Thread.sleep(2000);
                    }
               } 
               catch(Exception e)
                {

                }
            }
            
        });
        
        
    }
    void set(String name){
        s_name=name;
        StudentName.setText(name);
    }
   class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter{                //FIND
        
        public MyHighlightPainter(Color c) {
            super(c);
        }
        
    }
    Highlighter.HighlightPainter myHighlightPainter =new MyHighlightPainter(Color.red);
    
    public void highlight(String pattern){
        try{
            JTextComponent textComp=code_editor;
            Highlighter hilite=textComp.getHighlighter();
            Document doc=textComp.getDocument();
            String text=doc.getText(0, doc.getLength());
            int pos=0;
            
            while((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0){
                hilite.addHighlight(pos,pos+pattern.length(),myHighlightPainter);
                pos+=pattern.length();
            }
        }
        catch(Exception e){
            System.out.println(e+"Find");
        }
    }                                                                                          //FIND Finish
    
    void replaceFunc(String search,String replace_with) throws IOException{                     //Replace Function
        String txt=code_editor.getText();
        if(txt.toLowerCase().contains(search.toLowerCase())){
            code_editor.setText(txt.replaceAll("(?i)"+search,replace_with));
        }
        dout.writeInt(0);
        dout.writeUTF(code_editor.getText());
        dout.flush();  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jColorChooser1 = new javax.swing.JColorChooser();
        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        buttonGroup6 = new javax.swing.ButtonGroup();
        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        Question = new javax.swing.JTextField();
        StudentName = new javax.swing.JTextField();
        Message = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        message_display_area = new javax.swing.JTextPane();
        jComboBox2 = new javax.swing.JComboBox<>();
        font_size = new javax.swing.JComboBox<>();
        font_style = new javax.swing.JComboBox<>();
        send = new javax.swing.JButton();
        tabbed_pane = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        input_text_tab = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        output_text_tab = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        code_editor = new javax.swing.JEditorPane();
        jButton3 = new javax.swing.JButton();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        Find = new javax.swing.JMenuItem();
        Replace = new javax.swing.JMenuItem();

        jTextField1.setText("jTextField1");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jButton1.setText("jButton1");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Question.setEditable(false);
        Question.setText("Question");
        Question.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                QuestionKeyPressed(evt);
            }
        });

        StudentName.setEditable(false);
        StudentName.setText("Student's Name");
        StudentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StudentNameActionPerformed(evt);
            }
        });

        Message.setText("Message");
        Message.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MessageMouseClicked(evt);
            }
        });
        Message.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MessageActionPerformed(evt);
            }
        });
        Message.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MessageKeyPressed(evt);
            }
        });

        message_display_area.setEditable(false);
        message_display_area.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                message_display_areaKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(message_display_area);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        font_size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" }));
        font_size.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                font_sizeItemStateChanged(evt);
            }
        });
        font_size.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                font_sizeKeyPressed(evt);
            }
        });

        font_style.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Times New Roman", "Abadi MT Condensed Extra Bold", "Abadi MT Condensed Light", "Academy Engraved LET", "Al Bayan", "American Typewriter", "Andale Mono", "Apple Casual", "Apple Chancery", "Apple LiGothic", "Apple LiSung", "Apple Symbols", "AppleGothic", "AppleMyungjo", "Arial", "Arial Black", "Arial Hebrew", "Arial Narrow", "Arial Rounded MT Bold", "Ayuthaya", "Baghdad", "Bank Gothic", "Baskerville", "Baskerville Old Face", "Batang", "Bauhaus 93", "Bell MT", "Bernard MT Condensed", "BiauKai", "Big Caslon", "Bitstream Vera Sans", "Bitstream Vera Sans Mono", "Bitstream Vera Serif", "Blackmoor LET", "BlairMdITC TT", "Bodoni Ornaments ITC TT", "Bodoni SvtyTwo ITC TT", "Bodoni SvtyTwo OS ITC TT", "Bodoni SvtyTwo SC ITC TT", "Book Antiqua", "Bookman Old Style", "Bordeaux Roman Bold LET", "Bradley Hand ITC TT", "Braggadocio", "Britannic Bold", "Brush Script MT", "Calisto MT", "Century", "Century Gothic", "Century Schoolbook", "Chalkboard", "Charcoal CY", "Cochin", "Colonna MT", "Comic Sans MS", "Cooper Black", "Copperplate", "Copperplate Gothic Bold", "Copperplate Gothic Light", "Corsiva Hebrew", "Courier", "Courier New", "Cracked", "Curlz MT", "DecoType Naskh", "Desdemona", "Devanagari MT", "Dialog", "DialogInput", "Didot", "Edwardian Script ITC", "Engravers MT", "Euphemia UCAS", "Eurostile", "Footlight MT Light", "Futura", "Garamond", "GB18030 Bitmap", "Geeza Pro", "Geneva", "Geneva CY", "Georgia", "Gill Sans", "Gill Sans Ultra Bold", "Gloucester MT Extra Condensed", "Goudy Old Style", "Gujarati MT", "Gulim", "Gurmukhi MT", "Haettenschweiler", "Handwriting - Dakota", "Harrington", "Hei", "Helvetica", "Helvetica CY", "Helvetica Neue", "Herculanum", "Hiragino Kaku Gothic Pro", "Hiragino Kaku Gothic Std", "Hiragino Maru Gothic Pro", "Hiragino Mincho Pro", "Hoefler Text", "Impact", "Imprint MT Shadow", "InaiMathi", "Jazz LET", "Kai", "Kino MT", "Krungthep", "KufiStandardGK", "LiHei Pro", "LiSong Pro", "Lucida Blackletter", "Lucida Bright", "Lucida Calligraphy", "Lucida Fax", "Lucida Grande", "Lucida Handwriting", "Lucida Sans", "Lucida Sans Typewriter", "Marker Felt", "Matura MT Script Capitals", "Mistral", "Modern No. 20", "Mona Lisa Solid ITC TT", "Monaco", "Monospaced", "Monotype Corsiva", "Monotype Sorts", "MS Gothic", "MS Mincho", "MS PGothic", "MS PMincho", "Mshtakan", "MT Extra", "Nadeem", "New Peninim MT", "News Gothic MT", "Onyx", "OpenSymbol", "Optima", "Osaka", "Palatino", "Papyrus", "Party LET", "Perpetua Titling MT", "Plantagenet Cherokee", "Playbill", "PMingLiU", "PortagoITC TT", "Princetown LET", "Raanana", "Rockwell", "Rockwell Extra Bold", "SansSerif", "Santa Fe LET", "Sathu", "Savoye LET", "SchoolHouse Cursive B", "SchoolHouse Printed A", "Serif", "Silom", "SimSun", "Skia", "Snell Roundhand", "Stencil", "STFangsong", "STHeiti", "STKaiti", "Stone Sans ITC TT", "Stone Sans Sem ITC TT", "STSong", "Symbol", "Synchro LET", "Tahoma", "Thonburi", "Times", "Times New Roman", "Trebuchet MS", "Type Embellishments One LET", "Verdana", "Webdings", "Wide Latin", "Wingdings", "Wingdings 2", "Wingdings 3", "Zapf Dingbats", "Zapfino" }));
        font_style.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                font_styleItemStateChanged(evt);
            }
        });
        font_style.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                font_styleActionPerformed(evt);
            }
        });
        font_style.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                font_styleKeyPressed(evt);
            }
        });

        send.setText("Send");
        send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendActionPerformed(evt);
            }
        });
        send.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sendKeyPressed(evt);
            }
        });

        tabbed_pane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabbed_paneKeyPressed(evt);
            }
        });

        input_text_tab.setColumns(20);
        input_text_tab.setRows(5);
        input_text_tab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                input_text_tabKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(input_text_tab);

        tabbed_pane.addTab("Input", jScrollPane2);

        output_text_tab.setColumns(20);
        output_text_tab.setRows(5);
        output_text_tab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                output_text_tabKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(output_text_tab);

        tabbed_pane.addTab("Output", jScrollPane4);

        code_editor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                code_editorMouseClicked(evt);
            }
        });
        code_editor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                code_editorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                code_editorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                code_editorKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(code_editor);

        jButton3.setText("Close");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenuBar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jMenuBar3KeyPressed(evt);
            }
        });

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu2.setText("Edit");

        Find.setText("Find");
        Find.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FindActionPerformed(evt);
            }
        });
        jMenu2.add(Find);

        Replace.setText("Replace");
        Replace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReplaceActionPerformed(evt);
            }
        });
        jMenu2.add(Replace);

        jMenuBar3.add(jMenu2);

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(font_style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(font_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(Question)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Message, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addComponent(send, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(StudentName, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))))
            .addComponent(tabbed_pane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StudentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Message, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Question, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(font_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(send)
                    .addComponent(font_style, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabbed_pane, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendScreenshot() throws AWTException, IOException {
        Socket is = new Socket("192.168.43.148", 6600);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimensions = toolkit.getScreenSize();
        Robot robot = new Robot();  // Robot class 
        BufferedImage screenshot = robot.createScreenCapture(new Rectangle(dimensions));
        ImageIO.write(screenshot, "png", is.getOutputStream());
        System.out.println("ScreenShot sent successfully!!");
        is.close();
    }

    private void MessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MessageActionPerformed
        // TODO add your handling code here:
        try {
            dout.writeInt(1);
            dout.writeUTF(Message.getText());
            message_display_area.setText(message_display_area.getText() + "\n" + "You: " + Message.getText());
            Message.setText("");
            dout.flush();
            System.out.println("Msg sent successfully");
        } catch (Exception e) {
            System.out.println(e + "exception occured");
        }
    }//GEN-LAST:event_MessageActionPerformed

    private void font_styleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_font_styleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_font_styleActionPerformed

    private void code_editorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_code_editorKeyPressed
        // TODO add your handling code here:
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }
        try {

            dout.writeInt(0);
            dout.writeUTF(code_editor.getText());
            dout.flush();
            System.out.println("Code sent successfully");
        } catch (Exception e) {
            System.out.println(e + "exception occured");
        }
        
        if(flag==1)
        {
            lthread.start();
            flag=0;
        }
    }//GEN-LAST:event_code_editorKeyPressed


    private void font_styleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_font_styleItemStateChanged
        // TODO add your handling code here:
        String str = (String) font_style.getSelectedItem().toString();
        int size = parseInt((String) font_size.getSelectedItem().toString());
        code_editor.setFont(new Font(str, Font.PLAIN, size));
    }//GEN-LAST:event_font_styleItemStateChanged

    private void font_sizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_font_sizeItemStateChanged
        // TODO add your handling code here:
        String str = (String) font_style.getSelectedItem().toString();
        int size = parseInt((String) font_size.getSelectedItem().toString());
        code_editor.setFont(new Font(str, Font.PLAIN, size));
    }//GEN-LAST:event_font_sizeItemStateChanged

    private void sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendActionPerformed
        // TODO add your handling code here:
        try {
//System.out.println("here");
            dout.writeInt(1);
            dout.writeUTF(Message.getText());
            message_display_area.setText(message_display_area.getText() + "\n" + "You: " + Message.getText());
            Message.setText("");
            dout.flush();
            System.out.println("Msg sent successfully");
        } catch (Exception e) {
            System.out.println(e + "exception occured");
        }

    }//GEN-LAST:event_sendActionPerformed

    private void MessageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MessageMouseClicked
        // TODO add your handling code here:
        Message.setText("");
    }//GEN-LAST:event_MessageMouseClicked

    private void code_editorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_code_editorKeyTyped
        // TODO add your handling code here:
        int k = evt.getKeyCode();
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
        }
    }//GEN-LAST:event_code_editorKeyTyped

    private void QuestionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QuestionKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        //System.out.println(k);
        //System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_QuestionKeyPressed

    private void message_display_areaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_message_display_areaKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        //System.out.println(k);
        //System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);   
        }// TODO add your handling code here:
    }//GEN-LAST:event_message_display_areaKeyPressed

    private void jMenuBar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jMenuBar3KeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar3KeyPressed

    private void MessageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MessageKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_MessageKeyPressed

    private void sendKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sendKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_sendKeyPressed

    private void input_text_tabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_input_text_tabKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_input_text_tabKeyPressed

    private void output_text_tabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_output_text_tabKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_output_text_tabKeyPressed

    private void tabbed_paneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabbed_paneKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_tabbed_paneKeyPressed

    private void font_styleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_font_styleKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }// TODO add your handling code here:
    }//GEN-LAST:event_font_styleKeyPressed

    private void font_sizeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_font_sizeKeyPressed
        System.out.println("Here");
        int k = evt.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this); 
        }// TODO add your handling code here:
    }//GEN-LAST:event_font_sizeKeyPressed

    private void code_editorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_code_editorKeyReleased
try {

            dout.writeInt(0);
            dout.writeUTF(code_editor.getText());
            dout.flush();
            System.out.println("Code sent successfully");
        } catch (Exception e) {
            System.out.println(e + "exception occured");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_code_editorKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void FindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FindActionPerformed
        try {
            // TODO add your handling code here:
            find=new Find();
            find.set(this);
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.setVisible(false);
        find.setVisible(true);
    }//GEN-LAST:event_FindActionPerformed

    private void ReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReplaceActionPerformed
        // TODO add your handling code here:
        replace=new Replace();
        replace.set(this);
        replace.setVisible(true);
    }//GEN-LAST:event_ReplaceActionPerformed

    private void code_editorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_code_editorMouseClicked
        // TODO add your handling code here:
        Highlighter hilite;
        hilite = code_editor.getHighlighter();
        hilite.removeAllHighlights();
    }//GEN-LAST:event_code_editorMouseClicked

    private void StudentNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StudentNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StudentNameActionPerformed
    public void func() throws IOException {
        try {
            dis = new DataInputStream(s.getInputStream());
            while (true) {
                str = (String) dis.readUTF();
                int area = dis.readInt();
                if (area == 0) {
                    code_editor.setText(str);
                } else if (area == 1) {
                    message_display_area.setText(message_display_area.getText() + "\n" + str);
                } else if (area == 2) {
                    Question.setText(str);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Screen scr = new Screen();
                    scr.setVisible(true);
                    device.setFullScreenWindow(scr);
                } catch (IOException ex) {
                    Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Find;
    private javax.swing.JTextField Message;
    private javax.swing.JTextField Question;
    private javax.swing.JMenuItem Replace;
    private javax.swing.JTextField StudentName;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JEditorPane code_editor;
    private javax.swing.JComboBox<String> font_size;
    private javax.swing.JComboBox<String> font_style;
    private javax.swing.JTextArea input_text_tab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane message_display_area;
    private javax.swing.JTextArea output_text_tab;
    private javax.swing.JButton send;
    private javax.swing.JTabbedPane tabbed_pane;
    // End of variables declaration//GEN-END:variables
    private JTextArea lines;
    
    private class Mthread extends Thread {

        @Override
        public void run() {
            try {
                func();
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private class Ssthread extends Thread {

        @Override
        public void run() {
            try {
                sendScreenshot();
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (AWTException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        System.out.println("Key Typed!!");

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        System.out.println("Here");
        int k = e.getKeyCode();
        System.out.println(k);
        System.out.println(VK_ESCAPE);
        if (k == VK_ESCAPE) {
            device.setFullScreenWindow(null);
            new Ssthread().start();
        }
        if (k == 122) {
            device.setFullScreenWindow(this);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
        System.out.println("Key released");
    }

}
