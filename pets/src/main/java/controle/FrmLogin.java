package controle;

import conexao.Conexao;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.sql.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class FrmLogin extends JFrame{
    Conexao con_cliente;
    JPasswordField Psenha;
    JLabel Lpet, Lsenha, Ltitulo, bv, CPets, image, imagem, imgsenha, imgdog;
    JTextField TPet;
    JButton Blogin, sair;
    int verificacao = 3;
    int contador = 3;

public FrmLogin() {
    ImageIcon icon = new ImageIcon("src/icone.png");
    setIconImage(icon.getImage());

    con_cliente = new Conexao();
    con_cliente.conecta();
   
    setTitle("Login de acesso - Pets");
    Container tela = getContentPane();
    setLayout(null);

    Ltitulo = new JLabel("Pet");
    Ltitulo.setForeground(new Color(158, 52, 6));
    Ltitulo.setFont(new Font("Courier New",Font.BOLD,30));
    Ltitulo.setBounds(355,70,300,50);
    
    CPets = new JLabel("Cadastro de Pets");
    CPets.setFont(new Font("Courier New",Font.BOLD,17));
    CPets.setBounds(300,120,200,20);
    
    bv = new JLabel("Bem-Vindo!");
    bv.setForeground(new Color(150, 60, 21));
    bv.setFont(new Font("Courier New",Font.BOLD,12));
    bv.setBounds(355,175,200,20);
    
    Lpet = new JLabel("Nome do Pet:");
//    Lusuario.setFont(new Font("Courier New",Font.BOLD,11));
    Lpet.setBounds(318,230,200,20);
    
    Lsenha = new JLabel("Código:");
//    Lsenha.setFont(new Font("Courier New",Font.BOLD,11));
    Lsenha.setBounds(318,280,100,20);
    
    Psenha = new JPasswordField();
    Psenha.setBackground(new Color(242, 242, 242));
    Psenha.setBounds(315,300,150,20); 
    
    TPet = new JTextField();
    TPet.setBackground(new Color(242, 242, 242));
    TPet.setBounds(315,250,150,20);
    
    Blogin = new JButton("Login");
    Blogin.setBackground(new Color(189, 94, 40)); // cor do botao
    Blogin.setForeground(new Color(247, 247, 247)); // cor da letra
    Blogin.setMnemonic(KeyEvent.VK_L); // atalho
    Blogin.setBounds(330,370,120,25);
    
    sair = new JButton("Sair");
    sair.setBackground(new Color(189, 94, 40)); // cor do botao
    sair.setForeground(new Color(247, 247, 247)); // cor da letra
    sair.setMnemonic(KeyEvent.VK_S); // atalho
    sair.setBounds(330,420,120,25);

    // Implementação do DocumentFilter para permitir apenas letras no campo TPet
((PlainDocument) TPet.getDocument()).setDocumentFilter(new DocumentFilter() {
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
        throws BadLocationException {
            String newText = text.replaceAll("[^a-zA-Z]", "");
            super.replace(fb, offset, length, newText, attrs);
        }
});
 
    Blogin.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        String pesquisa = "select * from registros where nome_pet like '" + TPet.getText() + "' && num_registro = " + Psenha.getText() + "";
                        con_cliente.executaSQL(pesquisa);
                                
                        if (con_cliente.resultset.first())
                        {   // acesso ao form de cadastro    
                            FrmTela mostra = new FrmTela();
                            mostra.setVisible(true);
                            dispose();
                        }
                        else if(contador == 0 | contador == 1 | contador == 2 | contador == 3 )
                        {
                            if(con_cliente.resultset.first() == false) {
                            JOptionPane.showMessageDialog(null, "\n Senha incorreta!! Restam " + contador + " tentativas", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                            contador = contador -1;
                            TPet.setText("");
                            Psenha.setText("");
                        }         
                        
                        if (contador == -1) {
                            con_cliente.desconecta();
                            System.exit(0);
                        }
                        
                    }else {
                            JOptionPane.showMessageDialog(null, "\n Usuarios não cadastrado!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        }
                            
                    }catch(SQLException errosql){
                                JOptionPane.showMessageDialog(null, "\n Os dados digitados não foram localizados!! :\n " +errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }});
    
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
    
    ImageIcon iconee = new ImageIcon("src/CPets.png");
    imagem = new JLabel (iconee);
    imagem.setBounds(600,385,200,180);
    
    ImageIcon iconesenha = new ImageIcon("src/Senha.png");
    imgsenha = new JLabel (iconesenha);
    imgsenha.setBounds(275,295,30,30);
    
    ImageIcon iconedog = new ImageIcon("src/dog.png");
    imgdog = new JLabel (iconedog);
    imgdog.setBounds(275,245,30,30);
    
    tela.add(Psenha);
    tela.add(TPet);
    tela.add(Blogin);
    tela.add(Ltitulo);
    tela.add(Lpet);
    tela.add(Lsenha);
    tela.add(sair);
    tela.add(bv);
    tela.add(CPets);
    tela.add(imagem);
    tela.add(imgsenha);
    tela.add(imgdog);
    
    tela.setBackground(new Color(240, 237, 237));
    setSize(800,600);
    setVisible(true);
    setResizable(false);
    setLocationRelativeTo(null);
    
}public static void main( String args[]){
        FrmLogin app = new FrmLogin();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  
}
