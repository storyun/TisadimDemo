package ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.MouseInputListener;

import file.handler.ObjectHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import model.Shape;
import model.handler.ShapeList;
import model.handler.ShapeStack;
import ui.panel.SadimPanel;

import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class SadimFrame extends JFrame  implements ActionListener, MouseInputListener {
	
	//메뉴 상단바 변수
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem menuItem;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnEdit;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_9;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JMenu mnView;
	private JMenuItem mntmNewMenuItem_7;
	private JMenuItem mntmNewMenuItem_8;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem_1;
	
	private JFileChooser fileChooser;
	
	//왼쪽 편집 창
	private JPanel panel;
	private JToggleButton btnSelect;
	private JToggleButton btnCircle;
	private JToggleButton btnRectangle;
	private JToggleButton btnPolygon;
	private JToggleButton btnCurve;
	private JToggleButton btnLine;
	private JButton btnEdgecolor;
	private JButton btnFillcolor;
	private JSlider slider;
	private JLabel lblBorder;
	private JSpinner spinner;
	
	private ButtonGroup buttonGruop;
	
	//가운데 창
	private SadimPanel mainpanel;
	
	// 변수
	
	public SadimFrame() {
		super();
		this.setSize(new Dimension(850, 650));
		
		//initialization
	
		//왼쪽 편집창
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(175, 300));
		getContentPane().add(panel, BorderLayout.WEST);
				
		btnSelect = new JToggleButton("Select");
		btnSelect.setBounds(1, 1, 174, 43);
		panel.add(btnSelect);
				
		btnCircle = new JToggleButton("Circle");
		btnCircle.setBounds(1, 45, 174, 43);
		panel.add(btnCircle);
				
		btnRectangle = new JToggleButton("Rectangle");
		btnRectangle.setBounds(1, 89, 174, 43);
		panel.add(btnRectangle);
				
		btnPolygon = new JToggleButton("Polygon");
		btnPolygon.setBounds(1, 133, 174, 43);
		panel.add(btnPolygon);
				
		btnCurve = new JToggleButton("Curve");
		btnCurve.setBounds(1, 177, 174, 43);
		panel.add(btnCurve);
				
		btnLine = new JToggleButton("Line");
		btnLine.setBounds(1, 221, 174, 43);
		panel.add(btnLine);
				
		btnEdgecolor = new JButton("Edge Color");
		btnEdgecolor.setBounds(1, 265, 174, 43);
		int reserveColor = Color.BLACK.getRGB();
		reserveColor ^= 0x00FFFFFF;
		btnEdgecolor.setForeground(new Color(reserveColor));
		btnEdgecolor.setOpaque(true);
		btnEdgecolor.setBorderPainted(false);
		panel.add(btnEdgecolor);
				
		btnFillcolor = new JButton("Fill Color");
		btnFillcolor.setBounds(1, 309, 174, 43);
		reserveColor = Color.white.getRGB();
		reserveColor ^= 0x00FFFFFF;
		btnEdgecolor.setForeground(new Color(reserveColor));
		btnFillcolor.setOpaque(true);
		btnFillcolor.setBorderPainted(false);
		panel.add(btnFillcolor);
				
		lblBorder = new JLabel("Border");
		lblBorder.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorder.setBounds(1, 353, 86, 43);
		panel.add(lblBorder);
		
		// button group
		buttonGruop = new ButtonGroup();
		buttonGruop.add(btnCircle);
		buttonGruop.add(btnCurve);
		buttonGruop.add(btnLine);
		buttonGruop.add(btnPolygon);
		buttonGruop.add(btnRectangle);
		buttonGruop.add(btnSelect);
		
		// button listener
		btnCircle.addActionListener(this);
		btnCurve.addActionListener(this);
		btnLine.addActionListener(this);
		btnPolygon.addActionListener(this);
		btnPolygon.addActionListener(this);
		btnRectangle.addActionListener(this);
		btnSelect.addActionListener(this);
		btnEdgecolor.addActionListener(this);
		btnFillcolor.addActionListener(this);
				
		spinner = new JSpinner();
		spinner.setBounds(87, 356, 87, 35);
		spinner.setModel(new SpinnerNumberModel((float)1, (float)0, (float)10, (float)1));
		panel.add(spinner);
		
		slider = new JSlider(SwingConstants.HORIZONTAL);
		slider.setBounds(1, 390, 174, 43);
		panel.add(slider);

		//상단 메뉴바
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("파일");
		menuBar.add(mnFile);
		
		menuItem = new JMenuItem("새로 만들기");
		menuItem.addActionListener(this);
		mnFile.add(menuItem);
		
		mntmNewMenuItem = new JMenuItem("열기");
		mntmNewMenuItem.addActionListener(this);
		mnFile.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("저장");
		mntmNewMenuItem_1.addActionListener(this);
		mnFile.add(mntmNewMenuItem_1);
		
		mnEdit = new JMenu("편집");
		menuBar.add(mnEdit);
		
		mntmNewMenuItem_2 = new JMenuItem("실행 취소");
		mntmNewMenuItem_2.setEnabled(false);
		mntmNewMenuItem_2.addActionListener(this);
		mnEdit.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_9 = new JMenuItem("되돌리기");
		mntmNewMenuItem_9.setEnabled(false);
		mntmNewMenuItem_9.addActionListener(this);
		mnEdit.add(mntmNewMenuItem_9);
		
		mntmNewMenuItem_3 = new JMenuItem("잘라내기");
		mntmNewMenuItem_3.setEnabled(false);
		mntmNewMenuItem_3.addActionListener(this);
		mnEdit.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_4 = new JMenuItem("복사");
		mntmNewMenuItem_4.setEnabled(false);
		mntmNewMenuItem_4.addActionListener(this);
		mnEdit.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_5 = new JMenuItem("붙여넣기");
		mntmNewMenuItem_5.setEnabled(false);
		mntmNewMenuItem_5.addActionListener(this);
		mnEdit.add(mntmNewMenuItem_5);
		
		mntmNewMenuItem_6 = new JMenuItem("삭제");
		mntmNewMenuItem_6.setEnabled(false);
		mntmNewMenuItem_6.addActionListener(this);
		mnEdit.add(mntmNewMenuItem_6);
		
		mnView = new JMenu("보기");
		menuBar.add(mnView);
		
		mntmNewMenuItem_7 = new JMenuItem("확대");
		mnView.add(mntmNewMenuItem_7);
		
		mntmNewMenuItem_8 = new JMenuItem("축소");
		mnView.add(mntmNewMenuItem_8);
		
		chckbxmntmNewCheckItem = new JCheckBoxMenuItem("상태 표시줄");
		mnView.add(chckbxmntmNewCheckItem);
		
		chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("도형 정보");
		mnView.add(chckbxmntmNewCheckItem_1);
		
		//가운데
		mainpanel = new SadimPanel(this);
		getContentPane().add(mainpanel, BorderLayout.CENTER);
		
		fileChooser = new JFileChooser();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//그림 선택
		if(e.getSource() == btnSelect) {
			 mainpanel.setShapeId(Shape.SELECT);			
		}
		//원 선택
		else if(e.getSource() == btnCircle) {
			mainpanel.setShapeId(Shape.CIRCLE);
		}
		//사각형 선택
		else if(e.getSource() == btnRectangle) {
			mainpanel.setShapeId(Shape.RECTANGLE);
		}
		//다각형 선택
		else if(e.getSource() == btnPolygon) {
			mainpanel.setShapeId(Shape.POLYGON);
		}
		//곡선 선택
		else if(e.getSource() == btnCurve) {
			mainpanel.setShapeId(Shape.CURVE);
		}
		//직선 선택
		else if(e.getSource() == btnLine) {
			mainpanel.setShapeId(Shape.LINE);
		}
		//선 색 선택
		else if(e.getSource() == btnEdgecolor) {
			mainpanel.setEdgeColor(JColorChooser.showDialog(this, "색을 선택하세요", mainpanel.getEdgeColor()));
			btnEdgecolor.setBackground(mainpanel.getEdgeColor());
			int reserveColor = mainpanel.getEdgeColor().getRGB();
			reserveColor ^= 0x00FFFFFF;
			btnEdgecolor.setForeground(new Color(reserveColor));
		}
		//도형 내부 칠할 색 선택
		else if(e.getSource() == btnFillcolor) {
			mainpanel.setFillColor(JColorChooser.showDialog(this, "색을 선택하세요", mainpanel.getFillColor()));
			btnFillcolor.setBackground(mainpanel.getFillColor());
			int reserveColor = mainpanel.getEdgeColor().getRGB();
			reserveColor ^= 0x00FFFFFF;
			btnFillcolor.setForeground(new Color(reserveColor));
		}
		else if(e.getActionCommand() == mntmNewMenuItem_1.getText()) {
			if( fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				
				String path = "";
				path = fileChooser.getSelectedFile().toString();
				
				ObjectHandler.ObjectSave(path, mainpanel.getShapeList());
				
			}
		}
		else if(e.getActionCommand() == mntmNewMenuItem.getText()) {
			if( fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				String path = "";
				path = fileChooser.getSelectedFile().toString();
				mainpanel.setShapeList( ObjectHandler.ObjectLoad(path));
				
				mainpanel.repaintCanvas();
			}
		}
		else if(e.getActionCommand() == menuItem.getText()) {
			mainpanel.getShapeList().clear();
			mainpanel.repaintCanvas();
		}
		else if(e.getActionCommand() == mntmNewMenuItem_2.getText()) {
			//실행취소
			
		}
		else if(e.getActionCommand() == mntmNewMenuItem_9.getText()) {
			// 되돌리기
		}
		else if(e.getActionCommand() == mntmNewMenuItem_3.getText()) {
			// 잘라내기
		}
		else if(e.getActionCommand() == mntmNewMenuItem_4.getText()) {
			// 복사
		}
		else if(e.getActionCommand() == mntmNewMenuItem_5.getText()) {
			// 붙여넣기
		}
		else if(e.getActionCommand() == mntmNewMenuItem_6.getText()) {
			// 삭제
		}
	}

	public JSpinner getSpinner() {
		return spinner;
	}

	public JMenuItem getMntmNewMenuItem_2() {
		return mntmNewMenuItem_2;
	}

	public void setMntmNewMenuItem_2(JMenuItem mntmNewMenuItem_2) {
		this.mntmNewMenuItem_2 = mntmNewMenuItem_2;
	}

	public JMenuItem getMntmNewMenuItem_9() {
		return mntmNewMenuItem_9;
	}

	public void setMntmNewMenuItem_9(JMenuItem mntmNewMenuItem_9) {
		this.mntmNewMenuItem_9 = mntmNewMenuItem_9;
	}

	public JMenuItem getMntmNewMenuItem_3() {
		return mntmNewMenuItem_3;
	}

	public void setMntmNewMenuItem_3(JMenuItem mntmNewMenuItem_3) {
		this.mntmNewMenuItem_3 = mntmNewMenuItem_3;
	}

	public JMenuItem getMntmNewMenuItem_4() {
		return mntmNewMenuItem_4;
	}

	public void setMntmNewMenuItem_4(JMenuItem mntmNewMenuItem_4) {
		this.mntmNewMenuItem_4 = mntmNewMenuItem_4;
	}

	public JMenuItem getMntmNewMenuItem_5() {
		return mntmNewMenuItem_5;
	}

	public void setMntmNewMenuItem_5(JMenuItem mntmNewMenuItem_5) {
		this.mntmNewMenuItem_5 = mntmNewMenuItem_5;
	}

	public JMenuItem getMntmNewMenuItem_6() {
		return mntmNewMenuItem_6;
	}

	public void setMntmNewMenuItem_6(JMenuItem mntmNewMenuItem_6) {
		this.mntmNewMenuItem_6 = mntmNewMenuItem_6;
	}

	public JMenuItem getMntmNewMenuItem_7() {
		return mntmNewMenuItem_7;
	}

	public void setMntmNewMenuItem_7(JMenuItem mntmNewMenuItem_7) {
		this.mntmNewMenuItem_7 = mntmNewMenuItem_7;
	}

	public JMenuItem getMntmNewMenuItem_8() {
		return mntmNewMenuItem_8;
	}

	public void setMntmNewMenuItem_8(JMenuItem mntmNewMenuItem_8) {
		this.mntmNewMenuItem_8 = mntmNewMenuItem_8;
	}
}
