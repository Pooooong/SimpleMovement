import javax.swing.JComponent;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Rectangle;

public class Player{
	
	public BufferedImage image; 
	public URL resource = getClass().getResource("standing/idle0.png");


	// circle's position
	public int x = 5;
	public int y = 530;
	public int height;
	public int width;
	public int gravity = 530;

	public boolean superS = false;
	public boolean runback = false;
	public boolean right = true;
	public boolean isAttacking = false;
	public int state = 0;

	Draw draw;



	public Player(Draw draw){

		this.draw = draw;

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public Player(int x, int y, Draw draw){

		this.x = x;
		this.y = y;

		this.draw = draw;

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}

		height = image.getHeight();
		width = image.getWidth() + 5;
	}

	public Rectangle playerBounds(){
		return(new Rectangle (x, y, width, height));
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
				isAttacking = true;
				for(int ctr = 0; ctr < 11; ctr++){
					try {
						if (right == true){
							resource = getClass().getResource("sword/attack"+ctr+".png");
						}
						else{
							resource = getClass().getResource("sword/attackback"+ctr+".png");
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
						draw.repaint();
				        Thread.sleep(1000/30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				draw.checkCollision();
				isAttacking = false;
				idle();
				
			}
		});
		thread1.start();
	}

	public void jumpAnimation(){
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				for(int ctr =0; ctr < 6; ctr++){
					int dist = 0;

					if(ctr<3){
						dist = -5;
					}
					else{
						dist = 5;
					}

					try {
						if(right == true){
							resource = getClass().getResource("jumping/jump"+ctr+".png");
							y = y + dist;
							x = x + 3;
						}
						else{
							resource = getClass().getResource("jumping/jumpback"+ctr+".png");
							y = y + dist;
							x = x - 3;
						}
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
					    Thread.sleep(1000/30);
					} 
					catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
				idle();
			}
		});

		thread2.start();
	}

	public void slideAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 6; ctr++){
						try {
							if(right == true){
									resource = getClass().getResource("sliding/slide"+ctr+".png");
									x = x + 5;
							}
							else{
									resource = getClass().getResource("sliding/slideback"+ctr+".png");
									x = x - 5;
							}
							try{
								image = ImageIO.read(resource);
							}
							catch(IOException e){
								e.printStackTrace();
							}
					        Thread.sleep(1000/30);
							}catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
		thread1.start();
	}

	public void idle(){
		Thread thread = new Thread( new Runnable(){
			public void run(){
					for(int i = 0; i < 3; i++){
						try {
							if(right == true){
									resource = getClass().getResource("standing/idle"+i+".png");
							}
							else{
									resource = getClass().getResource("standing/idleback"+i+".png");
							}
							try{
								image = ImageIO.read(resource);
								draw.repaint();
					        	Thread.sleep(1000/30);
							}
							catch(IOException e){
								e.printStackTrace();
							}
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
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
		draw.repaint();
		draw.checkCollision();
	}

	public void moveDown(){
		if(y > 490){
		 y = 490;
		}
		y = y + 5;
		draw.repaint();
		draw.checkCollision();
	}

	public void moveLeft(){
		right = false;
		x = x - 5;
		reloadImage1();
		draw.repaint();
		draw.checkCollision();
	}

	public void moveRight(){
		right = true;	
		if(x > 540){
			x = 540;
		}
		x = x + 5;
		reloadImage0();
		draw.repaint();
		draw.checkCollision();
	}
}