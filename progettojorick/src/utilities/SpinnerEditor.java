package utilities;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.EventObject;

//public  class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
//    final JSpinner spinner = new JSpinner();
//
//    public SpinnerEditor() {
//        spinner.setModel(new SpinnerNumberModel(1,1,20,1));
//    }
//
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
//                                                 int row, int column) {
//        spinner.setValue(value);
//        return spinner;
//    }
//
//    public boolean isCellEditable(EventObject evt) {
//        if (evt instanceof MouseEvent) {
//            return ((MouseEvent) evt).getClickCount() >= 2;
//        }
//        return true;
//    }
//
//    public Object getCellEditorValue() {
//        return spinner.getValue();
//    }
//}
public class SpinnerEditor extends DefaultCellEditor
{
    private JSpinner spinner;

    public SpinnerEditor()
    {
        super( new JTextField() );
        spinner = new JSpinner(new SpinnerNumberModel(0, 0, 100, 5));
        spinner.setBorder( null );
    }

    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
    {
        spinner.setValue( value );
        return spinner;
    }

    public Object getCellEditorValue()
    {
        return spinner.getValue();
    }
}
