import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Arrays;
//import java.awt.Dimension;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

class GameView{
	private JPanel panel;
	private JFrame frame;
	private JSlider slider;//
	private BasicArrowButton nextRound;
	private BasicArrowButton lastRound;
	private JButton pause;
	private JComboBox preset;
	private JLabel round;
	private Timer timer;
	private String[] options = {"Clear","Repeating Bombs","SpaceShip","Random"};
	private boolean timerOn = false;
	private ActionListener actionListener;
	private JTextField currentBoardX;
	private JTextField currentBoardY;
	//dont forget to do .setEditable = true;
	private BasicArrowButton xIncr;
	private BasicArrowButton xDecr;
	private BasicArrowButton yIncr;
	private BasicArrowButton yDecr;
	
	public GameView() {
		frame = new JFrame();
		frame.setSize(1000,1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridBagLayout());
		panel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		c.weightx = 1.0;
		//c.weighty = 0.5;
		
		
		preset = new JComboBox(options);
		preset.setSelectedIndex(0);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		frame.add(preset,c);

		slider = new JSlider(0,30,0);
		slider.setPaintLabels(true);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(6);
		slider.setMinorTickSpacing(3);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 2;
		frame.add(slider,c);
		
		lastRound = new BasicArrowButton(SwingConstants.WEST);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 2;
		frame.add(lastRound,c);
		
		pause = new JButton("||");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 3;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 2;
		frame.add(pause,c);
		
		nextRound = new BasicArrowButton(SwingConstants.EAST);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 2;
		frame.add(nextRound,c);
		
		round = new JLabel("0");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 6;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		frame.add(round,c);
		
		panel = new MyPanel();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 7;
		c.gridheight = 14;
		c.weightx = 1;
		c.weighty = 1;
		frame.add(panel,c);
		
		frame.setVisible(true);
		actionListener = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				panel.repaint();
				System.out.println("1");
			}
		};
		timer = new Timer(1000,actionListener);
	}
	
	

	
	
	//GETTER METHODS
	public ActionListener getActionListener() {
		return actionListener;
	}
	public boolean getTimerOn() {
		return timerOn;
	}
	public Timer getTimer() {
		return timer;
	}
	public JButton getPauseButton() {
		return pause;
	}
	public JButton getNextRoundButton() {
		return nextRound;
		//what if I did public JButton getNextRoundButton?
	}
	public JButton getLastRoundButton() {
		return lastRound;
	}
	public JSlider getSlider() {
		return slider;
	}
	public JComboBox getPresets() {
		return preset;
	}
	public MyPanel getMyPanel(){
		return (MyPanel) panel;
	}
	public int getOptionsLength() {
		return options.length;
	}
	public JButton getXIncr() {
		return xIncr;
	}
	public JButton getXDecr() {
		return xDecr;
	}
	public JButton getYIncr() {
		return yIncr;
	}
	public JButton getYDecr() {
		return yDecr;
	}
	
	//SETTER METHODS
	public void setXSize(int boardSizeX) {
		currentBoardX.setText(String.valueOf(boardSizeX));
	}
	public void setYSize(int boardSizeY) {
		currentBoardY.setText(String.valueOf(boardSizeY));
	}
	public void setRound(String string) {
		round.setText(string);
	}
	public void setSpeed(int speed) {
		if(speed==0) {
			timer.stop();
			//timerOn = false;
		}else {
			if(timerOn) {
				timer.start();
			}
			timer.setDelay(1000/speed);
		}
	}
	public void setRound(int roundNum) {
		round.setText(String.valueOf(roundNum));
	}
	public void pausePlay() {
		if(timerOn) {
			timer.stop();
		}else {
			timer.start();
		}
		timerOn = !timerOn;
	}
	
	class MyPanel extends JPanel{
		private int boardSizeX = 15;
		
		private int boardSizeY = 15;
		private boolean[][] board  = new boolean[15][15];
		public void prepareForPainting(boolean[][] board) {
			//panelSizeX = panel.getSize().width;
			//panelSizeY = panel.getSize().height;
			this.boardSizeX = board[0].length;
			this.boardSizeY = board.length;
			this.board = new boolean[boardSizeY][boardSizeX];
			//System.out.println(boardSizeX + " " + boardSizeY);
			System.out.println(board[0].length + " " + board.length);
			/*for(int i = 0; i<boardSizeY;i++) {
				for(int j = 0; j<boardSizeX;j++) {
					this.board[i][j] = board[i][j];
				}
			}*/
			this.board = board;
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			System.out.println("on");
			int xInc = (int)panel.getSize().getWidth()/boardSizeX ;
			int yInc = (int)panel.getSize().getHeight()/boardSizeY;
			//System.out.println(xInc + " " + yInc + " " + boardSizeX + " " + boardSizeY);
			g.setColor(Color.black);
			for(int i = 0; i<boardSizeY; i++) {
				for(int v = 0; v<boardSizeX; v++) {
					//System.out.println()
					if(board[i][v]) {
						g.fillRect(v*xInc, (i*yInc), xInc, yInc);
						//System.out.println("works");
					}
				}
			}
		}
	}
	
}
	

