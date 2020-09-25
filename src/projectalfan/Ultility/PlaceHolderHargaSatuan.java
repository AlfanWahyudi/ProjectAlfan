/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectalfan.Ultility;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.LEFT;
import javax.swing.text.Document;

/**
 *
 * @author user
 */
public class PlaceHolderHargaSatuan extends JTextField{
    
    
  private String placeholder = "Harga Satuan";
    private Image icon = null;
 
    public PlaceHolderHargaSatuan(){
        initListener();
        setOpaque(false);
        setForeground(Color.BLACK);
        setCaretColor(Color.black);
        setHorizontalAlignment(LEFT);
    }
 
    public PlaceHolderHargaSatuan(String text){
        initListener();
    }
 
    public PlaceHolderHargaSatuan(int columns){
        initListener();
    }
 
    public PlaceHolderHargaSatuan(String text, int columns){
        super(text, columns);
        initListener();
    }
 
    public PlaceHolderHargaSatuan(Document doc, String text, int columns){
        super(doc, text, columns);
        initListener();
    }
 
    private void initListener(){
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                super.keyPressed(e);
                repaint();
            }
        });
    }
 
    public String getPlaceholder(){
        return placeholder;
    }
 
    public void setPlaceholder(String placeholder){
        this.placeholder = placeholder;
        repaint();
    }
 
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        super.paint(g2);
        g2.dispose();
    }
 
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g2);
        g2.dispose();
        super.paintComponent(g);
        if (getText().trim().equals("")){
            Font font = getFont().deriveFont(Font.PLAIN).deriveFont(Font.PLAIN);
            Graphics2D g2d = (Graphics2D) g;
            FontMetrics fontMetrics = g2d.getFontMetrics(font);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.GRAY);
            g2d.setFont(font);
            java.awt.geom.Rectangle2D rect = fontMetrics.getStringBounds(placeholder, g2d);
            int textHeight = (int) rect.getHeight();
            g2d.drawString(placeholder, 1, textHeight + 3);
        }
    }
}
