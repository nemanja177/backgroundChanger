package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;



public class SlikeMain {
	
	private static JFrame imageFrame = new JFrame();
	private static int backgroundIndex;
	private static int imageAngle;
		
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
	    JButton izaberiSliku = new JButton("Izaberi sliku");
	    JButton promeniKrugove = new JButton("Promeni Boju Oblika");
	    
	    JTextField txtInput = new JTextField("");
	    JTextField imageXInput = new JTextField("230");
	    JTextField imageYInput = new JTextField("175");
	    JTextField backgroundXInput = new JTextField("450");
	    JTextField backgroundYInput = new JTextField("450");
	    
	    JTextField brojKrugovaTxt = new JTextField("5");
	    JLabel brojKrugovaLbl = new JLabel("Broj Krugova");
	    
	    JLabel errorMsg = new JLabel();
	    JLabel dimensions = new JLabel("Dimenzije Slike");
	    JLabel imageXLabel = new JLabel("Sirina");
	    JLabel imageYLabel = new JLabel("Visina");
	    JLabel dimenzijePozadine = new JLabel("Dimenzije Pozadine");
	    JLabel backgroundXLabel = new JLabel("Sirina");
	    JLabel backgroundYLabel = new JLabel("Visina");
	    txtInput.setPreferredSize( new Dimension( 200, 24 ) );
	    imageXInput.setPreferredSize( new Dimension( 200, 24 ) );
	    backgroundXLabel.setPreferredSize( new Dimension( 200, 24 ) );
	    imageXInput.setPreferredSize( new Dimension( 200, 24 ) );
	    backgroundYLabel.setPreferredSize( new Dimension( 200, 24 ) );
	    JPanel panel = new JPanel();
//	    panel.setBounds(0, 0, 300, 300);
	    JPanel dimensionsPanel = new JPanel();
	    JPanel circlesPanel = new JPanel();
	    circlesPanel.setSize(new Dimension(30, 60));
	    dimensionsPanel.setLayout(new GridLayout(5,2, 10, 0));
//	    panel.setSize(150, 150);
//	    dimensionsPanel.setSize(100, 100);
//	    dimensionsPanel.setBounds(300, 300, 300, 300);
//	    f.setSize(1000, 750);
	    panel.add(button);
	    panel.add(izaberiSliku);
	    panel.add(txtInput);
	    panel.add(errorMsg);
	 
	    circlesPanel.add(promeniKrugove);
	    brojKrugovaTxt.setPreferredSize(new Dimension(50, 20));
	    panel.add(brojKrugovaLbl);
	    panel.add(brojKrugovaTxt);
	    
	    
	    dimensionsPanel.add(dimensions);
//	    dimensionsPanel.add(imageXLabel);
	    dimensionsPanel.add(imageXInput);
//	    dimensionsPanel.add(imageYLabel);
	    dimensionsPanel.add(imageYInput);
//	    dimensionsPanel.add(dimenzijePozadine);
//	    dimensionsPanel.add(backgroundXLabel);
//	    dimensionsPanel.add(backgroundXInput);
//	    dimensionsPanel.add(backgroundYLabel);
//	    dimensionsPanel.add(backgroundYInput);
	    
	    //String urlImage = baseURL + txtInput.getText();
	    
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					makeImage(txtInput.getText(), Integer.parseInt(imageXInput.getText()), Integer.parseInt(imageYInput.getText()), Integer.parseInt(brojKrugovaTxt.getText()), false);
					errorMsg.setText("");
				} catch (IOException e1) {
					errorMsg.setText("Slika ne postoji!");
				}
			}
		});
        
        promeniKrugove.addActionListener(new ActionListener() {
			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				try {
    					makeImage(txtInput.getText(), Integer.parseInt(imageXInput.getText()), Integer.parseInt(imageYInput.getText()), Integer.parseInt(brojKrugovaTxt.getText()), true);
    					errorMsg.setText("");
    				} catch (IOException e1) {
    					errorMsg.setText("Slika ne postoji!");
    				}
    			}
    		});
        
        
        izaberiSliku.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            
              JFileChooser file = new JFileChooser();
              file.setCurrentDirectory(new File(System.getProperty("user.home")));
              FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
              file.addChoosableFileFilter(filter);
              int result = file.showSaveDialog(null);
              if(result == JFileChooser.APPROVE_OPTION){
                  File selectedFile = file.getSelectedFile();
                  String path = selectedFile.getAbsolutePath();
                  txtInput.setText(path);
//                  label.setIcon(ResizeImage(path));
              }
              else if(result == JFileChooser.CANCEL_OPTION){
                  System.out.println("No File Select");
              }
            }
        });
        
