import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JComponent;
import java.awt.Rectangle;

public class Monster{
	
	public int xPos = 150;
	public int yPos = 300;
	public int width = 0;
	public int height = 0;
	public int life = 20;
	public boolean idle = true;
	public boolean alive = true;
	public boolean contact = false;

	public BufferedImage image;
	public URL resource = getClass().getResource("slime/idle0.png");

	public boolean right = false;

	public Monster(Draw comp){
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		animate(comp);
	}

	public Monster(int xPass, int yPass, Draw comp){
		xPos = xPass;
		yPos = yPass;

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth();

		animate(comp);
	}

	public Rectangle monsterBounds(){
		return(new Rectangle (xPos, yPos, width, height));

	}

	public void animate(Draw compPass){
		Thread monThread = new Thread(new Runnable(){
			public void run(){
				while(idle){
					for(int ctr = 0; ctr < 5; ctr++){
						try {
							if(right == true){
							if(ctr == 4){
								resource = getClass().getResource("slime/idle0.png");
							}
							else if(ctr > 4){
								resource = getClass().getResource("slime/idle"+ctr+".png");
								ctr = 0;
							}
							}else{
							if(ctr == 4){
								resource = getClass().getResource("slime/idleback0.png");
							}
							else if(ctr > 4){
								resource = getClass().getResource("slime/idleback"+ctr+".png");
								ctr = 0;
							}
							}
							try{
								image = ImageIO.read(resource);
							}
							catch(IOException e){	
								e.printStackTrace();
							}

					        compPass.repaint();
					        Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if(life<=0){
						die(compPass);
					}
				}
			}	
		});
		monThread.start();
	}

	public void moveTo(int toX, int toY){
		if(xPos<toX){
			right = false;
			xPos++;
		}
		else if(xPos>toX){
			right = true;
			xPos--;
		}

		if(yPos<toY){
			yPos++;
		}
		else if(yPos>toY){
			yPos--;
		}
	}

	public void die(Draw compPass){
		idle = false;
		if(alive){
			Thread monThread = new Thread(new Runnable(){
				public void run(){
					for(int ctr = 0; ctr < 4; ctr++){
						try {
							if(right == true){
									resource = getClass().getResource("slime/die"+ctr+".png");
							}
							else{
									resource = getClass().getResource("slime/dieback"+ctr+".png");
							}
							try{
								image = ImageIO.read(resource);
							}
							catch(IOException e){
								e.printStackTrace();
							}
					        compPass.repaint();
					        Thread.sleep(1000/30);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			monThread.start();
		}
		alive = false;
	}
}