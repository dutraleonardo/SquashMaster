/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import JPlay.GameImage;
import JPlay.Keyboard;
import JPlay.Sound;
import JPlay.Sprite;
import JPlay.Time;
import JPlay.Window;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * Grupo 3:
 * José Leonardo Dutra de Lima
 * João Angelo Candido Júnior
 * Mário Sérgio de França Fonteles
 * Universidade Estadual do Ceará - UECE - Brasil - 2016
 * Ciência da Computaçãoo
 */

public class Pong
{
    public Pong()
    {
            //Window window = new Window(largura, altura);
            //largura = 800, altura = 600, a dimensão da janela é dada em pixels, não se esqueça disso.
            Window janela = new Window(800, 600);

            //Faz o cursor do mouse desaparecer.
            //O parâmetro é o nome da imagem a ser usada no lugar do cursor do mouse.
            //janela.setCursor( janela.createCustomCursor("mouse.png") );
            //Mas como não foi colocada nenhuma imagem, o cursor irá desaparecer
            //somente dentro da tela do jogo.
            janela.setCursor( janela.createCustomCursor("") );

            Keyboard keyboard = janela.getKeyboard();
            //No teclado do pacote JPlay há as seguintes teclas adicionadas por default:
            //UP_KEY     = Seta para cima
            //DOWN_KEY   = Seta para baixo
            //LEFT_KEY   = Seta para a esquerda
            //RIGHT_KEY  = Seta para baixo
            //ESCAPE_KEY = Tecla ESC
            //SPACE_KEY  = Barra de Espaço
            //ENTER_KEY  = Tecla Enter

            //Para adicionar uma tecla que não se encontra adicionada por default
            //usamos o método addKey(Codigo da Tecla, Comportamento da tecla).

            //O código de cada tecla pode ser descoberto usando-se a classe KeyEvent
            //do próprio Java.
            //Escrevendo KeyEvent (com 'K' maiúsculo) e depois 'VK_', a IDE
            //(NetBeans ou Eclipse) irá listar todas as teclas passiveis de uso
            //na máquina corrente.

            //Cada tecla no Keyboard do JPlay pode ter somente dois comportamentos:

            //1ͦ : DETECT_EVERY_PRESS - o método keyDown presente no teclado
            //      retornará true enquanto a tecla estiver pressionada.

            //2ͦ : DETECT_INITAL_PRESS_ONLY - o método keyDown presente no teclado
            //      retornará true somente no momento que a tecla for pressionada.
            //      Se o usuário pressionar a tecla durante cinco horas, o método
            //      keydown() só retornará true UMA VEZ. Para retornar novamente true
            //      o usuário terá que liberar a tecla pressionada.
            //
            //O modo DETECT_INITAL_PRESS_ONLY pode ser utilizado quando queremos fazer
            //um boneco ou uma nave atirar. Se for escolhido o primeiro modo
            //DETECT_EVERY_PRESS o boneco dará uma quantidade excessiva de tiros.           

            //O dois comportamentos do teclado citados acima, também servem para o mouse.
            //Os botões adicionados por default no Mouse do pacote JPlay são:
            //BUTTON_LEFT = botão esquerdo
            //BUTTON_MIDDLE = Scroll do mouse
            //BUTTON_RIGHT = botão direito do mouse

            keyboard.addKey(KeyEvent.VK_UP, Keyboard.DETECT_EVERY_PRESS);
            keyboard.addKey(KeyEvent.VK_DOWN, Keyboard.DETECT_EVERY_PRESS);

            //É obrigatório colocar a extensão da imagem, ao contrário, o arquivo não será reconhecido.
            //Nesse caso a extensão é PNG.
            GameImage fundo = new GameImage("fundo.png");

            Bola bola = new Bola();
             //bola.setPosition(coordenadaX, coordenadaY);
            bola.setPosition(300,300);

            Barra barraVerde = new Barra("cachaca.png");

            barraVerde.setPosition(40,300);

            //Coloca as barras na suas posições iniciais.
            barraVerde.centralizarNaTela();

            //Time tempo = new Time(horas, minutos, segundos, x, y)
            //horas = 0
            //minutos = 5
            //segundos = 0
            //x = 450
            //y = 100
            Time tempo = new Time(0, 0, 35, 400, 588, false);
            tempo.setColor(Color.WHITE);
            tempo.setFont(new Font("Comic Sans MS", Font.TRUETYPE_FONT, 16));
            Font fonte = new Font("Comic Sans MS", Font.BOLD, 24);

            //Carrega uma música do tipo wav.
            //Por enquanto o JPlay só aceita sons com a extensão wav.
            //É obrigatório colocar a extensão do som.
            //Aqui a extensão é 'wav'.
            Sound musica = new Sound("musica.wav");
            musica.setRepeat(true);//faz a música ser tocada continuamente.
            musica.play();

            int pontuacaoVerde = 0;

            boolean executando = true;
            while(executando)
            {
                    //A ordem em que os objetos são desenhados na tela é importante.
                    //Para ter uma idéia do que estou falando tente mudar a ordem com os objetos
                    //são desenhados na tela.
                    //Por exemplo:
                    //bola.draw();
                    //fundo.draw();
                    fundo.draw();
                    bola.draw();
                    barraVerde.draw();
                    tempo.draw();
                    
                    //Verifica se o tempo acabou.
                    //Em seguida executa um som.
                    if (tempo.getTotalSecond() < 1)
                        new Sound("ressaca.wav").play();
                    
                    //Se só restar 30 segundos para o tempo acabar imprime mensagem na tela.
                    //tempo.getTotalSecond() - retorna, o tempo total, convertido em segundos.
                    if (tempo.getTotalSecond() < 30)
                        janela.drawText("O tempo está terminando!", 310, 105, Color.CYAN);
                    
                        
                    //Desenha texto na tela
                    //janela.drawText(string, x, y, cor, fonte);
                    janela.drawText(Integer.toString(pontuacaoVerde), 295,70, Color.BLACK, fonte);

                    //window.display() SEMPRE deve ser chamado por último, no quesito desenhar na tela,
                    //pois, é esse método que mostra as atualizações feitas dutante o jogo.
                    janela.display();

                    //O método collided retorna true se a bola colidiu com a barra azul
                    //ao contrário, retorna falso.
                    
                    //Verifica se o tempo total acabou e executa um som.
                        
                    boolean colidiu = true;
                    if (bola.collided(barraVerde))
                    {                        
                        //Faz a bola andar no sentido contrário.
                        bola.setStateOfX(Sprite.RIGHT);

                        //Seta o mesmo sentido que a barra estava se deslocando.
                        //Se a barra estava subindo a bola irá subir também, e vice-versa.
                        bola.setStateOfY(barraVerde.getStateOfY());
                    }
                    else
                       colidiu = false;

                    if (colidiu)
                    {
                        //Reproduz um som.
                        new Sound("bateu.wav").play();
                    }

                    boolean marcouPonto = true;
                    if (bola.x < Constante.LIMITE_ESQUERDA_X - 1)
                    {
                        bola.centralizarNaTela();
                    }
                    else
                        if (bola.x + bola.width > Constante.LIMITE_DIREITA_X - 1)
                        {
                            bola.setStateOfX(Sprite.LEFT);
                            bola.setStateOfY(barraVerde.getStateOfY());
                            new Sound("ponto.wav").play();
                            pontuacaoVerde++;
                        }
                        else
                            marcouPonto = false;
                    //Move a bola.
                    bola.moveX();
                    bola.moveY();

                    //move as barras
                    barraVerde.moveY(KeyEvent.VK_UP, KeyEvent.VK_DOWN);

                    //Atrasa a execução do jogo em 10ms.
                    //Esse método existe pois em algumas máquinas o jogo pode
                    //ser executado muito rapidamente.
                    janela.delay(10);

                    //if (Se a tecla ESC for pressionada) sai do jogo.
                    //teclado.keyDown(tecla) - retorna true se a tecla está 
                    //pressionada, ao contrário, retorna falso.
                    //Para acessar as teclas defaults do teclado basta escrever 
                    //Keyboard seguido de um ponto final.
                    //Seria assim: Keyboard.
                    //Obs.: Não se esqueça de escrever Keyboard com o 'K' maiúsculo.

                    //tempo.timeEnded() = retorna true se o tempo acabou,
                    //caso contrário, retorna falso.
                    if (keyboard.keyDown(Keyboard.ESCAPE_KEY) || tempo.timeEnded())
                        executando = false;
            }

            //Aqui o delay serve para atrasar o encerramento do jogo em 500ms = 0.5s.
            janela.delay(500);

            //Fecha a janela e sai do jogo.
            janela.exit();
        }
}
