package tp_02;

import static java.lang.Boolean.TRUE;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kauad
 */
public class Forca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
       
       //Seleciona aleatoriamente uma das palavras        
        String selecionada = selecionaPalavra("");
        
        //Chama a subrotina com recursão que vai pedir a letra, fazer a testagem e dar o resultado
        int erro = verificador("", selecionada, 6);
        
        System.out.println("testando o valor do return: " + erro);
        
        // Verifica se o usuário perdeu e define qual msg ele irá receber
        if (erro == 0){
            System.out.println("Você perdeu a cabeça!");
            System.out.println("GAME OVER!!");
            System.out.println("A PALAVRA ERA: " + selecionada);          
        }
        else {
            System.out.println("PARABÉNS! VOCÊ VENCEU!");
            System.out.println("A PALAVRA É: " + selecionada);
        }
        
        System.out.println("DESEJA JOGAR NOVAMENTE?");
        System.out.println("Digite sim ou nao: ");
        String rejogar = sc.next();
        
        if (rejogar.equals("sim")){
        	selecionada = selecionaPalavra("");
            verificador("", selecionada, 6);
        }
        else {
            System.out.println("OBRIGADO POR JOGAR!");
        }
    }
    
    private static String selecionaPalavra(String selecionada) {
    	//Vetor com as palavras a serem sorteadas
    	String palavra[] = new String[]{"laranja", "abacate", "banana", "morango", 
        		"manga", "ameixa", "goiaba", "carambola", "uva", "abacaxi"};
    	
    	selecionada = palavra[new Random().nextInt(palavra.length)];
    	
    	return selecionada;
    }

    private static int verificador(String palpite, String selecionada, int erro) {
        Scanner sc = new Scanner(System.in);
        
        //Verifica se o usuário ainda não ganhou nem perdeu
        //se o jogo continua, pede uma nova letra
        if (selecionada != "" && erro > 0) {
            System.out.print("Digite uma letra: ");
            palpite = sc.next();
           
            //Verifica se a letra informada está na palavra
            if (selecionada.contains(palpite) == TRUE) {
                System.out.println("Letra correta!");
                
                //envia a letra digitada e o tipo TRUE
                boolean acerto = selecionada.contains(palpite);
                gravaArquivo(palpite, acerto);
                
                // elimina a letra selecionada da palavra
                selecionada = selecionada.replace(palpite, "");
               
                // acerto++;

               // se não tiver a letra, verifica qual mensagem informar
            } else if (erro > 0){
                System.out.println("Ops! Letra errada ou repetida!");
                
              //envia a letra digitada e o tipo FALSE
                boolean acerto = selecionada.contains(palpite);
                gravaArquivo(palpite, acerto);
                
                erro--;

                switch (erro) {
                    case 5:
                        System.out.println("Você perdeu o braço esquerdo!");
                        break;
                    case 4:
                        System.out.println("Você perdeu o braço direito!");
                        break;
                    case 3:
                        System.out.println("Você perdeu a perna esquerda!");
                        break;
                    case 2:
                        System.out.println("Você perdeu a perna direita!");
                        break;
                    case 1:
                        System.out.println("Você perdeu o tronco!");
                        break;
                }
            }
            
            System.out.println(selecionada);
            System.out.println(erro);
            
            //chama a própria função para iniciar a repetição
            verificador(palpite, selecionada, erro);
        } 
       
        return erro;   

    }
    
    private static void gravaArquivo(String palpite, boolean acerto) {
    	
    	//Arquivo
    	File objArquivo = new File("forca.txt");
    	
    	 if(objArquivo.exists()){
    	        //System.out.println(objArquivo + " Exists");
    	        
    	        try{
    	            // instancia objeto (objLeitorArquivo) capaz de ler conteúdos do arquivo (representado pelo objeto objArquivo)
    	            FileReader objLeitorArquivo = new FileReader(objArquivo);
    	            
    	            // instancia objeto (objBufferDeLeitura) para armazenar (em buffer) conteudo lido do arquivo
    	            BufferedReader objBufferDeLeitura = new BufferedReader(objLeitorArquivo);
    	            
    	            // comando para ler conteúdo do arquivo
    	            String conteudoLido = "";
    	            // lógica para leitura do conteúdo: enquanto o buffer de leitura estiver "ready", 
    	            // significa que há conteúdo a ser lido; com isso, continua no laço
    	            while(objBufferDeLeitura.ready()){
    	                // lê conteúdo e o armazena em uma variável (para ser exibido de uma única vez)
    	                conteudoLido += objBufferDeLeitura.readLine() + "\n";
    	            }
    	            
    	            //System.out.println("Conteúdo lido: ");
    	            // exibe o conteúdo recuperado do arquivo
    	            //System.out.println(conteudoLido);
    	            
    	            
    	         // instancia objeto (objEscritorArquivo) capaz de escrever em arquivos (representado pelo objeto objArquivo)
    	            FileWriter objEscritorArquivo = new FileWriter(objArquivo);
    	            
    	         // instancia objeto (objBufferDeEscrita) para armazenar (em buffer) conteudo a ser escrito no arquivo
    	            BufferedWriter objBufferDeEscrita = new BufferedWriter(objEscritorArquivo);
    	            
    	            // instancia objeto (objEscritor) que envia caracteres ao buffer, para depois envia-los ao arquivo
    	           PrintWriter objEscritor = new PrintWriter(objBufferDeEscrita, true);
    	            
    	            // imprime conteudos no buffer
    	            objEscritor.println(conteudoLido + palpite + " , " + acerto);    	            
    	            
    	        } catch(Exception ex){
    	            System.out.println("Erro ao manipular arquivo para ler valores.\n" + ex.toString());
    	        }
    	 }
    	 
    	 else{
    	        //System.out.println(objArquivo + " Does not exists");
    	        try{
    		           
    	            // tenta criar o arquivo
    	            objArquivo.createNewFile();
    	            //System.out.println("CRIOU 1° VEZ!!");
    	            
    	            // instancia objeto (objEscritorArquivo) capaz de escrever em arquivos (representado pelo objeto objArquivo)
    	            FileWriter objEscritorArquivo = new FileWriter(objArquivo);
    	            
    	            // instancia objeto (objBufferDeEscrita) para armazenar (em buffer) conteudo a ser escrito no arquivo
    	            BufferedWriter objBufferDeEscrita = new BufferedWriter(objEscritorArquivo);
    	            
    	            // instancia objeto (objEscritor) que envia caracteres ao buffer, para depois envia-los ao arquivo
    	           PrintWriter objEscritor = new PrintWriter(objBufferDeEscrita, true);
    	            
    	            // imprime conteudos no buffer
    	           objEscritor.println(palpite + " , " + acerto);
    	            
    	            //System.out.println("Conteúdo escrito com sucesso.");
    	        }
    	        catch(Exception ex){
    	            System.out.println("Erro ao manipular arquivo para gravar valores.\n" + ex.toString());
    	        }
    	    }
    	
    }

}