package br.edu.univas.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.edu.univas.dao.ConnectionUtil;

public class Login extends JFrame{
	
	private JTextField nameTextField;
	private JTextField senhaTextField; 
	
	public Login() {
		setSize(500, 340);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initialize();
	}

	private void initialize() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		Font titleFont = new Font("ARIAL", Font.BOLD, 40);
		
		JLabel LoginLabel = new JLabel();
		LoginLabel.setText("Login");
		LoginLabel.setFont(titleFont);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(15, 15, 15, 15);
		this.add(LoginLabel, gbc);
		
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Usuário:");
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
		
		senhaTextField = new JPasswordField();
		gbc.gridx= 1;
		gbc.gridy= 2;
		gbc.weightx= 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(senhaTextField, gbc);
		
		JButton loginButton = new JButton();
		loginButton.setText("Entrar");
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = ConnectionUtil.getConnection();
					
					String sql = "select * from login where usuario=? and senha=?";
					
					
					PreparedStatement statement = con.prepareStatement(sql);
					int index=1;
					statement.setString(index++, nameTextField.getText());
					statement.setString(index++, senhaTextField.getText());
				
					
					ResultSet result = statement.executeQuery();
					if(result.next()) {
						
						Menu menu = new Menu();
						setVisible(false);
						menu.setVisible(true);	
						
					}else {
						JOptionPane.showMessageDialog(null, "Usuario ou senha incorreto");
					}
					statement.close();
					con.close();
						
				} catch (SQLException e1) {
					// TODO: handle exception
				}			
			}
		});
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(loginButton, gbc);
		
		JButton cadastroButton = new JButton();
		cadastroButton.setText("Cadastrar");
		
		
		cadastroButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Cadastro telaCadastro = new Cadastro();
				setVisible(false);	
				telaCadastro.setVisible(true);
				
			}
		});
		gbc.gridx=0;
		gbc.gridy=4;
		gbc.gridwidth = 2;
		gbc.weightx = 0.0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		this.add(cadastroButton, gbc);
		
	}
}
