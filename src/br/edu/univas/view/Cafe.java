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
import br.edu.univas.vo.CafeDados;

public class Cafe extends JPanel{

	private DadosDAO dadosDAO;
	private JTextField medidasT;
	private JTable caféTable;
	private JTextField medidasPagarT;
	private JTextField ValormedidasT;
	
	public Cafe() throws SQLException {
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
		
		
		JLabel medidas = new JLabel();
		medidas.setText("Medidas colhidas:");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.0;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill=GridBagConstraints.NONE;
		westPanel.add(medidas, gbc);
		
		medidasT = new JTextField();
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.weightx=1.0;
		gbc.fill=GridBagConstraints.BOTH;
		westPanel.add(medidasT, gbc);
		
		JLabel medidasPagar = new JLabel();
		medidasPagar.setText("Medidas a pagar:");
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.weightx=0.0;
		gbc.fill=GridBagConstraints.NONE;
		westPanel.add(medidasPagar, gbc);
		
		medidasPagarT = new JTextField();
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.weightx=1.0;
		gbc.fill=GridBagConstraints.BOTH;
		westPanel.add(medidasPagarT, gbc);
		
		JLabel Valormedidas = new JLabel();
		Valormedidas.setText("Valor da medida:");
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.weightx=0.0;
		gbc.fill=GridBagConstraints.NONE;
		westPanel.add(Valormedidas, gbc);
		
		ValormedidasT = new JTextField();
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.weightx=1.0;
		gbc.fill=GridBagConstraints.BOTH;
		westPanel.add(ValormedidasT, gbc);
		
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
			CafeDados dados = new CafeDados();
			Float medida = Float.parseFloat(medidasT.getText());
			dados.setMedidas(medida);	
			Integer medidaPagar = Integer.parseInt(medidasPagarT.getText());
			dados.setMedidasPagar(medidaPagar);	
			Float valor = Float.parseFloat(ValormedidasT.getText());
			dados.setValorMedida((valor));
			dadosDAO.saveCafe(dados);
			
			clearFields();
			JOptionPane.showMessageDialog(this, "Café cadastrado com sucesso!",
										"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) caféTable.getModel();
		tableModel.setRowCount(0);
		
		for(CafeDados cafeD: dadosDAO.getCafé()) {
			Object[] data = {
						cafeD.getMedidas(), 
						cafeD.getMedidasPagar(),
						cafeD.getValorMedida()
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
		columnNames.add("Medidas colhidas");
		columnNames.add("Medidas a pagar");
		columnNames.add("Valor da medida");
		
		Vector<? extends Vector> vector = new Vector();
		caféTable = new JTable(vector, columnNames);
		caféTable.setFont(titleFont);
		JScrollPane caféTableScroll = new JScrollPane(caféTable);
		caféTableScroll.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		caféTableScroll.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		caféTableScroll.setMaximumSize(new Dimension(683, 0));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		eastPanel.add(caféTableScroll, gbc);
		
		this.add(eastPanel, BorderLayout.EAST);
	}
	
	private boolean validateFields() {
		if (medidasT.getText() == null ||medidasT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha as medidas", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			medidasT.requestFocus();
			return false;
		}
		if (medidasPagarT.getText() == null || medidasPagarT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a quantidade de medidas a pagar", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			medidasPagarT.requestFocus();
			return false;
		}
		if (ValormedidasT.getText() == null || ValormedidasT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o valor", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			ValormedidasT.requestFocus();
			return false;
		}
		return true;
	}

	private void clearFields() {
		medidasT.requestFocus();
		medidasT.setText(null);
		medidasPagarT.setText(null);
		ValormedidasT.setText(null);
	}
}
