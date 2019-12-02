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
import br.edu.univas.vo.VendasDados;

public class Vendas extends JPanel{
	private JTable vendasTable;
	private DadosDAO dadosDAO;
	private JTextField QuantCafeVendidoT;
	private JTextField ValorT;
	
	public Vendas() throws SQLException {
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
		
		JLabel QuantCafeVendido = new JLabel();
		QuantCafeVendido.setText("Quantidade de café vendido:");
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx=0.0;
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill=GridBagConstraints.NONE;
		westPanel.add(QuantCafeVendido, gbc);
		
		QuantCafeVendidoT = new JTextField();
		gbc.gridx=1;
		gbc.gridy=0;
		gbc.weightx=1.0;
		gbc.fill=GridBagConstraints.BOTH;
		westPanel.add(QuantCafeVendidoT, gbc);
		
		JLabel Valor = new JLabel();
		Valor.setText("Valor da medida:");
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.weightx=0.0;
		gbc.fill=GridBagConstraints.NONE;
		westPanel.add(Valor, gbc);
		
		ValorT = new JTextField();
		gbc.gridx=1;
		gbc.gridy=2;
		gbc.weightx=1.0;
		gbc.fill=GridBagConstraints.BOTH;
		westPanel.add(ValorT, gbc);
		
		
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
				VendasDados dados = new VendasDados();
				Integer quant = Integer.parseInt(QuantCafeVendidoT.getText());
				dados.setQuantCafeVendido(quant);	
				Float valor = Float.parseFloat(ValorT.getText());
				dados.setValor((valor));
				dadosDAO.saveVendas(dados);
				
				clearFields();
				JOptionPane.showMessageDialog(this, "Dia cadastrado com sucesso!",
											"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void updateTable() {
		DefaultTableModel tableModel = (DefaultTableModel) vendasTable.getModel();
		tableModel.setRowCount(0);
		
		for(VendasDados vendasD: dadosDAO.getVendas()) {
			Object[] data = {
						vendasD.getQuantCafeVendido(),
						vendasD.getValor(),
					
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
		columnNames.add("Quantidde de café vendido");
		columnNames.add("Valor");
		
		Vector<? extends Vector> vector = new Vector();
		vendasTable = new JTable(vector, columnNames);
		vendasTable.setFont(titleFont);
		JScrollPane caféTableScroll = new JScrollPane(vendasTable);
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
		if (QuantCafeVendidoT.getText() == null ||QuantCafeVendidoT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha as medidas", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			QuantCafeVendidoT.requestFocus();
			return false;
		}
		if (ValorT.getText() == null || ValorT.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o valor", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			ValorT.requestFocus();
			return false;
		}
	
		return true;
	}

	private void clearFields() {
		QuantCafeVendidoT.requestFocus();
		QuantCafeVendidoT.setText(null);
		ValorT.setText(null);
	
	}
}
