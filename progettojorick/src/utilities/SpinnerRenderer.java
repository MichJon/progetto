package utilities;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public  class SpinnerRenderer extends JSpinner implements TableCellRenderer {
    public SpinnerRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setModel((SpinnerModel) value);

        return this;
    }
}