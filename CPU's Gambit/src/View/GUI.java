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
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import Implementation.*;

public class GUI extends JFrame implements WindowListener, ActionListener{
	JPanel allSections,rightSection,upperSection, lowerSection, centerSection, registers;
	RegFilePanel rfp;
	JButton nextBtn;
	int clickedNext =0;
	boolean done=false;
	String CC;

	//colors
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 


	public GUI() throws IOException {
		Main.init();
		rfp=new RegFilePanel();
		this.setVisible(true);
		this.setSize(2000, 100);
		this.setExtendedState(JFrame.MAXIMIZED_HORIZ);
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
		upperSection.setBackground(lightGray);
		upperSection.setPreferredSize(new Dimension((int) this.getSize().getWidth(), 30));

		CC = "Clock Cycle number: " + clickedNext;

		JLabel clockCycles = new JLabel();
		clockCycles.setText(CC);
		clockCycles.setFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		clockCycles.setForeground(darkBlue);
		upperSection.add(clockCycles);

		allSections.add(upperSection, BorderLayout.PAGE_START);



		/* ---------- Center Part --------------*/
		centerSection = new JPanel();
		centerSection.setVisible(true);
		centerSection.setBackground(Color.white);
		centerSection.setPreferredSize(new Dimension(300,(int) this.getSize().getHeight()));
		centerSection.setLayout(new GridLayout(2,1)); //2 items so far (RegFile, IEW table)



		registers = new JPanel();
		registers.setVisible(true);
		registers.setBackground(lightGray);
		registers.setPreferredSize(new Dimension(300,((int) this.getSize().getHeight())/2-5));
		registers.setLayout(new BorderLayout()); 

		//		registers.add(intReg);
		registers.add(new intRegPanel(), BorderLayout.LINE_START);
		registers.add(rfp, BorderLayout.CENTER);

		centerSection.add(registers);
		centerSection.add(new IEWPanel());
		allSections.add(centerSection, BorderLayout.CENTER);



		/* ---------- Left Part --------------*/
		allSections.add(new IUPanel(), BorderLayout.LINE_START);


		/* ---------- Right Part --------------*/
		allSections.add(new RSPanel(), BorderLayout.LINE_END);




		/* ---------- Lower Part --------------*/
		lowerSection = new JPanel();
		lowerSection.setVisible(true);
		lowerSection.setBackground(lightGray);
		lowerSection.setPreferredSize(new Dimension((int) this.getSize().getWidth(), 30));

		nextBtn = new JButton();
		nextBtn.setText("NEXT");
		nextBtn.setBackground(darkRed);
		nextBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD,16));
		nextBtn.setSize(100,15); //TODO: 
		nextBtn.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int x=Main.nextCycle();

					if(!done) {
						clickedNext++;
						clockCycles.setText("Clock Cycle number: " + clickedNext);
						centerSection.remove(1);
						registers.remove(1);
						centerSection.add(new IEWPanel());
						registers.add(new RegFilePanel());

						allSections.remove(2);
						allSections.remove(3);
						allSections.remove(lowerSection);

						allSections.add(new IUPanel(), BorderLayout.LINE_START);
						allSections.add(new RSPanel(), BorderLayout.LINE_END);
						allSections.add(lowerSection, BorderLayout.PAGE_END);

						if(x==-1) {
							done=true;
							nextBtn.setEnabled(false);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		lowerSection.add(nextBtn);

		allSections.add(lowerSection, BorderLayout.PAGE_END);

		this.validate();
		this.repaint();
		this.pack();
	}


	public static void main(String[] args) throws IOException {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}
}
