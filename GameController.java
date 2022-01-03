
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class GameController{
	private GameModel m;
	private GameView v;
	
	public GameController(GameModel model, GameView view) {
		m = model;
		v = view;


	}
	
	public void doStuff(){
		//startUp
		
		//setsBoardToRandom and initializes the main timer
		m.resetBoard(1);
		v.getTimer().removeActionListener(v.getActionListener());
		v.getTimer().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.nextRound();
				v.getMyPanel().prepareForPainting(m.getBoard());
				v.getMyPanel().repaint();
				System.out.println("10");
				v.setRound(m.getRound());
			}
		});
		//makes the time between pause and play 0
		v.getTimer().setInitialDelay(0);
		
		//sets up the next round button
		v.getNextRoundButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.getTimerOn()) {
					v.pausePlay();
				}
				m.nextRound();
				v.getMyPanel().prepareForPainting(m.getBoard());
				v.getMyPanel().repaint();
				v.setRound(m.getRound());
			}
		});
		
		//sets up the last round button
		v.getLastRoundButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(v.getTimerOn()) {
					v.pausePlay();
				}
				m.lastRound();
				v.getMyPanel().prepareForPainting(m.getBoard());
				v.getMyPanel().repaint();
				v.setRound(m.getRound());
			
			}
		});
		
		//sets up the pause/play button
		v.getPauseButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				v.pausePlay();
			}
		});
		
		//sets up the slider
		v.getSlider().addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int value = v.getSlider().getValue();
				v.setSpeed(value);
			}
		});
		
		//sets up the presets options box
		v.getPresets().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.resetBoard(v.getPresets().getSelectedIndex());
				v.getMyPanel().prepareForPainting(m.getBoard());
				v.getMyPanel().repaint();
			}
		});
		
		//sets up the panel for manual changing
		v.getMyPanel().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				m.changeBoard(e.getX()/((int)v.getMyPanel().getSize().getWidth()/m.getBoardSizeX()),e.getY()/((int)v.getMyPanel().getSize().getHeight()/m.getBoardSizeY()));
				v.getMyPanel().prepareForPainting(m.getBoard());
				v.getMyPanel().repaint();
			}
			
		});
		
		/*v.getXIncr().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setBoardSizeX(m.getBoardSizeX()+1);
				v.setXSize(m.getBoardSizeX());
			}
		});
		
		v.getXDecr().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setBoardSizeX(m.getBoardSizeX()-1);
				v.setXSize(m.getBoardSizeX());
			}
		});
		
		v.getYIncr().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setBoardSizeY(m.getBoardSizeY()+1);
				v.setYSize(m.getBoardSizeY());
			}
		});
		
		v.getYDecr().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setBoardSizeY(m.getBoardSizeY()-1);
				v.setYSize(m.getBoardSizeY());
			}
		});*/
		
	}
	
}