//        f.pack();
//        f.setSize(1000, 750);
        button.setPreferredSize(new Dimension(130, 30));
        f.add(panel);
        f.add(circlesPanel);
        f.add(dimensionsPanel);
//        button.setBounds(803,303,1203,430);
        f.setSize(300, 300);
        f.pack();
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setVisible(true);
        
	  }
	
	  public static void makeImage(String url, int width, int height, int numberOfCircles, boolean isCirclesOnly) throws IOException {
		  String baseURL = "proizvodi\\";
		    // String urlImage = baseURL + imgName;
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
////			  System.out.prin)tln(imageName);
////		  }
//		  String backgroundImage = "backgrounds\\" + imageNames[index];
//		  System.out.println(backgroundImage);
		  
		  
		  Random rand = new Random();
		  String[] imageNames = file.list();
		  int index;
		  if ( imageNames.length != 0 && !isCirclesOnly ) {
			  backgroundIndex = rand.nextInt(imageNames.length);
		  } else {
			  index = 1;
		  }
		  
		  System.out.println(imageNames);
		  String backgroundImage = "backgrounds\\" + imageNames[backgroundIndex];
		  
		  BufferedImage im = ImageIO.read(new File(backgroundImage));
//		  ImageIcon ii = new ImageIcon(backgroundImage);
		  BufferedImage newBackground = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		  BufferedImage im2 = ImageIO.read(new File(url));
//		  JLabel l = new JLabel(new ImageIcon(im2));
		  int x = newBackground.getWidth();
		  int y = newBackground.getHeight();
		  System.out.println(x);
		  System.out.println(y);
		  System.out.println(im2.getWidth());
		  float ratio = (float)im2.getWidth() / (float)im2.getHeight();
		 
		  System.out.println("Old Height: " + height);
		  double newHeight = width / ratio;
		  System.out.println("Width: " + width + " Height: " + newHeight + " Ratio: " + ratio);
		  im.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		  Graphics2D g = newBackground.createGraphics();
		  
		 
		  int randomBojaR = rand.nextInt(255);
		  int randomBojaG = rand.nextInt(255);
		  int randomBojaB = rand.nextInt(255);
		  if (!isCirclesOnly) {
			  imageAngle = rand.nextInt(20) - 10;
		  }
//		  int randomAngle = rand.nextInt(20) - 10;
		  RenderingHints hints = new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		  g.setRenderingHints(hints);
		  g.drawImage(im, 0, 0, 500, 500, null);
		  g.setColor(new Color(randomBojaR, randomBojaG, randomBojaB, 90));
		  for ( int i = 0; i < numberOfCircles; i++) {
			  int randomX = rand.nextInt(x);
			  int randomY = rand.nextInt(y); 
			  g.fillOval(randomX, randomX - randomY, (int)(Math.random()*(400 - 200 + 1) + 200), (int)(Math.random()*(400 - 200 + 1) + 200));
			  System.out.println((int)(Math.random()*(400 - 200 + 1) + 200));
		  }
		  
//		  g.fillOval(x, y, width, height);
			 g.rotate(Math.toRadians(imageAngle), im2.getWidth() / 2, im2.getHeight() / 2);
		  
//		    g.scale(0.5, 0.5);
		    //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f)); ((x / 2) - (im2.getWidth() / 2)), ((y / 2) - (im2.getHeight() / 2))
//		  int x1 = x - ( 400 / 2 );
//		  int y1 = y - ( 400 / 2 );
		  
		  g.drawImage(im2, ((x / 2) - (width / 2 )), ((y / 2) - ((int)newHeight / 2)), width, (int)newHeight, null);
		  g.dispose();
		  System.out.println("Im sirina slike - " + im.getWidth());

		  imageFrame.dispose();
		  display(newBackground);
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
