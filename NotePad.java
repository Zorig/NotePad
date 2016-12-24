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
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLEditorKit;

import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;
import java.awt.event.InputEvent;
import javax.swing.JSeparator;

public class NotePad {

	private JFrame frmHe;
	private FileFilter filter;
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
		filter = new FileNameExtensionFilter("Text file","txt"); //Файлын төрлийг text болгож тохируулах
		final JEditorPane textTalbai = new JEditorPane(); //Editorpane -ыг оноож өгнө.
		textTalbai.setFont(new Font("Noto Sans", Font.PLAIN, 12)); //Editorpane -ын фонтыг солих
		//scroll том жижиг болгоход дагаад ихсэх багасах
		textTalbai.setEditorKit(new HTMLEditorKit()); //text format оруулахын тулд 
		textTalbai.setContentType("text/html"); //text format оруулахын тулд mime төрлийг өөрчлөв
		JScrollPane scroll = new JScrollPane (textTalbai, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frmHe.getContentPane().add(scroll);

		JMenuBar menuBar = new JMenuBar(); //menu байрлах мөр
		menuBar.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuBar.setBackground(SystemColor.menu); //menu арын өнгийг системийн default өнгө болгосон
		frmHe.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Файл"); //файл menu
		mnFile.setFont(new Font("Noto Sans", Font.BOLD, 12)); //Font тааруулах
		menuBar.add(mnFile);
		
		JMenuItem hadgalah = new JMenuItem("Хадгалах"); //хадгалах menu
		hadgalah.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK)); //ctrl+s хослол
		hadgalah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Хадгалах дарлаа"); //хадгалах дарж байгаа эсэхийг тест хийж байна
				JFileChooser fileChooser = new JFileChooser(); //filechooser хувьсагчид объект заах
				fileChooser.setDialogTitle("Хадгалах"); //Гарч ирэх хадгалах dialog -ын гарчиг
				fileChooser.setFileFilter(filter); //Дээр заасан файлын төрлийг файл сонгоход тааруулах
				int userSelection = fileChooser.showSaveDialog(frmHe);
				if (userSelection == JFileChooser.APPROVE_OPTION) { // Filechooser гараад сонголттой эсэхийг шалгах 
				    String content = textTalbai.getText(); //бичсэн текстийг авч content хувьсагчид оноох
				    File fi = fileChooser.getSelectedFile(); //Сонгосон файлыг авах
				    try {
				    	FileWriter fw = new FileWriter(fi.getPath()); //Filewriter-ыг дуудаж сонгосон файлын замыг заах
				    	fw.write(content); //editorpane-ын текстийг файлруу бичих
				    	fw.flush(); //filewriter -ыг цэвэрлэх
				    	fw.close(); //filewriter -ыг хаах
					} catch (Exception e2) { //алдаатай эсэхийг шалгах
						JOptionPane.showMessageDialog(null, e2.getMessage()); //алдаа гарсан үед алдааны мэдэгдэл харуулах
					}
				}
			}
		});
		
		JMenuItem mntmNeeh_1 = new JMenuItem("Шинэ");
		mntmNeeh_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mntmNeeh_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNeeh_1);
		hadgalah.setFont(new Font("Noto Sans", Font.BOLD, 12));
		mnFile.add(hadgalah);
				
		JMenuItem garah = new JMenuItem("Гарах");
		garah.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		garah.setFont(new Font("Noto Sans", Font.BOLD, 12));
		garah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				System.exit(0); //Гарах меню дарахад цонх гарах
			}
		});
		
		JMenuItem mntmNeeh = new JMenuItem("Нээх");
		mntmNeeh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mntmNeeh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser openNew = new JFileChooser(); //filechooser санг дуудаж openNew хувьсагч болгох
				openNew.setFileFilter(filter);
				openNew.setDialogTitle("Текст файл нээх");
				int opNew = openNew.showOpenDialog(frmHe); //File сонгох dialog гарч ирнэ
				if (opNew == JFileChooser.APPROVE_OPTION) {
					File selectedFile = openNew.getSelectedFile();
					//System.out.println(selectedFile); //File замыг зөв олж байна
					try {
						textTalbai.setPage(selectedFile.toURI().toURL()); //Сонгосон файлыг Editorpane-д сэт хийнэ
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
			}
		});
		mnFile.add(mntmNeeh);
		
		JMenuItem mntmPrint = new JMenuItem("Хэвлэх");
		mntmPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean hevleh;
				try {
					hevleh = textTalbai.print();
					if (hevleh) {
						JOptionPane.showMessageDialog(null, "Хэвлэж дууслаа");
					} else {
						JOptionPane.showMessageDialog(null, "Хэвлэхэд алдаа гарлаа");
					}
				} catch (PrinterException e1) {
					JOptionPane.showMessageDialog(null, "Алдаа гарлаа");
					e1.printStackTrace();
				}
				
			}
		});
		mntmPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mnFile.add(mntmPrint);
		
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
				textTalbai.selectAll();
				textTalbai.requestFocusInWindow();
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
		
		JMenu mnTekct = new JMenu("Текст");
		mnTekct.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuBar.add(mnTekct);
		
		JMenuItem menuItemBold = new JMenuItem(new StyledEditorKit.BoldAction());
		menuItemBold.setText("Бүдүүн");
		menuItemBold.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuItemBold.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK));
		mnTekct.add(menuItemBold);
		
		JMenuItem mntmItalic = new JMenuItem(new StyledEditorKit.ItalicAction());
		mntmItalic.setText("Ташуу");
		mntmItalic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnTekct.add(mntmItalic);
		
		JMenuItem mntmUnderline = new JMenuItem(new StyledEditorKit.UnderlineAction());
		mntmUnderline.setText("Доогуур зураас");
		mntmUnderline.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mnTekct.add(mntmUnderline);
		
		JMenuItem mntmFont = new JMenuItem();
		mntmFont.setText("Фонт");
		mnTekct.add(mntmFont);
		
		JMenuItem mntmSearch = new JMenuItem("Хайх");
		mntmSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnTekct.add(mntmSearch);
		
		JMenu mnHelp = new JMenu("Тусламж");
		mnHelp.setFont(new Font("Noto Sans", Font.BOLD, 12));
		menuBar.add(mnHelp);
		frmHe.getContentPane().setLayout(new CardLayout(0, 0));
	}
}
