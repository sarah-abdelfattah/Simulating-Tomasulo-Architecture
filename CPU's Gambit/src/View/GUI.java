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
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import Implementation.*;

//TODO: isDone --> deactivate (start-next-done)
//TODO: all filling functions
//TODO: next ActionListener
public class GUI extends JFrame implements WindowListener, ActionListener{
	JPanel allSections,rightSection,upperSection, lowerSection, centerSection, registers;
	RegFilePanel rfp;
	JButton nextBtn;
	int clickedNext =0;
	boolean done=false;
	String CC;
	//static Main main;
	//	JTextField clockCycles;

	//colors
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);



	public GUI() throws IOException {
		//main=new Main();
		Main.init();
		rfp=new RegFilePanel();
		this.setVisible(true);
		this.setSize(2000, 100);
		this.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		//this.setUndecorated(true);
		this.setTitle("CPU's Gambit");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());


		//inputs

		//The modeless dialog box
//		JDialog d1 = new JDialog();
//		d1.setBackground(Color.red);
//		this.add(d1);

//		String first_name;
//		first_name = JOptionPane.showInputDialog("First Name");
//		System.out.println(first_name);
//		String family_name;
//		family_name = JOptionPane.showInputDialog("Family Name");
//		
//		String full_name;
//		full_name = "You are " + first_name + " " + family_name;
//
//		JOptionPane.showMessageDialog( null, full_name );
//
//		System.exit(0);
		String input="";
		String temp = JOptionPane.showInputDialog("Please enter your instructions in order of issuing ")+"\n";
		String in="";
				
		while(in!= null) {
			in = JOptionPane.showInputDialog("More Instructions? (cancel for no) ");
			temp +=in + "\n";
		}
		
		String inst = temp.substring(0, temp.length()-6);

		input += "#";

		String LDnum= JOptionPane.showInputDialog("Number of LD Reservation Stations?");
		String SDnum= JOptionPane.showInputDialog("Number of SD Reservation Stations?");
		String ADDnum= JOptionPane.showInputDialog("Number of ADD Reservation Stations?");
		String MULnum= JOptionPane.showInputDialog("Number of MUL Reservation Stations?");

		input += "Load Reservation Stations : " + LDnum + "\n";
		input += "Store Reservation Stations : " + SDnum + "\n";
		input += "Add Reservation Stations : " + ADDnum + "\n";
		input += "Multiply Reservation Stations : " + MULnum + "\n";

		
		String LDcc= JOptionPane.showInputDialog("Number of cycles for Load instruction?");
		String SDcc= JOptionPane.showInputDialog("Number of cycles for Store instruction?");
		String ADDcc= JOptionPane.showInputDialog("Number of cycles for Add instruction?");
		String SUBcc= JOptionPane.showInputDialog("Number of cycles for Sub instruction?");
		String MULcc= JOptionPane.showInputDialog("Number of cycles for Mul instruction?");
		String DIVcc= JOptionPane.showInputDialog("Number of cycles for Div instruction?");

		
		input += "Load Cycles : " + LDcc + "\n";
		input += "Store Cycles : " + SDcc + "\n";
		input += "Add Cycles : " + ADDcc + "\n";
		input += "Sub Cycles : " + SUBcc + "\n";
		input += "Mul Cycles : " + MULcc + "\n";
		input += "Div Cycles : " + DIVcc;



System.out.println(input);

try
{
	BufferedWriter BW = new BufferedWriter(new FileWriter("/inputTextFile.txt"));
	BW.write(input);
	BW.close();
} 
catch (IOException e) 
{
	System.out.println(e.getMessage());
}




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
		//		clockCycles.setEditable(false);
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
		registers.setLayout(new BorderLayout()); //2 items so far (RegFile, IEW table)

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
		nextBtn.setSize(100,15);
		nextBtn.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
						//pack();
						if(x==-1) {
							done=true;
						}
					}
					//System.out.println("x:"+x);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		lowerSection.add(nextBtn);

		allSections.add(lowerSection, BorderLayout.PAGE_END);

		//System.out.println(clickedNext);

		this.validate();
		this.repaint();
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

	public static void main(String[] args) throws IOException {
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
