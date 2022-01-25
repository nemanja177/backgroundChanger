package main;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SlikeMain {
	
	private static JFrame imageFrame = new JFrame();
		
	public static void main(String[] args) throws IOException {
		//String baseURL = "slike\\";
		
		File file = new File("backgrounds");
		File slike = new File("images");
		File proizvodi = new File("products");
		if ( !file.exists() ) {
			new File("backgrounds").mkdirs();
		}
		if ( !slike.exists() ) {
			new File("slike").mkdirs();
		}
		if ( !proizvodi.exists() ) {
			new File("proizvodi").mkdir();
		}
		
		
	    JFrame f = new JFrame();
	    f.setLayout(new GridLayout(2,2));
	    JButton button = new JButton("Generisi Sliku");
	    JTextField txtInput = new JTextField("projektor.png");
	    JTextField imageXInput = new JTextField("600");
	    JTextField imageYInput = new JTextField("400");
	    JLabel errorMsg = new JLabel();
	    JLabel dimensions = new JLabel("Dimenzije");
	    JLabel imageXLabel = new JLabel("Sirina");
	    JLabel imageYLabel = new JLabel("Visina");
	    txtInput.setPreferredSize( new Dimension( 200, 24 ) );
	    imageXInput.setPreferredSize( new Dimension( 200, 24 ) );
	    imageYInput.setPreferredSize( new Dimension( 200, 24 ) );
	    JPanel panel = new JPanel();
//	    panel.setBounds(0, 0, 300, 300);
	    JPanel dimensionsPanel = new JPanel();
	    dimensionsPanel.setLayout(new GridLayout(5,2, 10, 0));
//	    panel.setSize(150, 150);
//	    dimensionsPanel.setSize(100, 100);
//	    dimensionsPanel.setBounds(300, 300, 300, 300);
//	    f.setSize(1000, 750);
	    panel.add(button);
	    panel.add(txtInput);
	    panel.add(errorMsg);
	    dimensionsPanel.add(dimensions);
	    dimensionsPanel.add(imageXLabel);
	    dimensionsPanel.add(imageXInput);
	    dimensionsPanel.add(imageYLabel);
	    dimensionsPanel.add(imageYInput);
	    //String urlImage = baseURL + txtInput.getText();
	    
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					makeImage(txtInput.getText(), Integer.parseInt(imageXInput.getText()), Integer.parseInt(imageYInput.getText()));
					errorMsg.setText("");
				} catch (IOException e1) {
					errorMsg.setText("Slika ne postoji!");
				}
			}
		});
//        f.pack();
//        f.setSize(1000, 750);
        button.setPreferredSize(new Dimension(130, 30));
        f.add(panel);
        f.add(dimensionsPanel);
//        button.setBounds(803,303,1203,430);
        f.setSize(300, 300);
//        f.pack();
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
        
	  }
	
	  public static void makeImage(String imgName, int width, int height) throws IOException {
		  String baseURL = "proizvodi\\";
		  String urlImage = baseURL + imgName;
//		  Random rand = rand = new Random();
//		  System.out.println(urlImage);
//		  //
		  File file = new File("backgrounds");
		  //File slike = new File("images");
//		  if ( !file.exists() ) {
//			  new File("backgrounds").mkdirs();
//		  }
//		  if ( !slike.exists() ) {
//			  new File("slike").mkdirs();
//		  }
//		  String[] imageNames = file.list(); 
//		  int index = rand.nextInt(imageNames.length);
//		  System.out.println(imageNames);
////		  for ( String imageName : imageNames ) {
////			  System.out.println(imageName);
////		  }
//		  String backgroundImage = "backgrounds\\" + imageNames[index];
//		  System.out.println(backgroundImage);
		  
		  Random rand = new Random();
		  //Random randAngle = new Random();
		  String[] imageNames = file.list();
		  int index;
		  if ( imageNames.length != 0 ) {
			  index = rand.nextInt(imageNames.length);
		  } else {
			  index = 1;
		  }
		  
		  System.out.println(imageNames);
		  String backgroundImage = "backgrounds\\" + imageNames[index];
		  
		  BufferedImage im = ImageIO.read(new File(backgroundImage));
		  BufferedImage im2 = ImageIO.read(new File(urlImage));
		  JLabel l = new JLabel(new ImageIcon(im2));
		  int x = im.getWidth();
		  int y = im.getHeight();
		  System.out.println(x);
		  System.out.println(y);
		  System.out.println(im2.getWidth());
		  Graphics2D g = im.createGraphics();
		  int randomAngle = rand.nextInt(20) - 10;
		  RenderingHints hints = new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		  g.rotate(Math.toRadians(randomAngle), im2.getWidth() / 2, im2.getHeight() / 2);
//		    g.scale(0.5, 0.5);
		    //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f)); ((x / 2) - (im2.getWidth() / 2)), ((y / 2) - (im2.getHeight() / 2))
		  g.setRenderingHints(hints);
		  g.drawImage(im2, ((x / 2) - (width / 2 )), ((y / 2) - (height / 2)), width, height, null);
		  g.dispose();
		  System.out.println(im2.getWidth());

		  imageFrame.dispose();
		  display(im);
//		  ImageIO.write(im, "jpeg", new File("C:\\Users\\Nemanja\\Desktop\\output.jpeg"));
	  }

	  public static void display(BufferedImage image) {
	    JFrame f = new JFrame();
	    f.setSize(1000, image.getHeight() + 100);
	    f.setLocationRelativeTo(null);
	    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JLabel label = new JLabel(new ImageIcon(image));
	    JLabel msg = new JLabel();
	    JPanel panel = new JPanel();
	    JTextField txtInput = new JTextField("imeSlike");
	    txtInput.setPreferredSize( new Dimension( 200, 24 ) );
	    panel.setBounds(0, 0, 0, 0);
	    JButton button = new JButton("Preuzmi Sliku");
	    
	    button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(image, "png", new File("slike\\" + txtInput.getText() + ".png"));
					msg.setText("Slika uspesno sacuvana.");
				} catch (IOException e1) {
					msg.setText("Greska prilikom cuvanja slike.");
				}
			}
		});
	    
	    label.setBounds(0, 0, image.getWidth(), image.getHeight());
	    panel.add(label);
	    panel.add(button);
	    panel.add(txtInput);
	    panel.add(msg);
	    button.setBounds(0, 0, 50, 0);
	    f.add(panel);
//	    f.pack();
	    f.setVisible(true);
	    imageFrame = f;
	  }

}
