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
		JTextArea comments=new JTextArea(Main.s);
		//comments.setFont(new FONT());
		//File file = new File("Tests/Comments"); 
		//BufferedReader br = new BufferedReader(new FileReader(file)); 
		//String s="",t="";
	///	while((s=br.readLine())!=null) {
		//	t+=s+"\n";
		//}
		//if()
		//System.out.println(Main.s);
		//comments.setText(Main.s);
		//comments.set
		comments.setSize(new Dimension(300,(int) this.getSize().getHeight()-150));
		comments.setEnabled(false);

		TitledBorder borderInfoIU = new TitledBorder("Comments");
		borderInfoIU.setTitleJustification(TitledBorder.CENTER);
		borderInfoIU.setTitlePosition(TitledBorder.TOP);
		borderInfoIU.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		borderInfoIU.setTitleColor(ivory);
		this.setBorder(borderInfoIU);

//		//headers for the table
//		String[] columnsIU = new String[] {
//				"#","Instruction",
//		};

		//        data for the table in a 2d array
//		Object[][] dataIU = IUfillGUI();

//				Object[][] dataIU = new Object[][] {
//					{"1","MUL F3,F1,F2"},
//					{"2","ADD F5,F3,F4"},
//					{"3","ADD F7,F2,F6"},
//					{"4","ADD F7,F2,F6"},
//					{"5","ADD F7,F2,F6"},
//					{"6","ADD F7,F2,F6" },
//					{"7","","","",""},
//					{"8","","","",""},
//					{"9","","","",""},
//					{"10","","","",""},
//					{"11","","","",""},
//					{"12","","","",""},
//					{"13","","","",""},
//		//			{"14","","","",""},
//		//			{"15","","","",""},
				//};

		//create table with data
//		JTable tableIU = new JTable(dataIU, columnsIU);
//		tableIU.setGridColor(Color.white);
//		tableIU.setBackground(lightGray);
//		tableIU.setPreferredSize(new Dimension(260,800));
//		tableIU.setFont(new Font("Serif", Font.PLAIN, 15));
//		tableIU.setRowHeight(25);
//		tableIU.setEnabled(false);
//		tableIU.getColumnModel().getColumn(0).setPreferredWidth(0);

		//to center text
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//		for(int x = 0 ; x < 2 ; x++){
//			tableIU.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
//		}
//
//		//edit header text
//		JTableHeader tableHeader = tableIU.getTableHeader();
//		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
//		tableHeader.setPreferredSize(new Dimension(260,25));
//
//		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 
//				tableHeader.getDefaultRenderer();
//		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollUI = new JScrollPane(comments);
		scrollUI.setPreferredSize(new Dimension(280,700));
		this.add(scrollUI);
		this.repaint();
	}


	private Object[][] IUfillGUI() {
		// TODO Auto-generated method stub
		return null;
	}

}
