package cl.inacap.contactosconimagenbdswingapp;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TablaImagen extends DefaultTableCellRenderer{ //Es importante renderizar la tabla porque no soporta JLabel.
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (value instanceof JLabel) { //Aca se consulta si el dato es un JLabel.
			JLabel lbl = (JLabel)value;
			return lbl;
		}
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
}
