import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Exp3 extends JFrame {
	DefaultTableModel tm;
	ReceiveAndProcessData receiveAndProcessData;

	public Exp3() {
		tm = new DefaultTableModel(new String[] { "航班号", "航班数据", "数据行号" }, 0);
		JTable table = new JTable(tm);
		RowSorter<DefaultTableModel> rowSorter = new TableRowSorter<DefaultTableModel>(tm);
		table.setRowSorter(rowSorter);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		JButton startButton = new JButton("Start");
		JButton closeButton = new JButton("Close");

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//防止多次连接服务器，严格来说，ReceiveAndProcessData应按照单例模式来设计，本程序从略
				if (receiveAndProcessData == null) {
					receiveAndProcessData = new ReceiveAndProcessData();
					receiveAndProcessData.execute();
				}
				startButton.setEnabled(false);
			}
		});

		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		southPanel.add(startButton);
		southPanel.add(closeButton);

		add(southPanel, BorderLayout.SOUTH);

		pack();
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// 使用SwingWorker类来接受并分析航班数据，SwingWorker用法见《JAVA核心技术》第一卷14.11节
	class ReceiveAndProcessData extends SwingWorker<String, String> {		
		private int numberOfline = 0;

		@Override
		protected String doInBackground() throws Exception {
			String totalLines = null;
			try (Socket socket = new Socket("10.5.25.193", 9999)) {				
				String line;
				Scanner scanner = new Scanner(socket.getInputStream());
				totalLines = scanner.nextLine();
				System.out.println(totalLines);
				JOptionPane.showMessageDialog(null, "连接服务器成功\r\n" + totalLines );
				while (!(line = scanner.nextLine()).equals("no data!")) {
					publish(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return totalLines;
		}

		@Override
		protected void process(List<String> chunks) {			
			for (String line : chunks) {
				tm.addRow(new String[] { "待分析", line, "" + numberOfline++});
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Exp3();
			}
		});
	}
}
