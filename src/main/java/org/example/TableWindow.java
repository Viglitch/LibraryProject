package org.example;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class TableWindow {
    private JFrame frame;
    private DefaultTableModel model;
    private JLabel infoLabel;

    public TableWindow(ArrayList<ArrayList<String>> data, String[] columnNames) {
        initialize(data, columnNames);
    }

    private void initialize(ArrayList<ArrayList<String>> data, String[] columnNames) {
        // Устанавливаем кодировку UTF-8
        System.setProperty("file.encoding", "UTF-8");
        try {
            java.lang.reflect.Field charset = java.nio.charset.Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Создаем фрейм
        frame = new JFrame("Работа библиотеки");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());

        // Создаем модель
        Object[][] objectArray = convertToObjectArray(data);
        model = new DefaultTableModel(objectArray, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1 || column == 2 || column == 4 || column == 5 || column == 6 || column == 7;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 3) { return Integer.class; }
                else { return String.class; }
            }
        };

        // Создаем и настраиваем таблицу
        JTable table = createTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        // Панель с информацией
        JPanel infoPanel = createInfoPanel();

        // Добавляем все на фрейм
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(infoPanel, BorderLayout.SOUTH);

        // Центрируем и показываем
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private Object[][] convertToObjectArray(ArrayList<ArrayList<String>> data) {
        int numRows = data.size();
        int maxCols = 0;
        for (ArrayList<String> innerList : data) {
            if (innerList.size() > maxCols) {
                maxCols = innerList.size();
            }
        }

        Object[][] objectArray = new Object[numRows][maxCols];
        for (int i = 0; i < numRows; i++) {
            ArrayList<String> currentInnerList = data.get(i);
            for (int j = 0; j < currentInnerList.size(); j++) {
                objectArray[i][j] = currentInnerList.get(j);
            }
        }
        return objectArray;
    }

    private JTable createTable(DefaultTableModel model) {
        JTable table = new JTable(model);
        Font font = new Font("Dialog", Font.PLAIN, 14);
        table.setFont(font);
        table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 14));

        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        table.setGridColor(Color.DARK_GRAY);
        table.setSelectionBackground(new Color(0, 100, 200));
        table.setSelectionForeground(Color.WHITE);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(30, 30, 30));
        header.setForeground(new Color(220, 220, 220));
        header.setFont(new Font("Dialog", Font.BOLD, 14));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value,
                        isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(20, 20, 20));
                    } else {
                        c.setBackground(new Color(40, 40, 40));
                    }
                    c.setForeground(Color.WHITE);
                }

                if (value instanceof Number) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.LEFT);
                }

                return c;
            }
        });

        return table;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.BLACK);
        infoLabel = new JLabel("Общее количество записей: " + model.getRowCount());
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        infoPanel.add(infoLabel, BorderLayout.WEST);

        model.addTableModelListener(e -> {
            infoLabel.setText("Общее количество записей: " + model.getRowCount());
        });

        return infoPanel;
    }

    // Публичный метод для обновления данных
    public void updateTableData(ArrayList<ArrayList<String>> newData) {
        // Добавляем новые данные
        Object[][] objectArray = convertToObjectArray(newData);
        for (Object[] row : objectArray) {
            model.addRow(row);
        }
    }

    // Метод для добавления одной строки
    public void addRow(Object[] rowData) {
        model.addRow(rowData);
    }

    // Метод для обновления строки
    public void updateRow(int rowIndex, Object[] rowData) {
        for (int i = 0; i < rowData.length; i++) {
            if (i < model.getColumnCount()) {
                model.setValueAt(rowData[i], rowIndex, i);
            }
        }
    }

    // Метод для удаления строки
    public void removeRow(int rowIndex) {
        model.removeRow(rowIndex);
    }

    // Геттер для фрейма
    public JFrame getFrame() {
        return frame;
    }

    // Геттер для модели
    public DefaultTableModel getModel() {
        return model;
    }
}
