package controle;

import java.awt.*;
import java.text.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import conexao.Conexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
        

import java.sql.*;
import static java.time.temporal.TemporalAdjusters.next;
import java.util.logging.Level;
import java.util.logging.Logger;
        
public class controleP extends JFrame{
    Conexao con_cliente;
    
    JLabel rCodigo, rNome, rEspecie, rRaca, rNasc, rCorPred, rSexo, rPesquisar, rTitulo,imagem, image;
    JTextField tcodigo, tnome, tespecie, traca, tcorpred, tsexo, tPesquisar;
    JFormattedTextField nasc;
    MaskFormatter mNasc;
    JButton Primeiro, Anterior, Proximo, Ultimo, Novo, Gravar, Alterar,Excluir,LimparPesq,LimparForms,Pesquisar,Sair,Voltar;
    JTable tbPets;
    JScrollPane scp_tabela;
    ImageIcon imagens[];
    public controleP(){
        
        con_cliente = new Conexao();
        con_cliente.conecta();
        
        setTitle("Cadastro de Pets");
        Container tela = getContentPane();
        tela.setBackground(new Color(240, 237, 237));
        setLayout(null);
        setResizable(false);
        
        ImageIcon icon = new ImageIcon("src/Ctela.png");
        setIconImage(icon.getImage());
        
        ImageIcon image = new ImageIcon("src/image.png");
        imagem = new JLabel(image);
        imagem.setBounds(495,26,290,290);
        
        rTitulo = new JLabel("Cadastro de Pets");
        rCodigo = new JLabel ("Codigo: ");
        rNome =  new JLabel ("Nome: ");
        rNasc = new JLabel ("Nascimento: ");
        rEspecie = new JLabel ("Espécie: ");
        rRaca = new JLabel ("Raca: ");
        rCorPred = new JLabel ("Cor predominante: ");
        rSexo = new JLabel ("Sexo: ");
        rPesquisar = new JLabel ("Pesquisa pelo nome do Animal:");
        
        rTitulo.setBounds(330,10,200,20);
        rCodigo.setBounds(50,30,80,20);
        rNome.setBounds(50,60,80,20);
        rNasc.setBounds(50,90,80,20);
        rEspecie.setBounds(50,120,180,20);
        rRaca.setBounds(50,150,80,20);
        rCorPred.setBounds(50,180,115,20);
        rSexo.setBounds(50,210,80,20);
        rPesquisar.setBounds(70,330,180,20);
        rTitulo.setFont(new Font("Lucida Sans",Font.BOLD, 14));
           
        tcodigo = new JTextField();
        tnome = new JTextField();
        tespecie = new JTextField();
        traca = new JTextField();
        tcorpred = new JTextField();
        tsexo = new JTextField();
        tPesquisar = new JTextField();
        
        tcodigo.setBounds(110,30,70,20);
        tnome.setBounds(110,60,200,20);
        tespecie.setBounds(115,120,180,20);
        traca.setBounds(115,150,180,20);
        tcorpred.setBounds(165,180,130,20);
        tsexo.setBounds(110,210,80,20);
        tPesquisar.setBounds(250,330,250,20);
        
        //setar antes do try
        nasc = new JFormattedTextField();
        
        try{ 
            mNasc = new MaskFormatter("####-##-##");                                  
        }
        
        catch(ParseException excp){}
        
        nasc = new JFormattedTextField(mNasc);
        nasc.setBounds(145,90,80,20);
       
        Primeiro = new JButton("Primeiro");
        Anterior = new JButton("Anterior");
        Proximo = new JButton("Proximo");
        Ultimo = new JButton("Ultimo");
        
        Novo = new JButton("Registrar");
        Gravar = new JButton("Salvar");
        Alterar = new JButton("Alterar");
        Excluir = new JButton("Excluir");
        
        Pesquisar = new JButton("Pesquisar");
        Sair = new JButton("Sair");
        Voltar = new JButton("Voltar");
        LimparPesq = new JButton("Limpar pesquisa");
        LimparForms = new JButton("Limpar formulário");
        
        Primeiro.setBounds(50,270,85,20);
        Anterior.setBounds(145,270,80,20);
        Proximo.setBounds(235,270,85,20);
        Ultimo.setBounds(330,270,80,20);
        
        Novo.setBounds(420,270,95,20);
        Gravar.setBounds(525,270,75,20);
        Alterar.setBounds(610,270,75,20);
        Excluir.setBounds(695,270,75,20);
        
        Pesquisar.setBounds(505,330,95,20);
        LimparPesq.setBounds(605,330,130,20);
        LimparForms.setBounds(325,210,150,20);
        Voltar.setBounds(300,550,70,20);
        Sair.setBounds(390,550,70,20);
        
        rTitulo.setForeground(new Color(158, 52, 6));
        Primeiro.setBackground(new Color (230, 129, 85));
        Anterior.setBackground(new Color (230, 129, 85));
        Proximo.setBackground(new Color (230, 129, 85));
        Ultimo.setBackground(new Color (230, 129, 85));
        Novo.setBackground(new Color (230, 129, 85));
        Excluir.setBackground(new Color (230, 129, 85));
        Gravar.setBackground(new Color (230, 129, 85));
        Alterar.setBackground(new Color (230, 129, 85));
        
        Pesquisar.setForeground(new Color(247, 247, 247)); // cor da letra
        LimparPesq.setForeground(new Color(247, 247, 247)); // cor da letra
        LimparForms.setForeground(new Color(247, 247, 247)); // cor da letra
        Voltar.setForeground(new Color(247, 247, 247)); // cor da letra
        Sair.setForeground(new Color(247, 247, 247)); // cor da letra
        Pesquisar.setBackground(new Color (189, 94, 40));
        LimparPesq.setBackground(new Color (189, 94, 40));
        LimparForms.setBackground(new Color (189, 94, 40));
        Voltar.setBackground(new Color (189, 94, 40));
        Sair.setBackground(new Color (189, 94, 40));
                
        Primeiro.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if(con_cliente.resultset.first()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o primeiro registro! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        });
        
