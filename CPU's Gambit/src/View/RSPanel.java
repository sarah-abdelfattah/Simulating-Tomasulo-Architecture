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

import Implementation.Main;
import Implementation.ResEntry;
import Implementation.ReservationStation;

public class RSPanel extends JPanel {
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);

	public RSPanel() {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(600,900));
		this.setLayout(new GridLayout(5,1,0,40));

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
		Object[][] dataLD =LDfillGUI();

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
		scrollLD.setPreferredSize(new Dimension(450,270));
		
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

		Object[][] dataSD = SDfillGUI();

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
		scrollSD.setPreferredSize(new Dimension(450,270));
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

		Object[][] dataADD = ADDfillGUI();

		JTable tableADD = new JTable(dataADD,columnsADD);
		tableADD.setGridColor(Color.white);
		tableADD.setBackground(lightGray);
//		tableADD.setPreferredSize(new Dimension(250,80));
		tableADD.setFont(new Font("Serif", Font.PLAIN, 15));
		tableADD.setRowHeight(25);
		tableADD.setEnabled(false);
		tableADD.getColumnModel().getColumn(0).setPreferredWidth(50);

		for(int x = 0 ; x < 7 ; x++){
			tableADD.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		tableHeader = tableADD.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollADD = new JScrollPane(tableADD);
		scrollADD.setPreferredSize(new Dimension(450,270));
		
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

		Object[][] dataMUL = MULfillGUI();

		JTable tableMUL = new JTable(dataMUL,columnsMUL);
		tableMUL.setGridColor(Color.white);
		tableMUL.setBackground(lightGray);
//		tableMUL.setPreferredSize(new Dimension(260,80));
		tableMUL.setFont(new Font("Serif", Font.PLAIN, 15));
		tableMUL.setRowHeight(25);
		tableMUL.setEnabled(false);
		tableMUL.getColumnModel().getColumn(0).setPreferredWidth(50);

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
		scrollMUL.setPreferredSize(new Dimension(450,270));
		
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
		ReservationStation res=Main.mulResStation;
		Object[][] mul=new Object[res.resEntries.length][7];
		for(int i=0;i<mul.length;i++) {
			mul[i][0]= "M"+(i+1);
			//mul[i][6]="";
			ResEntry re=res.resEntries[i];
			if(re==null) {
				mul[i][6]="0";
				mul[i][1]=mul[i][2]=mul[i][3]=mul[i][4]=mul[i][5]="";
			}else {
				mul[i][6]="1";
				mul[i][1]=re.op;
				mul[i][2]=re.qj.equals("0")?re.vj:"";
				mul[i][3]=re.qk.equals("0")?re.vk:"";
				mul[i][4]=!re.qj.equals("0")?re.qj:"";
				mul[i][5]=!re.qk.equals("0")?re.qk:"";
			}
		}
		return mul;
	}

	private Object[][] ADDfillGUI() {
		ReservationStation res=Main.addResStation;
		Object[][] add=new Object[res.resEntries.length][7];
		for(int i=0;i<add.length;i++) {
			add[i][0]= "A"+(i+1);
			//mul[i][6]="";
			ResEntry re=res.resEntries[i];
			//System.out.println(re);
			if(re==null) {
				add[i][6]="0";
				add[i][1]=add[i][2]=add[i][3]=add[i][4]=add[i][5]="";
			}else {
				add[i][6]="1";
				add[i][1]=re.op;
				add[i][2]=re.qj.equals("0")?re.vj:"";
				add[i][3]=re.qk.equals("0")?re.vk:"";
				add[i][4]=!re.qj.equals("0")?re.qj:"";
				add[i][5]=!re.qk.equals("0")?re.qk:"";
			}
		}
		return add;
	}

	private Object[][] SDfillGUI() {
		ReservationStation res=Main.SDResStation;
		Object[][] SD=new Object[res.resEntries.length][7];
		for(int i=0;i<SD.length;i++) {
			SD[i][0]= "S"+(i+1);
			//mul[i][6]="";
			ResEntry re=res.resEntries[i];
			if(re==null) {
				SD[i][1]="0";
				SD[i][2]=SD[i][3]=SD[i][4]="";
			}else {
				SD[i][1]="1";
				SD[i][2]=re.qj.equals("0")?re.vj:"";
				SD[i][3]=!re.qj.equals("0")?re.qj:"";
				SD[i][4]=re.address;
			}
		}
		return SD;
	}

	private Object[][] LDfillGUI() {
		ReservationStation res=Main.LDResStation;
		Object[][] LD=new Object[res.resEntries.length][7];
		for(int i=0;i<LD.length;i++) {
			LD[i][0]= "L"+(i+1);
			//mul[i][6]="";
			ResEntry re=res.resEntries[i];
			if(re==null) {
				LD[i][1]="0";
				LD[i][2]="";
			}else {
				LD[i][1]="1";
				LD[i][2]=re.address;
			}
		}
		return LD;
	}
}
