package br.edu.univas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import br.edu.univas.dao.DadosDAO;

public class Dashboard extends JPanel{
	
	private DadosDAO dadosDAO;
	private String s1;
	private String s3;
	private String s4;
	private JLabel Totala;
	
	ImageIcon dash = new ImageIcon(getClass().getResource("/dashboard.png"));
	JLabel label = new JLabel(dash);
	
	public Dashboard() throws SQLException {
		dadosDAO = new DadosDAO();
		initialize();
	}

	private void initialize() {
		
		this.setLayout(new BorderLayout());	
		createWestPanel();
		createCenterPanel();	
		
	}

	private void createWestPanel() {
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(200, 0));
		westPanel.setBackground(new Color(0, 100, 250));
		westPanel.setLayout(new BorderLayout());
		westPanel.add(label);
		
		this.add(westPanel, BorderLayout.WEST);
		
	}
	
	private void createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(0, 0));
		centerPanel.setBackground(Color.LIGHT_GRAY);	
		centerPanel.setLayout(new BorderLayout());	
		Font titleFont = new Font("ARIAL", Font.BOLD, 80);
		
		
		JPanel centerNorth = new JPanel();
		centerNorth.setPreferredSize(new Dimension(0, 318));
		centerNorth.setBackground(Color.BLACK);
		centerNorth.setLayout(new BorderLayout());
		centerPanel.add(centerNorth, BorderLayout.NORTH);
		
		
		JPanel centerNorthWest = new JPanel();
		centerNorthWest.setPreferredSize(new Dimension(503, 300));
		centerNorthWest.setBackground(new Color(120, 255, 255));
		centerNorthWest.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JLabel TotalMedidas = new JLabel();
		TotalMedidas.setText("Total de medidas:");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerNorthWest.add(TotalMedidas, gbc);
		
		s1 = Float.toString(dadosDAO.getAllTM());
		
		JLabel Total = new JLabel();
		Total.setText(s1);
		Total.setFont(titleFont);
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerNorthWest.add(Total, gbc);	
		centerNorth.add(centerNorthWest, BorderLayout.WEST);
		
		
	/////////////////////////////////////////////////////////////////////////////////////	
		JPanel centerNorthEast = new JPanel();
		centerNorthEast.setPreferredSize(new Dimension(663, 300));
		centerNorthEast.setBackground(new Color(120, 220, 160));	
		centerNorthEast.setLayout(new GridBagLayout());
		
		
		JLabel TotalArrecadado = new JLabel();
		TotalArrecadado.setText("Total arrecadado:");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerNorthEast.add(TotalArrecadado, gbc);		
			
		String s2 = Float.toString(dadosDAO.getAllTA());
		
		Totala = new JLabel();
		Totala.setText(s2);
		Totala.setFont(titleFont);
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerNorthEast.add(Totala, gbc);	
		centerNorth.add(centerNorthEast, BorderLayout.EAST);
		
		
	/////////////////////////////////////////////////////////////////////////////////////	
		
		JPanel centerSouth = new JPanel();
		centerSouth.setPreferredSize(new Dimension(0, 318));
		centerSouth.setBackground(Color.WHITE);
		centerSouth.setLayout(new BorderLayout());
		centerPanel.add(centerSouth, BorderLayout.SOUTH);

		
		JPanel centerSountWest = new JPanel();
		centerSountWest.setPreferredSize(new Dimension(503, 300));
		centerSountWest.setBackground(new Color(120, 180, 180));		
		centerSountWest.setLayout(new GridBagLayout());

		
		JLabel CustoTotal = new JLabel();
		CustoTotal.setText("Custo Total:");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerSountWest.add(CustoTotal, gbc);	
		
		s3 = Float.toString(dadosDAO.getAllCT());
		
		JLabel Totalb = new JLabel();
		Totalb.setText(s3);
		Totalb.setFont(titleFont);
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerSountWest.add(Totalb, gbc);	
		
		centerSouth.add(centerSountWest, BorderLayout.WEST);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
		JPanel centerSountEast = new JPanel();
		centerSountEast.setPreferredSize(new Dimension(663, 300));
		centerSountEast.setBackground(new Color(120, 140, 250));		
		centerSountEast.setLayout(new GridBagLayout());
		
		
		JLabel Lucro = new JLabel();
		Lucro.setText("Lucro da produção:");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerSountEast.add(Lucro, gbc);		
		
		s4 = Float.toString(dadosDAO.getAllLP());
		
		JLabel Totall = new JLabel();
		Totall.setText(s4);
		Totall.setFont(titleFont);
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.weightx=0.0;
		gbc.fill= GridBagConstraints.NONE;
		centerSountEast.add(Totall, gbc);	
		
		centerSouth.add(centerSountEast, BorderLayout.EAST);
		
		this.add(centerPanel, BorderLayout.CENTER);	
		
	}
}
