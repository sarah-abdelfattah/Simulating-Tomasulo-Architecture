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

public class RSPanel extends JPanel {
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);

	public RSPanel() {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(300,(int) this.getSize().getHeight()-50));
		this.setLayout(new GridLayout(5,1));

//		TitledBorder borderInfoRS = new TitledBorder("Reservation Stations");
//		borderInfoRS.setTitleJustification(TitledBorder.CENTER);
//		borderInfoRS.setTitlePosition(TitledBorder.TOP);
//		borderInfoRS.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
//		borderInfoRS.setTitleColor(ivory);
//		this.setBorder(borderInfoRS);



		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );

		
		/* ------------------ LD TABLE ----------------------*/
		String[] columnsLD = new String[] {
				"#","Busy","Address",
		};

		//		Object[][] dataLD = LDfillGUI();
		Object[][] dataLD = new Object[][] {
			{"1","1","23"},
			{"2","0","49"},
			{"3","0","111"},
			{"","",""},
		};

		JTable tableLD = new JTable(dataLD, columnsLD);
		tableLD.setGridColor(Color.white);
		tableLD.setBackground(lightGray);
//		tableLD.setPreferredSize(new Dimension(250,80));
		tableLD.setFont(new Font("Serif", Font.PLAIN, 15));
		tableLD.setRowHeight(25);
		tableLD.setEnabled(false);
		tableLD.getColumnModel().getColumn(0).setPreferredWidth(0);

		for(int x = 0 ; x < 3 ; x++)
			tableLD.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );


		//edit header text
		JTableHeader tableHeader = tableLD.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollLD = new JScrollPane(tableLD);
		scrollLD.setPreferredSize(new Dimension(250,130));
		
		TitledBorder borderInfoLD = new TitledBorder("LD Reservation Station");
		borderInfoLD.setTitleJustification(TitledBorder.CENTER);
		borderInfoLD.setTitlePosition(TitledBorder.TOP);
		borderInfoLD.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,16));
		borderInfoLD.setTitleColor(ivory);
		scrollLD.setBorder(borderInfoLD);
		
		
		this.add(scrollLD);



		/* ------------------ SD TABLE ----------------------*/
		String[] columnsSD = new String[] {
				"#","Busy","Vi","Qi","Address"
		};

		//        data for the table in a 2d array
		//		Object[][] dataSD = SDfillGUI();

		Object[][] dataSD = new Object[][] {
			{"1","1","","A1","R2"},
			{"2","0","","",""},
			{"3","0","","",""},
			{"","","","",""},
		};

		JTable tableSD = new JTable(dataSD,columnsSD);
		tableSD.setGridColor(Color.white);
		tableSD.setBackground(lightGray);
//		tableSD.setPreferredSize(new Dimension(250,100));
		tableSD.setFont(new Font("Serif", Font.PLAIN, 15));
		tableSD.setRowHeight(25);
		tableSD.setEnabled(false);
		tableSD.getColumnModel().getColumn(0).setPreferredWidth(0);

		for(int x = 0 ; x < 2 ; x++){
			tableSD.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		tableHeader = tableSD.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollSD = new JScrollPane(tableSD);
		scrollSD.setPreferredSize(new Dimension(250,130));
//		scrollSD.setSize(250, 100);
		
		TitledBorder borderInfoSD = new TitledBorder("SD Reservation Station");
		borderInfoSD.setTitleJustification(TitledBorder.CENTER);
		borderInfoSD.setTitlePosition(TitledBorder.TOP);
		borderInfoSD.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,16));
		borderInfoSD.setTitleColor(ivory);
		scrollSD.setBorder(borderInfoSD);
		
		this.add(scrollSD);
		
		
		
		/* ------------------ ADD TABLE ----------------------*/
		String[] columnsADD = new String[] {
				"#","op","Vj","Vk","Qj","Qk","Busy"
		};

		//        data for the table in a 2d array
//				Object[][] dataADD = ADDfillGUI();

		Object[][] dataADD = new Object[][] {
			{"1","ADD","","F2","M1","0","1"},
			{"2","SUB","F3","F2","0","0","1"},
			{"3","","","","","","0"},
		};

		JTable tableADD = new JTable(dataADD,columnsADD);
		tableADD.setGridColor(Color.white);
		tableADD.setBackground(lightGray);
//		tableADD.setPreferredSize(new Dimension(250,80));
		tableADD.setFont(new Font("Serif", Font.PLAIN, 15));
		tableADD.setRowHeight(25);
		tableADD.setEnabled(false);
		tableADD.getColumnModel().getColumn(0).setPreferredWidth(0);

		for(int x = 0 ; x < 2 ; x++){
			tableADD.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		tableHeader = tableADD.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollADD = new JScrollPane(tableADD);
		scrollADD.setPreferredSize(new Dimension(250,130));
		
		TitledBorder borderInfoADD = new TitledBorder("ADD Reservation Station");
		borderInfoADD.setTitleJustification(TitledBorder.CENTER);
		borderInfoADD.setTitlePosition(TitledBorder.TOP);
		borderInfoADD.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,16));
		borderInfoADD.setTitleColor(ivory);
		scrollADD.setBorder(borderInfoADD);
		
		this.add(scrollADD);
		

		
		
		/* ------------------ MUL TABLE ----------------------*/
		String[] columnsMUL = new String[] {
				"#","op","Vj","Vk","Qj","Qk","Busy"
		};

		//        data for the table in a 2d array
//				Object[][] dataMUL = MULfillGUI();

		Object[][] dataMUL = new Object[][] {
			{"1","MUL","","F2","M1","0","1"},
			{"2","DIV","F3","F2","0","0","1"},
			{"3","","","","","","0"},
		};

		JTable tableMUL = new JTable(dataMUL,columnsMUL);
		tableMUL.setGridColor(Color.white);
		tableMUL.setBackground(lightGray);
//		tableMUL.setPreferredSize(new Dimension(260,80));
		tableMUL.setFont(new Font("Serif", Font.PLAIN, 15));
		tableMUL.setRowHeight(25);
		tableMUL.setEnabled(false);
		tableMUL.getColumnModel().getColumn(0).setPreferredWidth(0);

		for(int x = 0 ; x < 2 ; x++){
			tableMUL.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		tableHeader = tableMUL.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollMUL = new JScrollPane(tableMUL);
		scrollMUL.setPreferredSize(new Dimension(250,130));
		
		TitledBorder borderInfoMUL = new TitledBorder("MUL Reservation Station");
		borderInfoMUL.setTitleJustification(TitledBorder.CENTER);
		borderInfoMUL.setTitlePosition(TitledBorder.TOP);
		borderInfoMUL.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,16));
		borderInfoMUL.setTitleColor(ivory);
		scrollMUL.setBorder(borderInfoMUL);
		
		this.add(scrollMUL);
		
		this.validate();
		this.repaint();

	}

	private Object[][] MULfillGUI() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object[][] ADDfillGUI() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object[][] SDfillGUI() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object[][] LDfillGUI() {
		// TODO Auto-generated method stub
		return null;
	}
}