        Anterior.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if(con_cliente.resultset.previous()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o registro anterior! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        });
        
        Proximo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            
                try {
                    if(con_cliente.resultset.next()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o próximo registro! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
      
        }
        });
        
        Ultimo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    if(con_cliente.resultset.last()==true){
                        mostrar_Dados();
                    }else{
                        JOptionPane.showMessageDialog(null, "Não foi possível encontrar o último registro! Tente outro Botão.");
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
        }
        });
        
        Pesquisar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String pesquisa = "select * from registros where nome_pet like '"+ tPesquisar.getText() +"%'";
                    con_cliente.executaSQL(pesquisa);
                    
                    if(con_cliente.resultset.first()){
                        preencherTabela();
                    }else{
                        JOptionPane.showMessageDialog(null,"\n Não existe dados com este parâmetro!!","Mensagem do Programa",  JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(SQLException errosql){
                     JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        //Limpa somente os registros digitados nas caixas de texto do formulário
        LimparForms.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tcodigo.setText("");
               tnome.setText("");
               nasc.setText("");
               tespecie.setText("");
               traca.setText("");
               tcorpred.setText("");
               tsexo.setText("");
               tcodigo.requestFocus();
            }
        });
        
        LimparPesq.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tPesquisar.setText("");
               tPesquisar.requestFocus();
               
               //Foi necessário implementar essa linha de código para que ao limpar pesquisa a tabela volte a apresentar todos os registros
               try{
                    String pesquisa = "select * from registros where nome_pet like '"+ tPesquisar.getText() +"%'";
                    con_cliente.executaSQL(pesquisa);
                    
                    if(con_cliente.resultset.first()){
                        preencherTabela();
                    }else{
                        JOptionPane.showMessageDialog(null,"\n Não existe dados com este parâmetro!!","Mensagem do Programa",  JOptionPane.INFORMATION_MESSAGE);
                    }
                }catch(SQLException errosql){
                     JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Novo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               tcodigo.setText("");
               tnome.setText("");
               nasc.setText("");
               tespecie.setText("");
               traca.setText("");
               tcorpred.setText("");
               tsexo.setText("");
               tcodigo.requestFocus();
               JOptionPane.showMessageDialog(null,"Os registros foram limpos!\n Agora é possível incluir um novo registro.","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        Gravar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = tnome.getText();
                String nasc = controleP.this.nasc.getText();
                String especie = tespecie.getText();
                String raca = traca.getText();
                String sexo = tsexo.getText();
                String corpred = tcorpred.getText();
        
                try{
                    String insert_sql="insert into registros (nome_pet,especie,raca,cor_pred,nascimento,sexo) values ('"+nome+"','"+especie+"','"+raca+"','"+corpred+"','"+nasc+"','"+sexo+"')";
                    con_cliente.statement.executeUpdate(insert_sql);
                    JOptionPane.showMessageDialog(null,"Gravação realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                    
                    con_cliente.executaSQL("Select * from registros order by num_registro");
                    preencherTabela();
                }catch(SQLException errosql){
                    JOptionPane.showMessageDialog(null, "\n Erro de Gravação :\n"+errosql,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
       
        Alterar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String nome = tnome.getText();
                String nasc = controleP.this.nasc.getText();
                String especie = tespecie.getText();
                String raca = traca.getText();
                String sexo = tsexo.getText();
                String corpred = tcorpred.getText();
                String sql;
                String msg="";
                
                try{
                    if(tcodigo.getText().equals("")){
                        sql="insert into registros (nome_pet,especie,raca,cor_pred,nascimento,sexo) values ('"+nome+"','"+especie+"','"+raca+"','"+corpred+"','"+nasc+"','"+sexo+"')";
                        msg="Gravação de um novo registro";
                    }else{
                        sql="update registros set nome_pet='" + nome + "',especie='" + especie + "', raca='" + raca + "', cor_pred='" + corpred + "', nascimento='" + nasc + "', sexo='" + sexo + "' where num_registro = " + tcodigo.getText();
                        msg="Alteração de registro";
                    }
                    
                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"ALteração realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                    
                    con_cliente.executaSQL("Select * from registros order by num_registro");
                    preencherTabela();
                    
                }catch(SQLException errosql){
                    JOptionPane.showMessageDialog(null, "\n Erro de Alteração :\n"+errosql,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Excluir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                String sql="";
                
                try{
                    int resposta = JOptionPane.showConfirmDialog(rootPane,"Deseja excluir o regsitro: ","Confirmar Exclusão",JOptionPane.YES_NO_OPTION,3);
                    
                    if(resposta==0){
                        sql="delete from registros where num_registro ="+tcodigo.getText();
                        int excluir = con_cliente.statement.executeUpdate(sql);
                        
                        if(excluir==1){
                            JOptionPane.showMessageDialog(null,"Exclusão realizada com sucesso!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                            con_cliente.executaSQL("Select * from registros order by num_registro");
                            con_cliente.resultset.first();
                            preencherTabela();
                            posicionarRegistro();
                        }
                        
                        }else{
                            JOptionPane.showMessageDialog(null,"Operação cancelada pelo usuário!!","Mensagem do Progarama",JOptionPane.INFORMATION_MESSAGE);
                        }
                    
                }catch(SQLException excecao){
                    JOptionPane.showMessageDialog(null, "\n Erro na Exclusão :\n"+excecao,"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Voltar.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrmTela mostraa = new FrmTela();
                mostraa.setVisible(true);
                dispose();
            }
        });
        
        Sair.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        int status = JOptionPane.showConfirmDialog(null,"Deseja realmente fechar o programa?","Mensagem de saída",
        JOptionPane.YES_NO_OPTION);
        if (status == JOptionPane.YES_OPTION){
         System.exit(0);}
        }
        }
        );
        
        tela.add(Primeiro);
        tela.add(Anterior);
        tela.add(Proximo);
        tela.add(Ultimo);
        tela.add(Novo);
        tela.add(Gravar);
        tela.add(Alterar);
        tela.add(Excluir);
        tela.add(Pesquisar);
        tela.add(Sair);
        tela.add(Voltar);
        tela.add(LimparPesq);
        tela.add(LimparForms);
        tela.add(imagem);

//Este código a seguir deverá ser digitado antes da definição do tamanho e visibilidade do formulário, no final do“construtor”:Figuras
    
    //Configuração da JTable
    tbPets = new javax.swing.JTable();
    scp_tabela = new javax.swing.JScrollPane();
    
    tbPets.setBounds(50,320,780,130);
    scp_tabela.setBounds(50,390,700,130);
    
    tela.add(tbPets);
    tela.add(scp_tabela);
        
    tbPets.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
    tbPets.setFont(new java.awt.Font("Arial",1,12));
    
    tbPets.setModel(new javax.swing.table.DefaultTableModel(
    new Object [][] {
        {null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null},
        {null, null, null, null, null, null, null}
        },
     
        new String [] {"Código", "Nome", "Especie", "Raca" ,"Cor", "Nascimento", "Sexo"})
        {
        boolean[] canEdit = new boolean [] {
        false,false,false,false,false,false,false };
        
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];}
    });
        scp_tabela.setViewportView(tbPets);
        
        tbPets.setAutoCreateRowSorter(true);
        
        //fim da config da jlable
     
    tela.add(tcodigo);
    tela.add(tnome);
    tela.add(tespecie);
    tela.add(traca);
    tela.add(tsexo);
    tela.add(tcorpred);
    tela.add(tPesquisar);
    tela.add(nasc);
    
    tela.add(rTitulo);
    tela.add(rCodigo);
    tela.add(rNome);
    tela.add(rEspecie);
    tela.add(rRaca);
    tela.add(rNasc);
    tela.add(rSexo);
    tela.add(rCorPred);
    tela.add(rPesquisar);
    
    setSize(800,700);
    setVisible(true);
    setLocationRelativeTo(null);
        
    con_cliente.executaSQL("Select * from registros order by num_registro");
    preencherTabela();
    posicionarRegistro();
}
    public void preencherTabela(){
                    tbPets.getColumnModel().getColumn(0).setPreferredWidth(6);
                    tbPets.getColumnModel().getColumn(1).setPreferredWidth(15);
                    tbPets.getColumnModel().getColumn(2).setPreferredWidth(18);
                    tbPets.getColumnModel().getColumn(3).setPreferredWidth(18);
                    tbPets.getColumnModel().getColumn(4).setPreferredWidth(14);
                    tbPets.getColumnModel().getColumn(5).setPreferredWidth(10);
                    tbPets.getColumnModel().getColumn(6).setPreferredWidth(10);
                    
                    DefaultTableModel modelo = (DefaultTableModel) tbPets.getModel();
                    modelo.setNumRows(0);
                    
                    try {
                        con_cliente.resultset.beforeFirst();
                        while(con_cliente.resultset.next()){
                            modelo.addRow(new Object[]{
                                con_cliente.resultset.getString("num_registro"),con_cliente.resultset.getString("nome_pet"),con_cliente.resultset.getString("especie"),con_cliente.resultset.getString("raca"),con_cliente.resultset.getString("cor_pred"),con_cliente.resultset.getString("nascimento"),con_cliente.resultset.getString("sexo")    
                            });
                        }
                    }catch(SQLException erro){
                        JOptionPane.showMessageDialog(null, "\n Erro ao listar dados na tabela!! :\n " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                    }}
                    
                    public void posicionarRegistro(){
                        try{
                            con_cliente.resultset.first(); //posiciona no 1º registro da tabela
                            mostrar_Dados(); // chama o método que irá buscar o dado da tabela
                        }catch (SQLException erro){
                            JOptionPane.showMessageDialog(null, "Não foi possivel posicionar o 1º registro:" +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        }}
                        
                        public void mostrar_Dados(){
                            try{
                                tcodigo.setText(con_cliente.resultset.getString("num_registro")); // associar a caixa de texto ao campo cod
                                tnome.setText(con_cliente.resultset.getString("nome_pet")); // associar a caixa de texto ao campo nome
                                tespecie.setText(con_cliente.resultset.getString("especie"));
                                traca.setText(con_cliente.resultset.getString("raca"));
                                tcorpred.setText(con_cliente.resultset.getString("cor_pred"));
                                nasc.setText(con_cliente.resultset.getString("nascimento"));
                                tsexo.setText(con_cliente.resultset.getString("sexo"));
                            
                                
                            }catch (SQLException erro){
                                JOptionPane.showMessageDialog(null, "Os dados não foram localizados! " +erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                       
}
