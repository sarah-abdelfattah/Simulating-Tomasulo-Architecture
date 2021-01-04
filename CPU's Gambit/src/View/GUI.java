package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class GUI extends JFrame implements WindowListener, ActionListener{
	JPanel allSections,rightSection,upperSection, lowerSection, centerSection;
	JTextArea cycles;


	//colors
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);



	public GUI() {
		this.pack();
		this.setVisible(true);
		this.setSize(1200, 1200);
		this.setTitle("CPU's Gambit");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());


		/* ---------- ALL --------------*/
		allSections = new JPanel();
		allSections.setVisible(true);
		allSections.setLayout(new BorderLayout());
		this.add(allSections, BorderLayout.CENTER );	//added to the general container 



		/* ---------- Upper Part --------------*/
		upperSection = new JPanel();
		upperSection.setVisible(true);
		upperSection.setBackground(Color.blue);
		upperSection.setPreferredSize(new Dimension((int) this.getSize().getWidth(), 80));

		JLabel CCTitle = new JLabel();
		CCTitle.setText("Clock Cycles");
		upperSection.add(CCTitle);


		allSections.add(upperSection, BorderLayout.PAGE_START);



		/* ---------- Center Part --------------*/
		centerSection = new JPanel();
		centerSection.setVisible(true);
		centerSection.setBackground(Color.white);
		centerSection.setPreferredSize(new Dimension(300,(int) this.getSize().getHeight()));
		centerSection.setLayout(new GridLayout(2,1)); //2 items so far (RegFile, IEW table)
		
		centerSection.add(new RegFilePanel());


		allSections.add(centerSection, BorderLayout.CENTER);



		/* ---------- Left Part --------------*/
		allSections.add(new IUPanel(), BorderLayout.LINE_START);


		/* ---------- Right Part --------------*/
		allSections.add(new RSPanel(), BorderLayout.LINE_END);




		/* ---------- Lower Part --------------*/
		lowerSection = new JPanel();
		lowerSection.setVisible(true);
		lowerSection.setBackground(Color.blue);
		lowerSection.setPreferredSize(new Dimension((int) this.getSize().getWidth(), 80));

		JLabel NextTitle = new JLabel();
		NextTitle.setText("NEXT");
		lowerSection.add(NextTitle);


		allSections.add(lowerSection, BorderLayout.PAGE_END);
		
		
		
		
		this.pack();
	}


	private Object[][] IUfillGUI() {
		Object[][] data = new Object[32][2] ;
//		

		return data;
		//				{
		//			{"1","MUL F3,F1,F2"},
		//			{"2","ADD F5,F3,F4"},
		//			{"3","ADD F7,F2,F6"},
		//			{"4","ADD F7,F2,F6"},
		//			{"5","ADD F7,F2,F6"},
		//			{"6","ADD F7,F2,F6" },

	}

	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
