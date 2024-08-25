package controle;

import conexao.Conexao;
import java.sql.SQLException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/*
setUp(): Antes de cada teste, uma instância de controleP é criada e um registro de teste é inserido no banco de dados.

tearDown(): Após cada teste, o registro de teste é removido e a conexão com o banco de dados é fechada.

testCreateRecord(): Testa a criação de um novo registro no banco de dados e verifica se os dados foram inseridos corretamente.

testReadRecord(): Testa a leitura de um registro existente e verifica se os dados são exibidos corretamente na interface.

testUpdateRecord(): Testa a atualização de um registro existente e verifica se as alterações foram salvas corretamente.

testDeleteRecord(): Testa a exclusão de um registro existente e verifica se ele foi removido corretamente.

assertTrue(condition)*: Verifica se a condição fornecida é verdadeira. Se a condição for falsa, o teste falha. 

assertEquals(expected, actual)*: Compara o valor esperado (expected) com o valor real (actual). Se os valores forem diferentes, o teste falha. 
*/

public class controlePTest {

    private controleP instance;
    private Conexao con;

    @BeforeAll
    public static void setUpClass() {
        // Executado uma vez antes de todos os testes
    }

    @AfterAll
    public static void tearDownClass() {
        // Executado uma vez depois de todos os testes
    }

    @BeforeEach
    public void setUp() {
        instance = new controleP();
        con = new Conexao();
        con.conecta();

        // Insere um registro de teste no banco de dados antes de cada teste
        con.executaUpdate("INSERT INTO registros (nome_pet, especie, raca, cor_pred, nascimento, sexo) " +
                "VALUES ('TestePet', 'Cachorro', 'Vira-Lata', 'Marrom', '2020-01-01', 'M')");
    }

    @AfterEach
    public void tearDown() {
        // Remove registros de teste do banco de dados após cada teste
        con.executaUpdate("DELETE FROM registros WHERE nome_pet = 'TestePet'");
        con.desconecta();
    }

    @Test
    public void testGravar_Create() {
        System.out.println("Teste Gravar Registro");

        String nome = "NovoPet";
        String especie = "Gato";
        String raca = "Siamês";
        String cor = "Branco";
        String nasc = "2021-05-10";
        String sexo = "F";

        // Configura os campos na UI
        instance.tnome.setText(nome);
        instance.tespecie.setText(especie);
        instance.traca.setText(raca);
        instance.tcorpred.setText(cor);
        instance.nasc.setText(nasc);
        instance.tsexo.setText(sexo);

        // Executa o método para gravar no banco
        instance.Gravar.doClick();

        // Verifica se o registro foi adicionado
        con.executaQuery("SELECT * FROM registros WHERE nome_pet = '" + nome + "'");
        try {
            assertTrue(con.resultset.next());
            assertEquals(nome, con.resultset.getString("nome_pet"));
            assertEquals(especie, con.resultset.getString("especie"));
            assertEquals(raca, con.resultset.getString("raca"));
            assertEquals(cor, con.resultset.getString("cor_pred"));
            assertEquals(nasc, con.resultset.getString("nascimento"));
            assertEquals(sexo, con.resultset.getString("sexo"));
            System.out.println("Gravado com sucesso!");
        } catch (SQLException e) {
            fail("Erro ao consultar o banco de dados: " + e.getMessage());
        }
    }

    @Test
    public void testPesquisar_Listar() {
        System.out.println("Teste Pesquisar Registro");

        // Configura o campo de pesquisa e executa o método
        instance.tPesquisar.setText("NovoPet");
        instance.Pesquisar.doClick();

        // Verifica se o registro foi lido corretamente
        
        assertEquals("NovoPet", instance.tnome.getText());
        assertEquals("Gato", instance.tespecie.getText());
        assertEquals("Siamês", instance.traca.getText());
        assertEquals("Branco", instance.tcorpred.getText());
        assertEquals("2021-05-10", instance.nasc.getText());
        assertEquals("F", instance.tsexo.getText());
        
    }

    @Test
    public void testAlterar() {
        System.out.println("Teste Alterar Registro");

        // Altera os dados do pet "TestePet"
        instance.tnome.setText("TestePet");
        instance.Pesquisar.doClick(); // Pesquisar para garantir que o registro está carregado

        String novoNome = "TestePetAtualizado";
        instance.tnome.setText(novoNome);
        instance.Alterar.doClick();

        // Verifica se o registro foi atualizado
        con.executaQuery("SELECT * FROM registros WHERE nome_pet = '" + novoNome + "'");
        try {
            assertTrue(con.resultset.next());
            assertEquals(novoNome, con.resultset.getString("nome_pet"));
        } catch (SQLException e) {
            fail("Erro ao consultar o banco de dados: " + e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        System.out.println("Teste Deletar Registro");

        // Configura o campo de pesquisa e executa o método para excluir
        instance.tnome.setText("TestePetAtualizado");
        instance.Pesquisar.doClick();
        instance.Excluir.doClick();

        // Verifica se o registro foi removido
        con.executaQuery("SELECT * FROM registros WHERE nome_pet = 'TestePetAtualizado'");
        try {
            assertFalse(con.resultset.next(), "Registro não foi excluído.");
        } catch (SQLException e) {
            fail("Erro ao consultar o banco de dados: " + e.getMessage());
        }
    }
}
