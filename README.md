# 🖨️ Sistema de Controle de Impressora Elgin

## 📘 Sobre o Projeto

Este projeto foi desenvolvido como atividade da disciplina de Programação Orientada à Objetos no curso Técnico de Informática - UNASP. Seu objetivo é controlar uma impressora  Elgin por meio de um programa desenvolvido em Java. O sistema possui login e um menu com diferentes funções, como impressão de textos, QR Codes, códigos de barras e arquivos XML. Também permite controlar recursos da impressora, como abertura da gaveta, emissão de sinais sonoros e avanço do papel.

---

## 🧰 Tecnologias Utilizadas

- **Java 25** – linguagem utilizada no desenvolvimento.
- **IntelliJ IDEA** – ambiente de desenvolvimento.
- **JNA 5.15.0** – biblioteca utilizada para comunicação com a DLL.
- **DLL E1_Impressora01** – biblioteca disponibilizada pela Elgin.
- **Impressora Elgin i9** – modelo utilizado na configuração do projeto.

---

## 📁 Estrutura do Projeto

```
/src
   Main.java              → Responsável por iniciar o programa,
                            realizar o login e apresentar o menu.

   Usuario.java           → Armazena os dados do usuário e
                            verifica as informações de login.

   Impressora.java        → Contém os métodos responsáveis pelas
                            operações e pelo controle da impressora.

   ImpressoraDLL.java     → Interface responsável pela comunicação
                            entre o Java e a DLL da Elgin.

/Bibliotecas E1 Impressora
   /x64                   → Arquivos da DLL para sistemas 64 bits.
   /x86                   → Arquivos da DLL para sistemas 32 bits.

/libs Java
   jna-5.15.0.jar         → Biblioteca utilizada pelo projeto.
```

---

## ⚙️ Instalação e Execução

### 1. Baixar o projeto

1. Acesse o repositório do projeto no GitHub.
2. Clique em **Code → Download ZIP**.
3. Extraia os arquivos baixados.

### 2. Abrir no IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Selecione **File → Open** e escolha a pasta do projeto.
3. Em **File → Project Structure → Libraries**, verifique se a biblioteca `jna-5.15.0.jar` está adicionada.
4. Conecte e ligue a impressora.
5. Abra o arquivo `Main.java` e clique em **Run** para executar o programa.

---

## 🔐 Login

Para acessar o sistema, utilize os seguintes dados:

- **Usuário:** admin
- **Senha:** 1234
- **Tentativas permitidas:** 3

Caso as informações sejam digitadas incorretamente nas três tentativas, o acesso será encerrado.

---

## 📋 Funcionalidades do Menu

| Opção | Funcionalidade |
|---|---|
| 1 | Configurar conexão com a impressora |
| 2 | Abrir conexão com a impressora |
| 3 | Fechar conexão com a impressora |
| 4 | Imprimir texto |
| 5 | Imprimir QR Code |
| 6 | Imprimir código de barras |
| 7 | Imprimir XML SAT |
| 8 | Imprimir XML de cancelamento SAT |
| 9 | Abrir gaveta de dinheiro |
| 10 | Emitir sinal sonoro |
| 11 | Avançar papel |
| 12 | Consultar status da impressora |
| 0 | Encerrar o programa |

---

## 🧪 Testes Realizados

Durante o desenvolvimento, foram realizados testes para verificar o funcionamento do sistema e sua comunicação com a impressora.

- Teste de login com informações corretas e incorretas.
- Verificação do limite de três tentativas de acesso.
- Teste de abertura e fechamento da conexão.
- Teste físico de impressão e avanço do papel com corte.
- Verificação das mensagens de retorno das operações.
- Testes de validação das informações digitadas pelo usuário.

---

## 📚 Documentação Adicional

- Manual da impressora Elgin. 
- Documentação do SDK E1_Impressora.
- Biblioteca JNA 5.15.0.

---

## 👥 Autores

- Eduarda Alves de Sousa Barros
- Eduarda Andrade Silva
- João Pedro Gomes Augusto Gesteira