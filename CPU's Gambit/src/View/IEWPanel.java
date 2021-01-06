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

import Implementation.InstructionUnit;
import Implementation.Main;

import Implementation.*;

public class IEWPanel extends JPanel{
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);

	
	public IEWPanel() {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(500, 500));

		TitledBorder borderInfoIEW = new TitledBorder("Issue-Execute-Write Result");
		borderInfoIEW.setTitleJustification(TitledBorder.CENTER);
		borderInfoIEW.setTitlePosition(TitledBorder.TOP);
		borderInfoIEW.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		borderInfoIEW.setTitleColor(ivory);
		this.setBorder(borderInfoIEW);

		//headers for the table
		String[] columnsIEW = new String[] {
				"#","Instruction","Issue","Execute","Write Result"
		};

		//        data for the table in a 2d array
//		Object[][] dataIU = IEWfillGUI();

				Object[][] dataIEW =IEWfillGUI();

		//create table with data
		JTable tableIEW = new JTable(dataIEW, columnsIEW);
		tableIEW.setGridColor(Color.white);
		tableIEW.setBackground(lightGray);
		//tableIEW.setPreferredSize(new Dimension(560,250));
		tableIEW.setFont(new Font("Serif", Font.PLAIN, 15));
		tableIEW.setRowHeight(25);
		tableIEW.setEnabled(false);
		tableIEW.getColumnModel().getColumn(0).setPreferredWidth(0);

		//to center text
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for(int x = 0 ; x < 5 ; x++){
			tableIEW.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
		}

		//edit header text
		JTableHeader tableHeader = tableIEW.getTableHeader();
		tableHeader.setFont(new Font("Serif", Font.BOLD, 16));
		tableHeader.setPreferredSize(new Dimension(260,25));

		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 
				tableHeader.getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		JScrollPane scrollIEW = new JScrollPane(tableIEW);
		scrollIEW.setPreferredSize(new Dimension(1000,430));
		this.add(scrollIEW);
		
		
		
	}


	private Object[][] IEWfillGUI() {
		InstructionUnit iu=Main.instructionUnit;
		String[]insts=Main.insts;
		Object[][] dataIEW=new Object[32][5];
		for(int i=0;i<insts.length;i++) {
			String inst=insts[i];
			if(inst==null)break;
			dataIEW[i][0]=""+(i+1);
			dataIEW[i][1]=inst;
			dataIEW[i][2]=iu.instArr[i].issueCycle==0?"":iu.instArr[i].issueCycle;
			dataIEW[i][3]=iu.instArr[i].executionCycle==0?"":iu.instArr[i].executionCycle+"->"+(iu.instArr[i].finishExecCycle==0?"":iu.instArr[i].finishExecCycle);
			dataIEW[i][4]=iu.instArr[i].finishCycle==0?"":iu.instArr[i].finishCycle;
		}
		return dataIEW;
	}

}
