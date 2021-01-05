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

public class RegFilePanel extends JPanel {
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);

	public  RegFilePanel() {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(500, 500));

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

//		Object[][] dataRegFile = RegFilefillGUI();

				Object[][] dataRegFile = new Object[][] {
					{"1","M1",""},
					{"2","0","9.2"},
					{"3","0","2.23"},
					{"2","A1",""},
					{"5","0 ",""}
				};

		//create table with data
		JTable tableRegFile = new JTable(dataRegFile, columnsRegFile);
		tableRegFile.setGridColor(Color.white);
		tableRegFile.setBackground(lightGray);
		tableRegFile.setPreferredSize(new Dimension(560,250));
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
		tableHeader.setPreferredSize(new Dimension(260,25));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollRegFile = new JScrollPane(tableRegFile);
		scrollRegFile.setPreferredSize(new Dimension(560,250));
		this.add(scrollRegFile);
		
		
		
//		this.setLayout(new GridLayout(2,1))
	}

	private Object[][] RegFilefillGUI() {
		// TODO Auto-generated method stub
		return null;
	}
}
