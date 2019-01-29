import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener{

	Draw drawing;

	public MyFrame(){
		this.drawing = new Draw();
	}

	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_UP){
			drawing.jump();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			drawing.moveRight();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			drawing.slide();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			drawing.moveLeft();
			System.out.println("pos: " + drawing.x + ", " + drawing.y);
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			drawing.attack();
			System.out.println("attack");
		}
	}

	public void keyReleased(KeyEvent e){

		if(e.getKeyCode() == KeyEvent.VK_UP){
			drawing.idle();	
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			drawing.idle();
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			drawing.idle();
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			drawing.idle1();
			repaint();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			drawing.idle();
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
		System.out.println("practical programming");
	}
}