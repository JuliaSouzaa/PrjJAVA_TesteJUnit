package controle;

import conexao.Conexao;
import javax.swing.JOptionPane;
import java.sql.*;
import java.awt.*;
import java.text.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.border.LineBorder;

 

public class FrmTela extends JFrame {
    Conexao con_cliente;

    JLabel imagemE, imagemD, cadastroI, sobreI, icon, sairI;
    JButton cadCli, sobre, sair; 
    JMenu menuatalhos, menusobre, menusair;
    JMenuItem menucadastro, menusobree, menusai;

    public FrmTela()
    {
        ImageIcon icon = new ImageIcon("src/icone.png");
        setIconImage(icon.getImage());

        ImageIcon sairr = new ImageIcon("src/sair.png");
        sairI = new JLabel (sairr);
        sairI.setBounds(350,290,40,40);

        ImageIcon sob = new ImageIcon("src/sobree.png");
        sobreI = new JLabel (sob);
        sobreI.setBounds(350,210,40,40);

        ImageIcon cad = new ImageIcon("src/cadastrarr.png");
        cadastroI = new JLabel (cad);
        cadastroI.setBounds(345,130,50,50);

        ImageIcon flore = new ImageIcon("src/flower_esquerda.png");
        imagemE = new JLabel (flore);
        imagemE.setBounds(-10,395,100,200);

        ImageIcon flord = new ImageIcon("src/flower_direita.png");
        imagemD = new JLabel (flord);
        imagemD.setBounds(845,-30,140,170);

        con_cliente = new Conexao();
        con_cliente.conecta(); 

        setTitle("Cadastro de Pets");
        Container tela = getContentPane();
        setLayout(null);

        cadCli = new JButton("Cadastrar Pet");
        sobre = new JButton("Sobre");
        sair = new JButton("Sair");
        cadCli.setBounds(400,130,200,40);
        sobre.setBounds(400,210,200,40);
        sair.setBounds(400,290,200,40);

        cadCli.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                controleP mostra = new controleP();
                mostra.setVisible(true);
                dispose();
            }
        });

        sobre.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Sobre mostrab = new Sobre();
                mostrab.setVisible(true);
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

        cadCli.setBackground(new Color(189, 94, 40));
        sobre.setBackground(new Color(189, 94, 40));
        sair.setBackground(new Color(189, 94, 40));

        cadCli.setForeground(new Color(246, 246, 246));
        sobre.setForeground(new Color(246, 246, 246));
        sair.setForeground(new Color(246, 246, 246));

        //JMenubar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuatalhos = new JMenu("Atalhos");
        menuBar.add(menuatalhos);

        //itens do jmenu atalhos
        menucadastro = new JMenuItem("Cadastro de pets");
        menuatalhos.add(menucadastro);
        
        //açao
        menucadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controleP mostra = new controleP();
                mostra.setVisible(true);
            }
        });

        menusair = new JMenu("Sair");
        
        //itens do jmenu sair
        menusai = new JMenuItem("Sair");
        menusair.add(menusai);
        
        //ação
        menusai.addActionListener( new ActionListener(){
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

        menusobre = new JMenu("Sobre");
        menuBar.add(menusobre);
        
        //itens do jmenu sobre
        menusobree = new JMenuItem("Sobre");
        menusobre.add(menusobree);
        
        //ação
        menusobree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sobre mostra = new Sobre();
                mostra.setVisible(true);
            }
        }); 
        menuBar.add(menusobre);
        menuBar.add(menusair);

        //encerra JMenu

        menucadastro.setMnemonic(KeyEvent.VK_C);
        menusobree.setMnemonic(KeyEvent.VK_S);
        menusai.setMnemonic(KeyEvent.VK_A);
        menuatalhos.setMnemonic(KeyEvent.VK_T);
        menusobre.setMnemonic(KeyEvent.VK_O);
        menusair.setMnemonic(KeyEvent.VK_R);
        cadCli.setMnemonic(KeyEvent.VK_L);
        sobre.setMnemonic(KeyEvent.VK_B);

        tela.add(cadCli);
        tela.add(sair);
        tela.add(sobre);
        tela.add(imagemE);
        tela.add(imagemD);
        tela.add(cadastroI);
        tela.add(sobreI);
        tela.add(sairI);

        setSize(1000,600);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
   
}