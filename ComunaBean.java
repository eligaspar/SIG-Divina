/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.grlbean;

import entidade.GrlComuna;
import entidade.GrlMunicipio;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlComunaFacade;
import util.ItensAjaxBean;
import util.Mensagem;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class ComunaBean
{

   @Resource
   private UserTransaction userTransaction;

   /**
    * Pegando a interface managedbean ItensAjaxBean
    */
   FacesContext context = FacesContext.getCurrentInstance();
   ItensAjaxBean itensAjaxBean = (ItensAjaxBean) context.getELContext().getELResolver()
           .getValue(context.getELContext(), null, "itensAjaxBean");

   @EJB
   private GrlComunaFacade comunaFacade;

   /**
    *
    * Entidades
    */
   private GrlComuna comuna;

   /**
    * Creates a new instance of ComunaBean
    */
   public ComunaBean ()
   {
   }

   public GrlComuna getComuna ()
   {
      if (this.comuna == null)
      {
         comuna = new GrlComuna();
         comuna.setFkIdMunicipio(new GrlMunicipio());
      }

      return comuna;
   }

   public void setComuna (GrlComuna comuna)
   {
      this.comuna = comuna;
      itensAjaxBean.setPais(comuna.getFkIdMunicipio().getFkIdProvincia().getFkIdPais().getPkIdPais());
      itensAjaxBean.setProvincia(comuna.getFkIdMunicipio().getFkIdProvincia().getPkIdProvincia());
      itensAjaxBean.setMunicipio(comuna.getFkIdMunicipio().getPkIdMunicipio());
   }

   public String create ()
   {
      try
      {
         userTransaction.begin();
         comunaFacade.create(comuna);
         userTransaction.commit();
         Mensagem.sucessoMsg("Comuna guardada com sucesso!");
      }
      catch (Exception e)
      {
         try
         {
            userTransaction.rollback();
            Mensagem.erroMsg(e.toString());
         }
         catch (IllegalStateException | SecurityException | SystemException ex)
         {
            Mensagem.erroMsg("Rollback: " + ex.toString());
         }
      }

      comuna = null;

      return null;
   }

   public String edit ()
   {
      try
      {
         userTransaction.begin();
         if (comuna.getPkIdComuna() == null)
         {
            throw new NullPointerException("PK -> NULL");
         }
         int idMunicipio = comuna.getFkIdMunicipio().getPkIdMunicipio();
         comuna.setFkIdMunicipio(new GrlMunicipio(idMunicipio));
         comunaFacade.edit(comuna);
         userTransaction.commit();
         Mensagem.sucessoMsg("Comuna editada com sucesso! ");
      }
      catch (Exception e)
      {
         try
         {
            userTransaction.rollback();
            Mensagem.erroMsg(e.toString());
         }
         catch (IllegalStateException | SecurityException | SystemException ex)
         {
            Mensagem.erroMsg("Rollback: " + ex.toString());
         }
      }

      comuna = null;

      return null;
   }

   public List<GrlComuna> pesquisaPorMunicipio ()
   {
      if (comuna == null)
      {
         return null;
      }
      return comunaFacade.pesquisaPorMunicipio(comuna.getFkIdMunicipio().getPkIdMunicipio());
   }

   public String limpar ()
   {
      comuna = null;
      return "comunaGrl?faces-redirect=true";
   }
}
