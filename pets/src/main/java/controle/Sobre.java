package controle;

import conexao.Conexao;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Sobre extends JFrame {
    JButton sair, voltar;
    JLabel nos, tema, serie;
    
    public Sobre()
    {
    ImageIcon icon = new ImageIcon("src/sobree.png");
    setIconImage(icon.getImage());    
        
    setTitle("Sobre nós");
    Container tela = getContentPane();
    setLayout(null);
        
    nos = new JLabel("Desenvolvido pela aluna: Ana Julia Gonçalves de Souza");
    tema = new JLabel("Programa para manutenção de dados");
    serie = new JLabel("2° Desenvolvimento de sistemas.  2023");
    
    sair = new JButton("Sair");
    voltar = new JButton("Voltar");
    
    nos.setBounds(50,100,1000,50);
    tema.setBounds(50,150,1000,50);
    serie.setBounds(50,200,500,50);
    
    sair.setBounds(400,430,150,30);
    voltar.setBounds(400,480,150,30);
    
    nos.setFont(new Font("Arial", Font.BOLD,20));
    tema.setFont(new Font("Arial", Font.BOLD,20));
    serie.setFont(new Font("Arial", Font.BOLD,20));
    
    voltar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmTela mostraa = new FrmTela();
                mostraa.setVisible(true);
                dispose();
            }
        });
    
    sair.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int opcao;
                Object[] botoes = {"Sim","Não"};
                opcao = JOptionPane.showOptionDialog(null,"Deseja mesmo fechar a janela?","Fechar",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,botoes,botoes[0]);
                    if(opcao == JOptionPane.YES_OPTION){
                        System.exit(0);
                    }else{
                        JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuario","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
                    }
            }
        });
    
    tela.setBackground(new Color(246, 246, 246));
    sair.setForeground(new Color(246, 246, 246));
    voltar.setForeground(new Color(246, 246, 246));
    
    sair.setBackground(new Color(189, 94, 40));
    voltar.setBackground(new Color(189, 94, 40));
    
    tela.add(sair);
    tela.add(voltar);
    tela.add(nos);
    tela.add(tema);
    tela.add(serie);
    
    setSize(1000,600);
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    }
  
}
