package conexao;

import javax.swing.JOptionPane;
import java.sql.*; //para executar os comandos sql no java
import javax.swing.JFrame;

public class Conexao {
    //'final' na frente de uma declaração de variavel funciona igual ao 'const' no JavaScript
    final private String drive = "com.mysql.cj.jdbc.Driver"; //definindo driver MySql para acesso aos dados do banco
    final private String url = "jdbc:mysql://localhost/bd_pets"; //acesso ao bd 'floricultura' no myAdmin
    final private String usuario = "root";
    final private String senha = "";
    
    private Connection conexao; //variavel que armazenara a conexao aberta
    
    public Statement statement; //variavel para execução dos comandos SQL dentro do Java
    public ResultSet resultset; //variavel que armazenara o resultado dos comandos SQL
    private String sql;
    
    public boolean conecta(){
        boolean result = true;
        try{
            Class.forName(drive);
            conexao = DriverManager.getConnection(url,usuario,senha);
        }catch(ClassNotFoundException Driver){
            JOptionPane.showMessageDialog(null,"Driver não localizado"+Driver,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }catch (SQLException Fonte){
            JOptionPane.showMessageDialog(null,"Fonte de dados não localizada"+Fonte,"Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return false;
    }
    
    public void desconecta() {
        try{
            conexao.close();
            JOptionPane.showMessageDialog(null,"Conexão com o banco fechada","Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }catch(SQLException fecha){
            JOptionPane.showMessageDialog(null,"Erro ao fechar o banco","Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void executaSQL(String sql){
        try{
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        }catch(SQLException excecao){
            JOptionPane.showMessageDialog(null,"Erro no comando SQL \n Erro:"+excecao, "Mensagem do programa",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
        // Método para executar comandos SQL que retornam ResultSet (ex: SELECT)
    public void executaQuery(String sql) {
        try {
            statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(sql);
        } catch (SQLException excecao) {
            JOptionPane.showMessageDialog(null, "Erro no comando SQL \nErro: " + excecao, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para executar comandos SQL que não retornam ResultSet (ex: INSERT, UPDATE, DELETE)
    public int executaUpdate(String sql) {
        try {
            statement = conexao.createStatement();
            return statement.executeUpdate(sql); // Retorna o número de linhas afetadas
        } catch (SQLException excecao) {
            JOptionPane.showMessageDialog(null, "Erro no comando SQL \nErro: " + excecao, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
            return -1; // Retorna -1 em caso de erro
        }
    }
}

