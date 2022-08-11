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

    public static void abrirArquivo(){
        System.out.println("Coloque o nome do arquivo ou diretorio:");
        path = Paths.get("C:\\Users\\Rodrigo\\IdeaProjects\\PARA-CONCU-DISTRI\\src\\arquivosDeTexto");
        if(Files.exists(path)){
            lerArquivo();
        }else{
            System.out.printf("%s does not exist%n", path);
        }
    }

    public static void lerArquivo(){
        try{
            if(Files.isDirectory(path) || Files.isReadable(path)){

                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
                for(Path p : directoryStream){
                    System.out.println("Lendo arquivo: " + p.getFileName());
                    input = new Scanner(Paths.get(String.valueOf(p)));
                    while(input.hasNext()){
                        int numString = input.nextInt();
                        String[] texto = new String[numString];
                        System.out.println("numero de Strings no arquivo: " + numString);
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
    public static void fecharArquivo(){
        if(input != null){
            input.close();
            System.out.println("Fechando: " + path.getFileName());
        }
    }
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
