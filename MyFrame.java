import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener{

	Draw drawing;

	public MyFrame(){
		this.drawing = new Draw();
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W){
			drawing.player.jump(); 
			System.out.println("pos: " + drawing.player.x + ", " + drawing.player.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			drawing.player.moveRight();
			System.out.println("pos: " + drawing.player.x + ", " + drawing.player.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			drawing.player.slide();
			System.out.println("pos: " + drawing.player.x + ", " + drawing.player.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			drawing.player.moveLeft();
			System.out.println("pos: " + drawing.player.x + ", " + drawing.player.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			drawing.player.attack();
			System.out.println("attack");
		}
		else if(e.getKeyCode() == KeyEvent.VK_I){
			drawing.spawnEnemy();
			System.out.println("Spawn");
		}
	}

	public void keyReleased(KeyEvent e){

		if(e.getKeyCode() == KeyEvent.VK_W){
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			drawing.player.idle();
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			drawing.player.idle();
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			drawing.player.idle();
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			drawing.player.idle();
		}
	}

	public void keyTyped(KeyEvent e){
		
	}

	public static void main(String args[]){
		MyFrame gameFrame = new MyFrame();
		gameFrame.setSize(600,600);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.getContentPane().add(gameFrame.drawing);
		gameFrame.addKeyListener(gameFrame);
		gameFrame.setResizable(false);
		System.out.println("practical programming");
	}
}