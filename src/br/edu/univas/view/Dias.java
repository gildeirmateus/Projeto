package br.edu.univas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import br.edu.univas.dao.DadosDAO;
import br.edu.univas.vo.DiasDados;


public class Dias extends JPanel{
	
	private JTable dadosTable;
	private DadosDAO dadosDAO;
	private JTextField nameT;
	private JTextField quantMedidasT;
	
	public Dias() throws SQLException  {
		dadosDAO = new DadosDAO();
		initialize();
	}

	private void initialize() {		
		this.setLayout(new BorderLayout());	
		createWestPanel();
		createEastPanel();
	}
	private void createWestPanel() {
		JPanel westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(683, 0));
		westPanel.setBackground(new Color(120, 180, 255));
		this.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JLabel name = new JLabel();
		name.setText("Trabalhou Para:");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.0;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill=GridBagConstraints.NONE;
		westPanel.add(name, gbc);
		
		nameT = new JTextField();
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.weightx=1.0;
		gbc.fill=GridBagConstraints.BOTH;
		westPanel.add(nameT, gbc);
		
		JLabel quantMedidas = new JLabel();
		quantMedidas.setText("Quantidade de medidas:");
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.weightx=0.0;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.NONE;
		westPanel.add(quantMedidas, gbc);
		
		quantMedidasT = new JTextField();
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.weightx=1.0;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.BOTH;
		westPanel.add(quantMedidasT, gbc);
		
		JButton addButton = new JButton();
		addButton.setText("Salvar");
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.weightx=0.0;
		gbc.gridwidth=2;
		gbc.fill=GridBagConstraints.NONE;
		gbc.anchor= GridBagConstraints.CENTER;
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					saveDados();
					updateTable();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		westPanel.add(addButton, gbc);
		this.add(westPanel, BorderLayout.WEST);
		
	}
	
	private void saveDados() throws SQLException {
		
		if (validateFields()) {
			DiasDados dados = new DiasDados();
			dados.setNome(nameT.getText());				
			Float quantidade = Float.parseFloat(quantMedidasT.getText());
			dados.setQuantidadeM(quantidade);
			dadosDAO.save(dados);
			
			clearFields();
			JOptionPane.showMessageDialog(this, "Dia cadastrado com sucesso!",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) dadosTable.getModel();
		tableModel.setRowCount(0);
		
		for(DiasDados diasD: dadosDAO.getAll()) {
			Object[] data = {
						diasD.getNome(),
						diasD.getQuantidadeM(),
			};
			tableModel.addRow(data);
		}	
		
	}

	private void createEastPanel() {
		JPanel eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(683, 0));
		eastPanel.setBackground(new Color(120, 180, 255));
		eastPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		Font titleFont = new Font("ARIAL", Font.PLAIN, 13);
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Trabalhou para");
		columnNames.add("Quantidade de medidas");
		
		Vector<? extends Vector> vector = new Vector();
		dadosTable = new JTable(vector, columnNames);
		dadosTable.setFont(titleFont);
		JScrollPane dadosTableScroll = new JScrollPane(dadosTable);
		dadosTableScroll.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		dadosTableScroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		dadosTableScroll.setMaximumSize(new Dimension(683, 0));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		eastPanel.add(dadosTableScroll, gbc);
		
		this.add(eastPanel, BorderLayout.EAST);
	}
	
	private boolean validateFields() {
		if (nameT.getText() == null || nameT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o nome", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			nameT.requestFocus();
			return false;
		}
		if (quantMedidasT.getText() == null || quantMedidasT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a quantidade", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			quantMedidasT.requestFocus();
			return false;
		}
		return true;
	}

	private void clearFields() {
		nameT.requestFocus();
		nameT.setText(null);
		quantMedidasT.setText(null);
	}
}