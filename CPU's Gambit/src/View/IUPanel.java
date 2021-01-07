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
	Color ivory = new Color(25,25,112);

	
	public IUPanel() throws IOException {
		this.setVisible(true);
		this.setBackground(lightGray);
		this.setPreferredSize(new Dimension(300,(int) this.getSize().getHeight()-50));
		
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
}
