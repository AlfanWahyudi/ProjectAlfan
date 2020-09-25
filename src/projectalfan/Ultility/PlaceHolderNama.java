package projectalfan.Ultility;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;

/**
 */
public class PlaceHolderNama extends JTextField{
    private String placeholder = "Nama";
    private Image icon = null;
 
    public PlaceHolderNama(){
        initListener();
        setOpaque(false);
        setForeground(Color.BLACK);
        setCaretColor(Color.black);
        setHorizontalAlignment(LEFT);
    }
 
    public PlaceHolderNama(String text){
        initListener();
    }
 
    public PlaceHolderNama(int columns){
        initListener();
    }
 
    public PlaceHolderNama(String text, int columns){
        super(text, columns);
        initListener();
    }
 
    public PlaceHolderNama(Document doc, String text, int columns){
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