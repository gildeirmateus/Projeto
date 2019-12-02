package br.edu.univas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame{
	
	private JPanel contentPane;
	private JPanel centerPanel;
	private Dashboard dashboard;
	private Dias dias;
	private Cafe cafe;
	private Vendas vendas;
	
	public Menu() {
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Coffe Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initialize();
	}

	private void initialize() {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		createNorthPanel();
		
		try {
			dashboard = new Dashboard();
			dias= new Dias();
			cafe= new Cafe();
			vendas= new Vendas();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(dashboard);
		centerPanel.setBackground(Color.blue);
		contentPane.add(centerPanel, BorderLayout.CENTER);		
	}

	private void createNorthPanel() {
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 70));
		northPanel.setBackground(new Color(0, 100, 250));
		
		Font titleFont = new Font("ARIAL", Font.BOLD, 20);
		
		JButton DashboardButton = new JButton();
		DashboardButton.setText("Dashboard");
		DashboardButton.setFont(titleFont);
		DashboardButton.setPreferredSize(new Dimension(334, 60));
		ImageIcon image2 = new ImageIcon(getClass().getResource("/refresh.png"));
		DashboardButton.setIcon(image2);	
		northPanel.add(DashboardButton);
		DashboardButton.addActionListener(new ActionListener() {
		


			@Override
			public void actionPerformed(ActionEvent e) {
				centerPanel.removeAll();
				validate();
				centerPanel.add(dashboard);				
				centerPanel.revalidate();
				Menu.this.repaint();
				try {
					dashboard = new Dashboard();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton diasButton = new JButton();
		diasButton.setText("Dias");
		diasButton.setFont(titleFont);
		diasButton.setPreferredSize(new Dimension(334, 60));
		ImageIcon image3 = new ImageIcon(getClass().getResource("/day.png"));
		diasButton.setIcon(image3);
		northPanel.add(diasButton);
		diasButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dias.updateTable();
				centerPanel.removeAll();
				validate();
				centerPanel.add(dias);
				centerPanel.revalidate();
				Menu.this.repaint();
				try {
					dias = new Dias();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		northPanel.add(diasButton);
		
		JButton cafeButton = new JButton();
		cafeButton.setText("Café");
		cafeButton.setFont(titleFont);
		cafeButton.setPreferredSize(new Dimension(334, 60));
		ImageIcon image4 = new ImageIcon(getClass().getResource("/coffee.png"));
		cafeButton.setIcon(image4);
		northPanel.add(cafeButton);
		cafeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cafe.updateTable();
				validate();
				centerPanel.removeAll();
				centerPanel.add(cafe);
				centerPanel.revalidate();
				Menu.this.repaint();
				try {
					cafe = new Cafe();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		
		JButton vendasButton = new JButton();
		vendasButton.setText("Vendas");
		vendasButton.setFont(titleFont);
		vendasButton.setPreferredSize(new Dimension(334, 60));
		ImageIcon image5 = new ImageIcon(getClass().getResource("/sale.png"));
		vendasButton.setIcon(image5);
		northPanel.add(vendasButton);
		vendasButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				vendas.updateTable();
				centerPanel.removeAll();	
				centerPanel.add(vendas);
				centerPanel.revalidate();
				try {
					vendas = new Vendas();
					validate();	
					Menu.this.repaint();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				validate();	
				Menu.this.repaint();
			}
		});
			
		contentPane.add(northPanel, BorderLayout.NORTH);		
	}
}
