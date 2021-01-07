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

public class intRegPanel extends JPanel {
	Color lightGray = new Color(245,245,245);
	Color ivory = new Color(25,25,112);

	public  intRegPanel() {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(145,((int) this.getSize().getHeight()/2-7)));

		TitledBorder borderIntRegFile = new TitledBorder("Integer RF");
		borderIntRegFile.setTitleJustification(TitledBorder.CENTER);
		borderIntRegFile.setTitlePosition(TitledBorder.TOP);
		borderIntRegFile.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		borderIntRegFile.setTitleColor(ivory);
		this.setBorder(borderIntRegFile);

		//headers for the table
		String[] columnsIntRegFile = new String[] {
				"#","Content"
		};

		Object[][] dataIntRegFile = getIntReg();

		//create table with data
		JTable tableIntRegFile = new JTable(dataIntRegFile, columnsIntRegFile);
		tableIntRegFile.setGridColor(Color.white);
		tableIntRegFile.setBackground(lightGray);
		//tableIntRegFile.setPreferredSize(new Dimension(560,1000));
		tableIntRegFile.setFont(new Font("Serif", Font.PLAIN, 15));
		tableIntRegFile.setRowHeight(25);
		tableIntRegFile.setEnabled(false);
		tableIntRegFile.getColumnModel().getColumn(0).setPreferredWidth(30);

		//to center text
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x = 0 ; x < 2 ; x++){
			tableIntRegFile.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		//edit header text
		JTableHeader tableHeader = tableIntRegFile.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollIntRegFile = new JScrollPane(tableIntRegFile);
		scrollIntRegFile.setPreferredSize(new Dimension(128,430));
		this.add(scrollIntRegFile);
	}

	private Object[][] getIntReg() {
		ReservationStation rs=Main.addResStation;
		Object[][] reg= new Object[32][2];
		for(int i=0;i<rs.integerRegisters.length;i++) {
			reg[i][0]="R"+i;
			reg[i][1]=""+rs.integerRegisters[i];
		}
		return reg;
	}
}
