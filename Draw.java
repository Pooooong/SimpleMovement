import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;


public class Draw extends JComponent{

	private BufferedImage image; 
	private BufferedImage backgroundImage;
	public URL resource = getClass().getResource("standing/idle0.png");
	public URL Standing;

	// circle's position
	public int x = 5;
	public int y = 510;

	// animation states
	public int state = 0;

	public boolean runback = false;
	boolean jumping = false;
	boolean falling = false;
	public Draw(){
		try{
			image = ImageIO.read(resource);
			backgroundImage = ImageIO.read(getClass().getResource("bg/background.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void reloadImage0(){
		state++;
		runback = false;
		if(state == 0){
			resource = getClass().getResource("running/run0.png");
		}
		else if(state == 1){
			resource = getClass().getResource("running/run1.png");
		}
		else if(state == 2){
			resource = getClass().getResource("running/run2.png");
		}
		else if(state == 3){
			resource = getClass().getResource("running/run3.png");
		}
		else if(state == 4){
			resource = getClass().getResource("running/run4.png");
		}
		else if(state == 5){
			resource = getClass().getResource("running/run5.png");
			state = 0;
		}

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void reloadImage1(){
		state++;
		runback = true;
		if(state == 0){
			resource = getClass().getResource("running/runback0.png");
		}
		else if(state == 1){
			resource = getClass().getResource("running/runback1.png");
		}
		else if(state == 2){
			resource = getClass().getResource("running/runback2.png");
		}
		else if(state == 3){
			resource = getClass().getResource("running/runback3.png");
		}
		else if(state == 4){
			resource = getClass().getResource("running/runback4.png");
		}
		else if(state == 5){
			resource = getClass().getResource("running/runback5.png");
			state = 0;
		}

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void attackAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 11; ctr++){
					try {
						if(runback != true){
						if(ctr == 11){
							resource = getClass().getResource("standing/idle0.png");
						}
						else{
							resource = getClass().getResource("sword/attack"+ctr+".png");
						}
						}else{
						if(ctr == 11){
							resource = getClass().getResource("standing/idleback0.png");
						}
						else{
							resource = getClass().getResource("sword/attackback"+ctr+".png");
						}
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        idle();
				        Thread.sleep(35);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
	}
	public void jumpAnimation(){
		state = 0;
		jumping = true;
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				while(jumping){
				for(int ctr =0; ctr < 4; ctr++){
					try {
						if(runback != true){
						if(state == ctr){
							resource = getClass().getResource("jumping/jump"+ctr+".png");
							y = y - 4;
							x = x + 1;
							System.out.println(ctr);
							
						}
						else if(state > 5){
							jumping = false;
							state= 0;
							fallAnimation();
							}
						}else{
							if(state == ctr){
							resource = getClass().getResource("jumping/jumpback"+ctr+".png");
							y = y - 4;
							x = x + 1;
							idle1();
							repaint();
							System.out.println(ctr);
							
						}
						else if(state > 5){
							jumping = false;
							state= 0;
							fallAnimation();
							}
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        state++;
				        Thread.sleep(1000/30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		});

		thread2.start();
	}

	public void fallAnimation(){
		state = 0;
		falling = true;
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				while(falling){
				for(int ctr =0; ctr < 1; ctr++){
					try {
						if(runback != true){
						if(state == ctr){

							resource = getClass().getResource("jumping/fall"+ctr+".png");
							y = y + 16;
							x = x + 15;
							System.out.println(ctr);
							
						}
						else if(state > 3){
							resource = getClass().getResource("standing/idle"+ctr+".png");
							falling = false;
							state= 0;
							}
						}else{
							if(state == ctr){

							resource = getClass().getResource("jumping/fallback"+ctr+".png");
							y = y + 16;
							x = x - 15;
							System.out.println(ctr);
							
						}
						else if(state > 3){
							resource = getClass().getResource("standing/idleback"+ctr+".png");
							falling = false;
							state= 0;
							}
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        state++;
				        Thread.sleep(1000/30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		});

		thread2.start();
	}

	public void slideAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 6; ctr++){
					try {
						if(runback != true){
						if(ctr == 6){
							resource = getClass().getResource("standing/idle0.png");
						}
						else{
							resource = getClass().getResource("sliding/slide"+ctr+".png");
							x = x + 10;
						}
						}else{
						if(ctr == 6){
							resource = getClass().getResource("standing/idleback0.png");
						}
						else{
							resource = getClass().getResource("sliding/slideback"+ctr+".png");
							x = x - 10;
						}
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(2000/30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
	}

	public void idle(){
		
		Standing = getClass().getResource("standing/idle0.png");
		try{
			image = ImageIO.read(Standing);

		}catch(IOException e){
		e.printStackTrace();
		}
	}

		public void idle1(){
		
		Standing = getClass().getResource("standing/idleback0.png");
		try{
			image = ImageIO.read(Standing);

		}catch(IOException e){
		e.printStackTrace();
		}
	}

	public void slide(){
		slideAnimation();
	}

	public void jump(){
		jumpAnimation();
	}

	public void attack(){
		attackAnimation();
	}

	public void moveUp(){ 
		if(y < 490) {
		y = 490;	
		}
		y = y - 5;
		idle();
		repaint();
	}

	public void moveDown(){
		if(y > 490){
		 y = 490;
		}
		y = y + 5;
		idle();
		repaint();
	}

	public void moveLeft(){
		x = x - 5;
		reloadImage1();
		repaint();
	}

	public void moveRight(){
		if(x > 540){
			x = 540;
		}
		x = x + 5;
		reloadImage0();
		repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(image, x, y, this);
	}
}