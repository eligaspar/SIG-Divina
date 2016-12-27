/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ornela F. Boaventura
 */
public class Mensagem
{
    public static void sucessoMsg(FacesContext facesInstancia, String mensagem)
    {
        facesInstancia.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, "Operação Realizada Com Sucesso!"));
    }
    
    public static void erroMsg(FacesContext facesInstancia, String mensagem)
    {
        facesInstancia.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, "Erro! Operação Não Realizada!"));
    }
                        
}
