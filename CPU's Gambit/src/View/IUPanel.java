package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import Implementation.Main;

public class IUPanel extends JPanel{
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color ivory = new Color(25,25,112);

	
	public IUPanel() throws IOException {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(300,(int) this.getSize().getHeight()-50));
//		String t="<html>";
//		if(Main.s!=null) {
//			String[]x=Main.s.split("\n");
//			for(String s:x) {
//				t+=x+"<br>";
//			}
//		}System.out.println(t);
		//JLabel comments = new JLabel((Main.s==null?t:t.substring(0,t.length()-4))+"</html");
//
//		File file = new File("Tests/Results.txt"); 
//
//		BufferedReader br = new BufferedReader(new FileReader(file)); 
//		String st; 
//
//		JTextArea comments=new JTextArea(Main.s);

//		try {
//			Scanner scan = new Scanner(file);
//			while (scan.hasNextLine())
//			{
//				comments.append(scan.nextLine()+"\n");
//			}
//			scan.close();
//		} catch (FileNotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		//comments.setFont(new FONT());
		//File file = new File("Tests/Comments"); 
		//BufferedReader br = new BufferedReader(new FileReader(file)); 
		//String s="",t="";
		///	while((s=br.readLine())!=null) {
		//	t+=s+"\n";
		//}
		//if()
		//System.out.println(Main.s);
		
		JTextArea comments=new JTextArea(Main.s);
		comments.setText(Main.s);
		comments.setSize(new Dimension(700,500));
		comments.setEnabled(false);
		comments.setDisabledTextColor(Color.black);
		comments.setFont(new Font(Font.DIALOG_INPUT, Font.PLAIN,15));


		TitledBorder borderInfoIU = new TitledBorder("Comments");
		borderInfoIU.setTitleJustification(TitledBorder.CENTER);
		borderInfoIU.setTitlePosition(TitledBorder.TOP);
		borderInfoIU.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		borderInfoIU.setTitleColor(ivory);
		this.setBorder(borderInfoIU);


		JScrollPane scrollUI = new JScrollPane(comments);
		scrollUI.setPreferredSize(new Dimension(280,600));
		this.add(scrollUI);
		this.repaint();
	}


	private Object[][] IUfillGUI() {
		// TODO Auto-generated method stub
		return null;
	}

}
