package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import Implementation.*;
import Implementation.RegisterFile.RegEntry;
public class RegFilePanel extends JPanel {
	Color lightGray = new Color(245,245,245);
	Color ivory = new Color(25,25,112);

	public  RegFilePanel() {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(145,((int) this.getSize().getHeight()/2-7)));

		TitledBorder borderInfoRegFile = new TitledBorder("Register File");
		borderInfoRegFile.setTitleJustification(TitledBorder.CENTER);
		borderInfoRegFile.setTitlePosition(TitledBorder.TOP);
		borderInfoRegFile.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		borderInfoRegFile.setTitleColor(ivory);
		this.setBorder(borderInfoRegFile);

		//headers for the table
		String[] columnsRegFile = new String[] {
				"#","Qi","Content"
		};

		Object[][] dataRegFile = getReg();

		//create table with data
		JTable tableRegFile = new JTable(dataRegFile, columnsRegFile);
		tableRegFile.setGridColor(Color.white);
		tableRegFile.setBackground(lightGray);
		tableRegFile.setFont(new Font("Serif", Font.PLAIN, 15));
		tableRegFile.setRowHeight(25);
		tableRegFile.setEnabled(false);
		tableRegFile.getColumnModel().getColumn(0).setPreferredWidth(0);

		//to center text
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x = 0 ; x < 3 ; x++){
			tableRegFile.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		//edit header text
		JTableHeader tableHeader = tableRegFile.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(145,25));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollRegFile = new JScrollPane(tableRegFile);
		scrollRegFile.setPreferredSize(new Dimension(650,430));
		this.add(scrollRegFile);
	}

	private Object[][] getReg() {
		RegisterFile rf=Main.registerFile;
		Object[][] reg=new Object[rf.file.length][3];
		for(int i=0;i<reg.length;i++) {
			reg[i][0]="F"+i;
			String qi="";
			String content="";
			RegEntry res=rf.file[i];
			if(rf.file[i].qi.equals("0")) {
				content=""+res.content;
			}else {
				qi=""+res.qi;
			}
			reg[i][1]=qi;
			reg[i][2]=content;
		}
		return reg;
	}
}
