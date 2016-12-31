/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean.grlbean;

import entidade.GrlComuna;
import entidade.GrlContacto;
import entidade.GrlEndereco;
import entidade.GrlInstituicao;
import entidade.GrlCentroHospitalar;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import sessao.GrlContactoFacade;
import sessao.GrlEnderecoFacade;
import sessao.GrlInstituicaoFacade;
import sessao.GrlCentroHospitalarFacade;
import util.ItensAjaxBean;
import util.Mensagem;

/**
 *
 * @author Ornela F. Boaventura
 */
@ManagedBean
@SessionScoped
public class CentroHospitalarBean
{

   @Resource
   private UserTransaction userTransaction;

   /**
    * Pegando a interface managedbean ItensAjaxBean
    */
   FacesContext context = FacesContext.getCurrentInstance();
   ItensAjaxBean itensAjaxBean = (ItensAjaxBean) context.getELContext().getELResolver()
           .getValue(context.getELContext(),null, "itensAjaxBean");

   @EJB
   private GrlContactoFacade contactoFacade;
   @EJB
   private GrlEnderecoFacade enderecoFacade;
   @EJB
   private GrlInstituicaoFacade instituicaoFacade;
   @EJB
   private GrlCentroHospitalarFacade centroHospitalarFacade;

   /**
    *
    * Entidades
    */
   private GrlCentroHospitalar centroHospitalar;

   /**
    * Creates a new instance of CentroHospitalarBean
    */
   public CentroHospitalarBean ()
   {
   }

   public GrlCentroHospitalar getCentroHospitalar ()
   {
      if (this.centroHospitalar == null)
      {
         this.centroHospitalar = new GrlCentroHospitalar();
         this.centroHospitalar.setFkIdInstituicao(new GrlInstituicao());
         this.centroHospitalar.getFkIdInstituicao().setFkIdContacto(new GrlContacto());
         this.centroHospitalar.getFkIdInstituicao().setFkIdEndereco(new GrlEndereco());
         this.centroHospitalar.getFkIdInstituicao().getFkIdEndereco().setFkIdComuna(new GrlComuna());

      }

      return centroHospitalar;
   }

   public void setCentroHospitalar (GrlCentroHospitalar centroHospitalar)
   {
      this.centroHospitalar = centroHospitalar;

      //Fazendo set de algumas propriedades da ItensAjaxBean para aparecerem selecionados nas combobox da dialog editar
      GrlComuna com = centroHospitalar.getFkIdInstituicao().getFkIdEndereco().getFkIdComuna();
      itensAjaxBean.setPais(com.getFkIdMunicipio().getFkIdProvincia().getFkIdPais().getPkIdPais());
      itensAjaxBean.setProvincia(com.getFkIdMunicipio().getFkIdProvincia().getPkIdProvincia());
      itensAjaxBean.setMunicipio(com.getFkIdMunicipio().getPkIdMunicipio());
   }

   public String create ()
   {
      try
      {
         userTransaction.begin();
         contactoFacade.create(centroHospitalar.getFkIdInstituicao().getFkIdContacto());
         enderecoFacade.create(centroHospitalar.getFkIdInstituicao().getFkIdEndereco());
         instituicaoFacade.create(centroHospitalar.getFkIdInstituicao());
         centroHospitalarFacade.create(centroHospitalar);
         userTransaction.commit();
         Mensagem.sucessoMsg("Centro hospitalar guardado com sucesso!");
      }
      catch (Exception e)
      {
         try
         {
            Mensagem.erroMsg(e.toString());
            userTransaction.rollback();
         }
         catch (IllegalStateException | SecurityException | SystemException ex)
         {
            Mensagem.erroMsg("Rollback: " + ex.toString());
         }
      }

      centroHospitalar = null;

      return null;
   }

   public String edit ()
   {
      try
      {
         userTransaction.begin();
         if (centroHospitalar.getPkIdCentro() == null)
         {
            throw new NullPointerException("PK -> NULL");
         }
         contactoFacade.edit(centroHospitalar.getFkIdInstituicao().getFkIdContacto());
         int idComuna = centroHospitalar.getFkIdInstituicao().getFkIdEndereco().getFkIdComuna().getPkIdComuna();
         centroHospitalar.getFkIdInstituicao().getFkIdEndereco().setFkIdComuna(new GrlComuna(idComuna));
         enderecoFacade.edit(centroHospitalar.getFkIdInstituicao().getFkIdEndereco());
         instituicaoFacade.edit(centroHospitalar.getFkIdInstituicao());
         userTransaction.commit();
         Mensagem.sucessoMsg("Centro hospitalar editado com sucesso!");
      }
      catch (Exception e)
      {
         try
         {
            Mensagem.erroMsg(e.toString());
            userTransaction.rollback();
         }
         catch (IllegalStateException | SecurityException | SystemException ex)
         {
            Mensagem.erroMsg("Rollback: " + ex.toString());
         }
      }

      centroHospitalar = null;

      return null;
   }

   public List<GrlCentroHospitalar> findAll ()
   {
      return centroHospitalarFacade.findAll();
   }

   public String limpar ()
   {
      centroHospitalar = null;
      return "centroHospitalarGrl?faces-redirect=true";
   }

}
