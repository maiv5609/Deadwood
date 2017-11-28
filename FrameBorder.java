import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/* There are probably going to be multiple classes for certain 
 * types of UI elements
 */

/* WORKNOTE: Need to implement some sort of text box that gives feedback to the user
 * 
 */

class FrameBorder extends JFrame{
	
	public FrameBorder(){
		setTitle(Constants.DEADWOOD);
		
		JPanel main = new JPanel();
		//Might want to change this to a grid layout
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		add(main);
		
		main.add(Box.createVerticalGlue());
		
		
		JPanel bottom = new JPanel();
		//right align bottom panel
		bottom.setAlignmentX(1f);
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
		bottom.setAlignmentY(CENTER_ALIGNMENT);
		
		JButton Move = new JButton(Constants.MOVE);
		JButton Work = new JButton(Constants.WORK);
		JButton Upgrade = new JButton(Constants.UPGRADE);
		JButton Rehearse = new JButton(Constants.REHEARSE);
		JButton Act = new JButton(Constants.ACT);
		JButton Who = new JButton(Constants.WHO);
		JButton Where = new JButton(Constants.WHERE);
		JButton End = new JButton(Constants.END_TURN);
		
		JLabel testLabel = new JLabel("test label");
		testLabel.setVerticalTextPosition(JLabel.TOP);
		testLabel.setHorizontalTextPosition(JLabel.LEFT);
		//bottom.add(testLabel); Uncomment this to all the above label to the bottom row
		
		/* Add action listeners to buttons
		 * 
		 */
		bottom.add(Move);
		bottom.add(Box.createRigidArea(new Dimension(5, 0)));
		Move.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.handleUserInput(e);
				JTextField field = new JTextField();
				field.setBounds(30, 50, 150, 25);
				add(field);
				//add(field);
				//Need to figure out how to call action here
				//Bring up text box so that user can input room
				//Should probably also just display adjacent rooms
			}
		});
		
		bottom.add(Work);
		bottom.add(Box.createRigidArea(new Dimension(5, 0)));
		Work.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//Need to figure out how to call action here
				Game.handleUserInput(e);
			}
		});
		
		bottom.add(Upgrade);
		bottom.add(Box.createRigidArea(new Dimension(5, 0)));
		Upgrade.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.handleUserInput(e);
				//Need to figure out how to call action here
				//Bring up text box to tell them to input what rank they would like
			}
		});
		
		bottom.add(Rehearse);
		bottom.add(Box.createRigidArea(new Dimension(5, 0)));
		Rehearse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.handleUserInput(e);
				//Need to figure out how to call action here
			}
		});
		
		bottom.add(Act);
		bottom.add(Box.createRigidArea(new Dimension(5, 0)));
		Act.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.handleUserInput(e);
				//Need to figure out how to call action here
			}
		});
		
		bottom.add(Who);
		bottom.add(Box.createRigidArea(new Dimension(5, 0)));
		Who.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.handleUserInput(e);
				//Need to figure out how to call action here
			}
		});
		
		bottom.add(Where);
		bottom.add(Box.createRigidArea(new Dimension(15, 0)));
		Where.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.handleUserInput(e);
				//Need to figure out how to call action here
			}
		});
		
		bottom.add(End);
		bottom.add(Box.createRigidArea(new Dimension(15, 0)));
		End.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Game.handleUserInput(e);
				//Need to figure out how to call action here
			}
		});
		
		main.add(bottom);
		main.add(Box.createRigidArea(new Dimension(0, 15)));
		
		setSize(600, 250);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setVisible(true);
	}
	
	
	
}
