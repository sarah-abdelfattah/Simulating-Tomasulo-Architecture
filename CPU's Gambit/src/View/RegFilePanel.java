package View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class RegFilePanel extends JPanel {
	Color darkBlue = new Color(25,25,112);
	Color lightGray = new Color(245,245,245);
	Color darkRed = new Color(204,0,0); 
	Color seaShell = new Color(255,245,238);
	Color ivory = new Color(25,25,112);

	public  RegFilePanel() {
		this.setVisible(true);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension((int) this.getSize().getWidth(), 80));
		
		TitledBorder borderInfoReg = new TitledBorder("Register File");
		borderInfoReg.setTitleJustification(TitledBorder.CENTER);
		borderInfoReg.setTitlePosition(TitledBorder.TOP);
		borderInfoReg.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD,18));
		borderInfoReg.setTitleColor(ivory);
		this.setBorder(borderInfoReg);
		
//		this.setLayout(new GridLayout(2,1))
	}
}
