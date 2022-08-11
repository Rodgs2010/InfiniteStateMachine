/*
  Rodrigo Alves Bolincenha BCC 4 periodo noite
https://github.com/Rodgs2010/MaquinaDeEstadoFinito
    
  Este programa, quando executado, irá determinar se uma string de 
  entrada faz parte da linguagem 𝐿 definida por 𝐿 = {𝑥 | 𝑥 ∈{𝑎, 𝑏} 
  𝑒 𝑐𝑎𝑑𝑎 𝑎 𝑒𝑚 𝑥 é 𝑠𝑒𝑔𝑢𝑖𝑑𝑜 𝑝𝑜𝑟 𝑝𝑒𝑙𝑜 𝑚𝑒𝑛𝑜𝑠 𝑑𝑜𝑖𝑠 𝑏} segundo o alfabeto Σ = {𝑎,𝑏, 𝑐}.

  Este programa le 4 arquivos de texto contendo uma quantidade exata de Strings definido na primeiro linha de cada 
  arquivo.
  Exemplo:
  string1.txt    string2.txt
  3               4
  abbaba         abbbbbbbbbabb
  abababb        aa
  bbabbaaab      bb
                 acc

Obs: Os arquivos de texto estao dentro de uma pasta chamada "arquivosDeTexto", portanto a criaçao de um novo arquivo de texto deve ser dentro dessa pasta e também seguir o padrao dos arquivos existentes mostrado no exemplo acima.

na funcao abrirArquivo(), se for criar uma nova pasta com novos arquivos de texto dentro, por favor colocar
dentro do metodo Paths.get("caminho da pasta com os arquivos dentro");

Exemplo de como esta no meu computador
path = Paths.get("C:\\Users\\Rodrigo\\IdeaProjects\\PARA-CONCU-DISTRI\\src\\arquivosDeTexto");
Exemplo de como esta no replit.com
path = Paths.get("arquivosDeTexto");
*/

// importando as bibliotecas para abrir, let e fechar arquivos junto com as suas exceçoes.
package maquina;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.io.IOException;
import java.util.Scanner;

public class StateMachine {
    private static Scanner input;
    private static Path path;
    private static int i = -1;
    private static int tamanhoString;
    private static char let;
    
//funcao para abrir o arquivo
    public static void abrirArquivo(){
        path = Paths.get("C:\\Users\\Rodrigo\\IdeaProjects\\PARA-CONCU-DISTRI\\src\\arquivosDeTexto");
        if(Files.exists(path)){
            lerArquivo();
        }else{
            System.out.printf("%s does not exist%n", path);
        }
    }
  //funcao para ler o Arquivo
    public static void lerArquivo(){
        try{
            if(Files.isDirectory(path) || Files.isReadable(path)){
                 // Cria um objeto do tipo Path que contem o caminho da pasta com os arquivos dentro

                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
                /* 
                  Um loop para percorrer os arquivos de texto dentro da pasta.
                  Mostra o nome do primeiro arquivo lido.
                  o While percorra o conteudo dentro de cada.
                  Se o numero de strings fornecido dentro do arquivo estiver errado
                  sera lancado um erro e o arquivo sera fechado na hora.
                */
                for(Path p : directoryStream){
                    System.out.println("Lendo arquivo: " + p.getFileName());
                    input = new Scanner(Paths.get(String.valueOf(p)));
                    while(input.hasNext()){
                        int numString = input.nextInt();
                        String[] texto = new String[numString];
                        System.out.println("numero de Strings no arquivo: " + numString);
                        // pega o numero de Strings que foi fornecido na primeira linha do arquivo
                        for(int j = 0; j < numString; j++){
                            texto[j] = input.next();
                            tamanhoString = texto[j].length();
                            start(texto[j]);
                            i = -1;
                        }
                    }
                    System.out.println();
                }
            }
        }catch (NoSuchElementException | IllegalStateException elementException){
            System.err.println("File improperly formed. Terminating.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //funcao para fechar o arquivo
    public static void fecharArquivo(){
        if(input != null){
            input.close();
            System.out.println("Fechando: " + path.getFileName());
        }
    }
    /*
      A maquina tem o start e os estados s1, s2, s3(vericacao), s4.
    */
    public static char getString(String s){
        i++;
        return s.charAt(i);
    }
    public static String start(String s){
        if(i >= tamanhoString - 1){
            System.out.println(s + ": Nao pertence");
        }else{
            let = getString(s);
            if(let == 'c'){
                System.out.println(s + ": Nao pertence");
            }else if(let == 'a'){
                return state1(s);
            }else {
                return state3(s);
            }

        }
        return s;
    }
    public static String state1(String s){
        if(i >= tamanhoString - 1){
            System.out.println(s + ": Nao pertence");
        }else{
            let = getString(s);
            if(let == 'c'){
                System.out.println(s + ": Nao pertence");
                return s;
            }else if(let == 'a'){
                return state4(s);
            }else if(let == 'b'){
                return state2(s);
            }
        }
        return s;
    }
    public static String state2(String s){
        if(i >= tamanhoString - 1){
            System.out.println(s + ": Nao pertence");
        }else {
            let = getString(s);
            if(let == 'c'){
                System.out.println(s + ": Nao pertence");
                return s;
            }else if(let == 'a'){
                return state4(s);
            }else if(let == 'b'){
                return state3(s);
            }
        }
        return s;
    }
    public static String state3(String s){
        if(i >= tamanhoString - 1){
            System.out.println(s + ": pertence");
            return s;
        }else{
            let = getString(s);
            if(let == 'c'){
                System.out.println(s + ": Nao pertence");
                return s;
            }else if(let == 'a'){
                return state1(s);
            }else if(let == 'b'){
                return state3(s);
            }
        }
        return s;
    }
    public static String state4(String s){
        if(i >= tamanhoString - 1){
            System.out.println(s + ": Nao pertence");
        }else{
            let = getString(s);
            if(let == 'c'){
                System.out.println(s + ": Nao pertence");
                return s;
            }else if(let == 'a' || let == 'b'){
                return state4(s);
            }
        }
        return s;
    }
    public static void main(String[] args) {
        abrirArquivo();
        fecharArquivo();
    }
}
