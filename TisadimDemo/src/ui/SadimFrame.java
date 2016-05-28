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
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.MouseInputListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import model.Shape;
import ui.panel.SadimPanel;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;

public class SadimFrame extends JFrame  implements ActionListener, MouseInputListener {
	
	//���� ����
	private int shapeId;
	private Color edgeColor;
	private Color fillColor;
	
	//�޴� ��ܹ� ����
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem menuItem;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnEdit;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;
	private JMenuItem mntmNewMenuItem_4;
	private JMenuItem mntmNewMenuItem_5;
	private JMenuItem mntmNewMenuItem_6;
	private JMenu mnView;
	private JMenuItem mntmNewMenuItem_7;
	private JMenuItem mntmNewMenuItem_8;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem;
	private JCheckBoxMenuItem chckbxmntmNewCheckItem_1;
	
	//���� ���� â
	private JPanel panel;
	private JButton btnSelect;
	private JButton btnCircle;
	private JButton btnRectangle;
	private JButton btnPolygon;
	private JButton btnCurve;
	private JButton btnLine;
	private JLabel lblColor;
	private JButton btnEdgecolor;
	private JButton btnFillcolor;
	private JSlider slider;
	private JLabel lblBorder;
	private JSpinner spinner;
	
	//��� â
	private SadimPanel mainpanel;
	
	public SadimFrame() {
		super();
		this.setSize(new Dimension(850, 650));
		
		//initialization
		shapeId = 0;
		edgeColor = Color.BLACK;
		fillColor = Color.black;
	
		//���� ����â
		panel = new JPanel();
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(175, 300));
		getContentPane().add(panel, BorderLayout.WEST);
				
		btnSelect = new JButton("Select");
		btnSelect.setBounds(1, 1, 174, 43);
		panel.add(btnSelect);
				
		btnCircle = new JButton("Circle");
		btnCircle.setBounds(1, 45, 174, 43);
		panel.add(btnCircle);
				
		btnRectangle = new JButton("Rectangle");
		btnRectangle.setBounds(1, 89, 174, 43);
		panel.add(btnRectangle);
				
		btnPolygon = new JButton("Polygon");
		btnPolygon.setBounds(1, 133, 174, 43);
		panel.add(btnPolygon);
				
		btnCurve = new JButton("Curve");
		btnCurve.setBounds(1, 177, 174, 43);
		panel.add(btnCurve);
				
		btnLine = new JButton("Line");
		btnLine.setBounds(1, 221, 174, 43);
		panel.add(btnLine);
				
		btnEdgecolor = new JButton("Edge Color");
		btnEdgecolor.setBounds(1, 265, 174, 43);
		panel.add(btnEdgecolor);
				
		btnFillcolor = new JButton("Fill Color");
		btnFillcolor.setBounds(1, 309, 174, 43);
		panel.add(btnFillcolor);
				
		lblBorder = new JLabel("Border");
		lblBorder.setHorizontalAlignment(SwingConstants.CENTER);
		lblBorder.setBounds(1, 353, 86, 43);
		panel.add(lblBorder);
				
		spinner = new JSpinner();
		spinner.setBounds(87, 356, 87, 35);
		panel.add(spinner);
		
		slider = new JSlider(SwingConstants.HORIZONTAL);
		slider.setBounds(1, 390, 174, 43);
		panel.add(slider);

		//��� �޴���
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("����");
		menuBar.add(mnFile);
		
		menuItem = new JMenuItem("���� �����");
		mnFile.add(menuItem);
		
		mntmNewMenuItem = new JMenuItem("����");
		mnFile.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("����");
		mnFile.add(mntmNewMenuItem_1);
		
		mnEdit = new JMenu("����");
		menuBar.add(mnEdit);
		
		mntmNewMenuItem_2 = new JMenuItem("���� ���");
		mnEdit.add(mntmNewMenuItem_2);
		
		mntmNewMenuItem_3 = new JMenuItem("�߶󳻱�");
		mnEdit.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_4 = new JMenuItem("����");
		mnEdit.add(mntmNewMenuItem_4);
		
		mntmNewMenuItem_5 = new JMenuItem("�ٿ��ֱ�");
		mnEdit.add(mntmNewMenuItem_5);
		
		mntmNewMenuItem_6 = new JMenuItem("����");
		mnEdit.add(mntmNewMenuItem_6);
		
		mnView = new JMenu("����");
		menuBar.add(mnView);
		
		mntmNewMenuItem_7 = new JMenuItem("Ȯ��");
		mnView.add(mntmNewMenuItem_7);
		
		mntmNewMenuItem_8 = new JMenuItem("���");
		mnView.add(mntmNewMenuItem_8);
		
		chckbxmntmNewCheckItem = new JCheckBoxMenuItem("���� ǥ����");
		mnView.add(chckbxmntmNewCheckItem);
		
		chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("���� ����");
		mnView.add(chckbxmntmNewCheckItem_1);
		
		//���
		mainpanel = new SadimPanel(shapeId, edgeColor, fillColor);
		getContentPane().add(mainpanel, BorderLayout.CENTER);
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
		//�׸� ����
		if(e.getSource() == btnSelect) {
			shapeId = Shape.SELECT;			
		}
		//�� ����
		else if(e.getSource() == btnCircle) {
			shapeId = Shape.CIRCLE;
		}
		//�簢�� ����
		else if(e.getSource() == btnRectangle) {
			shapeId = Shape.RECTANGLE;
		}
		//�ٰ��� ����
		else if(e.getSource() == btnPolygon) {
			shapeId = Shape.POLYGON;
		}
		//� ����
		else if(e.getSource() == btnCurve) {
			shapeId = Shape.CURVE;
		}
		//���� ����
		else if(e.getSource() == btnLine) {
			shapeId = Shape.LINE;
		}
		//�� �� ����
		else if(e.getSource() == btnEdgecolor) {
			edgeColor = JColorChooser.showDialog(this, "���� �����ϼ���", edgeColor);
			btnEdgecolor.setBackground(edgeColor);
		}
		//���� ���� ĥ�� �� ����
		else if(e.getSource() == btnFillcolor) {
			fillColor = JColorChooser.showDialog(this, "���� �����ϼ���", fillColor);
			btnFillcolor.setBackground(fillColor);
		}
	}
}
