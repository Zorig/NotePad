package BieDaalt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JSeparator;

public class NotePad {

	private JFrame frmHe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotePad window = new NotePad();
					window.frmHe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public NotePad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHe = new JFrame();
		frmHe.setTitle("Notepad");
		frmHe.setFont(new Font("Noto Sans", Font.PLAIN, 12));
		frmHe.setBounds(100, 100, 450, 300);
		frmHe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JEditorPane textTalbai = new JEditorPane();
		textTalbai.setFont(new Font("Noto Sans", Font.PLAIN, 12));
		JScrollPane scroll = new JScrollPane (textTalbai, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frmHe.getContentPane().add(scroll);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuBar.setBackground(SystemColor.menu);
		frmHe.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Файл");
		mnFile.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuBar.add(mnFile);
		
		JMenuItem hadgalah = new JMenuItem("Хадгалах");
		hadgalah.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		hadgalah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Хадгалах дарлаа");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Хадгалах");
				int userSelection = fileChooser.showSaveDialog(frmHe);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    String content = textTalbai.getText();
				    File fi = fileChooser.getSelectedFile();
				    try {
				    	FileWriter fw = new FileWriter(fi.getPath());
				    	fw.write(content);
				    	fw.flush();
				    	fw.close();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage());
					}
				}
//				String sampletext = textTalbai.getText().toString();
//				try {
//					Files.write(Paths.get("./fileName.txt"), sampletext.getBytes());
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}
		});
		hadgalah.setFont(new Font("Noto Sans", Font.BOLD, 12));
		mnFile.add(hadgalah);
				
		JMenuItem garah = new JMenuItem("Гарах");
		garah.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		garah.setFont(new Font("Noto Sans", Font.BOLD, 12));
		garah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				System.exit(0); 
			}
		});
		
		JMenuItem mntmNeeh = new JMenuItem("Нээх");
		mntmNeeh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmNeeh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser openNew = new JFileChooser(); //filechooser sang initiate hiine
				FileFilter filter = new FileNameExtensionFilter("Text file","txt");
				openNew.setFileFilter(filter);
				openNew.setDialogTitle("Текст файл нээх");
				int opNew = openNew.showOpenDialog(frmHe); //Neeh dialog garj irne
				if (opNew == JFileChooser.APPROVE_OPTION) {
					File selectedFile = openNew.getSelectedFile();
					//System.out.println(selectedFile); //File iin path iig zuv olj baina
					try {
						textTalbai.setPage(selectedFile.toURI().toURL());
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
//					BufferedReader in = null;
//					try {
//						in = new BufferedReader(new FileReader(selectedFile));
//						String line = null;
//						while ((line=in.readLine()) !=null){
//							System.out.println(line); //line hevlene
//							textTalbai.setPage(selectedFile.toURI().toURL());
//						}
//					} catch (FileNotFoundException e2) {
//						// TODO Auto-generated catch block
//						e2.printStackTrace();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
					
			        //textTalbai.setText(line.toString());
					//System.out.println(openNew.getSelectedFile().getName());
				}
			}
		});
		mnFile.add(mntmNeeh);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		mnFile.add(garah);
		
		JMenu mnEdit = new JMenu("Засах");
		mnEdit.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuBar.add(mnEdit);
		
		JMenuItem mntmUndo = new JMenuItem("Буцах");
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.print("UNDO");
			}
		});
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mnEdit.add(mntmUndo);
		
		JMenuItem mntmSelectall = new JMenuItem("Бүгд сонгох");
		mntmSelectall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mntmSelectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnEdit.add(mntmSelectall);
		
		JSeparator separator = new JSeparator();
		mnEdit.add(separator);
		
		JMenuItem mntmCopy = new JMenuItem(new DefaultEditorKit.CopyAction());
		mntmCopy.setText("Хуулах");
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmCut = new JMenuItem(new DefaultEditorKit.CutAction());
		mntmCut.setText("Таслах");
		mntmCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnEdit.add(mntmCut);
		
		JMenuItem mntmPaste = new JMenuItem(new DefaultEditorKit.PasteAction());
		mntmPaste.setText("Наах");
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mnEdit.add(mntmPaste);
		
		JMenu mnHelp = new JMenu("Тусламж");
		mnHelp.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuBar.add(mnHelp);
		frmHe.getContentPane().setLayout(new CardLayout(0, 0));
		
	}
}
