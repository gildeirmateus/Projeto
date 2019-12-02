package br.edu.univas.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.edu.univas.dao.ConnectionUtil;

public class Cadastro extends JFrame{
	
	private JTextField nameTextField;
	private JTextField senhaTextField; 
	private JTextField repetirSenhaTextField;
	
	public Cadastro() {
		setSize(500, 340);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initialize();
		
	}

	private void initialize() {
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		Font titleFont = new Font("ARIAL", Font.BOLD, 40);
		
		JLabel cadastroLabel = new JLabel();
		cadastroLabel.setText("Cadastrar");
		cadastroLabel.setFont(titleFont);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.add(cadastroLabel, gbc);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Usu�rio:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(nameLabel, gbc);
		
		nameTextField = new JTextField();
		gbc.gridx= 1;
		gbc.gridy= 1;
		gbc.weightx= 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(nameTextField, gbc);
		
		JLabel senhaLabel = new JLabel();
		senhaLabel.setText("Senha:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.0;
		this.add(senhaLabel, gbc);
		
		senhaTextField = new JTextField();
		gbc.gridx= 1;
		gbc.gridy= 2;
		gbc.weightx= 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(senhaTextField, gbc);
		
		JLabel repetirSenhaLabel = new JLabel();
		repetirSenhaLabel.setText("Repita a senha:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.0;
		this.add(repetirSenhaLabel, gbc);
		
		repetirSenhaTextField = new JTextField();
		gbc.gridx= 1;
		gbc.gridy= 3;
		gbc.weightx= 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(repetirSenhaTextField, gbc);
		
		JButton saveButton = new JButton();
		saveButton.setText("Salvar");
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = ConnectionUtil.getConnection();
					
					String sql = "insert into login(usuario, senha) values (?, ?)";
					
					PreparedStatement statement = con.prepareStatement(sql);
					
					if(Eigual()) {
						if(validateFields()) {
						
							int index=1;
							statement.setString(index++, nameTextField.getText());
							statement.setString(index++, senhaTextField.getText());
							statement.executeUpdate();
							statement.close();
							con.close();
							
							JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
							Login telaLogin = new Login();
							setVisible(false);
							telaLogin.setVisible(true);			
						}
					}else {
						JOptionPane.showMessageDialog(null, "senha incorreta!");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(saveButton, gbc);
	}
	
	public boolean validateFields() {
		if (nameTextField.getText() == null ||nameTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha o usuario", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			nameTextField.requestFocus();
			return false;
		}
		if (senhaTextField.getText() == null || senhaTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a data", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			senhaTextField.requestFocus();
			return false;
		}
		if (repetirSenhaTextField.getText() == null || repetirSenhaTextField.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, preencha a data", "Campo vazio", JOptionPane.WARNING_MESSAGE);
			repetirSenhaTextField.requestFocus();
			return false;
		}
		return true;
	}
	public boolean Eigual() {
		if (senhaTextField.getText().equals(repetirSenhaTextField.getText())) {
			return true;
		}
		return false;
	}
}
