package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class IUPanel extends JPanel{
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);

	
	public IUPanel() {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(300,(int) this.getSize().getHeight()-50));

		TitledBorder borderInfoIU = new TitledBorder("Instruction Unit");
		borderInfoIU.setTitleJustification(TitledBorder.CENTER);
		borderInfoIU.setTitlePosition(TitledBorder.TOP);
		borderInfoIU.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		borderInfoIU.setTitleColor(ivory);
		this.setBorder(borderInfoIU);

		//headers for the table
		String[] columnsIU = new String[] {
				"#","Instruction",
		};

		//        data for the table in a 2d array
//		Object[][] dataIU = IUfillGUI();

				Object[][] dataIU = new Object[][] {
					{"1","MUL F3,F1,F2"},
					{"2","ADD F5,F3,F4"},
					{"3","ADD F7,F2,F6"},
					{"4","ADD F7,F2,F6"},
					{"5","ADD F7,F2,F6"},
					{"6","ADD F7,F2,F6" },
					{"7","","","",""},
					{"8","","","",""},
					{"9","","","",""},
					{"10","","","",""},
					{"11","","","",""},
					{"12","","","",""},
					{"13","","","",""},
		//			{"14","","","",""},
		//			{"15","","","",""},
				};

		//create table with data
		JTable tableIU = new JTable(dataIU, columnsIU);
		tableIU.setGridColor(Color.white);
		tableIU.setBackground(lightGray);
		tableIU.setPreferredSize(new Dimension(260,800));
		tableIU.setFont(new Font("Serif", Font.PLAIN, 15));
		tableIU.setRowHeight(25);
		tableIU.setEnabled(false);
		tableIU.getColumnModel().getColumn(0).setPreferredWidth(0);

		//to center text
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x = 0 ; x < 2 ; x++){
			tableIU.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		//edit header text
		JTableHeader tableHeader = tableIU.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollUI = new JScrollPane(tableIU);
		scrollUI.setPreferredSize(new Dimension(290,800));
		this.add(scrollUI);
		
		
		
	}


	private Object[][] IUfillGUI() {
		// TODO Auto-generated method stub
		return null;
	}

}
