
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;

public class Impressora {

    // Informações utilizadas para configurar a conexão com a impressora
    int tipo = 1;
    String modelo = "i9";
    String conexao = "USB";
    int parametro = 0;
    boolean conexaoAberta = false; // Indica se a conexão está aberta

    String assinaturaQRCode =
            "Q5DLkpdRijIRGY6YSSNsTWK1TztHL1vD0V1Jc4spo/CEUqICEb9SFy82ym8EhBRZjbh3btsZhF+sjHqEMR159i4agru9x6KsepK/q0" +
                    "E2e5xlU5cv3m1woYfgHyOkWDNcSdMsS6bBh2Bpq6s89yJ9Q6qh/J8YHi306ce9Tqb/drKvN2XdE5noRSS32TAWuaQEVd7u+TrvXlOQ" +
                    "sE3fHR1D5f1saUwQLPSdIv01NF6Ny7jZwjCwv1uNDgGZONJdlTJ6p0ccqnZvuE70aHOI09elpjEO6Cd+orI7XHHrFCwhFhAcbalc+Z" +
                    "fO5b/+vkyAHS6CYVFCDtYR9Hi5qgdk31v23w==";


    public void configurarConexao(int novoTipo, String novoModelo, String novaConexao, int novoParametro) {
        tipo = novoTipo;
        modelo = novoModelo;
        conexao = novaConexao;
        parametro = novoParametro;

        System.out.println("Conexão configurada!");
    }

    public void abrirConexao() {
        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo, modelo, conexao, parametro);

