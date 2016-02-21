/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import JPlay.Sprite;

/**
 * Grupo 3:
 * José Leonardo Dutra de Lima
 * João Angelo Candido Júnior
 * Mário Sérgio de França Fonteles
 * Universidade Estadual do Ceará - UECE - Brasil - 2016
 * Ciência da Computação
 */

public class Barra extends Sprite
{
    
    public Barra(String nomeImagem)
    {
            super(nomeImagem);
            setVelocityY(5);
    }


    @Override
    public void moveY(int upKey, int downKey)
    {            
            //A segunda condição do if não permite que a barra saia da tela.
            //Retire-a e veja o que acontece.
            if (keyboard.keyDown(upKey) && this.y >  Constante.LIMITE_SUPERIOR_Y  + 5)
            {
                    moveToUp();
                    setStateOfY(Sprite.UPWARD);
            }
            else
            {
                    //A segunda condição do if não permite que a barra saia da tela.
                    //Retire-a e veja o que acontece.
                    if (keyboard.keyDown(downKey) && this.y + this.height < Constante.LIMITE_INFERIOR_Y -5)
                    {
                            moveToDown();
                            setStateOfY(Sprite.DOWNWARD);
                    }           
                    else
                    {
                            //Serve para dizer que a barra não está se movendo
                            setStateOfY(Sprite.STOP);
                    }
            }            
    }

     public void centralizarNaTela()
     {
            this.y =  (Constante.LIMITE_INFERIOR_Y - this.height/2) /2;
            setStateOfY(Sprite.STOP);
     }
}
