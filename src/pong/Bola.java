/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import JPlay.Sound;
import JPlay.Sprite;


/**
 * Grupo 3:
 * José Leonardo Dutra de Lima
 * João Angelo Candido Júnior
 * Mário Sérgio de França Fonteles
 * Universidade Estadual do Ceará - UECE - Brasil - 2016
 * Ciência da Computação
 */

public class Bola  extends Sprite
{  
    public Bola()
    {
            //Abra a imagem da bolinha "pong.png" na pasta do projeto e você
            //verá que há sete bolinhas ligeiramente diferentes em uma única
            //imagem, a isso damos o nome de sprite e cada bolinha ligeiramente
            //diferente é chamada de frame.
            //Pelo comando abaixo, temos:
            //      "pong.png" = nome da imagem a ser carregada
            //      1 = número de frames
            super("limao.png", 1);

            setStateOfX(Sprite.LEFT);

            //A bola só se movimentará no eixo Y quando a mesma bater em alguma barra
            setStateOfY(Sprite.STOP);

            //Seta o tempo em milisegundos para a mundanca entre os frames da bola
            setTimeChangeFrame(30);

            //Seta a velocidade em relação ao eixo X com que a bola se locomoverá
            setVelocityX(4.5);

            //Seta a velocidade em relação ao eixo Y com que a bola se locomoverá
            setVelocityY(2);
    }   

    @Override
    public void moveX()
    {
            //Verifica em que sentido a bola está se deslocando e se a mesma não
            //está tentanto ultrapassar a tela.
            if ( getStateOfX() == Sprite.RIGHT
                    && this.x + this.width < Constante.LIMITE_DIREITA_X)
            {
                moveToRight();
            }

             //Verifica em que sentido a bola está se deslocando e se a mesma não
            //está tentanto ultrapassar a tela.
            if ( getStateOfX() == Sprite.LEFT && this.x > Constante.LIMITE_ESQUERDA_X)
            {
                moveToLeft();
            }
    }

    @Override
    public void moveY()
    {        
            //Verifica em que sentido a bola está se deslocando e se a mesma não
            //está tentanto ultrapassar a tela 
            if( (getStateOfY() == Sprite.DOWNWARD)
                    && ((this.y + this.height) < Constante.LIMITE_INFERIOR_Y) )
            {
                    moveToDown();
            }
            else
            {
                    //Verifica em que sentido a bola está se deslocando e se a mesma não
                    //está tentanto ultrapassar a tela.
                    if( (getStateOfY() == Sprite.UPWARD) && (this.y > Constante.LIMITE_SUPERIOR_Y) )
                    {
                            moveToUp();
                    }
                    else
                    {       //Só entrará nesse else se a bola bateu em cima ou embaixo da tela                        
                            
                            //Verifica se a bola bateu na parte de baixo da tela,
                            //se isso ocorreu então a bola deve ter o seu sentido invertido.
                            boolean bateu = true;
                            if ( (this.y + this.height) >= Constante.LIMITE_INFERIOR_Y )
                            {
                                setStateOfY(Sprite.UPWARD);
                            }
                            else
                            {
                                    //Verifica se a bola bateu na parte de cima da tela,
                                    //se isso ocorreu então a bola deve ter o seu sentido invertido.
                                    if ( this.y <=  Constante.LIMITE_SUPERIOR_Y )
                                    {
                                        setStateOfY(Sprite.DOWNWARD);
                                    }
                                    else
                                        bateu = false;
                            }
                            
                            if(bateu)
                               new Sound("bateu.wav").play();
                    }
            }
    }

    public void centralizarNaTela( )
    {
            //largura da tela de jogo
            int largura = Constante.LIMITE_DIREITA_X - Constante.LIMITE_ESQUERDA_X;

            ///altura da tela de jogo
            int altura =  Constante.LIMITE_INFERIOR_Y - Constante.LIMITE_SUPERIOR_Y;

            this.x = (largura - this.width) /2;
            this.y =  (altura +  7 * this.height) /2;
            setStateOfY(Sprite.STOP);
    }  
}