            if (retorno == 0) {
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso!");
            } else {
                System.out.println("Erro ao abrir conexão. Código: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    // Os demais métodos seguem esse mesmo padrão, incluindo a verificação do retorno e o corte do papel quando necessário.

    public void fecharConexao() {
        if (!conexaoAberta) {
            System.out.println("A conexão já está fechada.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();

            if (retorno == 0) {
                conexaoAberta = false;
                System.out.println("Conexão fechada com sucesso!");
            } else {
                System.out.println("Erro ao fechar conexão. Código: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public boolean verificarConexao() {
        if (!conexaoAberta) {
            System.out.println("Conexão fechada. Abra a conexão antes.");
            return false;
        }
        return true;
    }

    public void imprimirTexto(String texto) {
        if (!verificarConexao()) {
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoTexto(texto, 0, 0, 0);

            if (retorno < 0) {
                System.out.println("Erro na impressão: " + retorno);
            } else {
                System.out.println("Texto enviado para impressão com sucesso!");

                int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                if (retornoCorte == 0) {
                    System.out.println("Papel cortado com sucesso!");
                } else {
                    System.out.println("Erro ao cortar o papel: " + retornoCorte);
                }
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void imprimirQRCode(String dados, int tamanho, int correcao) {
        if (!verificarConexao()) {
            return;
        }

        if (tamanho < 1 || tamanho > 6 || correcao < 1 || correcao > 4) {
            System.out.println("Tamanho ou nível de correção inválido.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados, tamanho, correcao);

            if (retorno == 0) {
                System.out.println("QR Code enviado para impressão com sucesso!");

                int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                if (retornoCorte == 0) {
                    System.out.println("Papel cortado com sucesso!");
                } else {
                    System.out.println("Erro ao cortar o papel: " + retornoCorte);
                }
            } else {
                System.out.println("Erro na impressão do QR Code: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void imprimirCodigoBarras(int tipoCodigo, String dados, int altura, int largura, int hri) {
        if (!verificarConexao()) {
            return;
        }

        if (tipoCodigo != 0 && tipoCodigo != 2 && tipoCodigo != 3 &&
                tipoCodigo != 4 && tipoCodigo != 8) {
            System.out.println("Tipo de código de barras inválido.");
            return;
        }

        if (altura < 1 || altura > 255 || largura < 1 || largura > 6 || hri < 1 || hri > 4) {
            System.out.println("Parâmetros inválidos.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(tipoCodigo, dados, altura, largura, hri);

            if (retorno == 0) {
                System.out.println("Código de barras enviado para impressão com sucesso!");

                int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                if (retornoCorte == 0) {
                    System.out.println("Papel cortado com sucesso!");
                } else {
                    System.out.println("Erro ao cortar o papel: " + retornoCorte);
                }
            } else {
                System.out.println("Erro na impressão do código de barras: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void imprimirXMLSAT() {
        if (!verificarConexao()) return;

        try {
            String xml = java.nio.file.Files.readString(
                    java.nio.file.Paths.get("XMLSAT.xml"),
                    java.nio.charset.StandardCharsets.UTF_8
            );

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(xml, 0);

            if (retorno == 0) {
                System.out.println("XML SAT enviado para impressão com sucesso!");

                int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                if (retornoCorte == 0) {
                    System.out.println("Papel cortado com sucesso!");
                } else {
                    System.out.println("Erro ao cortar o papel: " + retornoCorte);
                }
            } else {
                System.out.println("Erro na impressão do XML SAT: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
        }
    }

    public void imprimirXMLCancelamento() {

        if (!verificarConexao()) {
            return;
        }

        try {
            String xml = java.nio.file.Files.readString(
                    java.nio.file.Paths.get("CANC_SAT.xml"),
                    java.nio.charset.StandardCharsets.UTF_8
            );

            int retorno = ImpressoraDLL.INSTANCE.ImprimeXMLCancelamentoSAT(xml, assinaturaQRCode, 0);

            if (retorno == 0) {
                System.out.println("XML de cancelamento enviado para impressão com sucesso!");

                int retornoCorte = ImpressoraDLL.INSTANCE.Corte(5);

                if (retornoCorte == 0) {
                    System.out.println("Papel cortado com sucesso!");
                } else {
                    System.out.println("Erro ao cortar o papel: " + retornoCorte);
                }

            } else {
                System.out.println("Erro na impressão do XML de cancelamento: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo XML: " + e.getMessage());
        }
    }

    public void abrirGaveta() {
        if (!verificarConexao()) {
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AbreGaveta(0, 1, 5);
            mostrarResultado(retorno);

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void sinalSonoro(int quantidade) {
        if (!verificarConexao()) {
            return;
        }

        if (quantidade < 1 || quantidade > 63) {
            System.out.println("Quantidade de bips inválida.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.SinalSonoro(quantidade, 50, 5);
            mostrarResultado(retorno);

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void avancarPapel(int linhas) {
        if (!verificarConexao()) {
            return;
        }

        if (linhas <= 0) {
            System.out.println("Digite uma quantidade válida de linhas.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.AvancaPapel(linhas);
            mostrarResultado(retorno);

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void consultarStatus(int opcao) {
        if (!verificarConexao()) {
            return;
        }

        if (opcao != 1 && opcao != 2 && opcao != 3 && opcao != 5) {
            System.out.println("Opção de status inválida.");
            return;
        }

        try {
            int retorno = ImpressoraDLL.INSTANCE.StatusImpressora(opcao);

            if (opcao == 3 && retorno == 5) {
                System.out.println("A impressora possui papel.");
            } else if (opcao == 3 && retorno == 6) {
                System.out.println("O papel está acabando.");
            } else if (opcao == 3 && retorno == 7) {
                System.out.println("A impressora está sem papel.");
            } else if (retorno == 0) {
                System.out.println("Comando executado com sucesso.");
            } else {
                System.out.println("Código retornado: " + retorno);
            }

        } catch (UnsatisfiedLinkError e) {
            System.out.println("Não foi possível carregar a DLL: " + e.getMessage());
        }
    }

    public void mostrarResultado(int retorno) {
        if (retorno == 0) {
            System.out.println("Comando executado com sucesso!");
        } else {
            System.out.println("Erro ao executar comando. Código: " + retorno);
        }
    }

    public boolean isConexaoAberta() {
        return conexaoAberta;
    }
}
